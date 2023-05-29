package com.example.unimate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

//hata veriyor
class MapFragment : Fragment() {//, OnMapReadyCallback {
    /*private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapContainer = view.findViewById<FrameLayout>(R.id.mapContainer)
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(mapContainer.id, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val istanbul = LatLng(41.0082, 28.9784)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(istanbul, 12f))

        // Harita üzerine bir işaretçi (marker) ekleyelim
        val markerOptions = MarkerOptions()
            .position(istanbul)
            .title("İstanbul")
        googleMap.addMarker(markerOptions)
    }
*/
}