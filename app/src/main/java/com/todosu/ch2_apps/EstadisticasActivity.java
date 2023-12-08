package com.todosu.ch2_apps;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.button.MaterialButton;
import com.todosu.ch2_apps.models.Evento;
import com.todosu.ch2_apps.models.registroEvento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class EstadisticasActivity extends AppCompatActivity {

    private MaterialButton btnSeleccEvento;
    private LinearLayout BarChartLayout;
    private LinearLayout minBarChartLayout;
    private LinearLayout maxBarChartLayout;
    private LinearLayout promBarChartLayout;
    private LinearLayout totalBarChartLayout;
    private Evento eventoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        findReferences();
        initBtnSeleccionarEvento();
        revisarEventosRegistrados();
        regresar();
    }
    // Inicializar el botón para seleccionar el evento
    private void initBtnSeleccionarEvento() {
        btnSeleccEvento.setOnClickListener(v -> seleccionarEventos());
    }
    // Revisar si hay eventos registrados
    private void revisarEventosRegistrados() {
        if (Data.getListaEventosDelUsuarioConectado().isEmpty()){
            btnSeleccEvento.setEnabled(false);
            btnSeleccEvento.setText("No tienes eventos");
        }
    }
    // Cambiar el nombre del botón según el evento seleccionado
    private void cambiarNombreBtnSeleccionarEvento() {
        if (eventoSeleccionado == null) {
            btnSeleccEvento.setText("Ver Todos");
        }else{
            btnSeleccEvento.setText(eventoSeleccionado.getNombreEvento());
        }
    }

    // Obtener Referencia de los Gráficos
    private void findReferences() {
        BarChartLayout = findViewById(R.id.BarChartLayout);
        minBarChartLayout = findViewById(R.id.minBarChartLayout);
        maxBarChartLayout = findViewById(R.id.maxBarChartLayout);
        promBarChartLayout = findViewById(R.id.promBarChartLayout);
        totalBarChartLayout = findViewById(R.id.totalBarChartLayout);
        btnSeleccEvento = findViewById(R.id.btnSeleccEvento);

    }

    // Método para seleccionar el evento y mostrar los gráficos
    public void seleccionarEventos() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

       // Convertir ArrayList a CharSequence[]
        CharSequence[] eventos = Data.getListaEventosDelUsuarioConectado().stream()
                .map(Evento::getNombreEvento)
                .toArray(CharSequence[]::new);
        // Opción adicional
        CharSequence opcTodos = "Ver Todos";

        // Concatenar la opción adicional al arreglo existente
        eventos = Stream.concat(Stream.of(eventos), Stream.of(opcTodos))
                .toArray(CharSequence[]::new);


        CharSequence[] finalEventos = eventos;
        builder.setTitle("Selecciona el evento:")
                .setItems(eventos, (dialog, posEventoSeleccionado) -> {
                    Toast.makeText(
                                    EstadisticasActivity.this,
                                    "Seleccionaste: " + finalEventos[posEventoSeleccionado],
                                    Toast.LENGTH_SHORT)
                            .show();
                    showCharts(finalEventos[posEventoSeleccionado].toString());
                    BarChartLayout.setVisibility(LinearLayout.VISIBLE);
                });

        builder.create();
        builder.show();
    }

    private void showCharts(String opcSeleccionada) {
        ArrayList<Double> minimosEventosSeleccionados = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        ArrayList<Double> maximosEventoSeleccionado = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        ArrayList<Double> promediosEventoSeleccionado = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        ArrayList<Double> totalesEventoSeleccionado = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));

        if (!opcSeleccionada.equals("Ver Todos")) {
            this.eventoSeleccionado = Data.getEventoDelUsuarioConectado(opcSeleccionada);
            // Obtener el mínimo
            minimosEventosSeleccionados = getMinimoPorEvento(eventoSeleccionado);
            // Obtener el máximo
            maximosEventoSeleccionado = getMaximoPorEvento(eventoSeleccionado);
            // Obtener el promedio
            promediosEventoSeleccionado = getPromedioPorEvento(eventoSeleccionado);
            // Obtener el total
            totalesEventoSeleccionado = getTotalPorEvento(eventoSeleccionado);
        }else{
            this.eventoSeleccionado = null;
            double fraccionProm = 1.0 / Data.getListaEventosDelUsuarioConectado().size();
            for (Evento evento : Data.getListaEventosDelUsuarioConectado()) {
                // Obtener cada mínimo y agregarlo a la lista
                ArrayList<Double> minimoEventoSeleccionado = getMinimoPorEvento(evento);
                for (int i = 0; i < 5; i++) {
                    minimosEventosSeleccionados.set(i, Math.min(minimosEventosSeleccionados.get(i), minimoEventoSeleccionado.get(i)));
                }
                // Obtener cada máximo y agregarlo a la lista
                ArrayList<Double> maximoEventoSeleccionado = getMaximoPorEvento(evento);
                for (int i = 0; i < 5; i++) {
                    maximosEventoSeleccionado.set(i, Math.max(maximosEventoSeleccionado.get(i),maximoEventoSeleccionado.get(i)));
                }
                // Obtener cada promedio y agregarlo a la lista
                ArrayList<Double> promedioEventoSeleccionado = getPromedioPorEvento(evento);
                for (int i = 0; i < 5; i++) {
                    promediosEventoSeleccionado.set(i, promediosEventoSeleccionado.get(i) + promedioEventoSeleccionado.get(i)*fraccionProm);
                }

                // Obtener cada total y agregarlo a la lista
                ArrayList<Double> totalEventoSeleccionado = getTotalPorEvento(evento);
                for (int i = 0; i < 5; i++) {
                    totalesEventoSeleccionado.set(i, totalesEventoSeleccionado.get(i) + totalEventoSeleccionado.get(i));
                }
            }
        }
        setBarChart(getDataSet(minimosEventosSeleccionados), R.id.minBarChart, minBarChartLayout);
        setBarChart(getDataSet(maximosEventoSeleccionado), R.id.maxBarChart, maxBarChartLayout);
        setBarChart(getDataSet(promediosEventoSeleccionado), R.id.promBarChart, promBarChartLayout);
        setBarChart(getDataSet(totalesEventoSeleccionado), R.id.totalBarChart, totalBarChartLayout);
        cambiarNombreBtnSeleccionarEvento();

    }

    private void setBarChart(BarDataSet dataset, int idBarChart, LinearLayout linearLayout) {
        BarChart barChart = new BarChart(this);
        barChart.setId(idBarChart);
        barChart.setData(new BarData(dataset));

        // Configuración de la cuadrícula
        barChart.getAxisLeft().setDrawGridLines(false); // Deshabilita las líneas de la cuadrícula en el eje Y izquierdo
        barChart.getXAxis().setDrawGridLines(false); // Deshabilita las líneas de la cuadrícula en el eje X
        barChart.getAxisRight().setDrawGridLines(false); // Deshabilita las líneas de la cuadrícula en el eje Y derecho
        // Oculta los valores del eje X
        barChart.getXAxis().setDrawLabels(false);
        // Oculta la descripción
        barChart.getDescription().setEnabled(false);
        // Ocultar los valores del eje Y
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);

        // Configurar tamaño de la fuente de los valores sobre las barras
        barChart.getBarData().setValueTextSize(20f);

        // Configura las leyendas personalizadas
        ArrayList<LegendEntry> listaLeyendas = new ArrayList<>();
        listaLeyendas.add(new LegendEntry("Plastico", Legend.LegendForm.DEFAULT, 10f, 2f, null, Color.rgb(43, 61, 55)));
        listaLeyendas.add(new LegendEntry("Aluminio", Legend.LegendForm.DEFAULT, 10f, 2f, null, Color.rgb(60, 98, 85)));
        listaLeyendas.add(new LegendEntry("Carton", Legend.LegendForm.DEFAULT, 10f, 2f, null, Color.rgb(97, 135, 110)));
        listaLeyendas.add(new LegendEntry("Tetrapack", Legend.LegendForm.DEFAULT, 10f, 2f, null, Color.rgb(166, 187, 141)));
        listaLeyendas.add(new LegendEntry("Vidrio", Legend.LegendForm.DEFAULT, 10f, 2f, null, Color.rgb(234, 231, 177)));
        barChart.getLegend().setCustom(listaLeyendas);

        // -- Configuración de la leyenda
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE); // Puedes ajustar la forma según tus preferencias
        legend.setTextSize(14f); // Establece el tamaño de la fuente de la leyenda
        // Establece la posición de la leyenda
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        // Evitar que la leyenda se salga de la ventana
        legend.setWordWrapEnabled(true);

        barChart.animateXY(2000, 2000);
        barChart.invalidate();  // refrescar el gráfico para que se muestren los cambios


        showBarChart(linearLayout, barChart);

    }

    // Agregar BarData al LineChart
    private void showBarChart(LinearLayout linearLayout, BarChart barChart) {
        int childCount = linearLayout.getChildCount();
        linearLayout.removeViewAt(childCount - 1);

        linearLayout.addView(barChart);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(236));
        layoutParams.setMargins(dpToPx(25), dpToPx(25), dpToPx(25), dpToPx(25));
        barChart.setLayoutParams(layoutParams);
    }

    private BarDataSet getDataSet(ArrayList<Double> YAxisValues) {
        int posX = 0;
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (Double value : YAxisValues) {
            entries.add(new BarEntry(posX, value.floatValue()));
            posX++;
        }
        BarDataSet dataset = new BarDataSet(entries,"Minimo");
        // Aplicar colores a las barras segun las leyendas
        dataset.setColors(Color.rgb(43, 61, 55), Color.rgb(60, 98, 85), Color.rgb(97, 135, 110), Color.rgb(166, 187, 141), Color.rgb(234, 231, 177));

        return dataset;
    }

    // Método para obtener el mínimo de cada material por evento
    private ArrayList<Double> getMinimoPorEvento(Evento eventoObtenerRegistro) {
        ArrayList<registroEvento> listaRegistros = new ArrayList<>(eventoObtenerRegistro.getListaRegistros());
        if (listaRegistros.isEmpty()) {
            return new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        }
        registroEvento primerRegistro = listaRegistros.get(0);

        double minPlastico = primerRegistro.getPlastico();
        double minAluminio = primerRegistro.getAluminio();
        double minCarton = primerRegistro.getCarton();
        double minTetrapack = primerRegistro.getTetrapack();
        double minVidrio = primerRegistro.getVidrio();

        for (registroEvento registro : listaRegistros) {
            if (registro.getPlastico() < minPlastico) {
                minPlastico = registro.getPlastico();
            }
            if (registro.getAluminio() < minAluminio) {
                minAluminio = registro.getAluminio();
            }
            if (registro.getCarton() < minCarton) {
                minCarton = registro.getCarton();
            }
            if (registro.getTetrapack() < minTetrapack) {
                minTetrapack = registro.getTetrapack();
            }
            if (registro.getVidrio() < minVidrio) {
                minVidrio = registro.getVidrio();
            }
        }

        return new ArrayList<>(Arrays.asList(minPlastico, minAluminio, minCarton, minTetrapack, minVidrio));
    }

    // Método para obtener el máximo de cada material por evento
    private ArrayList<Double> getMaximoPorEvento(Evento eventoObtenerRegistro) {
        ArrayList<registroEvento> listaRegistros = new ArrayList<>(eventoObtenerRegistro.getListaRegistros());
        if (listaRegistros.isEmpty()) {
            return new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        }
        registroEvento primerRegistro = listaRegistros.get(0);

        double maxPlastico = primerRegistro.getPlastico();
        double maxAluminio = primerRegistro.getAluminio();
        double maxCarton = primerRegistro.getCarton();
        double maxTetrapack = primerRegistro.getTetrapack();
        double maxVidrio = primerRegistro.getVidrio();

        for (registroEvento registro : listaRegistros) {
            if (registro.getPlastico() > maxPlastico) {
                maxPlastico = registro.getPlastico();
            }
            if (registro.getAluminio() > maxAluminio) {
                maxAluminio = registro.getAluminio();
            }
            if (registro.getCarton() > maxCarton) {
                maxCarton = registro.getCarton();
            }
            if (registro.getTetrapack() > maxTetrapack) {
                maxTetrapack = registro.getTetrapack();
            }
            if (registro.getVidrio() > maxVidrio) {
                maxVidrio = registro.getVidrio();
            }
        }

        return new ArrayList<>(Arrays.asList(maxPlastico, maxAluminio, maxCarton, maxTetrapack, maxVidrio));
    }

    // Método para obtener el promedio de cada material por evento
    private ArrayList<Double> getPromedioPorEvento(Evento eventoObtenerRegistro) {
        ArrayList<registroEvento> listaRegistros = new ArrayList<>(eventoObtenerRegistro.getListaRegistros());
        if (listaRegistros.isEmpty()) {
            return new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        }

        double promPlastico = 0;
        double promAluminio = 0;
        double promCarton = 0;
        double promTetrapack = 0;
        double promVidrio = 0;

        for (registroEvento registro : listaRegistros) {
            promPlastico += registro.getPlastico();
            promAluminio += registro.getAluminio();
            promCarton += registro.getCarton();
            promTetrapack += registro.getTetrapack();
            promVidrio += registro.getVidrio();
        }

        promPlastico /= listaRegistros.size();
        promAluminio /= listaRegistros.size();
        promCarton /= listaRegistros.size();
        promTetrapack /= listaRegistros.size();
        promVidrio /= listaRegistros.size();

        return new ArrayList<>(Arrays.asList(promPlastico, promAluminio, promCarton, promTetrapack, promVidrio));
    }

    // Método para obtener el total de cada material por evento
    private ArrayList<Double> getTotalPorEvento(Evento eventoObtenerRegistro) {
        ArrayList<registroEvento> listaRegistros = new ArrayList<>(eventoObtenerRegistro.getListaRegistros());
        if (listaRegistros.isEmpty()) {
            return new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        }
        double totalPlastico = 0;
        double totalAluminio = 0;
        double totalCarton = 0;
        double totalTetrapack = 0;
        double totalVidrio = 0;

        for (registroEvento registro : listaRegistros) {
            totalPlastico += registro.getPlastico();
            totalAluminio += registro.getAluminio();
            totalCarton += registro.getCarton();
            totalTetrapack += registro.getTetrapack();
            totalVidrio += registro.getVidrio();
        }

        return new ArrayList<>(Arrays.asList(totalPlastico, totalAluminio, totalCarton, totalTetrapack, totalVidrio));
    }



    // Método para convertir dp a píxeles
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    // Método para regresar al menú principal
    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        });

    }
}