package ru.gressor.skyengdictionary.interactors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData
import kotlin.coroutines.CoroutineContext

class MainInteractor constructor(
    private val onlineRepository: MainContract.Repository,
    private val offlineRepository: MainContract.RepositoryLocal
) : MainContract.Interactor {

    override suspend fun getData(word: String, isOnline: Boolean): DictData {
        CoroutineScope(Dispatchers.IO).launch {
            offlineRepository.saveData(word)
        }

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