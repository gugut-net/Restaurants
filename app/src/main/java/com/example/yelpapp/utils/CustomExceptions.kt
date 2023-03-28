package com.example.yelpapp.utils

class PermissionsRequiredException(message: String = "No permission granted"): Exception(message)

class LocationRequiredException(message: String = "Location is off"): Exception(message)

class InternetConnectionRequiredException(message: String = "No internet connection"): Exception(message)

class LocationFailureException(message: String = "Location failed"): Exception(message)

class NoResponseException(message: String = "Response is null"): Exception(message)

class NetworkCallFailureException(message: String): Exception(message)

class NoRestaurantException(message: String = "No restaurant selected"): Exception(message)