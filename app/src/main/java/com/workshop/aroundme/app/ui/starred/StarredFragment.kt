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
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.app.ui.home.HomeAdapter
import com.workshop.aroundme.app.ui.home.OnHomePlaceItemClickListener
import com.workshop.aroundme.data.model.Place
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StarredFragment : DaggerFragment(), OnHomePlaceItemClickListener {

    @Inject
    lateinit var starredViewModelFactory : StarredViewModelFactory


    private val starredViewModel by lazy {
        ViewModelProviders.of(this, starredViewModelFactory)[StarredViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingBar = view.findViewById<View>(R.id.loadingBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        starredViewModel.onViewCreated()

        starredViewModel.getStarredPlaces().observe(this, Observer {places ->
            recyclerView.adapter = HomeAdapter(places, this)
            loadingBar.visibility = View.GONE
        })
    }

    override fun onPlaceItemClicked(place: Place) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, DetailFragment.newInstance(place.slug))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onItemStarred(place: Place) {
//        presenter.onItemStarred(placeEntity)
        starredViewModel.onItemStarred(place)

    }
}