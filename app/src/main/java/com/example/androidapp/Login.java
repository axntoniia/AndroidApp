package com.example.androidapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    private static final String LOG_TAG = Login.class.getSimpleName();
    private Button btnToRegistration;
    private Button btnLogin;
    private EditText et_user;
    private EditText et_passwd;

    SharedPreferences userPref;
    SharedPreferences.Editor userPrefEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnToRegistration = findViewById(R.id.btnToRegistration);
        btnLogin          = findViewById(R.id.btn_confirm);
        et_user           = findViewById(R.id.et_username);
        et_passwd         = findViewById(R.id.et_password);
        btnToRegistration.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        userPref = getSharedPreferences("currentUser", MODE_PRIVATE);
        userPrefEdit = userPref.edit();
        userPrefEdit.putString("currentUser", null);
        userPrefEdit.commit();
    }

    public void safeUser(String user){
        userPrefEdit.putString("currentUser", user);
        userPrefEdit.commit();
    }


    public void login(){
        DbHelper dbHelper = new DbHelper(this);
        String user = et_user.getText().toString();
        String passwd = et_passwd.getText().toString();
        if(dbHelper.validateUser(user, passwd)){
            Toast.makeText(getApplicationContext(), "Gültig", Toast.LENGTH_SHORT).show();
            safeUser(user);
            Intent intent = new Intent(this, Evaluation.class);
            startActivity(intent);
            this.finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Ungültige Zugangsdaten", Toast.LENGTH_SHORT).show();
        }

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
            case R.id.btn_confirm:
                login();
                break;
            case R.id.btnToRegistration:
                register();
                break;
        }

    }
}