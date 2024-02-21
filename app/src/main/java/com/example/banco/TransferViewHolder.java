package com.example.banco;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TransferViewHolder extends RecyclerView.ViewHolder{
    public TextView name, temp, chap, first, genero;
    public CheckBox check;
    public Button btnEdit, btnDelete;

    public TransferViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.destiny);
        temp = itemView.findViewById(R.id.cuantity);
        chap = itemView.findViewById(R.id.date);

    }
}

