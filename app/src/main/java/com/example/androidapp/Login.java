package com.example.androidapp;


import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    private static final String LOG_TAG = Login.class.getSimpleName();
    private DbDataSource dataSource;
    private DbHelper db;
    private Button btnToRegistration;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnToRegistration = (Button) findViewById(R.id.btnToRegistration);
        btnToRegistration.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    public void login(){
        Intent intent = new Intent(this, Evaluation.class);
        startActivity(intent);
        this.finish();
    }

    public void register(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        this.finish();
    }
    
    @Override
    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btnToRegistration:
                register();
                break;
        }

    }
}