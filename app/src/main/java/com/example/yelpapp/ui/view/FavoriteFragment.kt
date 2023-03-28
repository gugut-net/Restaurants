package com.example.yelpapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentRestaurantsListBinding
import com.example.yelpapp.ui.adapter.RestaurantAdapter
import com.example.yelpapp.utils.UIState
import com.example.yelpapp.utils.ViewIntents

class FavoriteFragment : BaseFragment() {

    private val binding by lazy {
        FragmentRestaurantsListBinding.inflate(layoutInflater)
    }

    private val favoriteAdapter by lazy {
        RestaurantAdapter {
            restaurantsViewModel.selectedRestaurant = it
            findNavController().navigate(R.id.action_fragment_favorites_to_fragment_details)
        }
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.tvTextTitle.text= "Favorite Restaurant"

        binding.tvRestaurantList.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                true
            )
        }
        swipeRefreshLayout = binding.swipeLayout
        updateList()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            restaurantsViewModel.getIntentView(ViewIntents.GET_FAVORITES)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        restaurantsViewModel.fragmentState.observe(viewLifecycleOwner) {
            if (!it) {
                restaurantsViewModel.getIntentView(ViewIntents.GET_FAVORITES)
            }
        }
    }
    private fun updateList() {
        restaurantsViewModel.favoriteRestaurants.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.ERROR -> {
                    showError(state.e.localizedMessage) {
                        restaurantsViewModel.getIntentView(ViewIntents.GET_FAVORITES)
                    }
                }
                UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    favoriteAdapter.updateRestaurants(state.response)
                }
            }
        }
    }
}