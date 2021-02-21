package ru.gressor.skyengdictionary.viewmodels

import io.reactivex.rxjava3.observers.DisposableSingleObserver
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData

class MainViewModel constructor(
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