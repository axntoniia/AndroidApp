package com.example.androidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class Questions extends Activity implements View.OnClickListener
{
    private Button btn_a1;
    private Button btn_a2;
    private Button btn_a3;
    private Button btn_a4;
    private List<Question> questionList;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        btn_a1 = (Button) findViewById(R.id.btn_a1);
        btn_a2 = (Button) findViewById(R.id.btn_a2);
        btn_a3 = (Button) findViewById(R.id.btn_a3);
        btn_a4 = (Button) findViewById(R.id.btn_a4);

        btn_a1.setOnClickListener(this);
        btn_a2.setOnClickListener(this);
        btn_a3.setOnClickListener(this);
        btn_a4.setOnClickListener(this);

        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_a1:
                break;
            case R.id.btn_a2:
                break;
            case R.id.btn_a3:
                break;
            case R.id.btn_a4:
                break;
        }
    }
}
