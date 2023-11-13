package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        guardar();
        regresar();
    }


    private void guardar() {
        Button btnRegresar = findViewById(R.id.btnGuardar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, InicioSesionActivity.class);
            startActivity(intent);
        });

    }

    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, InicioSesionActivity.class);
            startActivity(intent);
        });

    }
}