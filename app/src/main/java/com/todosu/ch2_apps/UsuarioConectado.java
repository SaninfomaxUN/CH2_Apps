package com.todosu.ch2_apps;


import com.todosu.ch2_apps.models.Usuario;

public class UsuarioConectado {
    private static Usuario usuarioConectado = null;


    /* ------------------ Usuario Conectado ------------------ */

    // --- Metodos para obtener y modificar usuario conectado
    public static Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    public static void setUsuarioConectadoSegunEmailUsuario(String email) {
        usuarioConectado = Data.getUsuarioPorEmail(email);
    }

    public static void removeUsuarioConectadSegunEmailUsuario() {
        usuarioConectado = null;
    }
}
