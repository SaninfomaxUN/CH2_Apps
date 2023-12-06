package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5.setOnClickListener(view -> {
            Intent imageView51 = new Intent(MenuPrincipalActivity.this, ConsejosActivity.class);
            startActivity(imageView51);

        });

        ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6.setOnClickListener(view -> {
            Intent imageView61 = new Intent(MenuPrincipalActivity.this, CalculadoraActivity.class);
            startActivity(imageView61);
        });

        inicializarBtnCerrarSesion();

    }

    private void inicializarBtnCerrarSesion() {
        ImageView btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(view -> closeSession());
    }

    //Sobreescritura del método para cerrar sesión al presionar el botón de atrás
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            closeSession();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Cerrar sesión
    public void closeSession() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setMessage("¿Deseas cerrar sesión?");
        builder.setPositiveButton("Salir", (dialogInterface, i) -> {
            UsuarioConectado.removeUsuarioConectadSegunEmailUsuario();
            Intent intent = new Intent(this, InicioSesionActivity.class);
            startActivity(intent);
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }


}