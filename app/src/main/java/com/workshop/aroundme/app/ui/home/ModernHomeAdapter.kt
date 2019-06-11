package com.workshop.aroundme.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.data.model.Place

class ModernHomeAdapter(
    private val onHomePlaceItemClickListener: OnHomePlaceItemClickListener,
    private val onSortDropDownMenuClickListener : OnSortDropDownMenuClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var parentCategories = listOf<ParentCategory>()
        set(value) {
            field = value
            notifyItemChanged(1)
        }

    private var items = listOf<Place>()

    fun updateItems(places: List<Place>){
        items = places
        notifyItemRangeChanged(2, items.size + 2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_SORT_DROP_DOWN_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_sort_dropdown_menu_item, parent, false)
                SortDropDownViewHolder(view)
            }
            ITEM_TYPE_CATEGORIES_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_home_categories_item, parent, false)
                CategoriesViewHolder(view)
            }
            ITEM_TYPE_PLACE_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_place_item, parent, false)
                HomeViewHolder(view)
            }
            else -> {
                throw IllegalStateException("Invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_SORT_DROP_DOWN_ITEM
            1 -> ITEM_TYPE_CATEGORIES_ITEM
            else -> ITEM_TYPE_PLACE_ITEM
        }
    }

    override fun getItemCount() : Int {
        return if(items.isNotEmpty())
            items.size + 2
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_SORT_DROP_DOWN_ITEM ->{
                (holder as SortDropDownViewHolder).bind(onSortDropDownMenuClickListener)
            }
            ITEM_TYPE_CATEGORIES_ITEM -> {
                (holder as CategoriesViewHolder).bind(parentCategories)
            }
            ITEM_TYPE_PLACE_ITEM -> {
                (holder as HomeViewHolder).bind(items[position - 2], onHomePlaceItemClickListener)
            }
        }
    }

    companion object {
        const val ITEM_TYPE_SORT_DROP_DOWN_ITEM = 0
        const val ITEM_TYPE_CATEGORIES_ITEM = 1
        const val ITEM_TYPE_PLACE_ITEM = 2
    }

}