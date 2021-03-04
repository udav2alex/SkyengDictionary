package ru.gressor.skyengdictionary.data.local

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class SearchDataSourceLocal(
    private val dao: HistoryDAO
): MainContract.DataSourceLocal<List<DictWord>> {

    override suspend fun saveHistory(search: String) {
        dao.saveHistoryItem(HistoryItem(search))
    }

    override suspend fun getData(word: String): List<DictWord> {
        // TODO("Not yet implemented")
        return emptyList()
    }

    override suspend fun saveData(word: String, data: List<DictWord>) {
        // TODO("Not yet implemented")
    }
}