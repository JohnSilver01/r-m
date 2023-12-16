package com.john.portfolio.data.api

import com.john.portfolio.models.Characters
import com.john.portfolio.models.Episodes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com/api/"
object RetrofitInstance{
    private val interceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val searchApi: CharacterApi = retrofit.create(CharacterApi::class.java)
}

interface CharacterApi {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<Characters>

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): Response<Episodes>

    companion object{
        const val PAGE_SIZE = 10
    }
}