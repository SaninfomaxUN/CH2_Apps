package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        calcularResultados();
    }


    private void calcularResultados() {
        Button btnRegresar = findViewById(R.id.result);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, EstadisticasActivity.class);
            startActivity(intent);
        });

    }
}