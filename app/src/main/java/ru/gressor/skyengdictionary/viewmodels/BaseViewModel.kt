package ru.gressor.skyengdictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.gressor.skyengdictionary.rx.MainSchedulersProvider
import ru.gressor.skyengdictionary.rx.SchedulersProvider

abstract class BaseViewModel<State>(
    protected val stateMutableLiveData: MutableLiveData<State> = MutableLiveData(),
    protected open val schedulersProvider: SchedulersProvider = MainSchedulersProvider(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable()
): ViewModel() {

    val liveData: LiveData<State> = stateMutableLiveData

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}