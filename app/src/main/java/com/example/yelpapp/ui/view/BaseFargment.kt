package com.example.yelpapp.ui.view

import android.app.AlertDialog
import android.content.res.Configuration
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.yelpapp.ui.viewModel.RestaurantsViewModel
import com.example.yelpapp.utils.ViewIntents

private const val TAG = "BaseFragment"

open class BaseFragment : Fragment() {

    protected val restaurantsViewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(requireActivity())[RestaurantsViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        restaurantsViewModel.getIntentView(ViewIntents.START_FRAGMENT)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "onConfigurationChanged: changed")
        restaurantsViewModel.getIntentView(ViewIntents.CONFIGURATION_CHANGE)
    }
    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("Retry") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("Dismiss") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}