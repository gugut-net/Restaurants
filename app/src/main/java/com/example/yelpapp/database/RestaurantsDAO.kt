package com.example.yelpapp.database

import androidx.room.*
import com.example.yelpapp.domain.Restaurant

@Dao
interface RestaurantsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(vararg restaurant: Restaurant)

    @Delete
    suspend fun deleteFavorite(vararg restaurant: Restaurant)

    @Query("SELECT * FROM Restaurant")
    suspend fun getFavoriteRestaurants(): List<Restaurant>

    @Query("SELECT * FROM Restaurant WHERE id = :id")
    suspend fun getRestaurantById(id: String): Restaurant
}