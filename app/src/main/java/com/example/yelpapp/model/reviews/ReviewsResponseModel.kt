package com.example.yelpapp.model.reviews


import com.google.gson.annotations.SerializedName

data class ReviewsResponseModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("rating")
    val rating: Int? = 0,
    @SerializedName("text")
    val text: String? = "",
    @SerializedName("time_created")
    val timeCreated: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("user")
    val user: UserModel? = UserModel()
)