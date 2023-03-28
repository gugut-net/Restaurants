package com.example.yelpapp.service

import com.example.yelpapp.model.reviews.ReviewsResponseModel
import com.example.yelpapp.model.restaurant.RestaurantsResponseModel
import com.example.yelpapp.model.reviews.ReviewsModel
import com.example.yelpapp.utils.ApiSortedBy
import retrofit2.Response
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi

): NetworkRepository {

    override suspend fun getRestaurants(
        latitude: Double,
        longitude: Double,
        attributes: String,
        sortedBy: ApiSortedBy
    ): Response<RestaurantsResponseModel> =
        serviceApi.getRestaurants(latitude,longitude,attributes,sortedBy.sorted_by)

    override suspend fun getReviews(
        id: String,
        sortedBy: ApiSortedBy
    ): Response<ReviewsModel> =
        serviceApi.getReviews(id, sortedBy.sorted_by)

}

interface NetworkRepository {

    suspend fun getRestaurants(
        latitude: Double,
        longitude: Double,
        attributes: String,
        sortedBy: ApiSortedBy
    ): Response<RestaurantsResponseModel>

    suspend fun getReviews(
        id: String,
        sortedBy: ApiSortedBy
    ): Response<ReviewsModel>

}