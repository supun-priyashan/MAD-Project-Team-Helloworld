package com.example.daytoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExpenseManage extends AppCompatActivity {

    private EditText amount_ed,type_ed,note_ed;

    private Button btnView,saveBtn;

    DatabaseReference reff;
    Data data;
    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Toast.makeText(ExpenseManage.this,"sussuccesful",Toast.LENGTH_SHORT).show();

        amount_ed = (EditText) findViewById(R.id.amount_up);
        type_ed = (EditText) findViewById(R.id.type_ed);
        note_ed = (EditText) findViewById(R.id.note_ed);



        btnView = (Button) findViewById(R.id.btnUpdateexpense);
        saveBtn = (Button) findViewById(R.id.btnSave);

        reff= FirebaseDatabase.getInstance().getReference().child("Data");
        data=new Data();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float amount = Float.parseFloat((amount_ed.getText().toString().trim()));

                data.setAmount(amount);
                data.setType(type_ed.getText().toString());
                data.setNote(note_ed.getText().toString());

                reff.child(String.valueOf(maxid+1)).setValue(data);

                Toast.makeText(ExpenseManage.this,"data insert successfully",Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplicationContext(), ExpenseManage2.class));


        }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ExpenseManage2.class));
            }
        });
    }
}