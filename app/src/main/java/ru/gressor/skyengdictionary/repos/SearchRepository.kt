package ru.gressor.skyengdictionary.repos

import ru.gressor.core.BaseContract
import ru.gressor.core.entities.DictWord

class SearchRepository(
    private val dataSource: BaseContract.DataSource<List<DictWord>>
): BaseContract.Repository<List<DictWord>> {
    override suspend fun getData(word: String): List<DictWord> = dataSource.getData(word)
}