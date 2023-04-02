package com.example.myapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ListView listView;
    private ArrayList<String> foods ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i(TAG,"this is onCreate");


        Bundle b = getIntent().getExtras();
        // String userName = b.getString("username");

        foods = new ArrayList<>();
        foods.add("Burger");
        foods.add("Fries");
        foods.add("Cheese");
        foods.add("Burger");
        foods.add("Fries");
        foods.add("Cheese");
        foods.add("Burger");
        foods.add("Fries");
        foods.add("Cheese");
        foods.add("Burger");
        foods.add("Fries");
        foods.add("Cheese");
        foods.add("Burger");
        foods.add("Fries");
        foods.add("Cheese");
        foods.add("Milk");
        foods.add("Egg");
        foods.add("Bread");
        foods.add("Cola");
        foods.add("Fish");
        foods.add("Beef");
        foods.add("Salad");
        foods.add("Rice");
        foods.add("Ramen");
        initViews();
    }

    private void initViews(){
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,foods);
        arrayAdapter.notifyDataSetChanged();

        listView.setAdapter(arrayAdapter);
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

