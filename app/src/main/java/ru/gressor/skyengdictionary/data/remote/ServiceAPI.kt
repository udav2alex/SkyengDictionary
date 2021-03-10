package ru.gressor.skyengdictionary.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gressor.core.entities.DictWord

interface ServiceAPI {

    @GET("words/search")
    suspend fun searchFor(@Query("search") word: String) : Response<List<DictWord>>
}