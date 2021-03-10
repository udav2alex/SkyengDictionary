package ru.gressor.skyengdictionary.repos

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class SearchRepository(
    private val dataSource: MainContract.DataSource<List<DictWord>>
): MainContract.Repository<List<DictWord>> {
    override suspend fun getData(word: String): List<DictWord> = dataSource.getData(word)
}