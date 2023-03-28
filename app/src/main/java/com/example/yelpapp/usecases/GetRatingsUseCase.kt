package com.example.yelpapp.usecases

import com.example.yelpapp.domain.Rating
import com.example.yelpapp.domain.mapToRating
import com.example.yelpapp.service.NetworkRepository
import com.example.yelpapp.utils.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRatingsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val networkState: NetworkState,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(
        id: String,
        sortedBy: ApiSortedBy
    ): Flow<UIState<List<Rating>>> = flow {
        emit(UIState.LOADING)
        if (networkState.isInternetEnabled()) {
            val response = networkRepository.getReviews(
                id,
                sortedBy
            )
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    val ratings = result.reviews.mapToRating()
                    emit(UIState.SUCCESS(ratings))
                } ?: throw NoResponseException()
            } else throw NetworkCallFailureException(response.errorBody().toString())
        } else throw InternetConnectionRequiredException()
    }.catch {
        emit(UIState.ERROR(it as Exception))
    }.flowOn(ioDispatcher)
}