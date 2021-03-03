package ru.gressor.skyengdictionary

import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord

class MainContract {

    interface Interactor {
        suspend fun getData(word: String, isOnline: Boolean): DictData
    }

    interface Repository {
        suspend fun getData(word: String): List<DictWord>
    }

    interface RepositoryLocal: Repository {
        suspend fun saveData(word: String)
    }

    interface DataSource {
        suspend fun getData(word: String): List<DictWord>
    }

    interface DataSourceLocal: DataSource {
        suspend fun saveData(word: String)
    }
}