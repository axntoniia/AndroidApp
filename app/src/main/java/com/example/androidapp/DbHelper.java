package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.androidapp.QuizContract.*;
import com.example.androidapp.UserContractor.*;

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
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" + ")";
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

    private void insertQuestion() {
        Question q1 = new Question("Was ist Java?", "Programmiersprache", "Speichereinheit", "Gericht", "Kontinent", 1);
        addQuestion(q1);
        Question q2 = new Question("Was ist ein Byte?","Maßeinheit für Datenmengen","Programmiersprache","Bildbearbeitungsprogramm","Hardwareteil", 1);
        addQuestion(q2);
        Question q3 = new Question("Wie viele Bits sind ein Byte","8","16","2","1024", 1);
        addQuestion(q3);
        Question q4 = new Question("Was bedeutet die Abkürzung ROM?","read only memory","read on mind","rest or move","rest on medium", 1);
        addQuestion(q4);
        Question q5 = new Question("Was bedeutet IT?","Informationstechnologie","Außerirdischer","Intelligenzquotient","Internettreffpunkt", 1);
        addQuestion(q5);
        Question q6 = new Question("Wofür steht die Abkürzung WWW","World Wide Web","wer weiß was","welt weites Warten","wieso weshalb warum", 1);
        addQuestion(q6);
        Question q7 = new Question("Wie nennt man völlig kostenlose Programme für PCs?","Freeware","Shareware","Software","Hardware", 1);
        addQuestion(q7);
        Question q8 = new Question("Was bedeutet Open-Source","Quellcode ist änderbar","überall verwendbar","gecrackte Version","alles gratis", 1);
        addQuestion(q8);
        Question q9 = new Question("Welche 3 Grundfarben verwendet ein Monitor?","Rot, Grün, Blau","Rot, Gelb, Blau","Rot, Blau, Weiß","Schwarz, Weiß, Blau", 1);
        addQuestion(q9);
        Question q10 = new Question("Was ist kein Eingabegerät?","Monitor","Scanner","Tastatur","Maus", 1);
        addQuestion(q10);
        Question q11 = new Question("Was ist das EVA-Prinzip","Eingabe, Verarbeitung, Ausgabe","Eingabe, Verarbeitung, Annahme","Eingabe, Verwechslung, Ausgabe","Eingabe, Verwechslung, Annahme", 1);
        addQuestion(q11);
        Question q12 = new Question("In was wird die Bildschirmgröße angegeben?","Zoll","Zentimeter","Pixel ","Byte", 1);
        addQuestion(q12);
        Question q13 = new Question("Was ist die kleinste Einheit?","Bit","Kilobyte","Byte ","Megabyte", 1);
        addQuestion(q13);
        Question q14 = new Question("Wie wird ein infizierter Programmcode noch bezeichnet?","Wurm","Zecke","Schlange","Blutsauger", 1);
        addQuestion(q14);
        Question q15 = new Question("Was bedeutet Malware?","Schadsoftware","Beratersoftware","interne Software","OpenSourceSoftware", 1);
        addQuestion(q15);
        Question q16 = new Question("Welcher Hersteller hat kein Android auf seinen Handys?","Apple","HTC","Samsung","LG", 1);
        addQuestion(q16);
        Question q17 = new Question("Einen tragbaren Computer nennt man?","Laptop","Desktop","Computer","Buggy", 1);
        addQuestion(q17);

    }

    public void insertUser(String user, String passwd, String email){
        User u = new User(user, passwd, email, 0, 0);
        addUser(u);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    private void addUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UserTable.COLUMN_USER, user.getUser());
        cv.put(UserTable.COLUMN_PASSWD, user.getPasswd());
        cv.put(UserTable.COLUMN_EMAIL, user.getEmail());
        cv.put(UserTable.COLUMN_SCORE, user.getScore());
        cv.put(UserTable.COLUMN_SCORETOTAL, user.getScore_total());
        db.insert(UserTable.TABLE_NAME, null, cv);
    }

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
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);

            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

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

    public void setScore(String user, int score){
        db = getWritableDatabase();
        int scoreFinal = getUserScore(user) + score;
        db.rawQuery("UPDATE " + UserTable.TABLE_NAME + " SET " + UserTable.COLUMN_SCORE + " = " + scoreFinal + " WHERE " + UserTable.COLUMN_USER + " = ?", new String[] {user});
    }

    public void setScoreTotal(String user, int totalScore){
        db = getWritableDatabase();
        int scoreTotalFinal = getUserScore(user) + totalScore;
        db.rawQuery("UPDATE " + UserTable.TABLE_NAME + " SET " + UserTable.COLUMN_SCORETOTAL + " = " + scoreTotalFinal + " WHERE " + UserTable.COLUMN_USER + " = ?", new String[] {user});
    }


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