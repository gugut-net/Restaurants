package com.example.yelpapp.usecases

import com.example.yelpapp.database.LocalRepository
import com.example.yelpapp.domain.Restaurant
import com.example.yelpapp.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoriteRestaurants @Inject constructor(
    private val localRepository: LocalRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke():
            Flow<UIState<List<Restaurant>>> = flow {
            emit(localRepository.getFavorite())
        }.flowOn(ioDispatcher)
}