package ru.gressor.skyengdictionary.data.remote

import ru.gressor.core.BaseContract
import ru.gressor.core.entities.DictWord

class SearchDataSourceRemote(
    private val remoteProvider: RetrofitImpl
) : BaseContract.DataSource<List<DictWord>> {

    override suspend fun getData(word: String): List<DictWord> = remoteProvider.getData(word)
}