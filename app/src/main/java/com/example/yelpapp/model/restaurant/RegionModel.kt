package com.example.yelpapp.model.restaurant


import com.google.gson.annotations.SerializedName

data class RegionModel(
    @SerializedName("center")
    val center: CenterModel? = CenterModel()
)