package ru.gressor.skyengdictionary.interactors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.gressor.core.BaseContract
import ru.gressor.core.entities.SearchData
import ru.gressor.core.entities.DictWord

class SearchInteractor constructor(
    private val onlineRepository: BaseContract.Repository<List<DictWord>>,
    private val offlineRepository: BaseContract.RepositoryLocal<List<DictWord>>
) : BaseContract.Interactor<SearchData> {

    override suspend fun getData(word: String, isOnline: Boolean): SearchData {
        CoroutineScope(Dispatchers.IO).launch {
            offlineRepository.saveHistory(word)
        }

        return SearchData.Success(
            if (isOnline) {
                onlineRepository
            } else {
                offlineRepository
            }
                .getData(word)
        )
    }
}