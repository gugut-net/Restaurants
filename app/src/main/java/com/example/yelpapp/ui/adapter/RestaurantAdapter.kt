package com.example.yelpapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ItemRestaurantsHolderBinding
import com.example.yelpapp.domain.Restaurant
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlin.math.roundToInt

class RestaurantAdapter(
    private val itemSet: MutableList<Restaurant> = mutableListOf(),
    private val onRestaurantClickListener: (Restaurant) -> Unit
): RecyclerView.Adapter<RestaurantsViewHolder>() {

    fun updateRestaurants(newList: List<Restaurant>) {
        if (itemSet != newList) {
            itemSet.clear()
            itemSet.addAll(newList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RestaurantsViewHolder(
        ItemRestaurantsHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        holder.restaurantsBinding(itemSet[position], onRestaurantClickListener)
    }

    override fun getItemCount() = itemSet.size

}

class RestaurantsViewHolder(
    private val binding: ItemRestaurantsHolderBinding
): RecyclerView.ViewHolder(binding.root) {

    fun restaurantsBinding(item: Restaurant, onRestaurantClickListener: (Restaurant) -> Unit) {
        binding.tvRestaurantName.text = item.name
        binding.tvPrice.text = item.price
        binding.rbRating.rating = item.rating.toFloat()

        val roundedDistance = (item.distance * 100.0).roundToInt()/100.0
        binding.tvDistance.text = "Distance from you: ${roundedDistance.toString()}"
        Picasso
            .get()
            .load(item.imgUrl)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.ic_broken_image)
            .transform(RoundedCornersTransformation(0, 0))
            .into(binding.ivRestaurantImage)
        itemView.setOnClickListener {
            item.let(onRestaurantClickListener)
        }
    }
}