package com.workshop.aroundme.app.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.Place

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.name)
    private val addressTextView = itemView.findViewById<TextView>(R.id.address)
    private val likesTextView = itemView.findViewById<TextView>(R.id.likes)
    private val likesContainer = itemView.findViewById<View>(R.id.likesContainer)
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val favorite = itemView.findViewById<ImageView>(R.id.favorite)

    fun bind(
        place: Place,
        onHomePlaceItemClickListener: OnHomePlaceItemClickListener
    ) {
        nameTextView.text = place.name
        addressTextView.text = place.address
        likesTextView.text = place.likes.toString()

        Picasso.get().load(place.imageUrl).into(image)

        likesContainer.visibility = if (place.likes != null && place.likes > 0) {
            View.VISIBLE
        } else {
            View.GONE
        }

        if (place.isFavorite) {
            favorite.setImageResource(R.drawable.ic_star_on)
        } else {
            favorite.setImageResource(R.drawable.ic_star_off)
        }

        itemView.setOnClickListener {
            onHomePlaceItemClickListener.onPlaceItemClicked(place)
        }

        favorite.setOnClickListener {
            if (!place.isFavorite) {
                favorite.setImageResource(R.drawable.ic_star_on)
                place.isFavorite = true

                onHomePlaceItemClickListener.onItemStarred(place)
            } else {
                favorite.setImageResource(R.drawable.ic_star_off)
                place.isFavorite = false

                onHomePlaceItemClickListener.onItemStarred(place)
            }
        }
    }
}