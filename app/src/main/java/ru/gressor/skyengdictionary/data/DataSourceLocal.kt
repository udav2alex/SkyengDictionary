package ru.gressor.skyengdictionary.data

import io.reactivex.rxjava3.core.Single
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord

class DataSourceLocal: MainContract.DataSource {

    override fun getData(word: String): Single<List<DictWord>> {
        TODO("Not yet implemented")
    }
}