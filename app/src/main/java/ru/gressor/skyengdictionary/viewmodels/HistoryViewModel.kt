package ru.gressor.skyengdictionary.viewmodels

import kotlinx.coroutines.launch
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.HistoryData

class HistoryViewModel(
    private val interactor: MainContract.Interactor<HistoryData>
): BaseViewModel<HistoryData>() {

    init {
        stateMutableLiveData.value = HistoryData.Loading(null)
        getData("", false)
    }

    override fun getData(word: String, isOnline: Boolean) {
        cancelJob()
        viewModelCoroutineScope.launch {
            stateMutableLiveData.postValue(interactor.getData(word, isOnline))
        }
    }

    override fun handleError(throwable: Throwable) {
        stateMutableLiveData.postValue(HistoryData.Error(throwable))
    }


}