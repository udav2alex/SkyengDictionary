package ru.gressor.skyengdictionary.viewmodels

import kotlinx.coroutines.launch
import ru.gressor.skyengdictionary.data.local.HistoryItem

class HistoryViewModel: BaseViewModel<List<HistoryItem>>() {

    override fun getData(word: String, isOnline: Boolean) {
        cancelJob()
//        viewModelCoroutineScope.launch {
//            stateMutableLiveData.postValue()
//        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("Not yet implemented")
    }


}