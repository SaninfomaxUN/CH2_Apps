package com.todosu.ch2_apps.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {

    private String email;
    private String nombreCompleto;
    private String ciudad;
    private String contrasena;

    private HashMap<String, Evento> diccionarioEventos = new HashMap<>();

    public Usuario(String email, String nombreCompleto, String ciudad, String contrasena) {
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.ciudad = ciudad;
        this.contrasena = contrasena;
    }

    //Add Evento
    public void addEvento(Evento evento) {
        diccionarioEventos.put(evento.getNombreEvento(),evento);
    }

    //Get Evento
    public Evento getEvento(String nombreEvento) {
        return diccionarioEventos.get(nombreEvento);
    }

    //Update Evento
    public void updateEvento(Evento evento) {
        diccionarioEventos.put(evento.getNombreEvento(),evento);
    }

    //remove Evento
    public void removeEvento(String nombreEvento) {
        diccionarioEventos.remove(nombreEvento);
    }
    // Obetener lista de eventos del usuario
    public ArrayList<Evento> getListaEventos() {
        return new ArrayList<>(diccionarioEventos.values());
    }

    //Getters y Setters
    public String getEmail() {
        return email;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }



}
