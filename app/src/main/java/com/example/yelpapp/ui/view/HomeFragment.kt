package com.example.yelpapp.ui.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentRestaurantsListBinding
import com.example.yelpapp.ui.adapter.RestaurantAdapter
import com.example.yelpapp.utils.UIState
import com.example.yelpapp.utils.ViewIntents

class HomeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentRestaurantsListBinding.inflate(layoutInflater)
    }

    private val restaurantAdapter by lazy {
        RestaurantAdapter { restaurant ->
            restaurantsViewModel.selectedRestaurant = restaurant
            findNavController().navigate(R.id.action_fragment_home_to_fragment_details)
        }
    }

    private lateinit var permission: ArrayList<String>

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permission = arrayListOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        swipeRefreshLayout = binding.swipeLayout
        binding.tvTextTitle.text = "Restaurant Near Me"

        binding.tvRestaurantList.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        updateList()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            restaurantsViewModel.getIntentView(ViewIntents.GET_LOCATION)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        restaurantsViewModel.fragmentState.observe(viewLifecycleOwner) {
            if (!it) {
                permission.forEach {
                    if (checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permission.toTypedArray(), 900)

                    }else {
                    restaurantsViewModel.locationRuntimePermission = true
                    restaurantsViewModel.getIntentView(ViewIntents.GET_LOCATION)

                    }
                }
            }
        }
    }

    @Deprecated(" ")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 900) {
            grantResults.forEach {
                restaurantsViewModel.locationRuntimePermission = it == PackageManager.PERMISSION_GRANTED
                restaurantsViewModel.getIntentView(ViewIntents.GET_LOCATION)
            }
        }
    }

    private fun updateList() {
        restaurantsViewModel.locationSate.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.ERROR -> {
                    showError(state.e.localizedMessage) {
                        restaurantsViewModel.getIntentView(ViewIntents.GET_LOCATION)
                    }
                }
                UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    restaurantsViewModel.currentLocation = state.response
                    restaurantsViewModel.getIntentView(ViewIntents.RESTAURANTS_LIST)
                }
            }
        }
        restaurantsViewModel.restaurantNearMe.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.ERROR -> {
                    showError(state.e.localizedMessage) {
                        restaurantsViewModel.getIntentView(ViewIntents.RESTAURANTS_LIST)
                    }
                }
                UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    restaurantAdapter.updateRestaurants(state.response)
                }
            }
        }
    }

}