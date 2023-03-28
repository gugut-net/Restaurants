package com.example.yelpapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yelpapp.model.restaurant.BusinesseModel

/**
 * Data class that provides the information about the restaurant
 */
@Entity
data class Restaurant(

    @PrimaryKey
    val id: String = " ",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val phone: String = " ",
    val distance: Double = 0.0,
    val imgUrl: String = " ",
    val address: String = " ",
    val name: String = " ",
    val price: String = " ",
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val transactions: Boolean,
    var isFavorite: Boolean

    )

fun List<BusinesseModel>?.mapToRestaurant(): List<Restaurant> {
        return this?.map { it ->
                var streetAddress = " "
                it.location?.displayAddress?.forEach {
                        streetAddress = streetAddress + it + '\n'
                }
                Restaurant(
                        it.id ?: " ",
                        it.coordinates?.latitude ?: 0.0,
                        it.coordinates?.longitude ?: 0.0,
                        it.displayPhone ?: " ",
                        it.distance?.let { it*0.000612317 }?: 0.0,
                        it.imageUrl?: " ",
                        streetAddress,
                        it.name ?: " ",
                        it.price ?: " ",
                        it.rating ?: 0.0,
                        it.reviewCount ?: 0,
                        transactions = false,
                        isFavorite = false

                )
        }?: emptyList()
}