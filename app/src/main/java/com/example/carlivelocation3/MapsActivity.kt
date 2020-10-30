package com.example.carlivelocation3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carlivelocation3.manager.MQTTmanager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.eclipse.paho.android.service.MqttAndroidClient


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var myMarker:Marker
    var topic:String = "gps-1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sandton and move the camera
        val sandton = LatLng(-26.11, 28.05)
        myMarker = mMap.addMarker(
            MarkerOptions()
                .position(sandton)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sandton, 16f))

        // Creates an MQTTmanager object so that the GPS device can communicate with the app
        var mqtt = MQTTmanager(applicationContext, myMarker, mMap, topic)
        mqtt.connect()
    }
}