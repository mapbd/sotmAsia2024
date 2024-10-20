package org.map_bd.sotmasia2024



import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.map_bd.sotmasia2024.databinding.ActivityMapBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay



class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding

    private lateinit var mMap: MapView
    private lateinit var myLocationOverlay: MyLocationNewOverlay
    private lateinit var mapController: IMapController




    private var currentPoint: GeoPoint? = null
    private var permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            requestLocationUpdate()
            Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Denied!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mMap = binding.streetMapView

        initializeOSM()







    }

    private fun initializeOSM() {
        Configuration.getInstance()
            .load(this, getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE))
        mMap.setMultiTouchControls(true)
        myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), mMap)
        mapController = mMap.controller


        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            requestLocationUpdate()
        }
    }


    private fun requestLocationUpdate() {
        myLocationOverlay.enableMyLocation()
        myLocationOverlay.enableFollowLocation()
        myLocationOverlay.isDrawAccuracyEnabled = true

        myLocationOverlay.runOnFirstFix {
            GlobalScope.launch(Dispatchers.Main) {
                currentPoint = myLocationOverlay.myLocation
                mapController.setCenter(currentPoint)
                mapController.animateTo(currentPoint)

                mMap.overlays.add(myLocationOverlay)
                mapController.setZoom(19.0)

                updateTextView(currentPoint!!)
            }
        }
    }

    private fun updateTextView(geoPoint: GeoPoint) {
        val latitude = geoPoint.latitude
        val longitude = geoPoint.longitude
        binding.tvLatLng.text = "Latitude: $latitude, Longitude: $longitude"
    }





}