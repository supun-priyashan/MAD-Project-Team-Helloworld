package com.example.daytoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView signin;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = findViewById(R.id.regMail);
        password = findViewById(R.id.regPassword);

        register = findViewById(R.id.regBtn);
        signin = findViewById(R.id.haveAccount);

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                
            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ExpenseManager.class));
            }
        });
    }
}