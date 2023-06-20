package com.app.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.app.R
import com.app.core.Session
import com.app.databinding.HomeActivityBinding
import com.app.db.database.AppDatabase
import com.app.db.entity.LocationDetailsRoom
import com.app.di.component.ActivityComponent
import com.app.ui.base.BaseActivity
import com.fondesa.kpermissions.extension.onAccepted
import com.fondesa.kpermissions.extension.onDenied
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResult
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeActivity : BaseActivity(), OnMapReadyCallback {

    lateinit var binding: HomeActivityBinding

    // below are the latitude and longitude
    // of 4 different locations.
    var sydney = LatLng(29.751260, -95.373639)
    var tamWorth = LatLng(29.752881, -95.374454)
    var newCastle = LatLng(-32.916668, 151.750000)
    var brisbane = LatLng(-27.470125, 153.021072)

    // creating array list for adding all our locations.
    private val locationArrayList: ArrayList<LatLng> = arrayListOf()

    val location by lazy {
        permissionsBuilder(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET
        ).build()
    }

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    @Inject
    lateinit var session: Session

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.API_KEY))
        }

        // Create a new Places client instance.
        val placesClient = Places.createClient(this)

        val supportFragmenetManagr = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        supportFragmenetManagr?.getMapAsync(this)

        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(applicationContext).locationDao.insertLocationRoom(
                LocationDetailsRoom()
            )
            Log.e("LocationDetailsRoom",AppDatabase.getInstance(applicationContext).locationDao.getAll().toString())
        }

        locationArrayList.add(LatLng(25.71687391423798, -100.3730825815284))
        locationArrayList.add(LatLng(25.71682182829175, -100.3733097782636))
        locationArrayList.add(LatLng(25.71678126641878, -100.374090669652))
        locationArrayList.add(LatLng(25.71683201122758, -100.3731781269444))
        locationArrayList.add(LatLng(25.72018196813817, -100.3735084186905))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener {
            // Creating a marker
            val markerOptions = MarkerOptions()
            // Setting the position for the marker
            markerOptions.position(it)
            // Setting the title for the marker.
            // This will be displayed on taping the marker
            markerOptions.title(it.latitude.toString() + " : " + it.longitude)
            // Clears the previously touched position
            mMap.clear()
            // Animating to the touched position
            mMap.animateCamera(CameraUpdateFactory.newLatLng(it))
            // Placing a marker on the touched position
            mMap.addMarker(markerOptions)
        }
        for (i in locationArrayList.indices) {

            // below line is use to add marker to each location of our array list.
            mMap.addMarker(MarkerOptions().position(locationArrayList[i]).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))

            // below line is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList[i]))
        }
        displayLocationSettingsRequest(this)
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingPermission")
    private fun setUpMap() {
        location.onAccepted {
           /* mMap.setOnCameraIdleListener {
                placeMarkerOnMap(mMap.cameraPosition.target)
            }
            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    placeMarkerOnMap(currentLatLng)
                }
            }*/
        }.onDenied {
            dialog()
        }.send()
    }

    private fun placeMarkerOnMap(location: LatLng) {
        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(location.latitude, location.longitude))
                //.title(jsonobject.getString("country"))
                .snippet("4 E. 28TH Street From $15 /per night")
                .rotation(-15.0.toFloat())
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Give Permission")
        builder.setMessage("Are Sure Not Allow Location")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { _, _ ->
            Toast.makeText(applicationContext, "Location Deny", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ ->
            setUpMap()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun displayLocationSettingsRequest(context: Context) {
        val googleApiClient = GoogleApiClient.Builder(context)
            .addApi(LocationServices.API).build()
        googleApiClient.connect()

        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 10000 / 2

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result: PendingResult<LocationSettingsResult> = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())

        result.setResultCallback { p0 ->
            val status: Status = p0.status
            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS -> {
                }
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                    try {
                        status.startResolutionForResult(this@HomeActivity, 1)
                    } catch (e: IntentSender.SendIntentException) {
                    }
                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                }
            }
        }
    }
}
