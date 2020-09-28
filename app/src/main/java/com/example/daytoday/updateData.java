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

public class updateData extends AppCompatActivity {

    private EditText amount_up,type_up,note_up;
    private Button btnUpdate;




    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Bundle getBundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        reference = FirebaseDatabase.getInstance().getReference().child("Data");

        amount_up = (EditText) findViewById(R.id.amount_up);
        type_up = (EditText) findViewById(R.id.type_up);
        note_up = (EditText) findViewById(R.id.note_up);
        btnUpdate = (Button) findViewById(R.id.btnUpdateexpense);

        getBundle = this.getIntent().getExtras();
        Float amount = getBundle.getFloat("Amount");
        String type = getBundle.getString("type");
        String note = getBundle.getString("note");
        String key = getBundle.getString("key");

        String str = String.valueOf(amount);
        amount_up.setText(str);
        type_up.setText(type);
        note_up.setText(note);

        Toast.makeText(updateData.this,key,Toast.LENGTH_LONG).show();


    btnUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String amountUp = amount_up.getText().toString().trim();
        String typeUp = type_up.getText().toString().trim();
        String noteUp = note_up.getText().toString().trim();

        float floatAmount = Float.parseFloat(amountUp);

        Data data = new Data(floatAmount,typeUp,noteUp);

        reference.child(key).setValue(data);

        startActivity(new Intent(updateData.this,MainActivity2.class));

    }
});







    }
}