package com.todosu.ch2_apps;

import android.content.Context;

import com.todosu.ch2_apps.models.Evento;
import com.todosu.ch2_apps.models.Usuario;
import com.todosu.ch2_apps.models.registroEvento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Data{
    private static HashMap<String, Usuario> diccionarioDatos = new HashMap<>();



    /* ------------------ Usuarios ------------------ */

    // --- Metodos para obtener y modificar usuarios
    public static void registrarUsuario(Usuario usuario, Context context) {
        diccionarioDatos.put(usuario.getEmail(),usuario);
        saveData(context);


    }
    public static Usuario getUsuarioPorEmail(String email) {
        return diccionarioDatos.get(email);
    }
    public static void updateUsuario(Usuario usuario, Context context) {
        diccionarioDatos.put(usuario.getEmail(),usuario);
        saveData(context);
    }
    public static void removeUsuarioPorEmail(String email, Context context) {
        diccionarioDatos.remove(email);
        saveData(context);
    }



    /* ------------- Eventos ------------------ */

    // --- Metodos para Agregar, obtener, actualizar y eliminar eventos del usuario conectado
    public static void agregarEventoDelUsuarioConectado(Evento evento, Context context) {
        getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).addEvento(evento);
        saveData(context);
    }
    public static Evento getEventoDelUsuarioConectado(String nombreEvento) {
        return getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).getEvento(nombreEvento);
    }
    public static void updateEventoDelUsuarioConectado(Evento evento, Context context) {
        getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).updateEvento(evento);
        saveData(context);
    }
    public static void removeEventoDelUsuarioConectado(String nombreEvento, Context context) {
        getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).removeEvento(nombreEvento);
        saveData(context);
    }
    // Obtener lista de eventos del usuario conectado
    public static ArrayList<Evento> getListaEventosDelUsuarioConectado() {
        return getUsuarioPorEmail(UsuarioConectado.getUsuarioConectado().getEmail()).getListaEventos();
    }


    /* ------------------ Registros ------------------ */

    // --- Metodos para Agregar, obtener, actualizar y eliminar registros del evento del usuario conectado
    public static void agregarRegistroDelEventoDelUsuarioConectado(String nombreEvento, registroEvento registro, Context context) {
        getEventoDelUsuarioConectado(nombreEvento).addRegistro(registro);
        saveData(context);
    }
    public static ArrayList<registroEvento> getListaRegistrosDelEventoDelUsuarioConectado(String nombreEvento) {
        return getEventoDelUsuarioConectado(nombreEvento).getListaRegistros();
    }



    //---- Metodo para serializar el diccionario de datos
    public static void saveData(Context context){
        //Serializar
        try {
            FileOutputStream fos = new FileOutputStream(new File(context.getFilesDir(),"dataECOAPP.dic"));
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(diccionarioDatos);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // ---- Metodo para deserializar el diccionario de datos
    public static void loadData(Context context){
        try {
            FileInputStream fis = new FileInputStream(new File(context.getFilesDir(),"dataECOAPP.dic"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            diccionarioDatos = (HashMap<String, Usuario>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


