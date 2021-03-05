package ru.gressor.skyengdictionary

class MainContract {

    interface SearchRunner {
        fun runSearch(search: String)
    }

    interface Interactor<T> {
        suspend fun getData(word: String, isOnline: Boolean): T
    }

    interface Repository<T> {
        suspend fun getData(word: String): T
    }

    interface RepositoryLocal<T>: Repository<T> {
        suspend fun saveData(word: String, data: T)
        suspend fun saveHistory(word: String)
    }

    interface DataSource<T> {
        suspend fun getData(word: String): T
    }

    interface DataSourceLocal<T>: DataSource<T> {
        suspend fun saveData(word: String, data: T)
        suspend fun saveHistory(search: String)
    }
}