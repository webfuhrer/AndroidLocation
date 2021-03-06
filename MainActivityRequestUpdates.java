package com.example.localizacion_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationListener;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            if (requestCode == 1) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED)
                    {
                        LocationManager lc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        lc.requestLocationUpdates("gps", 10, 0, new LocationListener() {
                            @Override
                            public void onLocationChanged(@NonNull Location location) {
                                Log.d("LOCALIZACION", location.getLatitude()+", "+location.getLongitude());
                            }
                        });
                       /* List<String> proveedores_activados=lc.getProviders(true);
                        Log.d("PROVEEDORES", proveedores_activados.toString());*/

                       /* Location loc = lc.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                        String posicion=loc.getLatitude()+","+loc.getLongitude();
                        Log.d("posicion", posicion);*/
                    }
                }
            }
        }

        @SuppressLint("NewApi")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            chekearPermiso();
        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        public void chekearPermiso()
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            }
        }
    }
