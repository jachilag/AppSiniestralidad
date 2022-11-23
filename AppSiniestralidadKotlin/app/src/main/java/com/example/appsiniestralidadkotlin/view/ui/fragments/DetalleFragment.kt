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
import android.widget.TextView
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.properties.Delegates

class DetalleFragment : Fragment(), OnMapReadyCallback {
    lateinit var reportero:String
    lateinit var fecha:String
    lateinit var involucrados:String
    lateinit var reporte: String
    lateinit var txtLatitud:String
    lateinit var txtLongitud:String
    var latitud by Delegates.notNull<Double>()
    var longitud by Delegates.notNull<Double>()
    lateinit var btn_salir: Button
    lateinit var tviewReportero:TextView
    lateinit var tviewReporte:TextView
    lateinit var tviewInvolucrados:TextView
    lateinit var tviewUbicacion:TextView
    lateinit var tviewFecha:TextView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lastKnownLocation: Location? = null



    companion object{
        const val REQUEST_CODE_LOCATION=0
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detalle, container, false)
        btn_salir = view.findViewById(R.id.btn_salir)
        tviewReportero = view.findViewById(R.id.txtDetalleReportero)
        tviewReporte = view.findViewById(R.id.txtDetalleReporte)
        tviewInvolucrados = view.findViewById(R.id.txtDetalleInvolucrados)
        tviewFecha = view.findViewById(R.id.txtDetalleFecha)
//        tviewUbicacion = view.findViewById(R.id.txtDetalleUbicacion)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reportero = arguments?.getString("reportero").toString()
        reporte = arguments?.getString("reporte").toString()
        involucrados = arguments?.getString("involucrados").toString()
        fecha = arguments?.getString("fecha").toString()
        txtLatitud = arguments?.getString("latitud").toString()
        txtLongitud = arguments?.getString("longitud").toString()
        latitud = txtLatitud.toDouble()
        longitud = txtLongitud.toDouble()

        tviewReportero.text = "Reportado por: $reportero"
        tviewReporte.text = "Reporte: $reporte"
        tviewInvolucrados.text = "Afectados: $involucrados"
        tviewFecha.text = fecha
//        tviewUbicacion.text = "$txtLatitud, $txtLongitud"

        btn_salir.setOnClickListener {
            findNavController().navigate(R.id.action_detalleFragment_to_menuFragment)
        }

        // google maps
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.mapGoogleDetalle) as SupportMapFragment
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
                REQUEST_CODE_LOCATION
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
                        marcador(mMap)
                    }
                } else {
                }
            }
        } catch (e: SecurityException) {
        }
    }

    private fun marcador(map: GoogleMap) {
        val centerMark = LatLng(longitud,latitud)
        val cameraPosition = CameraPosition.Builder()
            .target(centerMark)
            .zoom(14f)            // Sets the zoom
            .bearing(0f)         // Sets the orientation of the camera to east
            .tilt(1f)            // Sets the tilt of the camera to 30 degrees
            .build()              // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        val bitmapDraw = context?.applicationContext?.let{
            ContextCompat.getDrawable(it, R.drawable.fire_2)
        } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,90,108,false)
        map.addMarker( MarkerOptions()
            .position(centerMark)
            .title(centerMark.toString())
            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        )
    }

}
