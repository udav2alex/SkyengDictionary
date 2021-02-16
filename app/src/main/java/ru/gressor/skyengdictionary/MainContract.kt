package ru.gressor.skyengdictionary

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord

class MainContract {

    interface View {
        fun renderData(data: DictData)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView(view: View)
        fun getData(word: String, isOnline: Boolean)
    }

    interface Interactor {
        fun getData(word: String, isOnline: Boolean): Single<DictData>
    }

    interface Repository {
        fun getData(word: String): Single<List<DictWord>>
    }

    interface DataSource {
        fun getData(word: String): Single<List<DictWord>>
    }
}