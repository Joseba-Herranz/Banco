package com.example.banco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransferActivity extends AppCompatActivity {

    private RecyclerView transfersRecyclerView;

    private DataManager db;

    private String accountNumber;

    private TransfersAdapter transfersAdapter;
    private EditText name, temp, chap;
    private Button bBack, bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_activity);


        db = new DataManager(this);
        transfersRecyclerView = findViewById(R.id.transfersRecyclerView);
        transfersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        accountNumber = getIntent().getStringExtra("account_number");


        bAdd = findViewById(R.id.cerrar);
        bBack = findViewById(R.id.guardar);

        name = findViewById(R.id.destiny);
        temp = findViewById(R.id.cuantity);
        chap = findViewById(R.id.date);

        String userId = getIntent().getStringExtra("usuarioSeleccionado");


        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nombre = Integer.parseInt(name.getText().toString().trim());
                int cuantity = Integer.parseInt(temp.getText().toString().trim());
                String date = chap.getText().toString().trim();


                Transfer serie = new Transfer(nombre, cuantity, date);
                db.addTransferencia(userId, serie);

                finish();
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualiza la lista de series cada vez que se retoma la actividad, por si hubo cambios
        String userId = getIntent().getStringExtra("usuarioSeleccionado");
        List<Transfer> series = db.getTransfer(userId);
       // transferAdapter.updateSeries(series);
    }
}