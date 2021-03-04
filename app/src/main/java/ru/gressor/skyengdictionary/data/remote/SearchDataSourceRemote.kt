package ru.gressor.skyengdictionary.data.remote

import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictWord

class SearchDataSourceRemote(
    private val remoteProvider: RetrofitImpl
) : MainContract.DataSource<List<DictWord>> {

    override suspend fun getData(word: String): List<DictWord> = remoteProvider.getData(word)
}