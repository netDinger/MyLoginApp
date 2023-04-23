package com.example.myapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.profile.util.DBHelper;

public class NewItemActivity extends AppCompatActivity {

    private EditText name,phoneNumber;
    private Button save;

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || phoneNumber.getText().toString().equals("")){
                    Toast.makeText(NewItemActivity.this, "Contact shouldn't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    saveContact();
                    Toast.makeText(NewItemActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveContact(){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME,name.getText().toString());
        values.put(DBHelper.COLUMN_PHONE,phoneNumber.getText().toString());
        database.insertOrThrow(DBHelper.TABLE_NAME,null,values);
    }
}