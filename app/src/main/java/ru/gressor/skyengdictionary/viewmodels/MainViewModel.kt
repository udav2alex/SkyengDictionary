package ru.gressor.skyengdictionary.viewmodels

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.rx.MainSchedulersProvider
import ru.gressor.skyengdictionary.rx.SchedulersProvider
import ru.gressor.skyengdictionary.data.DataSourceLocal
import ru.gressor.skyengdictionary.data.DataSourceRemote
import ru.gressor.skyengdictionary.interactors.MainInteractor
import ru.gressor.skyengdictionary.repos.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainContract.Interactor
) : BaseViewModel<DictData>() {

    init {
        stateMutableLiveData.value = DictData.Empty
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribeWith(
                    object : DisposableSingleObserver<DictData>() {
                        override fun onStart() {
                            stateMutableLiveData.value = DictData.Loading(null)
                        }

                        override fun onSuccess(t: DictData) {
                            stateMutableLiveData.value = t
                        }

                        override fun onError(e: Throwable) {
                            stateMutableLiveData.value = DictData.Error(e)
                        }
                    }
                )
        )
    }
}