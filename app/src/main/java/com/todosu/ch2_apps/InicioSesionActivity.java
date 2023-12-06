package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.todosu.ch2_apps.models.Usuario;

public class InicioSesionActivity extends AppCompatActivity {

    private MaterialButton btnIniciarSesion;
    private TextInputLayout editTextCorreo;
    private TextInputLayout editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        findReferences();
        inicializarBtnIniciarSesion();
        

                

       /* ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView2 = new Intent(InicioSesionActivity.this, FacebookActivity.class);
                startActivity(imageView2);
            }
        });
        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView3 = new Intent(InicioSesionActivity.this, GmailActivity.class);
                startActivity(imageView3);

            }
        });*/

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(view -> {
            Intent textView31 = new Intent(InicioSesionActivity.this, RegistroActivity.class);
            startActivity(textView31);
        });

    }
    private void findReferences() {
        btnIniciarSesion = findViewById(R.id.button);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextPass = findViewById(R.id.editTextPass);
    }

    private void inicializarBtnIniciarSesion() {
        btnIniciarSesion.setOnClickListener(view -> {
            checkLogin();
        });

    }
    private void checkLogin() {
        cleanErrors();
        if (!checkComplete()) {
            return;
        }
        Usuario usuarioEncontrado = checkEmail();
        if (usuarioEncontrado == null) {
            return;
        }
        if (!checkPass(usuarioEncontrado)) {
            return;
        }
        cleanErrors();
        logIn(usuarioEncontrado);
    }

    private boolean checkComplete() {
        boolean isComplete = true;
        if (editTextCorreo.getEditText().getText().toString().isEmpty()) {
            editTextCorreo.setError("Ingresa tu correo");
            isComplete = false;
        }
        if (!editTextCorreo.getEditText().getText().toString().contains("@")) {
            editTextCorreo.setError("Ingresa un correo válido");
            editTextCorreo.getEditText().setText("");
            isComplete = false;
        }
        if (editTextPass.getEditText().getText().toString().isEmpty()) {
            editTextPass.setError("Ingresa tu contraseña");
            isComplete = false;
        }
        return isComplete;
    }

    private Usuario checkEmail() {

        Usuario usuarioEncontrado = Data.getUsuarioPorEmail(editTextCorreo.getEditText().getText().toString());
        if (usuarioEncontrado == null) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setMessage("El correo ingresado no se encuentra registrado. Si no tienes una cuenta, puedes registrarte!");
            builder.setPositiveButton("Aceptar", null);
            builder.show();
            editTextCorreo.getEditText().setText("");
            editTextPass.getEditText().setText("");
            return null;
        }
        return usuarioEncontrado;
    }

    private boolean checkPass(Usuario usuarioEncontrado) {
        editTextPass.getEditText().setText("");
        if (usuarioEncontrado.getContrasena().equals(editTextPass.getEditText().getText().toString())) {
            editTextPass.setError(null);
            return true;
        } else {
            editTextPass.setError("Contraseña Incorrecta");
            return false;
        }
    }

    private void cleanErrors() {
        editTextCorreo.setError(null);
        editTextPass.setError(null);
    }

    private void logIn(Usuario usuarioEncontrado) {
        UsuarioConectado.setUsuarioConectadoSegunEmailUsuario(usuarioEncontrado.getEmail());
        Toast.makeText(this, "Bienvenido " + usuarioEncontrado.getNombreCompleto(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }



}