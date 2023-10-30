package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        guardar();
    }


    private void guardar() {
        Button btnRegresar = findViewById(R.id.btnGuardar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, InicioSesion.class);
            startActivity(intent);
        });

    }
}