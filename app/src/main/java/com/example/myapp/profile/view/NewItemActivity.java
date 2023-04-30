package com.example.myapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.profile.util.DBHelper;
import com.example.myapp.profile.util.MyContentProvider;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NewItemActivity extends AppCompatActivity {

    private static final String TAG = "NewItemActivity";

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
                    //saveContact();
                    saveToCP();
                    Toast.makeText(NewItemActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });

       // getNetworkData();

    }

    private void saveContact(){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME,name.getText().toString());
        values.put(DBHelper.COLUMN_PHONE,phoneNumber.getText().toString());
        database.insertOrThrow(DBHelper.TABLE_NAME,null,values);
    }

    private void saveToCP(){
        MyContentProvider cp = new MyContentProvider(this);
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME,name.getText().toString());
        values.put(DBHelper.COLUMN_PHONE,phoneNumber.getText().toString());

        cp.insert(MyContentProvider.CONTACT_URI,values);
    }


    public String getNetworkData(){
        try {
            URL url = new URL("https://github.com/netDinger/2K/blob/master/index.html");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            //InputStreamReader reader = new InputStreamReader(inputStream);

            int length = Integer.parseInt(connection.getHeaderField("Content-Length"));
            byte[] data = new byte[length];
            inputStream.read(data,0,length);
            String str = new String(data);
            File file = new File(Environment.getExternalStorageDirectory(),"Test.html");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(str);
            bufferedWriter.close();

            //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();


        }catch (IOException exception){
            Log.e(TAG,exception.toString());
        }
        return "";
    }
}