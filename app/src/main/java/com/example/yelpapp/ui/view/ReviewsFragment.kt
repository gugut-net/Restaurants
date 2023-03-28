package com.example.yelpapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentRatingsBinding
import com.example.yelpapp.ui.adapter.RatingAdapter
import com.example.yelpapp.utils.UIState
import com.example.yelpapp.utils.ViewIntents

class ReviewsFragment : BaseFragment() {

    val binding by lazy {
        FragmentRatingsBinding.inflate(layoutInflater)
    }

    val ratingAdapter by lazy {
        RatingAdapter()
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        swipeRefreshLayout = binding.swipeLayout

        binding.rvRatingsList.apply {
            adapter = ratingAdapter
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        }
        binding.topBar.detailsTopBar.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.fragment_ratings -> true
                R.id.fragment_details -> {
                    findNavController().navigate(R.id.action_fragment_ratings_to_fragment_details2)
                    true
                }
                else -> false
            }
        }
        updateList()
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            restaurantsViewModel.getIntentView(ViewIntents.RESTAURANT_RATINGS)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        restaurantsViewModel.fragmentState.observe(viewLifecycleOwner) {
            if (!it) {
                restaurantsViewModel.getIntentView(ViewIntents.RESTAURANT_RATINGS)
            }
        }
    }

    private fun updateList() {
        restaurantsViewModel.restaurantRatings.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.ERROR -> {
                    showError(state.e.localizedMessage) {
                        restaurantsViewModel.getIntentView(ViewIntents.RESTAURANT_RATINGS)
                    }
                }
                UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    ratingAdapter.updateRatings(state.response)
                }
            }
        }
    }

}