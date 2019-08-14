package com.example.pr_pro.newbloodapplication.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.helper.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import butterknife.ButterKnife;

public class MapLocationActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;
    private GPSTracker gps;
    public static double latitude, longitude, latitudePoint, longitudePoint;
    private Button directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        directions = findViewById(R.id.directions);
        Clicked();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gps = new GPSTracker(this, this);

        // Check if GPS enabled
        if (gps.getIsGPSTrackingEnabled()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        } else {
            gps.showSettingsAlert();
        }

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        latitudePoint = latitude;
        longitudePoint = longitude;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("MyLocation"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("MyLocation"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                latitude = latLng.latitude;
                longitude = latLng.longitude;

            }
        });


    }

    public void Clicked() {
        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addPolyline(new PolylineOptions().add(new LatLng(latitude, longitude)
                        , new LatLng(latitudePoint, longitudePoint)).width(15).color(getResources().getColor(R.color.blue_normal)).geodesic(true));
            }
        });

    }
//
//    private GoogleMap mMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map_location);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
////        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);
////        mMap.getUiSettings().setZoomControlsEnabled(true);
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")).showInfoWindow();
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,7));
////        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
////            @Override
////            public void onMapClick(LatLng latLng) {
////                Toast.makeText(MapLocationActivity.this, latLng.latitude+""+latLng.longitude, Toast.LENGTH_SHORT).show();
////            }
////        });
//    }
}
