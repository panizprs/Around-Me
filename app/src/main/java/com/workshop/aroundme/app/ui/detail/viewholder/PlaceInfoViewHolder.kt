package com.workshop.aroundme.app.ui.detail.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceDetail

class PlaceInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.name)
    private val categories = itemView.findViewById<TextView>(R.id.categories)
    private val address = itemView.findViewById<TextView>(R.id.address)

    fun bind(placeDetail: PlaceDetail) {
        name.text = placeDetail.name
        categories.text = placeDetail.categories
        address.text = placeDetail.address
    }
}