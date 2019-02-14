package com.workshop.aroundme.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.workshop.aroundme.R
import com.workshop.aroundme.data.PlaceRepository
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.PlaceDataSource
import com.workshop.aroundme.remote.service.PlaceService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val placeRepository = PlaceRepository(PlaceDataSource(PlaceService(NetworkManager())))
        placeRepository.getFeaturedPlaces(::onFeaturedPlacesReady)

    }

    private fun onFeaturedPlacesReady(list: List<PlaceEntity>?) = runOnUiThread {
        Toast.makeText(this, list.toString(), Toast.LENGTH_LONG).show()
    }
}
