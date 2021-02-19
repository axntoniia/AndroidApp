package com.example.androidapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;


public class Questions extends Activity implements View.OnClickListener
{
    private TextView tv_question;
    private TextView tv_user;
    private TextView tv_score;
    private TextView tv_counter;
    private TextView tv_scoreTotalRight;
    private TextView tv_scoreTotal;
    private Button btn_a1;
    private Button btn_a2;
    private Button btn_a3;
    private Button btn_a4;

    private Color buttonDefault;

    private List<Question> questionList;
    private int questionCounter;
    private int questionsCounterTotal;
    private Question currentQuestion;
    private int score;
    private int totalscore;
    private String user;
    private int right;

    SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        tv_question        = findViewById(R.id.tv_question);
        tv_user            = findViewById(R.id.tv_user);
        tv_score           = findViewById(R.id.tv_score);
        tv_counter         = findViewById(R.id.tv_counter);
        tv_scoreTotalRight = findViewById(R.id.tv_scoreTotalRight);
        tv_scoreTotal      = findViewById(R.id.tv_scoreTotal);

        btn_a1 = findViewById(R.id.btn_a1);
        btn_a2 = findViewById(R.id.btn_a2);
        btn_a3 = findViewById(R.id.btn_a3);
        btn_a4 = findViewById(R.id.btn_a4);

        btn_a1.setOnClickListener(this);
        btn_a2.setOnClickListener(this);
        btn_a3.setOnClickListener(this);
        btn_a4.setOnClickListener(this);

        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionsCounterTotal = 5;
        questionCounter = 0;
        score = 0;
        Collections.shuffle(questionList);
        userPref = getSharedPreferences("currentUser", MODE_PRIVATE);
        user = userPref.getString("currentUser", "");
        tv_user.setText("Angemeldet als: " + user);
        tv_scoreTotalRight.setText("Insgesamt richtig beanwortet: " + dbHelper.getUserScore(user));
        tv_scoreTotal.setText("Insgesamt beantwortet: " + dbHelper.getUserScoreTotal(user));
        showNextQuestion();
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion(){
        if (questionCounter < questionsCounterTotal) {
            currentQuestion = questionList.get(questionCounter);
            tv_question.setText(currentQuestion.getQuestion());
            double r = Math.random();
            if(r<0.25) {
                btn_a1.setText(currentQuestion.getOption1());
                btn_a2.setText(currentQuestion.getOption2());
                btn_a3.setText(currentQuestion.getOption3());
                btn_a4.setText(currentQuestion.getOption4());
                right = 1;
            }
            else if(r<0.5) {
                btn_a2.setText(currentQuestion.getOption1());
                btn_a4.setText(currentQuestion.getOption2());
                btn_a1.setText(currentQuestion.getOption3());
                btn_a3.setText(currentQuestion.getOption4());
                right = 2;
            }
            else if(r<0.75) {
                btn_a3.setText(currentQuestion.getOption1());
                btn_a1.setText(currentQuestion.getOption2());
                btn_a4.setText(currentQuestion.getOption3());
                btn_a2.setText(currentQuestion.getOption4());
                right = 3;
            }
            else {
                btn_a4.setText(currentQuestion.getOption1());
                btn_a3.setText(currentQuestion.getOption2());
                btn_a2.setText(currentQuestion.getOption3());
                btn_a1.setText(currentQuestion.getOption4());
                right = 4;
            }
           questionCounter++;
           tv_counter.setText("Frage: " + questionCounter + "/" + questionsCounterTotal);
           tv_score.setText("Score: " + score);

        }
        else {
            DbHelper dbHelper = new DbHelper(this);
            try {
                dbHelper.setScore(user, score);
                dbHelper.setScoreTotal(user, 5);
                finishQuiz();
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(), "Fehler: " + e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void finishQuiz(){
        Intent intent = new Intent(this, Evaluation.class);
        startActivity(intent);
        this.finish();
    }

    private void answerR(){
        score++;
        showNextQuestion();
    }

    private void answerW(){
        showNextQuestion();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(R.id.btn_a1 == id && right == 1) {
            answerR();
        }
        else if(R.id.btn_a2 == id && right == 2) {
            answerR();
        }
        else if(R.id.btn_a3 == id && right == 3) {
            answerR();
        }
        else if(R.id.btn_a4 == id && right == 4) {
            answerR();
        }
        else {
           if(R.id.btn_a1 == id){
               answerW();
           }
           else if(R.id.btn_a2 == id){
               answerW();
           }
           else if(R.id.btn_a3 == id){
               answerW();
           }
           else {
               answerW();
           }
        }
    }
}
