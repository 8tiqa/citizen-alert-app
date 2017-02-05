/**
 * Created by Nauman on 1/20/2015.
 */
package com.bese3.nauman.citizen;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ReportEmergency extends FragmentActivity {
    private GoogleMap map;
    private static final LatLng ROMA = new LatLng(42.093230818037,11.7971813678741);
    private LocationManager locationManager;
    private String provider;
    private Location location;
    private Marker myMarker;
    Button fire, crime, health;
    public String emergencyType = "none";

    GPSTracker gps;

    String userID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_emergency);
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        userID=getSharedPreferences("MyPrefs",MODE_PRIVATE).getString("userid","");
        Button report = (Button) findViewById(R.id.report_button);

        report.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportEmergency.this, EnterEmergencyDetails.class);
                Bundle myBundle=new Bundle();
                myBundle.putString("longitude",location.getLongitude()+"");
                myBundle.putString("latitude", location.getLatitude() + "");
                myBundle.putString("type", emergencyType);
                intent.putExtras(myBundle);
                startActivity(intent);
            }

        });

        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

             @Override
             public void onMarkerDragStart(Marker marker) {
             // TODO Auto-generated method stub
             // Here your code
             Toast.makeText(ReportEmergency.this, "Dragging Start",
             Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onMarkerDragEnd(Marker myMarker) {
             LatLng position = myMarker.getPosition(); //
             location.setLatitude(position.latitude);
             location.setLongitude(position.longitude);
             Toast.makeText(
             ReportEmergency.this,
             "Lat " + position.latitude + " "
             + "Long " + position.longitude,
             Toast.LENGTH_LONG).show();
             }

             @Override
             public void onMarkerDrag(Marker marker) {
             // TODO Auto-generated method stub
             // Toast.makeText(MainActivity.this, "Dragging",
             // Toast.LENGTH_SHORT).show();
             System.out.println("Draging");
             }
          });

        fire = (Button) findViewById(R.id.report_fire);

        fire.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // location = locationManager.getLastKnownLocation(provider);
                addFireMarker(location);
            }

        });

        crime = (Button) findViewById(R.id.report_crime);

        crime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //location = locationManager.getLastKnownLocation(provider);
                addCrimeMarker(location);
            }

        });

        // Setting click event handler for InfoWIndow
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                // Remove the marker
                marker.remove();
            }
        });



        health = (Button) findViewById(R.id.report_health);

        health.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //location = locationManager.getLastKnownLocation(provider);
                addHealthMarker(location);
            }

        });
        gps = new GPSTracker(getApplicationContext());

        // check if GPS enabled
        if(gps.canGetLocation()){
            location=gps.location;
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            viewMap(location);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            Toast.makeText(getApplicationContext(),"Location not found",Toast.LENGTH_SHORT);
            //gps.showSettingsAlert();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //locationManager.removeUpdates(this);
        finish();
    }



    public void viewMap(Location location) {
        double lat =  location.getLatitude();
        double lng = location.getLongitude();
        /*Toast.makeText(this, "Location " + lat+","+lng,
                Toast.LENGTH_LONG).show();*/
        LatLng coordinate = new LatLng(lat, lng);
        /*Toast.makeText(this, "Location " + coordinate.latitude+","+coordinate.longitude,
                Toast.LENGTH_LONG).show();*/
        myMarker = map.addMarker(new MarkerOptions()
                .position(coordinate)
                .title("I'm here")
                .snippet("My Location")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

    }

    public void addFireMarker(Location location) {

        LatLng latlng = myMarker.getPosition();
        myMarker.remove();
        myMarker = map.addMarker(new MarkerOptions()
                .position(latlng)
                .title("I'm here")
                .snippet("My Location")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_fire)));
        emergencyType = "fire";

        //(BitmapDescriptorFactory.fromResource(R.drawable.marker_fire));
    }

    public void addCrimeMarker(Location location) {
        //myMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_crime));
        LatLng latlng = myMarker.getPosition();
        myMarker.remove();
        myMarker = map.addMarker(new MarkerOptions()
                .position(latlng)
                .title("I'm here")
                .snippet("My Location")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_crime)));
        emergencyType = "crime";

    }

    public void addHealthMarker(Location location) {
        //myMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_health));

        LatLng latlng = myMarker.getPosition();
        myMarker.remove();
        myMarker = map.addMarker(new MarkerOptions()
                .position(latlng)
                .title("I'm here")
                .snippet("My Location")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_health)));
        emergencyType = "health";

    }
}



