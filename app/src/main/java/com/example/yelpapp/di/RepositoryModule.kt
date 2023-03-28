package com.example.yelpapp.di

import com.example.yelpapp.database.LocalRepository
import com.example.yelpapp.database.LocalRepositoryImpl
import com.example.yelpapp.location.LocationRepository
import com.example.yelpapp.location.LocationRepositoryImpl
import com.example.yelpapp.service.NetworkRepository
import com.example.yelpapp.service.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

    @Binds
    abstract fun providesLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    abstract fun providesLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}