package com.bese3.nauman.citizen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainMenuActivity extends Activity {

    private ImageButton Interact;
    private ImageButton Report;
    private ImageButton Delete;
    private ImageButton Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Interact = (ImageButton) findViewById(R.id.ViewEmergency);
        Report =  (ImageButton) findViewById(R.id.ReportEmergency);
        Delete = (ImageButton) findViewById(R.id.DeleteEmergency);
        Logout = (ImageButton) findViewById(R.id.logoutbtn);

        Interact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

          /*      Toast.makeText(MainMenuActivity.this,
                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();*/
                goToViewEmergency();

            }

        });

        Report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

         /*       Toast.makeText(MainMenuActivity.this,
                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();*/
                goToReportEmergency();

            }

        });

        Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

               /* Toast.makeText(MainMenuActivity.this,
                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();*/
                goToDeleteEmergency();

            }

        });

        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

               /* Toast.makeText(MainMenuActivity.this,
                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();*/
                Toast.makeText(MainMenuActivity.this,
                        "Logout Successful!",
                        Toast.LENGTH_LONG).show();

                goToLoginActivity2();
                //finish();
                onDestroy();

            }

        });


    }

    private void goToViewEmergency()
    {
        Intent intent = new Intent(this,ViewEmergency.class);
        startActivity(intent);
    }

    private void goToReportEmergency()
    {
        Intent intent = new Intent(this,ReportEmergency.class);
        startActivity(intent);
    }

    private void goToDeleteEmergency()
    {
        Intent intent = new Intent(this,DeleteEmergency.class);
        startActivity(intent);
    }

    private void goToLoginActivity2()
    {
        Intent intent = new Intent(this,LoginActivity2.class);
        startActivity(intent);
        finish();
    }

}
