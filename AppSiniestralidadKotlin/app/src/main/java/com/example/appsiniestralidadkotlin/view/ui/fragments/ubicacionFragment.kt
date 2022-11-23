package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsiniestralidadkotlin.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import org.osmdroid.views.MapView


class ubicacionFragment  : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    lateinit var mapView: MapView

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lastKnownLocation: Location? = null
    var geoPosicion: MutableList<Double> = mutableListOf()
    lateinit var latitud : String
    lateinit var longitud: String

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubicacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val involucrados = arguments?.getString("involucrados")
        val btn_siguiente = view.findViewById<Button>(R.id.btn_avanzar_to_camara)
        btn_siguiente.setOnClickListener {
            val bundle = bundleOf(
                "involucrados" to involucrados,
                "latitud" to latitud,
                "longitud" to longitud,
            )
            findNavController().navigate(R.id.action_ubicacionFragment_to_camaraFragment,bundle)
        }

        // google maps
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.mapGoogle) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map?.let {this.googleMap = it}
//        map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(),R.raw.custom_map))
        enableLocation()
        getDeviceLocation(map)
    }

    fun getDeviceLocation(mMap: GoogleMap){
        try {
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        val cameraPosition = CameraPosition.Builder()
                            .target(LatLng(lastKnownLocation!!.latitude,lastKnownLocation!!.longitude))
                            .zoom(16f)            // Sets the zoom
                            .bearing(0f)         // Sets the orientation of the camera to east
                            .tilt(1f)            // Sets the tilt of the camera to 30 degrees
                            .build()              // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                        geoPosicion.add(0,lastKnownLocation!!.latitude)
                        geoPosicion.add(1,lastKnownLocation!!.longitude)
                        latitud = lastKnownLocation!!.latitude.toString()
                        longitud = lastKnownLocation!!.longitude.toString()
                        marcador(mMap)
                    }
                } else {
                }
            }
        } catch (e: SecurityException) {
        }
    }

    private fun marcador(map:GoogleMap) {
        val centerMark = LatLng(geoPosicion.get(0), geoPosicion.get(1))
        val bitmapDraw = context?.applicationContext?.let{
            ContextCompat.getDrawable(it,R.drawable.fire_2)
        } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,90,108,false)
        map.addMarker( MarkerOptions()
            .position(centerMark)
            .title(centerMark.toString())
            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        )
    }

}






//        ---------------------------------------------------

//Conexion OSM y la App
//        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID

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