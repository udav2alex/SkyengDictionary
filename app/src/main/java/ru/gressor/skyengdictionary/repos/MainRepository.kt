package ru.gressor.skyengdictionary.repos

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class MainRepository(
    private val dataSource: MainContract.DataSource
): MainContract.Repository {
    override suspend fun getData(word: String): List<DictWord> = dataSource.getData(word)
}