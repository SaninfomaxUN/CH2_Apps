package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        calcularResultados();
        regresar();
    }


    private void calcularResultados() {
        Button btnRegresar = findViewById(R.id.result);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, EstadisticasActivity.class);
            startActivity(intent);
        });

    }

    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        });

    }
}