package com.example.daytoday;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class dataRetrive extends RecyclerView.ViewHolder {

    TextView amountR,typeR,noteR;
    ImageView btnUpdate1,btnDelete;


    public dataRetrive(@NonNull View itemView) {
        super(itemView);


        amountR = itemView.findViewById(R.id.amount_Re);
        typeR = itemView.findViewById(R.id.type_Re);
        noteR = itemView.findViewById(R.id.note_Re);

        btnUpdate1 = itemView.findViewById(R.id.editImage);
        btnDelete = itemView.findViewById(R.id.deleteImage);


    }
}
