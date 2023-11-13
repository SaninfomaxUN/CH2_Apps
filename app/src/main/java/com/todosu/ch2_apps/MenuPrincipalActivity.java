package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView5 = new Intent(MenuPrincipalActivity.this, ConsejosActivity.class);
                startActivity(imageView5);

            }
        });

        ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView6 = new Intent(MenuPrincipalActivity.this, CalculadoraActivity.class);
                startActivity(imageView6);
            }
        });

        ImageView imageView4 = findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView4 = new Intent(MenuPrincipalActivity.this, InicioSesionActivity.class);
                startActivity(imageView4);
            }
        });



    }
}