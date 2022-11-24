package com.example.appsiniestralidadkotlin.view.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.firestore.FirebaseFirestore


class configuracionesFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var googleMap: GoogleMap
    lateinit var btnRegresar: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lastKnownLocation: Location? = null
    val db = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_configuraciones, container, false)
        btnRegresar = view.findViewById(R.id.btn_regresar_mapa)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_configuracionesFragment_to_menuFragment)
        }

        // google maps
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.mapGoogleSiniestros) as SupportMapFragment
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
            Toast.makeText(this.context,"Activar permiso de geolocalizacion", Toast.LENGTH_LONG).show()
        }else{
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                ubicacionFragment.REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map?.let {this.googleMap = it}
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
                            .zoom(11f)            // Sets the zoom
                            .bearing(0f)         // Sets the orientation of the camera to east
                            .tilt(1f)            // Sets the tilt of the camera to 30 degrees
                            .build()              // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                       ponerMarcadores(mMap)
                    }
                } else {
                }
            }
        } catch (e: SecurityException) {
        }
    }

    private fun ponerMarcadores(mMap: GoogleMap) {
        db.collection("siniestros").get().addOnSuccessListener {result->
            for (document in result) {
                var info = document.data
                var latitud = (info["latitud"] as String).toDouble()
                var longitud = (info["longitud"] as String).toDouble()
                marcador(mMap,latitud,longitud,document.id)
            }
        }
    }

    private fun marcador(map:GoogleMap,latitud: Double,longitud:Double,idDoc:String) {
        val centerMark = LatLng(latitud,longitud)
        val bitmapDraw = context?.applicationContext?.let{
            ContextCompat.getDrawable(it,R.drawable.fire_2)
        } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,90,108,false)
        map.addMarker( MarkerOptions()
            .position(centerMark)
            .title(idDoc)
            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        )
        map.setOnMarkerClickListener(this,)

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val idDoc = marker.title
        val bundle = bundleOf(
            "idDoc" to idDoc
        )
        findNavController().navigate(R.id.action_configuracionesFragment_to_detalleMapFragment,bundle)
        return true
    }

    private fun ventana(mensaje:String){
        val builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Gracias por reportar")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Ok"){
                dialog,which->
        }
        builder.show()
    }

}