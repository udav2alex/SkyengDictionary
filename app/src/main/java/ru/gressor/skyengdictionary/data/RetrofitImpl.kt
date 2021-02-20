package ru.gressor.skyengdictionary.data

import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gressor.skyengdictionary.entities.DictWord

class RetrofitImpl(
    private val baseUrl: String = "https://dictionary.skyeng.ru/api/public/v1/"
) {

    fun getData(word: String): Single<List<DictWord>> = getDataProvider().searchFor(word)

    private fun getDataProvider(): ServiceAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(ServiceAPI::class.java)
    }
}