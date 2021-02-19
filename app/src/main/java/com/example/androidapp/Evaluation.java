package com.example.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class Evaluation extends Activity implements View.OnClickListener
{
    private Button btnNewGame;
    private Button btnLogout;

    private TextView tv_headerUser;
    private TextView tv_headerScore;
    private TextView tv_headerScoreTotal;
    private TextView tv_headerScorePercent;

    private TextView tv_user1;
    private TextView tv_score1;
    private TextView tv_scoreTotal1;
    private TextView tv_scorePercent1;

    private TextView tv_user2;
    private TextView tv_score2;
    private TextView tv_scoreTotal2;
    private TextView tv_scorePercent2;

    private TextView tv_user3;
    private TextView tv_score3;
    private TextView tv_scoreTotal3;
    private TextView tv_scorePercent3;

    private List<User> userList;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.getWritableDatabase();

        btnNewGame = findViewById(R.id.btnNewGame);
        btnLogout = findViewById(R.id.btnLogout);
        btnNewGame.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        tv_headerUser = findViewById(R.id.tv_headlineUser);
        tv_headerScore = findViewById(R.id.tv_headlineScore);
        tv_headerScoreTotal = findViewById(R.id.tv_headlineScoreTotal);
        tv_headerScorePercent = findViewById(R.id.tv_headlineScorePercent);

        tv_user1 = findViewById(R.id.tv_user1);
        tv_score1 = findViewById(R.id.tv_score1);
        tv_scoreTotal1 = findViewById(R.id.tv_scoreTotal1);
        tv_scorePercent1 = findViewById(R.id.tv_scorePercent1);

        tv_user2 = findViewById(R.id.tv_user2);
        tv_score2 = findViewById(R.id.tv_score2);
        tv_scoreTotal2 = findViewById(R.id.tv_scoreTotal2);
        tv_scorePercent2 = findViewById(R.id.tv_scorePercent2);

        tv_user3 = findViewById(R.id.tv_user3);
        tv_score3 = findViewById(R.id.tv_score3);
        tv_scoreTotal3 = findViewById(R.id.tv_scoreTotal3);
        tv_scorePercent3 = findViewById(R.id.tv_scorePercent3);

        userList = dbHelper.getTop3();
        try {
            fillScoreboard();
        }
        catch(Exception e){}
    }

    private void fillScoreboard(){
        User currentUser;
        int scoreR;
        int scoreTotal;
        int scorePercent;

        currentUser = userList.get(0);
        scoreR = currentUser.getScore();
        scoreTotal = currentUser.getScore_total();
        if(scoreTotal!=0) {
            scorePercent = scoreR * 100 / scoreTotal;
        }
        else {
            scorePercent = 0;
        }
        tv_user1.setText(currentUser.getUser());
        tv_score1.setText(String.valueOf(scoreR));
        tv_scoreTotal1.setText(String.valueOf(scoreTotal));
        tv_scorePercent1.setText(String.valueOf(scorePercent) + "%");

        currentUser = userList.get(1);
        scoreR = Integer.parseInt(String.valueOf(currentUser.getScore()));
        scoreTotal = Integer.parseInt(String.valueOf(currentUser.getScore_total()));
        if(scoreTotal!=0) {
            scorePercent = scoreR * 100 / scoreTotal;
        }
        else {
            scorePercent = 0;
        }
        tv_user2.setText(currentUser.getUser());
        tv_score2.setText(String.valueOf(scoreR));
        tv_scoreTotal2.setText(String.valueOf(scoreTotal));
        tv_scorePercent2.setText(String.valueOf(scorePercent) + "%");

        currentUser = userList.get(2);
        scoreR = Integer.parseInt(String.valueOf(currentUser.getScore()));
        scoreTotal = Integer.parseInt(String.valueOf(currentUser.getScore_total()));
        if(scoreTotal!=0) {
            scorePercent = scoreR * 100 / scoreTotal;
        }
        else {
            scorePercent = 0;
        }
        tv_user3.setText(currentUser.getUser());
        tv_score3.setText(String.valueOf(scoreR));
        tv_scoreTotal3.setText(String.valueOf(scoreTotal));
        tv_scorePercent3.setText(String.valueOf(scorePercent) + "%");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btnNewGame:
                intent = new Intent(this, Questions.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                intent = new Intent(this, Login.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}