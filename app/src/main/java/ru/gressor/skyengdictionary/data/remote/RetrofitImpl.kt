package ru.gressor.skyengdictionary.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gressor.skyengdictionary.entities.DictWord
import java.lang.IllegalStateException

class RetrofitImpl(
    private val baseUrl: String = "https://dictionary.skyeng.ru/api/public/v1/"
) {
    suspend fun getData(word: String): List<DictWord> {
        val response: Response<List<DictWord>> = getDataProvider().searchFor(word)

        if (response.isSuccessful && response.body() != null) {
            return response.body() ?: throw IllegalStateException(response.toString())
        } else {
            throw IllegalStateException(response.toString())
        }
    }

    private fun getDataProvider(): ServiceAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    // TODO Как BaseInterceptor может что-то сообщить в getData?
                    .addInterceptor(BaseInterceptor.interceptor)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ServiceAPI::class.java)
    }
}