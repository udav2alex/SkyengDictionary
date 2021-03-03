package ru.gressor.skyengdictionary.repos

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class RepositoryLocal(
    private val dataSource: MainContract.DataSourceLocal
): MainContract.RepositoryLocal {
    override suspend fun getData(word: String): List<DictWord> = dataSource.getData(word)
    override suspend fun saveData(word: String) = dataSource.saveData(word)
}