package ru.gressor.historyscreen

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.data.local.HistoryItem

class HistoryRepository(
    private val dataSource: MainContract.DataSource<List<HistoryItem>>
): MainContract.Repository<List<HistoryItem>> {
    override suspend fun getData(word: String): List<HistoryItem> = dataSource.getData(word)
}