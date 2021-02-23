package ru.gressor.skyengdictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel<State>(
    protected val stateMutableLiveData: MutableLiveData<State> = MutableLiveData()
) : ViewModel() {

    val liveData: LiveData<State> = stateMutableLiveData

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) }
    )

    abstract fun getData(word: String, isOnline: Boolean)

    abstract fun handleError(throwable: Throwable)

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }
}