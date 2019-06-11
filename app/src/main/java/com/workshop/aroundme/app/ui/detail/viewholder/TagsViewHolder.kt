package com.workshop.aroundme.app.ui.detail.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceDetail

class TagsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tags = itemView.findViewById<TextView>(R.id.tags)

    fun bind(placeDetail: PlaceDetail) {
        tags.text = placeDetail.tags
    }
}