package com.bese3.nauman.citizen;

/**
 * Created by Nauman on 1/20/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.bese3.nauman.citizen.data.AppController;
import com.bese3.nauman.citizen.data.CustomRequest;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by personal on 1/12/2015.
 */


public class EnterEmergencyDetails extends Activity {
    private EditText location;
    private EditText people;
    private EditText details;
    private EditText e_date;
    private EditText longitude;
    private EditText latitude;
    private EditText emergencyType;

    String userID="";

    Double get_latitude=0.0;
    Double get_longitude=0.0;
    String type="";

    public void vollry() {
        CustomRequest jsonObjReq = new CustomRequest(Method.POST,
                "http://citizenemergency.web44.net/response.php", null,//ip
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            int t=response.getInt("query");
                           // String s=response.getString("data");


                                Toast.makeText(EnterEmergencyDetails.this,
                                        "Database Updated",
                                        Toast.LENGTH_LONG).show();

                                goToMainMenuActivity();


                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(EnterEmergencyDetails.this,
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
                Toast.makeText(EnterEmergencyDetails.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {



                Map<String, String> params = new HashMap<String, String>();
                params.put("myqueery", "2");
                params.put("userid",userID);
                params.put("type",type);
                params.put("description",details.getText().toString());
                params.put("locationname",location.getText().toString());
                params.put("lattitude",get_latitude+"");
                params.put("longitude",get_longitude+"");
                params.put("edate",e_date.getText().toString());
                params.put("peopleeffected",people.getText().toString());

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_emergency_details);
        userID=getSharedPreferences("MyPrefs",MODE_PRIVATE).getString("userid","");

        Intent myIntent = getIntent();
        Bundle myBundle=myIntent.getExtras();
        get_latitude=Double.parseDouble(myBundle.get("latitude").toString());
        get_longitude=Double.parseDouble(myBundle.get("longitude").toString());
        type=myBundle.get("type").toString();
        final String longitude = myIntent.getStringExtra("longitude");
        final String latitude = myIntent.getStringExtra("latitude");
        final String emergencyType = myIntent.getStringExtra("emergencyType");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/DD HH:mm:ss");
        Date date = new Date();
        final String emergencyTime = dateFormat.format(date);
        System.out.println(dateFormat.format(date));

       /* TimePicker time = (TimePicker) findViewById(R.id.timePicker);
        final int hour = time.getCurrentHour();
        final int min = time.getCurrentMinute();
*/

        //DatePicker date = (DatePicker) findViewById(R.id.datePicker);

        /*Date date=Calendar.getInstance().getTime();
        System.out.println(date);

        final Calendar c = Calendar.getInstance();
        final int year, month, day;
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        */

        location = (EditText) findViewById(R.id.emergency_location);
        people = (EditText) findViewById(R.id.people_affected);
        details = (EditText) findViewById(R.id.emergency_details);
        e_date = (EditText) findViewById(R.id.Date);
        /*longitude= (EditText) findViewById(R.id.longitude);
        latitude = (EditText) findViewById(R.id.latitude);
        emergencyType = (EditText) findViewById(R.id.emergencyType);*/


        final EditText people = (EditText) findViewById(R.id.people_affected);

        final EditText details = (EditText) findViewById(R.id.emergency_details);

        Button report = (Button) findViewById(R.id.enter_emergency_details);

        report.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final String locationName = location.getText().toString();
                final String peopleAffected = people.getText().toString();
                final String emergencyDetails = details.getText().toString();

                Toast.makeText(EnterEmergencyDetails.this,
                        "Feedback Submitted!", Toast.LENGTH_SHORT).show();

                // display(locationName+peopleAffected+emergencyDetails+hour+min);
                vollry();
                goToMainMenuActivity();

            }
        });

    }

    public void display(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();

    }

    private void goToMainMenuActivity()
    {
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }


}