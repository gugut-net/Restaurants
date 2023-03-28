package com.example.yelpapp.service

import com.example.yelpapp.utils.NetworkState
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ForceCacheInterceptor @Inject constructor(
    private val networkSate: NetworkState

): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            if (!networkSate.isInternetEnabled()) {
                cacheControl(CacheControl.FORCE_CACHE)
            }
        }.build().also {
            return chain.proceed(it)
        }
    }

}