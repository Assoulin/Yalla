package com.example.yalla.network

import com.example.yalla.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original =
            chain.request()

        val url = original.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}