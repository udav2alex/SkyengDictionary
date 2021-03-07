package ru.gressor.historyscreen

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.data.local.HistoryItem
import ru.gressor.skyengdictionary.entities.HistoryData

class HistoryInteractor constructor(
    private val repository: MainContract.Repository<List<HistoryItem>>
) : MainContract.Interactor<HistoryData> {

    override suspend fun getData(word: String, isOnline: Boolean): HistoryData {
        return HistoryData.Success(repository.getData(word))
    }
}