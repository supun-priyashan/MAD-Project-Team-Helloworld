package com.example.daytoday;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daytoday.Model.Item;
import com.example.daytoday.Model.List;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class ListOfListsActivity extends AppCompatActivity {

    private FloatingActionButton fab_btn;
    private DatabaseReference mDatabase;
    //private FirebaseAuth mAuth;

    private FirebaseRecyclerOptions<List> options;
    private FirebaseRecyclerAdapter<List, ListOfListsActivity.MyViewHolder> adapter;

    private RecyclerView recyclerView;

    private String type;
    private float total;
    private  String note;
    private  String postKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_lists);

        mDatabase = FirebaseDatabase.getInstance().getReference("Lists");
        mDatabase.keepSynced(true);

        fab_btn = findViewById(R.id.fabLists);
        recyclerView = findViewById(R.id.recyclerLists);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        fab_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                customDialog();
            }
        });

        options = new FirebaseRecyclerOptions.Builder<List>()
                .setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Lists"),List.class).build();

        adapter = new FirebaseRecyclerAdapter<List, ListOfListsActivity.MyViewHolder>(options) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void onBindViewHolder(@NonNull ListOfListsActivity.MyViewHolder holder, final int position, @NonNull final List model) {

                holder.setType(model.getType());
                holder.setDate(model.getDate());
                holder.setAmount(model.getAmount());
                holder.setNote(model.getNote());

                /*holder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        postKey = getRef(position).getKey();
                        type = model.getType();
                        note = model.getNote();
                        amount = model.getAmount();

                        updateData();
                    }
                });*/

            }

            @NonNull
            @Override
            public ListOfListsActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
                return new ListOfListsActivity.MyViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);

    }

    private void customDialog(){

        AlertDialog.Builder myDialog = new AlertDialog.Builder(ListOfListsActivity.this);
        LayoutInflater inflater = LayoutInflater.from(ListOfListsActivity.this);
        View myView = inflater.inflate(R.layout.input_list,null);

        final AlertDialog dialog = myDialog.create();

        if (dialog.getWindow() != null)
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        dialog.setView(myView);

        final EditText type = myView.findViewById(R.id.edit_list_name);
        final EditText note = myView.findViewById(R.id.edit_list_desc);
        final Button save = myView.findViewById(R.id.btnSave);

        /*type.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });*/

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mType = type.getText().toString().trim();
                float mAmount = 0;
                String mNote = note.getText().toString().trim();

                if(TextUtils.isEmpty(mType)){
                    type.setError("Enter list name");
                    return;
                }

                if(TextUtils.isEmpty(mNote)){
                    note.setError("Enter description");
                    return;
                }

                String id = mDatabase.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());

                List list = new List(mType,mAmount,mNote,date,id);

                mDatabase.child(id).setValue(list);

                Toast.makeText(ListOfListsActivity.this,"Item Added",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        View myView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
        }

        public void setType(String type){
            TextView mType = myView.findViewById(R.id.listType);
            mType.setText(type);
        }

        public void setNote(String note){
            TextView mNote = myView.findViewById(R.id.listNote);
            mNote.setText(note);
        }

        public void setDate(String date){
            TextView mDate = myView.findViewById(R.id.listDate);
            mDate.setText(date);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public  void setAmount(float amount){

            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String nAmount = decimalFormat.format(amount);

            TextView mAmount = myView.findViewById(R.id.listAmount);
            mAmount.setText(String.valueOf(nAmount));
        }
    }

    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}