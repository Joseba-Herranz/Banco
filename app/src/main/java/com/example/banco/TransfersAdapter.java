package com.example.banco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransfersAdapter extends RecyclerView.Adapter<TransfersAdapter.TransferViewHolder> {
    private List<Transfer> series;
    private Context context;
    private DataManager databaseHelper;

    public TransfersAdapter(Context context, List<Transfer> series, DataManager databaseHelper) {
        this.context = context;
        this.series = series;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public TransferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_view, parent, false);
        return new TransferViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(TransferViewHolder holder, int position) {
        Transfer serie = series.get(position);

        holder.destiny.setText(serie.getDestiny());
        holder.cuantity.setText(serie.getCuantity());
        holder.date.setText(serie.getDate());

    }


    @Override
    public int getItemCount() {
        return series.size();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder{
        public TextView destiny, cuantity, date;


        public TransferViewHolder(View itemView) {
            super(itemView);
            // Actualizar los IDs para que coincidan con los definidos en el layout XML
            destiny = itemView.findViewById(R.id.destiny);
            cuantity = itemView.findViewById(R.id.cuantity);
            date = itemView.findViewById(R.id.date);


        }
}}