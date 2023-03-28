package com.example.yelpapp.di

import android.content.Context
import com.example.yelpapp.service.CacheInterceptor
import com.example.yelpapp.service.ForceCacheInterceptor
import com.example.yelpapp.service.RequestInterceptor
import com.example.yelpapp.service.ServiceApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesServiceApi(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ServiceApi =
        Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ServiceApi::class.java)

    @Provides
    fun providesOkHttpClient(
        cache: Cache,
        requestInterceptor: RequestInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cacheInterceptor: CacheInterceptor,
        forceCacheInterceptor: ForceCacheInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(requestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(forceCacheInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesCacheFile(
        @ApplicationContext context: Context
    ): Cache =
        Cache(File(context.cacheDir, "http-cache"), 10L*1024L*1024L)
}