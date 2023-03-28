package com.example.yelpapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentRestaurantsBinding
import com.example.yelpapp.utils.ViewIntents
import com.squareup.picasso.Picasso

private const val TAG = "RestaurantsFragment"

class RestaurantsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentRestaurantsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.tvRestaurantName.text = restaurantsViewModel.selectedRestaurant?.name
        binding.tvPrice.text = restaurantsViewModel.selectedRestaurant?.price
        binding.rbRatings.rating = restaurantsViewModel.selectedRestaurant?.rating?.toFloat() ?: 1F
        binding.tvAddress.text = restaurantsViewModel.selectedRestaurant?.address
        binding.tvPhone.text = restaurantsViewModel.selectedRestaurant?.phone
        Picasso
            .get()
            .load(restaurantsViewModel.selectedRestaurant?.imgUrl)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.ic_broken_image)
            .into(binding.ivRestaurantImage)

        checkFavorite()

        binding.topBar.detailsTopBar.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.fragment_ratings -> {
                    findNavController().navigate(R.id.action_fragment_ratings_to_fragment_details2)
                    true
                }
                R.id.fragment_details -> true
                else -> false
            }
        }
        binding.ivStartImage.setOnClickListener {
            restaurantsViewModel.getIntentView(ViewIntents.UPDATE_FAVORITE)
            checkFavorite()
        }
        return binding.root
    }

    private fun checkFavorite() {
        restaurantsViewModel.selectedRestaurant?.isFavorite?.let {
            if (it) {
                Log.d(TAG, "checkFavorite: is filled")
                binding.ivStartImage.setImageResource(R.drawable.baseline_star_rate_24)
            } else {
                Log.d(TAG, "checkFavorite: is empty")
                binding.ivStartImage.setImageResource(R.drawable.sart_empty)
            }
        } ?: binding.ivStartImage.setImageResource(R.drawable.sart_empty)
    }


}