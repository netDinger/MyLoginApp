package com.example.myapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapp.R;
import com.example.myapp.profile.model.ChatModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ListView listView;
    private Spinner chooseFoodType;
    private ArrayList<String> foods ;
    private String[] foodTypes = {"Drinks","Fast Foods","Snacks","Coffee"};

    private ArrayList<ChatModel> chatList = new ArrayList<>();

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

        //MVP -> Model,view,presenter
        //MVVM -> Model,View, ViewModel

        chatList.add(new ChatModel("User1","hello"));
        chatList.add(new ChatModel("User2","Hi"));

        initViews();
    }

    private void initViews(){
        listView = findViewById(R.id.listView);
        chooseFoodType = findViewById(R.id.chooseFoodType);

        //Creating and attaching adapter to listView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,foods);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

        //Creating and attaching adapter to spinner(ChooseFoodType)
        ArrayAdapter<String> chooseFoodAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,foodTypes);
        //chooseFoodAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        chooseFoodType.setAdapter(chooseFoodAdapter);
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

