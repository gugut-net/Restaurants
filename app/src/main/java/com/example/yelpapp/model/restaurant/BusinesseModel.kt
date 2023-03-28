package com.example.yelpapp.model.restaurant


import com.google.gson.annotations.SerializedName

data class BusinesseModel(
    @SerializedName("alias")
    val alias: String? = "",
    @SerializedName("categories")
    val categories: List<CategoryModel?>? = listOf(),
    @SerializedName("coordinates")
    val coordinates: CoordinatesModel? = CoordinatesModel(),
    @SerializedName("display_phone")
    val displayPhone: String? = "",
    @SerializedName("distance")
    val distance: Double? = 0.0,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("is_closed")
    val isClosed: Boolean? = false,
    @SerializedName("location")
    val location: LocationModel? = LocationModel(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("price")
    val price: String? = "",
    @SerializedName("rating")
    val rating: Double? = 0.0,
    @SerializedName("review_count")
    val reviewCount: Int? = 0,
    @SerializedName("transactions")
    val transactions: List<String?>? = listOf(),
    @SerializedName("url")
    val url: String? = ""
)