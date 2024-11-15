package org.map_bd.sotmasia2024



import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
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
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        mMap = binding.streetMapView

        initializeOSM()


        val rotationGestureOverlay = RotationGestureOverlay(mMap)
        rotationGestureOverlay.isEnabled
        mMap.setMultiTouchControls(true)
        mMap.overlays.add(rotationGestureOverlay)


        val dm : DisplayMetrics = this.resources.displayMetrics
        val scaleBarOverlay = ScaleBarOverlay(mMap)
        scaleBarOverlay.setCentred(true)
//play around with these values to get the location on screen in the right place for your application
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10)
        mMap.overlays.add(scaleBarOverlay)


        val compassOverlay = CompassOverlay(this, InternalCompassOrientationProvider(this), mMap)
        compassOverlay.enableCompass()
        mMap.overlays.add(compassOverlay)

        val marker = Marker(mMap)
        val geoPoint = GeoPoint(21.425975, 91.973977)
        marker.position = geoPoint
        marker.icon = ContextCompat.getDrawable(this, R.drawable.state_of_the_map_asia_logo)
        marker.title = "SotM Asia"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        mMap.overlays.add(marker)
        mMap.invalidate()



    binding.direction.setOnClickListener {

        //var mylocation =binding.tvLatLng.text

        val gmmIntentUri =
            Uri.parse("google.navigation:q=21.42607900785744,91.97393549532941&avoid=tf")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)




    }



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
        binding.tvLatLng.text = "$latitude,$longitude"
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }

        }
        return true
    }


}