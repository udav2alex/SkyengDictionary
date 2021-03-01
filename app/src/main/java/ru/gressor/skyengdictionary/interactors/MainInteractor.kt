package ru.gressor.skyengdictionary.interactors

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData

class MainInteractor constructor(
    private val onlineRepository: MainContract.Repository,
    private val offlineRepository: MainContract.Repository
) : MainContract.Interactor {

    override suspend fun getData(word: String, isOnline: Boolean): DictData {
        return DictData.Success(
            if (isOnline) {
                onlineRepository
            } else {
                offlineRepository
            }
                .getData(word)
        )
    }
}