package ru.gressor.skyengdictionary.data.local

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord

class DataSourceLocal: MainContract.DataSource {

    override suspend fun getData(word: String): List<DictWord> {
        TODO("Not yet implemented")
    }
}