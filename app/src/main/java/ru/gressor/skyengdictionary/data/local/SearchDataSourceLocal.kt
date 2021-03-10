package ru.gressor.skyengdictionary.data.local

import ru.gressor.core.BaseContract
import ru.gressor.core.entities.DictWord
import ru.gressor.core.entities.HistoryItem

class SearchDataSourceLocal(
    private val dao: HistoryDAO
): BaseContract.DataSourceLocal<List<DictWord>> {

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