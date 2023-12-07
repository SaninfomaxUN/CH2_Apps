package com.todosu.ch2_apps.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class Evento implements Serializable {
    private int idEvento;
    private String nombreEvento;
    private String categoria;
    private LocalDate fecha;
    private int numeroAsistentes;

    private ArrayList<registroEvento> listaRegistros = new ArrayList<>();




    public Evento (int idEvento, String nombreEvento, String categoria, LocalDate fecha, int numeroAsistentes) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.categoria = categoria;
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
    public String getCategoria() {
        return categoria;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public int getNumeroAsistentes() {
        return numeroAsistentes;
    }
    public int getIdEvento() {
        return idEvento;
    }
    public ArrayList<registroEvento> getListaRegistros() {
        return listaRegistros;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
