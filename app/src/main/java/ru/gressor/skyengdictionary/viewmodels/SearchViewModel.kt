package ru.gressor.skyengdictionary.viewmodels

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.gressor.core.BaseViewModel
import ru.gressor.core.BaseContract
import ru.gressor.core.entities.SearchData

class SearchViewModel constructor(
    private val interactor: BaseContract.Interactor<SearchData>
) : BaseViewModel<SearchData>() {

    init {
        stateMutableLiveData.value = SearchData.Empty
    }

    override fun getData(word: String, isOnline: Boolean) {
        stateMutableLiveData.value = SearchData.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                stateMutableLiveData.postValue(interactor.getData(word, isOnline))
            }
        }
    }

    override fun handleError(throwable: Throwable) {
        stateMutableLiveData.value = SearchData.Error(throwable)
    }
}