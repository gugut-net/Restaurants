package com.example.yelpapp.service

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            addHeader(
                "Authorization",
                "Bearer OyFRR5PRd5I5hJ1f1ihFkqyANxelEJi0L6T06z3OvrthiWSan7_0ZZSZ_IhganUVxsCMwcxA-qmCeJJGkcyN-zW5CMWm-IVlyc0JZx2Ya92MU6Smr9OTuHuoy2EzY3Yx"
            )
        }.build().also { return chain.proceed(it) }
    }

}