package com.example.yelpapp.usecases

class YelpUseCases(
    val getLocation: GetLocationUseCase,
    val getRatings: GetRatingsUseCase,
    val getRestaurants: GetRestaurantsUseCase,
    val updateFavorite: UpdateFavoriteUseCase,
    val getFavoriteRestaurants: GetFavoriteRestaurants
)