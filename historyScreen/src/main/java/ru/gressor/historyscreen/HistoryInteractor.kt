package ru.gressor.historyscreen

import ru.gressor.core.BaseContract
import ru.gressor.core.entities.HistoryItem
import ru.gressor.core.entities.HistoryData

class HistoryInteractor constructor(
    private val repository: BaseContract.Repository<List<HistoryItem>>
) : BaseContract.Interactor<HistoryData> {

    override suspend fun getData(word: String, isOnline: Boolean): HistoryData {
        return HistoryData.Success(repository.getData(word))
    }
}