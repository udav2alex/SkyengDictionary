package ru.gressor.skyengdictionary.interactors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.SearchData
import ru.gressor.skyengdictionary.entities.DictWord

class SearchInteractor constructor(
    private val onlineRepository: MainContract.Repository<List<DictWord>>,
    private val offlineRepository: MainContract.RepositoryLocal<List<DictWord>>
) : MainContract.Interactor<SearchData> {

    override suspend fun getData(word: String, isOnline: Boolean): SearchData {
        CoroutineScope(Dispatchers.IO).launch {
            offlineRepository.saveWord(word)
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