package com.example.yelpapp.domain

import com.example.yelpapp.model.reviews.ReviewsModel
import com.example.yelpapp.model.reviews.ReviewsResponseModel

data class Rating (
    val id: String = " ",
    val rating: Int = 0,
    val text: String = " ",
    val timeCreated: String = " ",
    val userImg: String?,
    val userName: String = " "

)

fun List<ReviewsResponseModel>?.mapToRating(): List<Rating> =
    this?.map {
        Rating(
            it.id ?: " ",
            it.rating ?: 0,
            it.text ?: " ",
            it.timeCreated ?: " ",
            it.user?.imageUrl,
            it.user?.name ?: ""
        )
    }?: emptyList()