package com.example.yelpapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yelpapp.domain.Restaurant

@Database(
    entities = [Restaurant::class],
    version = 1
)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun getRestaurantsDao(): RestaurantsDAO
}