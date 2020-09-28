package com.example.daytoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DebtListActivity extends AppCompatActivity {

    private FirebaseRecyclerOptions<Debt> options;
    private FirebaseRecyclerAdapter<Debt, DebtViewHolder> adapter;

    private RecyclerView recyclerView;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtlist);




        db= FirebaseDatabase.getInstance().getReference().child("New Debt");

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Debt>().setQuery(FirebaseDatabase.getInstance().getReference().child("New Debt"),Debt.class).build();
        adapter = new FirebaseRecyclerAdapter<Debt, DebtViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull final DebtViewHolder holder, int position, @NonNull final Debt debt) {


                holder.name.setText("Name="+debt.getName());
                holder.date_.setText("Date="+debt.getDate());
                holder.amount.setText("Amount="+ " "+ debt.getAmount());
                holder.dis.setText("Description="+debt.getDiscription());

                final String postkey= getRef(position).getKey();
                holder.btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle bundle = new Bundle();
                        bundle.putString("Name",debt.getName());
                        bundle.putString("Date",debt.getDate());
                        bundle.putFloat("Amount",debt.getAmount());
                        bundle.putString("Description",debt.getDiscription());

                        bundle.putString("key",postkey);

                        Intent intent = new Intent(view.getContext(), updateDebt.class);

                        intent.putExtras(bundle);

                        view.getContext().startActivity(intent);
                    }
                });

                holder.btnDl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        db.child(postkey).removeValue();


                    }
                });

            }

            @NonNull
            @Override
            public DebtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.view_layout,parent,false);
                return new DebtViewHolder(view);

            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

}