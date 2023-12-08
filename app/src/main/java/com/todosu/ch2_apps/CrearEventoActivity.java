package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.todosu.ch2_apps.models.Evento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CrearEventoActivity extends AppCompatActivity {

    private TextInputEditText edtNombreEvento, edtFechaEvento, edtLugarEvento, edtAsistentes;
    private TextInputLayout tilNombreEvento, tilFechaEvento, tilLugarEvento, tilAsistentes;
    private Button btnGuardarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_eventos);

        edtNombreEvento = findViewById(R.id.edtNombreEvento);
        edtFechaEvento = findViewById(R.id.edtFechaEvento);
        edtLugarEvento = findViewById(R.id.edtLugarEvento);
        edtAsistentes = findViewById(R.id.edtAsistentes);
        tilNombreEvento = findViewById(R.id.tilNombreEvento);
        tilFechaEvento = findViewById(R.id.tilFechaEvento);
        tilLugarEvento = findViewById(R.id.tilLugarEvento);
        tilAsistentes = findViewById(R.id.tilAsistentes);
        btnGuardarEvento = findViewById(R.id.btnGuardarEvento);

        btnGuardarEvento.setOnClickListener(v -> guardarEvento());

        regresar();
    }

    private void guardarEvento() {
        clearErrors();
        if (!validarCampos()) {
            return;
        }
        // Obtener datos del usuario
        String nombreEvento = edtNombreEvento.getText().toString();
        String lugarEvento = edtLugarEvento.getText().toString();
        LocalDate fechaEvento = formatDate(edtFechaEvento.getText().toString());
        if (fechaEvento == null) {
            showErrorDate();
            return;
        }
        int asistentesEvento = Integer.parseInt(edtAsistentes.getText().toString());

        // Crear un objeto Evento
        Evento eventoNuevo = new Evento(nombreEvento, lugarEvento, fechaEvento,  asistentesEvento);
        // Guardar datos en el archivo
        Data.agregarEventoDelUsuarioConectado(eventoNuevo, this );

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

    private boolean validarCampos() {
        boolean isValid = true;
        if (edtNombreEvento.getText().toString().isEmpty()) {
            tilNombreEvento.setError("El nombre del evento es requerido");
            isValid = false;
        }
        if (edtFechaEvento.getText().toString().isEmpty()) {
            tilFechaEvento.setError("La fecha del evento es requerida");
            isValid =  false;
        }
        if (edtLugarEvento.getText().toString().isEmpty()) {
            tilLugarEvento.setError("El lugar del evento es requerido");
            isValid =  false;
        }
        if (edtAsistentes.getText().toString().isEmpty()) {
            tilAsistentes.setError("El número de asistentes es requerido");
            isValid =  false;
        }
        return isValid;
    }
    private void clearErrors() {
        tilNombreEvento.setError(null);
        tilFechaEvento.setError(null);
        tilLugarEvento.setError(null);
        tilAsistentes.setError(null);
    }

    private void showErrorDate() {
        tilFechaEvento.setError("Por favor ingrese la fecha en el formato correcto!");
        edtFechaEvento.setText("");
    }

    private static LocalDate formatDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            return null;
        }
    }

}






