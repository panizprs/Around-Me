package com.workshop.aroundme.app.ui.home


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R.*

class SortDropDownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val dropdown = itemView.findViewById<Spinner>(id.spinner1)

    fun bind(onSortDropDownMenuClickListener: OnSortDropDownMenuClickListener) {
        val items = arrayOf("پیش فرض", "تعداد لایک ها", "تعداد بازدید ها")
        val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_dropdown_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = adapter
//        dropdown.onItemSelectedListener = onItemSelectedListener
//        dropdown.setSelection(0)

        dropdown.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            viewModel.onSortBasedOnLikesTapped()

                println("sdjhskd: $position")
                onSortDropDownMenuClickListener.onItemSelected(position)
                println("35678")
            }
        }

    }

}