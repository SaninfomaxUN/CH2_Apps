package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.todosu.ch2_apps.models.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {
    TextInputEditText nombre, contrasena, ciudad, correo;
    CheckBox aceptar_terminos;
    Button btn_registrar;

    ArrayList<Usuario> lista_usuarios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        
        iniciar_componente();
        guardar();
        regresar();
    }

    private void iniciar_componente() {

        nombre = findViewById(R.id.edt_nombre);
        contrasena = findViewById(R.id.etd_contrasena);
        ciudad = findViewById(R.id.etd_ciudad);
        correo = findViewById(R.id.etd_correo);
        aceptar_terminos = findViewById(R.id.checkBox);
        btn_registrar = findViewById(R.id.btnGuardar);

    }


    private void guardar() {

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_registro = nombre.getText().toString();
                String contrasena_registro = contrasena.getText().toString();
                String ciudad_registro = ciudad.getText().toString();
                String correo_registro = correo.getText().toString();
                Usuario usuario = new Usuario(correo_registro, nombre_registro, ciudad_registro, contrasena_registro);
                String usuario_registrar = usuario.getEmail() + "," + usuario.getNombreCompleto() + ","
                        + usuario.getCiudad() + "," +usuario.getContrasena() +"\n";

                if(!aceptar_terminos.isChecked()){
                    Toast.makeText(getApplicationContext(), "Debe aceptar los términos", Toast.LENGTH_LONG).show();

                }else{
                    leer_archivo();
                    boolean validar_usuario = validar_usuario_existe(lista_usuarios, correo_registro);
                    if(validar_usuario){
                        Toast.makeText(getApplicationContext(), "Ya existe el ususario", Toast.LENGTH_LONG).show();
                    }else{
                        String contrasena [] = contrasena_registro.split("");
                        if(contrasena.length<6){
                            Toast.makeText(getApplicationContext(), "Contraseña debe tener minimo 6 caracteres", Toast.LENGTH_LONG).show();
                        }else{
                            Data.registrarUsuario(usuario);
                            guardar_usuario(usuario_registrar);
                        }

                    }


                }



            }
        });

    }



    private void guardar_usuario(String usuarioRegistrar) {
        File archivo = new File(getFilesDir(), "registro_usuario.txt");
        if(!archivo.exists()){
            try {
                FileWriter escritor_de_archivo = new FileWriter(archivo);
                escritor_de_archivo.write(usuarioRegistrar.toString());
                escritor_de_archivo.flush();
                escritor_de_archivo.close();
                Toast.makeText(this, "Usuario guardado con ėxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, InicioSesionActivity.class);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar usuario", Toast.LENGTH_SHORT).show();
            }
        }else{
            try {
                Files.write(Paths.get(getFilesDir() + "/registro_usuario.txt"), usuarioRegistrar.toString().getBytes(), StandardOpenOption.APPEND);
                Toast.makeText(this, "Usuario guardado con ėxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, InicioSesionActivity.class);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar usuario", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void regresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, InicioSesionActivity.class);
            startActivity(intent);
        });

    }
    private void leer_archivo(){
        File archivo = new File(getFilesDir(), "registro_usuario.txt");
        try {
            FileReader lector_archivo = new FileReader(archivo);
            BufferedReader bf_lector_archivo = new BufferedReader(lector_archivo);
            String linea;
            while ((linea = bf_lector_archivo.readLine())!=null){
                String usuario_recuperado [] = linea.split(",");
                Usuario usuario_registrado = new Usuario(
                        usuario_recuperado[0].toString(),
                        usuario_recuperado[1].toString(),
                        usuario_recuperado[2].toString(),
                        usuario_recuperado[3].toString()
                );
                lista_usuarios.add(usuario_registrado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validar_usuario_existe(ArrayList<Usuario>lista_usuarios, String usuario_registrar) {
        boolean usuario_existe = false;
        if(lista_usuarios.size()>0){
            for (int i=0; i<lista_usuarios.size(); i++){
                if(lista_usuarios.get(i).getEmail().toString().equals(usuario_registrar)){
                    usuario_existe = true;
                    return usuario_existe;
                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "No hay usuarios registrados", Toast.LENGTH_LONG).show();
        }
        return usuario_existe;
    }
}