package com.example.androidapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    private Button btnToRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnToRegistration = (Button) findViewById(R.id.btnToRegistration);
        btnToRegistration.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        int ce = v.getId();
        if(ce == R.id.btnToRegistration)
        {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);
            this.finish();
        }

    }
}