package ru.gressor.historyscreen

import kotlinx.coroutines.launch
import ru.gressor.core.BaseContract
import ru.gressor.core.entities.HistoryData

class HistoryViewModel(
    private val interactor: BaseContract.Interactor<HistoryData>
): ru.gressor.core.BaseViewModel<HistoryData>() {

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