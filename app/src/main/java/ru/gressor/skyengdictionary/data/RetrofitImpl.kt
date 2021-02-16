package ru.gressor.skyengdictionary.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gressor.skyengdictionary.entities.DictWord

class RetrofitImpl {

    fun getData(word: String): Single<List<DictWord>> = getDataProvider().searchFor(word)

    private fun getDataProvider(): ServiceAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(ServiceAPI::class.java)
    }
}

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"