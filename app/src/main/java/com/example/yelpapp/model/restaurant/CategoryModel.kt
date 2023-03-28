package com.example.yelpapp.model.restaurant


import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("alias")
    val alias: String? = "",
    @SerializedName("title")
    val title: String? = ""
)