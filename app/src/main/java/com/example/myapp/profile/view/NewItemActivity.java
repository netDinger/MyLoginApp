package com.example.myapp.profile.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapp.R;
import com.example.myapp.profile.util.DBHelper;
import com.example.myapp.profile.util.MyContentProvider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class NewItemActivity extends AppCompatActivity {

    private static final String TAG = "NewItemActivity";
    private String MY_FILENAME = "MYNewData.txt";
    //data/data/<PackageName>/files

    private EditText name, phoneNumber;
    private TextView msg;
    private Button save;

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    private boolean isMediaAvailable = false;
    private boolean isMediaWritable = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //data/data/<packageName>/databases/Contacts.db
        //android/content/SharedPref

        setContentView(R.layout.activity_new_item);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        msg = findViewById(R.id.msg);

        save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            if (name.getText().toString().equals("") || phoneNumber.getText().toString().equals("")) {
                Toast.makeText(NewItemActivity.this, "Contact shouldn't be empty", Toast.LENGTH_SHORT).show();
            } else {
                saveContact();
               // saveToCP();

                //Toast.makeText(NewItemActivity.this, readInternalStorage(), Toast.LENGTH_SHORT).show();
            }
        });

        msg.setText(readExtStorage());


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);

        } else {
            //getNetworkData();


            checkExtStorage();
            if(isMediaAvailable&&isMediaWritable){
                writeExtStorage();
            }else{
                Toast.makeText(this, "not available", Toast.LENGTH_SHORT).show();
            }
            writeIntStorage();


        }


    }


    private void checkExtStorage() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            isMediaAvailable = true;
            isMediaWritable = true;

        }else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            isMediaAvailable = true;
            isMediaWritable = false;
        }else{
            isMediaAvailable = false;
            isMediaWritable = false;
        }
    }

    private void saveContact() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, name.getText().toString());
        values.put(DBHelper.COLUMN_PHONE, phoneNumber.getText().toString());
        database.insertOrThrow(DBHelper.TABLE_NAME, null, values);
    }

    private void saveToCP() {
        MyContentProvider cp = new MyContentProvider(this);
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, name.getText().toString());
        values.put(DBHelper.COLUMN_PHONE, phoneNumber.getText().toString());

        cp.insert(MyContentProvider.CONTACT_URI, values);
    }


    public String getNetworkData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("https://github.com/netDinger/2K/blob/master/index.html");
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    int dat = reader.read();
                    while (dat != -1) {
                        char current = (char) dat;
                        dat = reader.read();
                        Log.i(TAG, current + "");
                    }
                    byte[] data = new byte[4096];
                    inputStream.read(data, 0, 4096);
                    String str = new String(data);
                    File file = new File(Environment.getExternalStorageDirectory(), "Test.html");
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write(str);
                    bufferedWriter.close();


                } catch (IOException exception) {
                    Log.e(TAG, exception.toString());
                }
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();


        return "";
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getNetworkData();
            } else {
                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void writeIntStorage(){
        String myData = "this is qrwwwrwrqt4v a test";
        try {
            FileOutputStream outputStream = openFileOutput(MY_FILENAME, MODE_PRIVATE);
            outputStream.write(myData.getBytes(StandardCharsets.UTF_8));

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readInternalStorage() {
        try {
            FileInputStream inputStream = openFileInput(MY_FILENAME);
            byte[] reader = new byte[inputStream.available()];

            if(inputStream.read(reader) != -1){
                String myData = new String(reader);
                return myData;
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    //read txt file from res/raw
    private String readFromRaw(){
        try {
        Resources res = getResources();
        InputStream inputStream = res.openRawResource(R.raw.readme);
        byte[] reader = new byte[inputStream.available()];
            if(inputStream.read(reader) != -1){
                String myData = new String(reader);
                return myData;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }

    //Activity Guide Activity 4.2 -> Page 4.10



    private void writeExtStorage(){
        String myStr = "this is test!";
        try {
            FileOutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory()+"//"+MY_FILENAME);
            outputStream.write(myStr.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (IOException e) {
           Log.e(TAG,e.getMessage());
        }
    }

    private String readExtStorage(){
        try {

            FileInputStream inputStream = new FileInputStream(Environment.getExternalStorageDirectory()+"//"+MY_FILENAME);

            byte[] reader = new byte[inputStream.available()];

            if(inputStream.read(reader) != -1){
                String myData = new String(reader);
                return myData;
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private void readFromDB(){
        try{
            String[] cols;
            Context context = createPackageContext("com.example.myappdb.sharedID",Context.CONTEXT_IGNORE_SECURITY);
            File myFile = context.getDatabasePath(DBHelper.DATABASE_NAME);
            if (myFile.exists()){
                database = openOrCreateDatabase(myFile.getPath(),MODE_PRIVATE,null);
            }

            Cursor cursor = database.rawQuery("SELECT * FROM contacts;",null);
            cursor.moveToFirst();
            int colCount = cursor.getColumnCount();
            cols = new String[colCount];
            for (int i =0; i<colCount; i++){
                cols[i] = cursor.getString(i);
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}