package com.easv.myfriends.myfriends;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by User on 07-04-2018.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Activity's TAG used for Log
    private static final String TAG = "MapActivity";
    //Strings for checking permissions created out of Manifest permissions
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private Boolean mLocationPermissionsGranted = false; //Initial permission grant state for accessing device's location
    private GoogleMap mMap; //Instance of GoogleMap

    //Accessing GoogleMap by implementing OnMapReadyCallback interface
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        //Checking permissions (Manifest)
        if (mLocationPermissionsGranted) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getLocationPermission();
    }

    //GoogleMap intialization
    private void initMap(){
        Log.d(TAG, "GoogleMap: initializing");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    //Accessing device's location permission
    private void getLocationPermission(){
        Log.d(TAG, "LocationPermission: getting permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        //Permission has been granted
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else
        //Case where permission hasn't been granted
        {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        //Checking location permission using request code
        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:
                {
                    //If permissions have been granted, the array won't be empty
                    if(grantResults.length > 0) {
                    //Checking if permission was granted for each of grant results
                    for(int i = 0; i < grantResults.length; i++)
                        {
                            if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                        mLocationPermissionsGranted = true;
                        //Permission was granted; initializing GoogleMap
                        initMap();
                    }
                }
        }
    }

}
