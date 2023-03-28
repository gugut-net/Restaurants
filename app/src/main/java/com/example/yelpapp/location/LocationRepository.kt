package com.example.yelpapp.location

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject

private const val TAG = "LocationRepository"

interface LocationRepository {

    fun getLocationData(): Location?

    fun locationIsEnabled(): Boolean
}

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocation: FusedLocationProviderClient,
    private val locationManager: LocationManager
): LocationRepository {

    @SuppressLint("MissingPermission")
    override fun getLocationData(): Location? {
        if (locationIsEnabled()) {
            val location = fusedLocation.lastLocation
            while (!location.isComplete) {
                Thread.sleep(500)
            }
            Log.d(TAG, "getLocationData: ${location.result}")
            return location.result
        }
        return null
    }

    override fun locationIsEnabled(): Boolean =
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

}

