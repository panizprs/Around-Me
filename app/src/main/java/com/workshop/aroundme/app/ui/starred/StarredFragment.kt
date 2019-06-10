package com.workshop.aroundme.app.ui.starred

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector

class StarredFragment : Fragment() {

    private val starredViewModelFactory by lazy {
        StarredViewModelFactory(Injector.providePlaceRepository(requireContext()))
    }
    private val starredViewModel by lazy {
        ViewModelProviders.of(this, starredViewModelFactory)[StarredViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        starredViewModel.onViewCreated()

        starredViewModel.getStarredPlaces().observe(this, Observer {places ->
            recyclerView.adapter = StarredAdapter(places)
        })
    }
}