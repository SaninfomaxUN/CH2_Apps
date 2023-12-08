package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EventosActivity extends AppCompatActivity {

    private RecyclerView rvEventos;
    private EventosAdapter eventosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        rvEventos = findViewById(R.id.rvEventos);
        rvEventos.setLayoutManager(new LinearLayoutManager(this));

        List<String> eventos = FileManager.leerDatos(this);

        eventosAdapter = new EventosAdapter(this, eventos);
        rvEventos.setAdapter(eventosAdapter);

        crearEvento();
        regresar();
    }

    private void crearEvento() {
        Button btnCrearEvento = findViewById(R.id.CrearEvento);
        btnCrearEvento.setOnClickListener(view -> {
            Intent intent = new Intent(this, CrearEventoActivity.class);
            startActivity(intent);
        });
    }

    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresarM);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this,MenuPrincipalActivity.class);
            startActivity(intent);
        });
    }
}
