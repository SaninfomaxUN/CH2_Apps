package com.todosu.ch2_apps;

import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.todosu.ch2_apps.models.Evento;

import java.util.ArrayList;

public class EstadisticasActivity extends AppCompatActivity {
    private HorizontalBarChart horizontalBarChart;

    private Evento eventoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        horizontalBarChart = findViewById(R.id.horizontalBarChart);
        BarData data = new BarData(getDataSet());
        horizontalBarChart.setData(data);
        horizontalBarChart.animateXY(2000, 2000);
        horizontalBarChart.invalidate();

        seleccionarEventos();
        regresar();
    }

    public void seleccionarEventos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

       // Convertir ArrayList a CharSequence[]
        /* CharSequence[] nombresCharSequence = Data.getListaEventosDelUsuarioConectado().stream()
                .map(Evento::getNombreEvento)
                .toArray(CharSequence[]::new);*/

        final CharSequence[] eventos = new CharSequence[4];

        eventos[0] = "Evento 1";
        eventos[1] = "Evento 2";
        eventos[2] = "Evento 3";
        eventos[3] = "Evento 4";


        builder.setTitle("Selecciona el evento:")
                .setItems(eventos, (dialog, posEventoSeleccionado) -> {
                    Toast.makeText(
                                    EstadisticasActivity.this,
                                    "Seleccionaste: " + eventos[posEventoSeleccionado],
                                    Toast.LENGTH_SHORT)
                            .show();
                    eventoSeleccionado = Data.getEventoDelUsuarioConectado(eventos[posEventoSeleccionado].toString());
                })
                .setCancelable(false);

        builder.create();
        builder.show();
    }

    private BarDataSet getDataSet() {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries,"hi");
        dataset.setColor(Color.parseColor("#3f8d55"));
        return dataset;
    }

    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        });

    }


}