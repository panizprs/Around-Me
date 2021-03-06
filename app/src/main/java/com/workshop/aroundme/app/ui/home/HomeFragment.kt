package com.workshop.aroundme.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.data.model.Place
import dagger.android.support.DaggerFragment
import java.lang.ref.WeakReference
import javax.inject.Inject

class HomeFragment : DaggerFragment(), OnHomePlaceItemClickListener, OnSortDropDownMenuClickListener , HomeContract.View {

    @Inject
    lateinit var homeViewModelFactory : HomeViewModelFactory


    private val homeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private val adapter: ModernHomeAdapter by lazy {
        ModernHomeAdapter(this, this)
    }

//    private val presenter: HomeContract.Presenter by lazy {
//        HomePresenter(
//            Injector.providePlaceRepository(requireContext()),
//            Injector.provideCategoryRepository()
//        ).apply {
//            view = WeakReference(this@HomeFragment)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated")

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        presenter.onActivityCreated()

        println("onActivityCreated")

        homeViewModel.loadHomePage()

        val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)

        homeViewModel.places.observe(this, Observer {places ->
            progressBar?.visibility = View.GONE
            adapter.updateItems(places)
        })

        homeViewModel.categories.observe(this, Observer {categories->
            println(categories.size)
            adapter.parentCategories = categories
        })
    }

    override fun showPlaces(places: List<Place>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)
        progressBar?.visibility = View.GONE
        adapter.updateItems(places)
        recyclerView?.adapter = adapter
    }

    override fun showCategories(categories: List<ParentCategory>) {
        adapter.parentCategories = categories
    }

    override fun onPlaceItemClicked(place: Place) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, DetailFragment.newInstance(place.slug))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onItemSelected(sortType: Int) {
        homeViewModel.onSortSelected(sortType)
    }


    override fun onItemStarred(place: Place) {
//        presenter.onItemStarred(placeEntity)
        homeViewModel.onItemStarred(place)
    }

    override fun onDestroyView() {
//        presenter.onDestroyView()
        super.onDestroyView()
    }



}
