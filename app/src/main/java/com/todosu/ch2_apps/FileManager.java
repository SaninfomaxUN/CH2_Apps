package com.todosu.ch2_apps;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String NOMBRE_ARCHIVO = "datos_evento.txt";

    public static void guardarDatos(Context context, String datos) {
        try (FileOutputStream fos = context.openFileOutput(NOMBRE_ARCHIVO, Context.MODE_APPEND)) {
            fos.write((datos + "\n").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> leerDatos(Context context) {
        List<String> listaDatosEvento = new ArrayList<>();
        try (FileInputStream fis = context.openFileInput(NOMBRE_ARCHIVO);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                listaDatosEvento.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDatosEvento;
    }
}

