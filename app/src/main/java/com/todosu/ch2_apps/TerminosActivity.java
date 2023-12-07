package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TerminosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);
        regresar();
    }


    //Metodo para regresar

    public void regresar(){
        ImageButton btnRegresar = findViewById(R.id.terminos_btn_regresar);
        btnRegresar.setOnClickListener(view -> {
            // Cerrar la actividad actual y regresar a la actividad anterior
            Intent intent = new Intent(TerminosActivity.this, RegistroActivity.class);
            startActivity(intent);
        });
    }
}