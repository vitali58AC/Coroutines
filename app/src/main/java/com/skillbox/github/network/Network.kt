package com.skillbox.github.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Network {

    private val okHttpClient = OkHttpClient.Builder()
        //.addNetworkInterceptor(AddHeaderInterceptor())
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        //можно не создавать клиент, тогда по умолчанию будет пустой клиент
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    val gitHubApi: GitHubApi
        get() = retrofit.create()

}