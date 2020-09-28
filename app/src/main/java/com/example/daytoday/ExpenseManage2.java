package com.example.daytoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpenseManage2 extends AppCompatActivity {

    private FirebaseRecyclerOptions<Data> options;
    private FirebaseRecyclerAdapter<Data, dataRetrive> adapter;

    private ImageView btnUpdate,btnDelete;

    private RecyclerView recyclerView;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense2);

        recyclerView = findViewById(R.id.receive);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnUpdate = findViewById(R.id.editImage);
        btnDelete = (ImageView) findViewById(R.id.deleteImage);

        reff= FirebaseDatabase.getInstance().getReference().child("Data");

        options = new FirebaseRecyclerOptions.Builder<Data>()
                .setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Data"), Data.class).build();

        adapter = new FirebaseRecyclerAdapter<Data, dataRetrive>(options) {
            @Override
            protected void onBindViewHolder(@NonNull dataRetrive dataRetrive, final int i, @NonNull Data data) {
                dataRetrive.amountR.setText("Amount - " + " " + data.getAmount());
                dataRetrive.typeR.setText("Type - " + data.getType());
                dataRetrive.noteR.setText("Note - " + data.getNote());
                String postkey = getRef(i).getKey();

                dataRetrive.btnUpdate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle bundle = new Bundle();

                        bundle.putFloat("Amount",data.getAmount());
                        bundle.putString("type",data.getType());
                        bundle.putString("note",data.getNote());
                        bundle.putString("key",postkey);
                        Intent intent = new Intent(view.getContext(),updateData.class);
                        intent.putExtras(bundle);

                        view.getContext().startActivity(intent);
                    }
                });

                dataRetrive.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reff.child(postkey).removeValue();
                        Toast.makeText(ExpenseManage2.this,postkey,Toast.LENGTH_LONG).show();
                    }
                });
            }

            @NonNull
            @Override
            public dataRetrive onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_data_receive, parent, false);
                return new dataRetrive(view);
            }

        };


        recyclerView.setAdapter(adapter);




    };

    @Override
    protected void onStart() {


        super.onStart();
        adapter.startListening();
    }
}