package ru.gressor.skyengdictionary.repos

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class SearchRepositoryLocal(
    private val dataSource: MainContract.DataSourceLocal<List<DictWord>>
): MainContract.RepositoryLocal<List<DictWord>> {
    override suspend fun getData(word: String): List<DictWord> = dataSource.getData(word)
    override suspend fun saveHistory(word: String) = dataSource.saveHistory(word)
    override suspend fun saveData(word: String, data: List<DictWord>) {
        dataSource.saveData(word, data)
    }
}