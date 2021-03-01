package ru.gressor.skyengdictionary.viewmodels

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData

class MainViewModel constructor(
    private val interactor: MainContract.Interactor
) : BaseViewModel<DictData>() {

    init {
        stateMutableLiveData.value = DictData.Empty
    }

    override fun getData(word: String, isOnline: Boolean) {
        stateMutableLiveData.value = DictData.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                stateMutableLiveData.postValue(interactor.getData(word, isOnline))
            }
        }
    }

    override fun handleError(throwable: Throwable) {
        stateMutableLiveData.value = DictData.Error(throwable)
    }
}