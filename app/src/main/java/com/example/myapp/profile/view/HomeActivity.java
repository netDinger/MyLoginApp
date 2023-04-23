package com.example.myapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.profile.model.ChatModel;
import com.example.myapp.profile.util.DBHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Spinner chooseFoodType;
    private ImageView add;
    private ArrayList<String> contacts;
    private String[] foodTypes = {"Drinks","Fast Foods","Snacks","Coffee"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contacts = new ArrayList<>();

        Log.i(TAG,"this is onCreate");

        //MVP -> Model,view,presenter
        //MVVM -> Model,View, ViewModel

        initViews();
        readContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, contacts);

        readContacts();
        Log.e(TAG,"on resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        contacts = new ArrayList<>();
        Log.e(TAG,"on restart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        contacts = new ArrayList<>();
        Log.e(TAG,"on start");
    }

    private void initViews(){
        listView = findViewById(R.id.listView);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,NewItemActivity.class));
            }
        });

        chooseFoodType = findViewById(R.id.chooseFoodType);

        //Creating and attaching adapter to listView
       arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, contacts);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteContacts(contacts.get(position));
                return false;
            }
        });

        //Creating and attaching adapter to spinner(ChooseFoodType)
        ArrayAdapter<String> chooseFoodAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,foodTypes);
        //chooseFoodAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        chooseFoodType.setAdapter(chooseFoodAdapter);
    }




    private void readContacts(){

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()){
                contacts.add(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME)));
                //cursor.moveToNext();
            }
            arrayAdapter.notifyDataSetChanged();

            if (!cursor.isClosed()) {
                cursor.close();
            }

//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }

    private void deleteContacts(String name){
        try{
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete(DBHelper.TABLE_NAME,"Name="+name,null);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}

