package com.example.yelpapp.model.reviews


import com.google.gson.annotations.SerializedName

data class ReviewsModel(
    @SerializedName("possible_languages")
    val possibleLanguages: List<String?>? = listOf(),
    @SerializedName("reviews")
    val reviews: List<ReviewsResponseModel>? = listOf(),
    @SerializedName("total")
    val total: Int? = 0
)