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
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity
import kotlinx.android.synthetic.main.item_home_categories_item.*
import java.lang.ref.WeakReference

class HomeFragment : Fragment(), OnHomePlaceItemClickListener, HomeContract.View {


    private val homeViewModelFactory by lazy {
        HomeViewModelFactory(
            Injector.providePlaceRepository(requireContext()),
            Injector.provideCategoryRepository()
        )
    }

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private var adapter: ModernHomeAdapter? = null

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
            adapter = ModernHomeAdapter(places, this)
            recyclerView?.adapter = adapter
        })

        homeViewModel.categories.observe(this, Observer {categories->
            adapter?.parentCategories = categories
        })
    }

    override fun showPlaces(places: List<PlaceEntity>) {

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)
        progressBar?.visibility = View.GONE
        adapter = ModernHomeAdapter(places, this)
        recyclerView?.adapter = adapter

    }

    override fun showCategories(categories: List<ParentCategoryEntity>) {
        adapter?.parentCategories = categories
    }

    override fun onPlaceItemCliced(placeEntity: PlaceEntity) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, DetailFragment.newInstance(placeEntity.slug))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onItemStarred(placeEntity: PlaceEntity) {
        presenter.onItemStarred(placeEntity)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

}
