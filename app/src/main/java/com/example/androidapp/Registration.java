package com.example.androidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = Registration.class.getSimpleName();
    private Button btn_registration;
    private Button btnToLogin;
    private EditText et_username, et_password, et_confirmpassword, et_email;
    private DbDataSource dataSource;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        btnToLogin = (Button) findViewById(R.id.btnToLogin);
        btn_registration = (Button) findViewById(R.id.btn_login);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirmpassword = (EditText) findViewById(R.id.et_confirmpassword);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_registration.setOnClickListener(this);
        btn_registration.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v)
    {
        int ce = v.getId();
        if(ce == R.id.btnToLogin)
        {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            this.finish();
        }else {
            try {
                String password = et_password.getText().toString();
                if (password.equals(et_confirmpassword.getText().toString()) && !password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Sollte passen", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(this, Evaluation.class);
                    startActivity(intent1);
                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Fehler " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}