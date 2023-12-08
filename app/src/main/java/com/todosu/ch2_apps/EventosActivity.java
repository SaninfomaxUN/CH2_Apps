package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.todosu.ch2_apps.models.Evento;

import java.util.ArrayList;

public class EventosActivity extends AppCompatActivity {

    private RecyclerView rvEventos;
    private EventosAdapter eventosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        rvEventos = findViewById(R.id.rvEventos);
        rvEventos.setLayoutManager(new LinearLayoutManager(this));

        // Leer datos del Diccionario Data
        ArrayList<Evento> eventos = Data.getListaEventosDelUsuarioConectado();

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
        btnRegresar.setOnClickListener(view -> returnMain());
    }

    //Sobreescritura del método para cerrar sesión al presionar el botón de atrás
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            returnMain();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Cerrar sesión
    public void returnMain() {
        Intent intent = new Intent(this,MenuPrincipalActivity.class);
        startActivity(intent);
    }
}
