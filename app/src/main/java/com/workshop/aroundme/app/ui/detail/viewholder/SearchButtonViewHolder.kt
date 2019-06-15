package com.workshop.aroundme.app.ui.detail.viewholder

import android.app.SearchManager
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceDetail

class SearchButtonViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val searchButton = itemView.findViewById<Button>(R.id.searchButton)

    fun bind(placeDetail: PlaceDetail){
        searchButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, placeDetail.name)
            itemView.context.startActivity(intent)
        }
    }
}