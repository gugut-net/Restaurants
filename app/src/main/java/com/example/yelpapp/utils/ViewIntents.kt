package com.example.yelpapp.utils

sealed class ViewIntents {

    object GET_LOCATION: ViewIntents()

    object RESTAURANTS_LIST: ViewIntents()

    object CONFIGURATION_CHANGE: ViewIntents()

    object RESTAURANT_RATINGS: ViewIntents()

    object START_FRAGMENT: ViewIntents()

    object UPDATE_FAVORITE: ViewIntents()

    object GET_FAVORITES: ViewIntents()
}