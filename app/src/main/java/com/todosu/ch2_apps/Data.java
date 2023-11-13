package com.todosu.ch2_apps;

import com.todosu.ch2_apps.models.Evento;
import com.todosu.ch2_apps.models.Usuario;
import com.todosu.ch2_apps.models.registroEvento;

import java.util.ArrayList;
import java.util.HashMap;

public class Data{
    private static HashMap<String, Usuario> diccionarioDatos = new HashMap<>();



    /* ------------------ Usuarios ------------------ */

    // --- Metodos para obtener y modificar usuarios
    public static void registrarUsuario(Usuario usuario) {
        diccionarioDatos.put(usuario.getEmail(),usuario);
    }
    public static Usuario getUsuarioPorEmail(String email) {
        return diccionarioDatos.get(email);
    }
    public static void updateUsuario(Usuario usuario) {
        diccionarioDatos.put(usuario.getEmail(),usuario);
    }
    public static void removeUsuarioPorEmail(String email) {
        diccionarioDatos.remove(email);
    }



    /* ------------- Eventos ------------------ */

    // --- Metodos para Agregar, obtener, actualizar y eliminar eventos del usuario conectado
    public static void agregarEventoDelUsuarioConectado(Evento evento) {
        getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).addEvento(evento);
    }
    public static Evento getEventoDelUsuarioConectado(String nombreEvento) {
        return getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).getEvento(nombreEvento);
    }
    public static void updateEventoDelUsuarioConectado(Evento evento) {
        getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).updateEvento(evento);
    }
    public static void removeEventoDelUsuarioConectado(String nombreEvento) {
        getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).removeEvento(nombreEvento);
    }
    // Obtener lista de eventos del usuario conectado
    public static ArrayList<Evento> getListaEventosDelUsuarioConectado() {
        return getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).getListaEventos();
    }


    /* ------------------ Registros ------------------ */

    // --- Metodos para Agregar, obtener, actualizar y eliminar registros del evento del usuario conectado
    public static void agregarRegistroDelEventoDelUsuarioConectado(String nombreEvento, registroEvento registro) {
        getEventoDelUsuarioConectado(nombreEvento).addRegistro(registro);
    }
    public static ArrayList<registroEvento> getListaRegistrosDelEventoDelUsuarioConectado(String nombreEvento) {
        return getEventoDelUsuarioConectado(nombreEvento).getListaRegistros();
    }


}


