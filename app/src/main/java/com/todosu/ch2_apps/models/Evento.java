package com.todosu.ch2_apps.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class Evento implements Serializable {
    private String lugarEvento;
    private String nombreEvento;
    private LocalDate fecha;
    private int numeroAsistentes;

    private ArrayList<registroEvento> listaRegistros = new ArrayList<>();




    public Evento (String nombreEvento, String lugarEvento, LocalDate fecha, int numeroAsistentes) {
        this.lugarEvento = lugarEvento;
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.numeroAsistentes = numeroAsistentes;
    }

    /* ------------------ Registros ------------------ */

    //Agregar registro
    public void addRegistro(registroEvento registro) {
        listaRegistros.add(registro);
    }




    //Getters y Setters
    public String getNombreEvento() {
        return nombreEvento;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public int getNumeroAsistentes() {
        return numeroAsistentes;
    }
    public String getLugarEvento() {
        return lugarEvento;
    }
    public ArrayList<registroEvento> getListaRegistros() {
        return listaRegistros;
    }
    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setNumeroAsistentes(int numeroAsistentes) {
        this.numeroAsistentes = numeroAsistentes;
    }
    public void setListaRegistros(ArrayList<registroEvento> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

}
