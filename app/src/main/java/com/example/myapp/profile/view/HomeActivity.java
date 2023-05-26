package com.example.myapp.profile.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapp.R;
import com.example.myapp.profile.alarmManager.AlarmManagerService;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private TabHost tabHost;
    private TabHost.TabSpec spec1;
    private TabHost.TabSpec spec2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Log.i(TAG, "this is onCreate");

        //MVP -> Model,view,presenter
        //MVVM -> Model,View, ViewModel

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.e(TAG, "on resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void initViews() {
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        spec1 = tabHost.newTabSpec("FirstView");
        spec2 = tabHost.newTabSpec("SecView");
        spec1.setIndicator("FirstView");
        spec2.setIndicator("SecV");
        spec1.setContent(R.id.firstView);
        spec2.setContent(R.id.secondView);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);

        Button showMenu = findViewById(R.id.showMenu);
        registerForContextMenu(showMenu);

        String[] foods = {"cola", "burger", "egg", "milk"};

        Button showDialog = findViewById(R.id.showDialog);
        showDialog.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Yahoo!");
            builder.setItems(foods, ((dialog, which) -> {
                Toast.makeText(this, "" + foods[which], Toast.LENGTH_SHORT).show();
            }));
            builder.setNegativeButton("Cancel", ((dialog, which) -> {
            }));
            builder.setNeutralButton("OK", (dialog, which) -> {

            });
            builder.show();


        });


        findViewById(R.id.showPopUp).setOnClickListener(v ->{
            startActivity(new Intent(this,FragmentTest.class));
        });


        Intent intent = new Intent();
        intent.setAction("ALARM");
        intent.setClass(this, AlarmManagerService.class);
        //sendBroadcast(intent);

        IntentFilter filter = new IntentFilter();
        filter.addAction("ALARM");
        AlarmManagerService service = new AlarmManagerService();
        registerReceiver(service, filter);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        try {
//            pendingIntent.send();
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }

        long interval = System.currentTimeMillis() + 5000;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, interval, pendingIntent);


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);

        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)  {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            List<String> providers = locationManager.getAllProviders();
            for (String provider : providers) {
                Location location = locationManager.getLastKnownLocation(provider);


                if (location != null) {
                    Toast.makeText(this, "Lat " + location.getLatitude() + " : " + "Long " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error Location ", Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    @SuppressLint("NonConstantResourceId")
    void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.home_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.addItem:
                    Toast.makeText(HomeActivity.this, "add item", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.share:
                    Toast.makeText(HomeActivity.this, "share", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.about:
                    Toast.makeText(HomeActivity.this, "about", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
        popupMenu.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.e(TAG, "onOptionsItemSelected: " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.addItem:
                Toast.makeText(this, "add item", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.home_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                Toast.makeText(this, "add item", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                 ) {
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        }else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
//
//        }
    }
}


//4 application components ---->>>>
//activity lifecycle
//adb commands
//Log (w,d,i,v,e)
//Practice questions
//URI -> Uniform Resource Identifier (content://)





