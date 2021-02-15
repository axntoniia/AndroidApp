package com.example.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Evaluation extends Activity implements View.OnClickListener
{
    private Button btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);

        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this, Questions.class);
        startActivity(intent);
        this.finish();
    }
}
