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
    public static final String U_ID = "u_id";
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

    // Konstanten für Tabelle t_question
    public static final String Q_TABLE = "t_question";
    public static final String Q_ID = "q_id";
    public static final String Q_QUESTION = "q_question";
    public static final String Q_ANSWER_R = "q_answer_r";
    public static final String Q_ANSWER_F1 = "q_answer_f1";
    public static final String Q_ANSWER_F2 = "q_answer_f2";
    public static final String Q_ANSWER_F3 = "q_answer_f3";

    public static final String Q_CREATE =
            "CREATE TABLE " + Q_TABLE + "(" +
                    Q_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Q_QUESTION + " TEXT NOT NULL, " +
                    Q_ANSWER_R + " TEXT NOT NULL, " +
                    Q_ANSWER_F1 + " TEXT NOT NULL," +
                    Q_ANSWER_F2 + " TEXT NOT NULL," +
                    Q_ANSWER_F3 + " TEXT NOT NULL);";
    public static final String Q_DROP = "DROP TABLE IF EXISTS "+ Q_TABLE;
    public static final String Q_UPDATE = "SELECT * FROM " + Q_TABLE + "WHERE " + Q_ID + "=";

    // Kostanten für Tabelle t_user_question
    public static final String UQ_TABLE = "t_user_question";
    public static final String UQ_ID = "uq_id";
    public static final String UQ_UID = "uq_uid";
    public static final String UQ_QID = "uq_qid";
    public static final String UQ_RESULT = "uq_result";

    public static final String UQ_CREATE =
            "CREATE TABLE " + UQ_TABLE + "(" +
                    UQ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UQ_UID + " INTEGER NOT NULL, " +
                    UQ_QID + " INTEGER NOT NULL," +
                    UQ_RESULT + " BOOLEAN NOT NULL);";
    public static final String UQ_DROP = "DROP TABLE IF EXISTS "+ U_TABLE;
    public static final String UQ_UPDATE = "SELECT * FROM " + UQ_TABLE + "WHERE " + UQ_ID + "=";


    public DbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: "+ getDatabaseName() +" erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            Log.d(LOG_TAG, "DbHelper hat die Tabellen erstellt.");
            db.execSQL(USER_CREATE);
            db.execSQL(Q_CREATE);
            db.execSQL(UQ_CREATE);
            insertQuestion("Was ist Java?", "Programmiersprache", "Speichereinheit", "Gericht", "Kontinent");
            insertQuestion("Was ist ein Byte?","Maßeinheit für Datenmengen","Programmiersprache","Bildbearbeitungsprogramm","Hardwareteil");
            insertQuestion("Wie viele Bits sind ein Byte","8","16","2","1024");
            insertQuestion("Wie viele Kb sind 1 Mb?","1024","2048","1012","50");
            insertQuestion("Was bedeutet die Abkürzung ROM?","read only memory","read on mind","rest or move","rest on medium");
            insertQuestion("Was bedeutet IT?","Informationstechnologie","Außerirdischer","Intelligenzquotient","Internettreffpunkt");
            insertQuestion("Wofür steht die Abkürzung WWW","World Wide Web","wer weiß was","welt weites Warten","wieso weshalb warum");
            insertQuestion("Wie nennt man völlig kostenlose Programme für PCs?","Freeware","Shareware","Software","Hardware");
            insertQuestion("Was bedeutet Open-Source","Quellcode ist änderbar","überallverwendbar","gecrackte Version","alles gratis");
            insertQuestion("Welche 3 Grundfarben verwendet ein Monitor?","Rot, Grün, Blau","Rot, Gelb, Blau","Rot, Blau, Weiß","Schwarz, Weiß, Blau");
            insertQuestion("Was ist kein Eingabegerät?","Monitor","Scanner","Tastatur","Maus");
            insertQuestion("Was ist das EVA-Prinzip","Eingabe, Verarbeitung, Ausgabe","Eingabe, Verarbeitung, Annahme","Eingabe, Verwechslung, Ausgabe","Eingabe, Verwechslung, Annahme");
            insertQuestion("In was wird die Bildschirmgröße angegeben?","Zoll","Zentimeter","Pixel ","Byte");
            insertQuestion("Was ist die kleinste Einheit?","Bit","Kilobyte","Byte ","Megabyte");
            insertQuestion("Wie wird ein infizierter Programmcode noch bezeichnet?","Wurm","Zecke","Schlange","Blutsauger");
            insertQuestion("Was bedeutet Malware?","Schadsoftware","Beratersoftware","interne Software","OpenSourceSoftware");
            insertQuestion("Welcher Hersteller hat kein Android auf seinen Handys?","Apple","HTC","Samsung","LG");
            insertQuestion("Einen tragbaren Computer nennt man?","Laptop","Desktop","Computer","Buggy");


        }
        catch(Exception e)
        {
            Log.d(LOG_TAG, "Fehler beim Erstellen der Tabellen.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(USER_DROP);
        db.execSQL(Q_DROP);
        db.execSQL(UQ_DROP);
        onCreate(db);
    }

    public int insertUser(String user, String passwd, String email)
    {
        Log.d(LOG_TAG, "DbHelper Insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(U_USER, user);
        values.put(U_PASSWD, passwd);
        values.put(U_EMAIL, email);
        return (int) db.insert(U_TABLE, null, values);
    }

    public int insertQuestion(String q, String answ_r, String answ_f1, String answ_f2, String answ_f3)
    {
        Log.d(LOG_TAG, "DbHelper Insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Q_QUESTION, q);
        values.put(Q_ANSWER_R, answ_r);
        values.put(Q_ANSWER_F1, answ_f1);
        values.put(Q_ANSWER_F1, answ_f2);
        values.put(Q_ANSWER_F1, answ_f3);
        return (int) db.insert(Q_TABLE, null, values);
    }

    public int insertUQ(int u_id, int q_id, boolean res)
    {
        Log.d(LOG_TAG, "DbHelper Insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UQ_UID, u_id);
        values.put(UQ_QID, q_id);
        values.put(UQ_RESULT, res);
        return (int) db.insert(UQ_TABLE, null, values);
    }

    public boolean updateUser(int id, String user, String passwd, String email)
    {
        Log.d(LOG_TAG, "DbHelper updateUser");
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

    public boolean updateQ(int id, String q, String answ_r, String answ_f1, String answ_f2, String answ_f3)
    {
        Log.d(LOG_TAG, "DbHelper updateUser");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Q_ID, id);
        values.put(Q_QUESTION, q);
        values.put(Q_ANSWER_R, answ_r);
        values.put(Q_ANSWER_F1, answ_f1);
        values.put(Q_ANSWER_F1, answ_f2);
        values.put(Q_ANSWER_F1, answ_f3);
        Cursor cursor = db.rawQuery(Q_UPDATE + id, null);
        if(cursor.getCount()>0) {
            long result = db.update(Q_TABLE, values, "id="+id, null);
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

    public boolean updateUQ(int id, int u_id, int q_id, boolean res)
    {
        Log.d(LOG_TAG, "DbHelper updateUser");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UQ_ID, id);
        values.put(UQ_UID, u_id);
        values.put(UQ_QID, q_id);
        values.put(UQ_RESULT, res);
        Cursor cursor = db.rawQuery(UQ_UPDATE + id, null);
        if(cursor.getCount()>0) {
            long result = db.update(UQ_TABLE, values, "id="+id, null);
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