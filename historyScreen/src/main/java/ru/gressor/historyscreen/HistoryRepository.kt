package ru.gressor.historyscreen

import ru.gressor.core.BaseContract
import ru.gressor.core.entities.HistoryItem

class HistoryRepository(
    private val dataSource: BaseContract.DataSource<List<HistoryItem>>
): BaseContract.Repository<List<HistoryItem>> {
    override suspend fun getData(word: String): List<HistoryItem> = dataSource.getData(word)
}