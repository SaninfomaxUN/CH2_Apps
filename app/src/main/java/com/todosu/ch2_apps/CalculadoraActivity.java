package com.todosu.ch2_apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.todosu.ch2_apps.models.registroEvento;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.todosu.ch2_apps.models.Evento;



public class CalculadoraActivity extends AppCompatActivity {

    String nombreEvento;
    EditText plastico, aluminio, carton, tetrapack, vidrio;
    Button consultar, guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_calculadora );

        plastico = findViewById( R.id.editTextPlastico );
        aluminio = findViewById( R.id.editTextAluminio );
        carton = findViewById( R.id.editTextCarton );
        tetrapack = findViewById( R.id.editTextTetraPack );
        vidrio = findViewById( R.id.editTextVidrio );
        guardar = findViewById( R.id.registrar );
        consultar=findViewById( R.id.btnConsultar );

        consultar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarEventos();
            }
        } );
        guardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreEvento ==null){
                    Toast.makeText( getApplicationContext(), "Por favor seleccione un evento", Toast.LENGTH_LONG ).show();
                    return;
                }
                if (plastico.getText().toString().isEmpty() ||
                        aluminio.getText().toString().isEmpty() ||
                        carton.getText().toString().isEmpty() ||
                        tetrapack.getText().toString().isEmpty() ||
                        vidrio.getText().toString().isEmpty()) {
                    Toast.makeText( getApplicationContext(), "Todos los campos deben diligenciarse", Toast.LENGTH_LONG ).show();
                    return;
                } else {
                    double cantidadplastico = Double.parseDouble( plastico.getText().toString() );
                    double cantidadaluminio = Double.parseDouble( aluminio.getText().toString() );
                    double cantidadcarton = Double.parseDouble( carton.getText().toString() );
                    double cantidadtetrapack = Double.parseDouble( tetrapack.getText().toString() );
                    double cantidadvidrio = Double.parseDouble( vidrio.getText().toString() );

                    registroEvento registro = new registroEvento( cantidadplastico, cantidadaluminio, cantidadcarton, cantidadtetrapack, cantidadvidrio );
                    Data.agregarRegistroDelEventoDelUsuarioConectado(nombreEvento,registro, CalculadoraActivity.this );

                    Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), MenuPrincipalActivity.class);
                    startActivity(intent);
                }
            }
        } );

        setEventReceived();
        regresar();
    }


    public void seleccionarEventos() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        // Convertir ArrayList a CharSequence[]
        CharSequence[] eventos = Data.getListaEventosDelUsuarioConectado().stream()
                .map(Evento::getNombreEvento)
                .toArray(CharSequence[]::new);

        builder.setTitle("Selecciona el evento:")
                .setItems(eventos, (dialog, posEventoSeleccionado) -> {
                    Toast.makeText(
                                    CalculadoraActivity.this,
                                    "Seleccionaste: " + eventos[posEventoSeleccionado],
                                    Toast.LENGTH_SHORT)
                            .show();
                    nombreEvento =(eventos[posEventoSeleccionado].toString());
                    consultar.setText(nombreEvento);

                });

        builder.create();
        builder.show();
    }

    private void setEventReceived() {
        Intent intent = getIntent();

        // Verificar si el Intent tiene un extra con la clave "nombreEvento"
        if (intent.hasExtra("nombreEvento")) {
            nombreEvento = intent.getStringExtra("nombreEvento");
            consultar.setText(nombreEvento);
        }
    }


    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        });

    }


}
