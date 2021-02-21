package ru.gressor.skyengdictionary.interactors

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData

class MainInteractor constructor(
    private val onlineRepository: MainContract.Repository,
    private val offlineRepository: MainContract.Repository
) : MainContract.Interactor {

    override fun getData(word: String, isOnline: Boolean): Single<DictData> {
        return if (isOnline) {
            onlineRepository
        } else {
            offlineRepository
        }
            .getData(word)
            .map {
                DictData.Success(it)
            }
    }
}