package com.bese3.nauman.citizen;

/**
 * Created by Nauman on 1/21/2015.
 */


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.bese3.nauman.citizen.data.AppController;
import com.bese3.nauman.citizen.data.CustomRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class DeleteEmergency extends FragmentActivity {
    private GoogleMap map;
    private final LatLng coordinate = new LatLng(10.0,11.1);
    private LocationManager locationManager;
    private String provider;
    private String emergencyTitle;
    private String emergencyType;
    boolean markerClicked;

    private Location location;
    private Marker myMarker;

    String userID="";


/*    OnCameraChangeListener() {
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(33.6667, 73.1667));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

            map.moveCamera(center);
            map.animateCamera(zoom);
    }*/

    public void vollry() {
        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST,
                "http://citizenemergency.web44.net/response.php", null,//ip
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int t=response.getInt("query");
                            JSONArray s=response.getJSONArray("data");

                            for(int i = 0; i < s.length(); i++) {
                                JSONObject as = s.getJSONObject(i);
                                String longitude = as.getString("longitude");
                                String lattitude = as.getString("lattitude");
                                String description = as.getString("description");
                                String type = as.getString("emergencytype");
                                String peopleeffected = as.getString("peopleeffected");
                                String date = as.getString("edate");

                                LatLng temp = new LatLng(Double.parseDouble(lattitude),Double.parseDouble(longitude));

                                drawMarkers(description, type, temp, date, peopleeffected);

                               /* if(type=="fire") {
                                    myMarker = map.addMarker(new MarkerOptions()
                                            .position(temp)
                                            .title(description)
                                            .snippet(description + "  " + type + "  " + lattitude + "," + longitude)
                                            .draggable(true)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_fire)));
                                }

                                else if (type=="health"){
                                    myMarker = map.addMarker(new MarkerOptions()
                                            .position(temp)
                                            .title(description)
                                            .snippet(description + "  " + type + "  " + lattitude + "," + longitude)
                                            .draggable(true)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_health)));
                                }

                                else if (type=="crime"){
                                    myMarker = map.addMarker(new MarkerOptions()
                                            .position(temp)
                                            .title(description)
                                            .snippet(description + "  " + type + "  " + lattitude + "," + longitude)
                                            .draggable(true)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_crime)));
                                }

                                else {
                                    myMarker = map.addMarker(new MarkerOptions()
                                            .position(temp)
                                            .title(description)
                                            .snippet(description + "  " + type + "  " + lattitude + "," + longitude)
                                            .draggable(true)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                                }*/


                               /* Toast.makeText(DeleteEmergency.this,
                                        longitude,
                                        Toast.LENGTH_LONG).show();*/
                            }


                           /* else
                            {
                                Toast.makeText(ViewEmergency.this,
                                        "Invalid Username Or Password",
                                        Toast.LENGTH_LONG).show();
                            }*/

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(DeleteEmergency.this,
                                    "connected but not working",
                                    Toast.LENGTH_LONG).show();

                        }
						/*try {

							}
						/*else {
								Toast.makeText(MainActivity.this,
										response.getString("message"),
										Toast.LENGTH_LONG).show();
							}
						 catch (JSONException e) {
							Log.d("checking", e.toString());
							Toast.makeText(MainActivity.this, e.toString(),
									Toast.LENGTH_LONG).show();

						}*/
//						pDialog.hide();
//						load_to_view();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ddddd", "Error: " + error.getMessage());
                Toast.makeText(DeleteEmergency.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {



                Map<String, String> params = new HashMap<String, String>();
                params.put("myqueery", "3");
                params.put("id",userID);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "application/x-www-form-urlencoded");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_emergency);
        userID=getSharedPreferences("MyPrefs",MODE_PRIVATE).getString("userid","");
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

/*
        delete = (Button) findViewById(R.id.delete_button);

        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //location = locationManager.getLastKnownLocation(provider);
                myMarker.remove();
            }

        });*/

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabledGPS = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean enabledWiFi = service
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // Check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabledWiFi) {
            Toast.makeText(this, "WiFi signal not found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            Toast.makeText(this, "Selected Provider " + provider,
                    Toast.LENGTH_SHORT).show();
            //onLocationChanged(location);
        } else {

            //do something
        }

        MapsInitializer.initialize(getApplicationContext());


        // Setting click event handler for InfoWIndow
        map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                // Remove the marker
                myMarker.remove();
            }
        });

        vollry();
      /*  for(int x = 1; x < 4; x = x+1) {
            LatLng temp = new LatLng(coordinate.latitude*x, coordinate.longitude*x);
            drawMarkers("Test"+x, "Fire", temp);
        }
*/
    }


    public void drawMarkers(String emergencyTitle,  String emergencyType, LatLng coordinate, String Date, String People) {
        if(emergencyType.compareTo("fire")==0) {
            myMarker = map.addMarker(new MarkerOptions()
                    .position(coordinate)
                    .title(emergencyTitle)
                    .snippet("People Effected:" + People + "  " + Date + "  " + coordinate.latitude + "," + coordinate.longitude)
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_fire)));

            map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker marker) {
                    // Remove the marker
                    myMarker.remove();
                }
            });



        }

        else if(emergencyType.compareTo("health")==0) {
            myMarker = map.addMarker(new MarkerOptions()
                    .position(coordinate)
                    .title(emergencyTitle)
                    .snippet("People Effected:"+ People + "  " + Date + "  " + coordinate.latitude + "," + coordinate.longitude)
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_health)));

        }

        else if(emergencyType.compareTo("crime")==0) {
            myMarker = map.addMarker(new MarkerOptions()
                    .position(coordinate)
                    .title(emergencyTitle)
                    .snippet("People Effected:"+ People + "  " + Date + "  " + coordinate.latitude + "," + coordinate.longitude)
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_crime)));
        }

        else {
            myMarker = map.addMarker(new MarkerOptions()
                    .position(coordinate)
                    .title(emergencyTitle)
                    .snippet("People Effected:"+ People + "  " + Date + "  " + coordinate.latitude + "," + coordinate.longitude)
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        }
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        //locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        //locationManager.removeUpdates(this);
    }
}
