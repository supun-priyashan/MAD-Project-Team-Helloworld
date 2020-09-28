package com.example.daytoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateDebt extends AppCompatActivity {

    private EditText name,_date,amount_,des;
    Button btnUpdate;

    DatabaseReference reference;

    public Bundle getBundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        reference= FirebaseDatabase.getInstance().getReference().child("New Debt");

        name = (EditText)findViewById(R.id.name);
        _date = (EditText)findViewById(R.id._date);
        amount_ = (EditText)findViewById(R.id.amount_);
        des= (EditText)findViewById(R.id.des);
        btnUpdate=(Button)findViewById(R.id.btnupdate);

        getBundle= this.getIntent().getExtras();

        String Name = getBundle.getString("Name");
        String Date = getBundle.getString("Date");
        Float Amount = getBundle.getFloat("Amount");
        String Description = getBundle.getString("Description");

        final String key = getBundle.getString("key");

             final    String s = String.valueOf(Amount);
        amount_.setText(s);
        name.setText(Name);
        _date.setText(Date);

        des.setText(Description);

       btnUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String Named = name.getText().toString().trim();
               String Dated = _date.getText().toString().trim();
               String Amountd = amount_.getText().toString().trim();
               String Descd = des.getText().toString().trim();


               float floatamount = Float.parseFloat(Amountd);

               Debt debt= new Debt(Named,Dated,floatamount,Descd);

               reference.child(key).setValue(debt);
               startActivity(new Intent(updateDebt.this, DebtListActivity.class));
           }
       });







    }
}