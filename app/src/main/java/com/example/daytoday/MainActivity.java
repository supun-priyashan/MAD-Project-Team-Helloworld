package com.example.daytoday;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
     private EditText cus_name,date,d_amount,discription;
     private Button btn,view;
     Debt dept;
     DatabaseReference db;

     long maxid = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Successfully!", Toast.LENGTH_SHORT).show();






        cus_name=(EditText)findViewById(R.id.cus_name);
        date = (EditText)findViewById(R.id.date);
        d_amount = (EditText)findViewById(R.id.d_amount);
        discription=(EditText)findViewById(R.id.discription);
        btn=(Button)findViewById(R.id.btn);
        view=(Button)findViewById(R.id.view);


        db= FirebaseDatabase.getInstance().getReference().child("New Debt");

        dept= new Debt();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Float amount = Float.parseFloat(d_amount.getText().toString().trim());



                dept.setName(cus_name.getText().toString().trim());
                dept.setDate(date.getText().toString().trim());
                dept.setAmount(amount);
                dept.setDiscription(discription.getText().toString().trim());


                String did=db.push().getKey();

                db.child(String.valueOf(did)).setValue(dept);


                Toast.makeText(MainActivity.this, "Insert Successfully!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), MainActivity2.class));



            }




        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });




     }
    }
