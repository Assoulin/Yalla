package com.example.yalla.services

import com.example.yalla.BuildConfig
import com.example.yalla.BuildConfig.BASE_URL
import com.example.yalla.models.responses.DestinationsResponse
import com.example.yalla.network.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface YallaService {
    @GET(BuildConfig.DESTINATIONS)
    suspend fun getDestinations(): DestinationsResponse

    //Setup:
    companion object {
        fun create(): YallaService {
            //print all requests to log
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            //Add the Api Key
            val client = OkHttpClient.Builder().apply {
                addInterceptor(TokenInterceptor())
                addInterceptor(logger)
            }.build()

            //basic retrofit
            return Retrofit.Builder().apply {
                client(client)
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
            }.build()
                .create(YallaService::class.java)
        }
    }
}