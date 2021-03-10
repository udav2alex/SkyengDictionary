package ru.gressor.skyengdictionary.data.local

import ru.gressor.core.BaseContract
import ru.gressor.core.entities.HistoryItem

class HistoryDataSource(
    private val dao: HistoryDAO
): BaseContract.DataSource<List<HistoryItem>> {

    override suspend fun getData(word: String) = dao.getHistory()
}