package com.example.appsiniestralidadkotlin.view.ui.fragments

//import com.google.android.gms.maps.model.Marker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig
import org.osmdroid.views.MapView


class ubicacionFragment  : Fragment(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    lateinit var mapView: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lastKnownLocation: Location? = null

    lateinit var firebaseAuth: FirebaseAuth
    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ubicacion, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_siguiente = view.findViewById<Button>(R.id.btn_avanzar_to_camara)
        btn_siguiente.setOnClickListener {
            findNavController().navigate(R.id.action_ubicacionFragment_to_camaraFragment)
        }

        // google maps
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.mapGoogle) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //---------------------------------------------------

        //Conexion OSM y la App
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID

        // open Street maps
//        mapView = view.findViewById(R.id.mapOpenStreet)
//        mapView.setTileSource(TileSourceFactory.MAPNIK)

        //localizacion con OSM
//        val geoPoint = GeoPoint(5.070275,-75.513817)
//        val mapController = mapView.controller
//        mapController.setZoom(16.0)
//        mapController.setCenter(geoPoint)

        //marcador
//        val marker = Marker(mapView)
//        marker.position = geoPoint
//        marker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_BOTTOM)
//        marker.title = "siniestralidad"
//        mapView.overlays.add(marker)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


    }

    @SuppressLint("MissingPermission")
    fun enableLocation(){
        if(!::googleMap.isInitialized)return
        if (IsLocationPermissionGrated()){
            googleMap.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }
    fun IsLocationPermissionGrated()= ContextCompat.checkSelfPermission(
        this.requireContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED

    fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this.context,"Activar permiso de geolocalizacion",Toast.LENGTH_LONG).show()
        }else{
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                com.example.appsiniestralidadkotlin.view.ui.fragments.ubicacionFragment.Companion.REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onMapReady(map: GoogleMap) {


        map?.let {
            this.googleMap = it

        }
        enableLocation()
        getDeviceLocation(map)


    }
    private fun getDeviceLocation(mMap: GoogleMap) {
        try {
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    // Set the map's camera position to the current location of the device.
                    lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                            LatLng(lastKnownLocation!!.latitude,
                                lastKnownLocation!!.longitude),  16F))

                    }
                } else {

                }
            }
        } catch (e: SecurityException) {

        }
    }

}