package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearEventoActivity extends AppCompatActivity {

    private EditText edtNombreEvento, edtFechaEvento, edtLugarEvento, edtAsistentes;
    private Button btnGuardarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_eventos);

        edtNombreEvento = findViewById(R.id.edtNombreEvento);
        edtFechaEvento = findViewById(R.id.edtFechaEvento);
        edtLugarEvento = findViewById(R.id.edtLugarEvento);
        edtAsistentes = findViewById(R.id.edtAsistentes);
        btnGuardarEvento = findViewById(R.id.btnGuardarEvento);

        btnGuardarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEvento();
            }
        });

        regresar();
    }

    private void guardarEvento() {
        // Obtener datos del usuario
        String nombreEvento = edtNombreEvento.getText().toString();
        String fechaEvento = edtFechaEvento.getText().toString();
        String lugarEvento = edtLugarEvento.getText().toString();
        String asistentesEvento = edtAsistentes.getText().toString();

        // Crear un objeto Evento y convertirlo a una cadena
        String datosEvento = String.format("%s,%s,%s,%s", nombreEvento, fechaEvento, lugarEvento, asistentesEvento);

        // Guardar datos en el archivo
        FileManager.guardarDatos(this, datosEvento);

        // Limpiar los campos después de guardar
        edtNombreEvento.getText().clear();
        edtFechaEvento.getText().clear();
        edtLugarEvento.getText().clear();
        edtAsistentes.getText().clear();

        mostrarAlerta();

        Intent intent = new Intent(this, EventosActivity.class);
        startActivity(intent);
    }

    private void mostrarAlerta() {
        Toast.makeText(this, "Los datos fueron ingresados", Toast.LENGTH_LONG).show();
    }
    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresarE);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this,EventosActivity.class);
            startActivity(intent);
        });
    }
}






