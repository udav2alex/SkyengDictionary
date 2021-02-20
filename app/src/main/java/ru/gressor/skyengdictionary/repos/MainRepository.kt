package ru.gressor.skyengdictionary.repos

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class MainRepository(
    private val dataSource: MainContract.DataSource
): MainContract.Repository {
    override fun getData(word: String): Single<List<DictWord>> = dataSource.getData(word)
}