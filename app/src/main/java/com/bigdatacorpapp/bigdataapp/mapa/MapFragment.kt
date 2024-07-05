package com.cibertec.cibertecapp.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bigdatacorpapp.bigdataapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R .layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //referenciando el fragmento de mapa creado
        val fragmentMap = childFragmentManager.findFragmentById(R.id.fragmentMap)
            as SupportMapFragment
        fragmentMap.getMapAsync(this)
    }

    companion object{
        fun newInstance(): MapFragment = MapFragment()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.style_map))

        val marker = LatLng(-8.105742832163953, -79.07425305158458)
        map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                .position(marker)
                .title("BigData")
            )
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(marker, 18f)
        )
    }
}