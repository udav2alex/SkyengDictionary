package ru.gressor.skyengdictionary

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord

class MainContract {

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