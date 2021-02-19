package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.androidapp.QuizContract.*;
import com.example.androidapp.UserContract.*;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "quiz_database";
    public static final int DATABASE_VERSION = 1;
    private static final String LOG_TAG = DbHelper.class.getSimpleName();

    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String QUESTIONS_CREATE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT" + " ) ";
        db.execSQL(QUESTIONS_CREATE);
        insertQuestion();

        final String USER_CREATE = "CREATE TABLE " +
                UserTable.TABLE_NAME + " ( " +
                UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserTable.COLUMN_USER + " TEXT, " +
                UserTable.COLUMN_PASSWD + " TEXT, " +
                UserTable.COLUMN_EMAIL + " TEXT, " +
                UserTable.COLUMN_SCORE + " INTEGER, " +
                UserTable.COLUMN_SCORETOTAL +  " INTEGER" + ")";
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: "+ getDatabaseName() +" erzeugt.");
        db.execSQL(USER_CREATE);
        insertUser("admin", "admin", "admin@test.de");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        onCreate(db);
    }

    // Funktion erstellt Question Objekte und fügt diese der Tabelle hinzu
    // Return: null
    private void insertQuestion() {
        Question q1 = new Question("Was ist Java?", "Programmiersprache", "Speichereinheit", "Gericht", "Kontinent");
        addQuestion(q1);
        Question q2 = new Question("Was ist ein Byte?","Maßeinheit für Datenmengen","Programmiersprache","Bildbearbeitungsprogramm","Hardwareteil");
        addQuestion(q2);
        Question q3 = new Question("Wie viele Bits sind ein Byte","8","16","2","1024");
        addQuestion(q3);
        Question q4 = new Question("Was bedeutet die Abkürzung ROM?","read only memory","read on mind","rest or move","rest on medium");
        addQuestion(q4);
        Question q5 = new Question("Was bedeutet IT?","Informationstechnologie","Außerirdischer","Intelligenzquotient","Internettreffpunkt");
        addQuestion(q5);
        Question q6 = new Question("Wofür steht die Abkürzung WWW","World Wide Web","wer weiß was","welt weites Warten","wieso weshalb warum");
        addQuestion(q6);
        Question q7 = new Question("Wie nennt man völlig kostenlose Programme für PCs?","Freeware","Shareware","Software","Hardware");
        addQuestion(q7);
        Question q8 = new Question("Was bedeutet Open-Source","Quellcode ist änderbar","überall verwendbar","gecrackte Version","alles gratis");
        addQuestion(q8);
        Question q9 = new Question("Welche 3 Grundfarben verwendet ein Monitor?","Rot, Grün, Blau","Rot, Gelb, Blau","Rot, Blau, Weiß","Schwarz, Weiß, Blau");
        addQuestion(q9);
        Question q10 = new Question("Was ist kein Eingabegerät?","Monitor","Scanner","Tastatur","Maus");
        addQuestion(q10);
        Question q11 = new Question("Was ist das EVA-Prinzip","Eingabe, Verarbeitung, Ausgabe","Eingabe, Verarbeitung, Annahme","Eingabe, Verwechslung, Ausgabe","Eingabe, Verwechslung, Annahme");
        addQuestion(q11);
        Question q12 = new Question("In was wird die Bildschirmgröße angegeben?","Zoll","Zentimeter","Pixel ","Byte");
        addQuestion(q12);
        Question q13 = new Question("Was ist die kleinste Einheit?","Bit","Kilobyte","Byte ","Megabyte");
        addQuestion(q13);
        Question q14 = new Question("Wie wird ein infizierter Programmcode noch bezeichnet?","Wurm","Zecke","Schlange","Blutsauger");
        addQuestion(q14);
        Question q15 = new Question("Was bedeutet Malware?","Schadsoftware","Beratersoftware","interne Software","OpenSourceSoftware");
        addQuestion(q15);
        Question q16 = new Question("Welcher Hersteller hat kein Android auf seinen Handys?","Apple","HTC","Samsung","LG");
        addQuestion(q16);
        Question q17 = new Question("Einen tragbaren Computer nennt man?","Laptop","Desktop","Computer","Buggy");
        addQuestion(q17);

    }

    // Die Funktion erstellt ein neues UserObjekt und fügt es der Tabelle hinzu
    // Return: null
    public void insertUser(String user, String passwd, String email){
        User u = new User(user, passwd, email, 0, 0);
        addUser(u);
    }

    // Funktion fügt die übergebene Frage in die Tabelle der Fragen ein
    // Return: null
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    // Funktion fügt den übergebenen User in die Tabelle der User ein
    // Return: null
    private void addUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UserTable.COLUMN_USER, user.getUser());
        cv.put(UserTable.COLUMN_PASSWD, user.getPasswd());
        cv.put(UserTable.COLUMN_EMAIL, user.getEmail());
        cv.put(UserTable.COLUMN_SCORE, user.getScore());
        cv.put(UserTable.COLUMN_SCORETOTAL, user.getScore_total());
        db.insert(UserTable.TABLE_NAME, null, cv);
    }

    // Funktion erstellt eine ArrayList mit den Top 3 Usern
    // Return: ArrayList List<User>
    public List<User> getTop3(){
        List<User> userList = new ArrayList<>();
        db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UserTable.TABLE_NAME + " ORDER BY " + UserTable.COLUMN_SCORE + " DESC", null );
        if(c.moveToFirst()) {
            do{
               User user = new User();
               user.setUser(c.getString(c.getColumnIndex(UserTable.COLUMN_USER)));
               user.setScore(c.getInt(c.getColumnIndex(UserTable.COLUMN_SCORE)));
               user.setScore_total(c.getInt(c.getColumnIndex(UserTable.COLUMN_SCORETOTAL)));
               userList.add(user);
            } while (c.moveToNext());
        }
        c.close();
        return userList;
    }

    // Funktion erstellt eine ArrayList mit allen Fragen und Antworten und gibt diese zurück
    // Return: ArryList List<Question>
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    // Funktion ermittelt den Score eines Users
    // Return: int Score
    public int getUserScore(String user){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + UserTable.COLUMN_SCORE + " FROM " + UserTable.TABLE_NAME + " WHERE " + UserTable.COLUMN_USER + " = ?", new String[] {user});
        if(c.moveToFirst()) {
            return c.getInt(c.getColumnIndex(UserTable.COLUMN_SCORE));
        }
        else{
            return 0;
        }
    }

    // Funktion ermittelt den TotalScore eines Users
    // Return: int TotalScore
    public int getUserScoreTotal(String user){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT " + UserTable.COLUMN_SCORETOTAL + " FROM " + UserTable.TABLE_NAME + " WHERE " + UserTable.COLUMN_USER + " = ?", new String[] {user});
        if(c.moveToFirst()) {
            return c.getInt(c.getColumnIndex(UserTable.COLUMN_SCORETOTAL));
        }
        else{
            return 0;
        }
    }

    // Funktion erhöht den User Score um die übergebene Anzahl
    // Return: Null
    public void setScore(String user, int score){
        db = getWritableDatabase();
        int scoreFinal = getUserScore(user) + score;
        db.execSQL("UPDATE " + UserTable.TABLE_NAME + " SET " + UserTable.COLUMN_SCORE + " = " + scoreFinal + " WHERE " + UserTable.COLUMN_USER + " = ?", new String[] {user});
    }

    // Funktion erhöht den TotalScore/Anzahl der beantworteten Fragen
    // Return: null
    public void setScoreTotal(String user, int totalScore){
        db = getWritableDatabase();
        int scoreTotalFinal = getUserScoreTotal(user) + totalScore;
        db.execSQL("UPDATE " + UserTable.TABLE_NAME + " SET " + UserTable.COLUMN_SCORETOTAL + " = " + scoreTotalFinal + " WHERE " + UserTable.COLUMN_USER + " = ?", new String[] {user});
    }

    // Funktion prüft, ob es schon einen User mit dem Benutzernamen gibt
    // Return: User vorhanden
    public boolean checkUsername(String user){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UserTable.TABLE_NAME + " WHERE " + UserTable.COLUMN_USER + " = ? ", new String[] {user});
        if(c.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }
    }

    // Funktion prüft, ob ein User mit dem Namen und dem Passwort vorhanden ist
    // Return: User vorhanden
    public boolean validateUser(String user, String passwd){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UserTable.TABLE_NAME + " WHERE " + UserTable.COLUMN_USER + " = ? and " + UserTable.COLUMN_PASSWD + " = ?", new String[] {user, passwd});
        if(c.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }
    }
}