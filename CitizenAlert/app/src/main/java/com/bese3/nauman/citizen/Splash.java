package com.bese3.nauman.citizen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Handler;

/**
 * Created by Nauman on 1/9/2015.
 */
public class Splash extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 3000; // time to display the splash screen in ms



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(Splash.this,
                            LoginActivity2.class));
                    finish();
                }
            };
        };
        splashTread.start();
    }
}

