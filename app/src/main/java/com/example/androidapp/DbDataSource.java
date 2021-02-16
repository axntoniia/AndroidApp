package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DbDataSource {

    private static final String LOG_TAG = DbDataSource.class.getSimpleName();
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private int id;

    public DbDataSource(Context context)
    {
        Log.d(LOG_TAG, "DataSource erzeugt DbHelper");
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Insert Adminuser");
    }

}
