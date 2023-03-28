package com.example.yelpapp.model.restaurant


import com.google.gson.annotations.SerializedName

data class RestaurantsResponseModel(
    @SerializedName("businesses")
    val businesses: List<BusinesseModel>? = listOf(),
    @SerializedName("region")
    val region: RegionModel? = RegionModel(),
    @SerializedName("total")
    val total: Int? = 0
)