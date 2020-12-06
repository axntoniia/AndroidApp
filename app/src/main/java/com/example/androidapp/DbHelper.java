package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "db_quiz";
    public static final int DB_VERSION = 1;

    // Kostanten für Tabelle t_user
    public static final String U_TABLE = "t_user";
    public static final String U_ID = "id";
    public static final String U_USER = "user";
    public static final String U_PASSWD = "passwd";
    public static final String U_EMAIL = "email";

    public static final String USER_CREATE =
            "CREATE TABLE " + U_TABLE + "(" +
                    U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    U_USER + " TEXT NOT NULL, " +
                    U_PASSWD + " TEXT NOT NULL," +
                    U_EMAIL + " TEXT NOT NULL);";
    public static final String USER_DROP = "DROP TABLE IF EXISTS "+ U_TABLE;
    public static final String USER_UPDATE = "SELECT * FROM " + U_TABLE + "WHERE " + U_ID + "=";


    public DbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: "+ getDatabaseName() +" erzeugt.");
    }

    public void test(SQLiteDatabase db)
    {
        try
        {
            Log.d(LOG_TAG, "DbHelper hat die Tabelle " + U_TABLE + " erstellt.");
            db.execSQL(USER_CREATE);
        }
        catch(Exception e)
        {
            Log.d(LOG_TAG, "Fehler beim Erstellen der Tabelle " + U_TABLE + ".");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            Log.d(LOG_TAG, "DbHelper hat die Tabelle " + U_TABLE + " erstellt.");
            db.execSQL(USER_CREATE);
        }
        catch(Exception e)
        {
            Log.d(LOG_TAG, "Fehler beim Erstellen der Tabelle " + U_TABLE + ".");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(USER_DROP);
        onCreate(db);
    }

    public int insert(int id, String user, String passwd, String email)
    {
        Log.d(LOG_TAG, "DbHelper Insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(U_ID, id);
        values.put(U_USER, user);
        values.put(U_PASSWD, passwd);
        values.put(U_EMAIL, email);
        return (int) db.insert(U_TABLE, null, values);
    }


    public boolean update(int id, String user, String passwd, String email)
    {
        Log.d(LOG_TAG, "DbHelper update");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(U_ID, id);
        values.put(U_USER, user);
        values.put(U_PASSWD, passwd);
        values.put(U_EMAIL, email);
        Cursor cursor = db.rawQuery(USER_UPDATE + id, null);
        if(cursor.getCount()>0) {
            long result = db.update(U_TABLE, values, "id="+id, null);
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean delete(int id)
    {
        Log.d(LOG_TAG, "DbHelper delete");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(USER_UPDATE+id, null);
        if(cursor.getCount()>0) {
            long result = db.delete(U_TABLE, "id="+id, null);
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getData()
    {
        Log.d(LOG_TAG, "DbHelper Insert");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(USER_UPDATE, null);
        return cursor;
    }
}