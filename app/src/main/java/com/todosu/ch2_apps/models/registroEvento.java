package com.todosu.ch2_apps.models;


import java.time.LocalDate;

public class registroEvento{
    private LocalDate fecha;
    private double plastico;
    private double aluminio;
    private double carton;
    private double tetrapack;
    private double vidrio;


    public registroEvento(double plastico, double aluminio, double carton, double tetrapack, double vidrio) {
        this.fecha = LocalDate.now();
        this.plastico = plastico;
        this.aluminio = aluminio;
        this.carton = carton;
        this.tetrapack = tetrapack;
        this.vidrio = vidrio;
    }


    //Getters
    public LocalDate getFecha() {
        return fecha;
    }
    public double getPlastico() {
        return plastico;
    }
    public double getAluminio() {
        return aluminio;
    }
    public double getCarton() {
        return carton;
    }
    public double getTetrapack() {
        return tetrapack;
    }
    public double getVidrio() {
        return vidrio;
    }

}
