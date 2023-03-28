package com.example.yelpapp.database

import android.util.Log
import com.example.yelpapp.domain.Restaurant
import com.example.yelpapp.utils.UIState
import javax.inject.Inject

private const val TAG = "LocalRepository"

class LocalRepositoryImpl @Inject constructor(
    private val restaurantDAO: RestaurantsDAO
): LocalRepository {

    override suspend fun insertFavorite(restaurant: Restaurant) {
        try {
            restaurantDAO.insertFavorite(restaurant)
        }catch (e: Exception) {
            Log.e(TAG, "insertFavorite: ${e.localizedMessage}", e)
        }
    }

    override suspend fun deleteFavorite(restaurant: Restaurant) {
        try {
            restaurantDAO.deleteFavorite(restaurant)
        }catch (e: Exception) {
            Log.e(TAG, "deleteFavorite: ${e.localizedMessage}", e)
        }
    }

    override suspend fun getFavorite(): UIState<List<Restaurant>> {
        return try {
            UIState.SUCCESS(restaurantDAO.getFavoriteRestaurants())
        }catch (e: Exception) {
            UIState.ERROR(e)
        }
    }

    override suspend fun getFavoriteById(id: String): Restaurant? {
        return try {
            restaurantDAO.getRestaurantById(id)
        }catch (e: Exception) {
            Log.e(TAG, "getFavoriteById: ${e.localizedMessage}", e)
            null
        }
    }

}

interface LocalRepository {

    suspend fun insertFavorite(restaurant: Restaurant)

    suspend fun deleteFavorite(restaurant: Restaurant)

    suspend fun getFavorite(): UIState<List<Restaurant>>

    suspend fun getFavoriteById(id: String): Restaurant?

}