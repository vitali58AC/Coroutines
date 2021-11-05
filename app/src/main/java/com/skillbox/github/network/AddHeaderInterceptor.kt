package com.skillbox.github.network

import android.util.Log
import com.skillbox.github.data.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response

class AddHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", AuthRepository.accessToken)
            .build()

        Log.e("test", "$originalRequest and $modifiedRequest")
        return chain.proceed(modifiedRequest)
    }

}