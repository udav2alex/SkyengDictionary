package ru.gressor.skyengdictionary

class MainContract {

    interface Interactor<T> {
        suspend fun getData(word: String, isOnline: Boolean): T // DictData
    }

    interface Repository<T> {
        suspend fun getData(word: String): T // List<DictWord>
    }

    interface RepositoryLocal<T>: Repository<T> {
        suspend fun saveData(word: String, data: T)
        suspend fun saveWord(word: String)
    }

    interface DataSource<T> {
        suspend fun getData(word: String): T // List<DictWord>
    }

    interface DataSourceLocal<T>: DataSource<T> {
        suspend fun saveData(word: String, data: T)
        suspend fun saveWord(word: String)
    }
}