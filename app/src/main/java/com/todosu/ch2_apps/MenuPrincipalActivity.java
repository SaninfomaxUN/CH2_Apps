package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MenuPrincipalActivity extends AppCompatActivity {

    private BottomNavigationView navBarMenuPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5.setOnClickListener(view -> {
            Intent imageView51 = new Intent(MenuPrincipalActivity.this, ConsejosActivity.class);
            startActivity(imageView51);

        });

        inicializarBtnCerrarSesion();
        findComponents();
        setNavBarMenuPrincipalActions();

    }

    private void inicializarBtnCerrarSesion() {
        ImageView btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(view -> closeSession());
    }

    private void findComponents() {
        navBarMenuPrincipal = findViewById(R.id.navBarMenuPrincipal);
    }

    @SuppressLint("NonConstantResourceId")
    private void setNavBarMenuPrincipalActions() {
        navBarMenuPrincipal.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.MisEventos) {
                Intent intent = new Intent(this, ConsejosActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.RegistrarConsumo) {
                Intent intent = new Intent(this, CalculadoraActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.Estadisticas) {
                Intent intent = new Intent(this, EstadisticasActivity.class);
                startActivity(intent);
            }
            return false;
        });

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