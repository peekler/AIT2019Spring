package hu.ait.android.aitmapdemo

import android.graphics.Color
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    MyLocationProvider.OnNewLocationAvailable {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnNormal.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear()
        }
        btnSatellite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(47.0, 19.0))
                .zoom(17f)
                .bearing(90f)
                .tilt(30f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                cameraPosition))


        }
    }

    private lateinit var myLocationProvider: MyLocationProvider

    override fun onStart() {
        super.onStart()
        startLocation()
    }

    @WithPermissions(
        permissions = [android.Manifest.permission.ACCESS_FINE_LOCATION]
    )
    fun startLocation() {
        myLocationProvider = MyLocationProvider(this,
            this)
        myLocationProvider.startLocationMonitoring()
    }


    override fun onStop() {
        super.onStop()
        myLocationProvider.stopLocationMonitoring()
    }

    override fun onNewLocation(location: Location) {
        tvData.text =
                "Loc: ${location.latitude}, ${location.longitude}"
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val marker = LatLng(47.0, 19.0)
        mMap.addMarker(MarkerOptions().position(marker).title("Marker in Hungary"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))

        mMap.isTrafficEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE


        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true


        mMap.setOnMapClickListener {
            val markerOpt = MarkerOptions().
                    position(it).
                    title("My marker ${it.latitude}, ${it.longitude}")
            val marker = mMap.addMarker(markerOpt)
            marker.isDraggable = true


            mMap.animateCamera(CameraUpdateFactory.newLatLng(it))
        }


        mMap.setOnMarkerClickListener {
            Toast.makeText(this@MapsActivity, it.title,
                Toast.LENGTH_LONG).show()

            true
        }


        val polyRect: PolygonOptions = PolygonOptions().add(
            LatLng(44.0, 19.0),
            LatLng(44.0, 26.0),
            LatLng(48.0, 26.0),
            LatLng(48.0, 19.0))
        val polygon: Polygon = mMap.addPolygon(polyRect)
        polygon.fillColor = Color.GREEN
    }
}
