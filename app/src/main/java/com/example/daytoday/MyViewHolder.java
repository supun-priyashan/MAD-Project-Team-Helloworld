package com.example.daytoday;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    Button btnUp,btnDl;
    TextView name,date_,amount,dis;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        date_ = itemView.findViewById(R.id.date_);
        amount = itemView.findViewById(R.id.amount);
        dis = itemView.findViewById(R.id.dis);
        btnUp = itemView.findViewById(R.id.btnUp);
        btnDl = itemView.findViewById(R.id.btnDl);


    }
}
