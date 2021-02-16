package ru.gressor.skyengdictionary.presenters

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

class MainPresenter(
    private val interactor: MainContract.Interactor = MainInteractor(
        MainRepository(DataSourceRemote()),
        MainRepository(DataSourceLocal())
    ),
    private val schedulersProvider: SchedulersProvider = MainSchedulersProvider(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MainContract.Presenter {

    var view: MainContract.View? = null

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribeWith(
                    object : DisposableSingleObserver<DictData>() {
                        override fun onStart() {
                            view?.renderData(DictData.Loading(null))
                        }

                        override fun onSuccess(t: DictData) {
                            view?.renderData(t)
                        }

                        override fun onError(e: Throwable) {
                            view?.renderData(DictData.Error(e))
                        }
                    }
                )
        )
    }

    override fun attachView(view: MainContract.View) {
        if (this.view == null) {
            this.view = view
        }
    }

    override fun detachView(view: MainContract.View) {
        compositeDisposable.clear()
        if (this.view != null) {
            this.view = null
        }
    }
}