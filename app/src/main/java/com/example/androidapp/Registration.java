package com.example.androidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = Registration.class.getSimpleName();
    private Button btn_registration;
    private Button btnToLogin;
    private EditText et_username, et_password, et_confirmpassword, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        btnToLogin       = findViewById(R.id.btnToLogin);
        btn_registration = findViewById(R.id.btn_confirm);
        et_username      = findViewById(R.id.et_username);
        et_password      = findViewById(R.id.et_password);
        et_confirmpassword = (EditText) findViewById(R.id.et_confirmpassword);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_registration.setOnClickListener(this);
        btn_registration.setVisibility(View.VISIBLE);
        btnToLogin.setOnClickListener(this);
        btnToLogin.setVisibility(View.VISIBLE);
    }

    public void backToLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }

    public void registrateUser(){
        String user = et_username.getText().toString();
        String passwd = et_password.getText().toString();
        String passwd_conf = et_confirmpassword.getText().toString();
        String email = et_email.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(getApplicationContext(), "Benutzername darf nicht leer sein.", Toast.LENGTH_SHORT).show();
        }
        else if(user.contains(" ") | user.contains("!") | user.contains("'") | user.contains("§") | user.contains("$") | user.contains("%") | user.contains("&") | user.contains("/")){
            Toast.makeText(getApplicationContext(), "Der Benutzername darf keine Leerzeichen und Sonderzeichen beinhalten.", Toast.LENGTH_SHORT).show();
        }
        else if(passwd.isEmpty()){
            Toast.makeText(getApplicationContext(), "Das Passwort darf nicht leer sein.", Toast.LENGTH_SHORT).show();
        }
        else if(passwd.contains(" ")){
            Toast.makeText(getApplicationContext(), "Das Passwort darf keine Leerzeichen enthalten.", Toast.LENGTH_SHORT).show();
        }
        else if(passwd.equals(passwd_conf)){
            DbHelper dbHelper = new DbHelper(this);
            if(!dbHelper.checkUsername(user)) {
                dbHelper.insertUser(user, passwd, email);
                Toast.makeText(getApplicationContext(), "Benutzer erfolgreich erstellt.", Toast.LENGTH_SHORT).show();
                backToLogin();
            }
            else{
                Toast.makeText(getApplicationContext(), "Benutzername ist schon vorhanden.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Die Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.btn_confirm:
                registrateUser();
                break;
            default:
                backToLogin();
                break;

        }
    }
}