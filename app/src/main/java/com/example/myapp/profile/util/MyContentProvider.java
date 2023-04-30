package com.example.myapp.profile.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//browser
//contacts
//media data
public class MyContentProvider extends ContentProvider {

    public static final String uri = "content://com.example.myapp.MyContentProvider/ContactInfo";
    public static final Uri CONTACT_URI = Uri.parse(uri);
    private static final UriMatcher uriMatcher = new UriMatcher(1);
    DBHelper helper;
    SQLiteDatabase db;
    Context context;

    public MyContentProvider(){}
    public MyContentProvider(Context context){

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DBHelper.TABLE_NAME);
        Cursor cursor = builder.query(db,projection,selection,selectionArgs,null,null,sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        db.insertOrThrow(DBHelper.TABLE_NAME,null,values);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
