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
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.data.model.Place
import java.lang.ref.WeakReference

class HomeFragment : Fragment(), OnHomePlaceItemClickListener, HomeContract.View {


    private val homeViewModelFactory by lazy {
        HomeViewModelFactory(
            Injector.providePlacesUseCase(requireContext()),
            Injector.provideStarPlaceUseCase(requireContext()),
            Injector.provideCategoryUseCase()
        )
    }

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private var adapter = ModernHomeAdapter(this)

    private val presenter: HomeContract.Presenter by lazy {
        HomePresenter(
            Injector.providePlaceRepository(requireContext()),
            Injector.provideCategoryRepository()
        ).apply {
            view = WeakReference(this@HomeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        presenter.onActivityCreated()

        homeViewModel.loadHomePage()

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)

        homeViewModel.places.observe(this, Observer {places ->
            progressBar?.visibility = View.GONE
            adapter.items = places
            println("adapter : " + adapter.parentCategories.size)
            recyclerView?.adapter = adapter
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
        adapter = ModernHomeAdapter(this)
        adapter.items = places
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

    override fun onItemStarred(place: Place) {
//        presenter.onItemStarred(placeEntity)
        homeViewModel.onItemStarred(place)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

}
