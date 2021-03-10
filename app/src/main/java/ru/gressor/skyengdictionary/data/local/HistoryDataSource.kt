package ru.gressor.skyengdictionary.data.local

import ru.gressor.skyengdictionary.MainContract

class HistoryDataSource(
    private val dao: HistoryDAO
): MainContract.DataSource<List<HistoryItem>> {

    override suspend fun getData(word: String) = dao.getHistory()
}