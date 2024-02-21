package com.example.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MyAccounts extends AppCompatActivity {

        private DataManager db;
        private Spinner userSpinner;

        private Button bCerrar, bEntrar;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataManager(this);
        db.inicializarDatosPorDefecto();


        String[] usuarios = {"123456", "456789", "78945"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, usuarios);

        userSpinner.setAdapter(adapter);


        bCerrar = findViewById(R.id.bCerrar);
        bCerrar.setOnClickListener(v -> {

            finish();
        });

        bEntrar = findViewById(R.id.bEntrar);
        bEntrar.setOnClickListener(view -> {

            String usuarioSeleccionado = userSpinner.getSelectedItem().toString();


            Intent intent = new Intent(MyAccounts.this, TransferActivity.class);

            intent.putExtra("usuarioSeleccionado", usuarioSeleccionado);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }
}