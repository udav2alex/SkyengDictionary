package ru.gressor.skyengdictionary

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.entities.DictData

class Contract {

    interface MainView {
        fun renderData(data: DictData)
    }

    interface MainPresenter {
        fun attachView(mainView: MainView)
        fun detachView()
    }

    interface MainInteractor {
        fun getData(word: String): Single<DictData>
    }

    interface MainRepository {
        fun getData(word: String): Single<DictData>
    }
}