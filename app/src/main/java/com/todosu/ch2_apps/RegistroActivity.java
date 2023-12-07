package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.todosu.ch2_apps.models.Usuario;

public class RegistroActivity extends AppCompatActivity {
    TextInputEditText edt_nombre, etd_contrasena, etd_ciudad, etd_correo;
    TextInputLayout til_nombre, til_contrasena, til_ciudad, til_correo;
    CheckBox aceptar_terminos;
    Button btn_registrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        
        iniciar_componentes();
        inicializarBtnRegistro();
        inicializarTerminos();
        inicializarBtnRegresar();
    }

    private void iniciar_componentes() {

        edt_nombre = findViewById(R.id.edt_nombre);
        etd_contrasena = findViewById(R.id.etd_contrasena);
        etd_ciudad = findViewById(R.id.etd_ciudad);
        etd_correo = findViewById(R.id.etd_correo);
        aceptar_terminos = findViewById(R.id.aceptar_terminos);
        btn_registrar = findViewById(R.id.btnGuardar);

        til_nombre = findViewById(R.id.til_nombre);
        til_contrasena = findViewById(R.id.til_contrasena);
        til_ciudad = findViewById(R.id.til_ciudad);
        til_correo = findViewById(R.id.til_correo);

    }


    private void inicializarBtnRegistro() {

        btn_registrar.setOnClickListener(view -> {
            if(!validarCamposCompletosFormulario()){
                return;
            }
            if(!validarContenidoFormulario()){
                return;
            }
            if(!checkUsuario()){
                return;
            }
            guardar_usuario();

        });

    }

    //Metodo para obtener el texto de los terminos y redirigir
    public void inicializarTerminos(){
        TextView terminos = findViewById(R.id.registro_terminos);
        terminos.setMovementMethod(LinkMovementMethod.getInstance());

        // Obtener el texto del CheckBox
        String text = getResources().getString(R.string.registro_terminos);

        // Crear un SpannableString para modificar el CheckBox
        SpannableString spannableString = new SpannableString(text);

        // Definir el enlace y su comportamiento al hacer clic
        ClickableSpan link1ClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // Acciones a realizar cuando se hace clic en el enlace 1
                Intent intent = new Intent(RegistroActivity.this, TerminosActivity.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(link1ClickableSpan, text.indexOf("Términos y Condiciones"), text.indexOf("Términos y Condiciones") + "Términos y Condiciones".length(), 0);
        // Aplicar el SpannableString al TextView
        terminos.setText(spannableString);

    }

    private boolean validarCamposCompletosFormulario() {
        cleanErrors();
        boolean valido = true;
        if(edt_nombre.getText().toString().isEmpty()){
            til_nombre.setError("El nombre es requerido");
            valido = false;
        }
        if(etd_contrasena.getText().toString().isEmpty()){
            til_contrasena.setError("La contraseña es requerida");
            valido = false;
        }
        if(etd_ciudad.getText().toString().isEmpty()){
            til_ciudad.setError("La ciudad es requerida");
            valido = false;
        }
        if(etd_correo.getText().toString().isEmpty()){
            til_correo.setError("El correo es requerido");
            valido = false;
        }
        return valido;
    }

    private boolean validarContenidoFormulario() {
        cleanErrors();
        boolean valido = true;
        if(etd_contrasena.getText().toString().length() < 8){
            til_contrasena.setError("La contraseña debe tener al menos 8 caracteres!");
            valido = false;
        }
        if(!etd_correo.getText().toString().contains("@")){
            til_correo.setError("El correo electrónico debe tener un formato válido!");
            valido = false;
        }
        if(!aceptar_terminos.isChecked()){
            aceptar_terminos.setButtonTintList(ColorStateList.valueOf(Color.RED));
            Toast.makeText(getApplicationContext(), "Debe aceptar los términos!", Toast.LENGTH_LONG).show();
            valido = false;
        }
        return valido;
    }


    private boolean checkUsuario() {
        Usuario usuario = Data.getUsuarioPorEmail(etd_correo.getText().toString());
        if(usuario != null){
            Toast.makeText(getApplicationContext(), "El usuario ya se encuentra registrado!", Toast.LENGTH_LONG).show();
            regresar();
            return false;
        }
        return true;
    }

    private void cleanErrors() {
        til_nombre.setError(null);
        til_contrasena.setError(null);
        til_ciudad.setError(null);
        til_correo.setError(null);
        aceptar_terminos.setButtonTintList(ColorStateList.valueOf(Color.GRAY));
    }



    private void guardar_usuario(){
        String nombre_registro = edt_nombre.getText().toString();
        String contrasena_registro = etd_contrasena.getText().toString();
        String ciudad_registro = etd_ciudad.getText().toString();
        String correo_registro = etd_correo.getText().toString().toLowerCase();
        Usuario usuario = new Usuario(correo_registro, nombre_registro, ciudad_registro, contrasena_registro);

        Data.registrarUsuario(usuario, this);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setMessage("Usuario registrado con éxito!").setCancelable(false);
        builder.setPositiveButton("Aceptar", (dialogInterface, i) -> regresar());
        builder.show();
    }

    private void inicializarBtnRegresar() {
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(view -> regresar());

    }

    private void regresar(){
        Intent intent = new Intent(this, InicioSesionActivity.class);
        startActivity(intent);
    }


}