package ru.gressor.skyengdictionary.data

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord

class DataSourceRemote(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
): MainContract.DataSource {

    override fun getData(word: String): Single<List<DictWord>> = remoteProvider.getData(word)
}