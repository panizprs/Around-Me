package com.workshop.aroundme.app.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceEntity

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.name)

    fun bind(placeEntity: PlaceEntity) {
        nameTextView.text = placeEntity.name
    }
}