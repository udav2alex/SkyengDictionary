package ru.gressor.skyengdictionary.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gressor.skyengdictionary.entities.DictWord

interface ServiceAPI {

    @GET("words/search")
    fun searchFor(@Query("search") word: String) : Single<List<DictWord>>
}