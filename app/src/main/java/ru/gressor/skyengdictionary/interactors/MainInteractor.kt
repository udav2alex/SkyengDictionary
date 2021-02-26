package ru.gressor.skyengdictionary.interactors

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.di.NAME_LOCAL
import ru.gressor.skyengdictionary.di.NAME_REMOTE
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val onlineRepository: MainContract.Repository,
    @Named(NAME_LOCAL) val offlineRepository: MainContract.Repository
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