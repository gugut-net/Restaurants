package com.example.yelpapp.service

import com.example.yelpapp.model.reviews.ReviewsResponseModel
import com.example.yelpapp.model.restaurant.RestaurantsResponseModel
import com.example.yelpapp.model.reviews.ReviewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    //https://api.yelp.com/v3/businesses/search?latitude=34.0022861&longitude=-84.6165137&term=restaurants&attributes=hot_and_new&sort_by=best_match&limit=10
    //https://api.yelp.com/v3/businesses/M3UL-rHZ4Sp8k_5bRBPbWg/reviews?limit=20&sort_by=yelp_sort
    companion object {
        const val BASE_URL = "https://api.yelp.com/v3/businesses/"
        const val SEARCH_RESTAURANTS = "search"
        const val PATH_ID = "{id}/"
        const val SEARCH_REVIEWS = "reviews"
    }


    @GET(SEARCH_RESTAURANTS)
    suspend fun getRestaurants(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("attributes") attributes: String,
        @Query("sort_by") sorting: String
    ): Response<RestaurantsResponseModel>

    @GET(PATH_ID + SEARCH_REVIEWS)
    suspend fun getReviews(
        @Path("id") id: String,
        @Query("sort_by") sorting: String
    ): Response<ReviewsModel>
}