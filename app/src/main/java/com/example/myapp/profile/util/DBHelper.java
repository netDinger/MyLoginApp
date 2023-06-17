package com.example.myapp.profile.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contacts.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "ContactInfo";

    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PHONE = "PhoneNo";
  //  public static final String COLUMN_ADDRESS = "Address";

    public DBHelper(@NonNull Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (Name Text,PhoneNo Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

//DAO -> Data Access Object
