package ru.gressor.skyengdictionary.data.local

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class DataSourceLocal(
    private val dao: HistoryDAO
): MainContract.DataSourceLocal {

    override suspend fun saveData(word: String) {
        dao.saveHistoryItem(HistoryItem(word))
    }

    override suspend fun getData(word: String): List<DictWord> {
        TODO("Not yet implemented")
    }
}