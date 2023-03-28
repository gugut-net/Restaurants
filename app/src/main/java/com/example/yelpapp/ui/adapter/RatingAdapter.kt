package com.example.yelpapp.ui.adapter

import android.view.LayoutInflater
import android.view.View.LAYOUT_DIRECTION_RTL
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ItemRatingsHolderBinding
import com.example.yelpapp.databinding.ItemUserHolderBinding
import com.example.yelpapp.domain.Rating
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class RatingAdapter(
    private val itemSet: MutableList<ViewType> = mutableListOf()
): RecyclerView.Adapter<ViewHolder>() {

    /**
     *
     */
    fun updateRatings(newRatings: List<Rating>) {
        itemSet.clear()
        newRatings.forEachIndexed { index, rating ->
            if (index % 2 == 0) {
                itemSet.add(ViewType.USER(rating))
                itemSet.add(ViewType.RATING(rating))
            } else {
                itemSet.add(ViewType.USER(rating))
                itemSet.add(ViewType.RATING(rating))
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutParam: RecyclerView.LayoutParams
        val viewUser = ItemUserHolderBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        val viewRating = ItemRatingsHolderBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return if (viewType == 0) {
            layoutParam = (viewUser.root.layoutParams as LayoutParams).apply {
                width = parent.width/4
                isFullSpan = true
                layoutDirection = LAYOUT_DIRECTION_RTL
            }
            viewUser.root.layoutParams = layoutParam
            UserViewHolder(viewUser)
        } else {
            layoutParam = (viewRating.root.layoutParams as LayoutParams).apply {
                width = parent.width*3/4
                isFullSpan = true
            }
            viewRating.root.layoutParams = layoutParam
            RatingViewHolder(viewRating)

        }
    }
    override fun getItemCount() = itemSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       when(val item = itemSet[position]) {
           is ViewType.RATING -> {
               (holder as RatingViewHolder).ratingsBind(item.rating)
           }
           is ViewType.USER -> {
               (holder as UserViewHolder).usersBind(item.rating)
           }
       }
    }

    override fun getItemViewType(position: Int): Int =
        when(itemSet[position]) {
            is ViewType.RATING -> 1
            is ViewType.USER -> 0
        }
}

class UserViewHolder(
    private val binding: ItemUserHolderBinding
): ViewHolder(binding.root) {

    fun usersBind(item: Rating) {
        binding.tvUserName.text = item.text
        Picasso
            .get()
            .load(item.userImg)
            .transform(CropCircleTransformation())
            .resize(20, 0)
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.ic_loading)
            .into(binding.ivUserPic)
    }

}

class RatingViewHolder(
    private val binding: ItemRatingsHolderBinding
): ViewHolder(binding.root) {

    fun ratingsBind(item: Rating) {
        binding.rbRating.rating = item.rating.toFloat()
        binding.tvTimeCreated.text = item.timeCreated
        binding.tvRating.text = item.text
    }

}