package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InicioSesionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent button = new Intent(InicioSesionActivity.this, MenuPrincipalActivity.class);
                startActivity(button);
            }
        });

        ImageView imageView2 = findViewById(R.id.imageView2);
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
        });

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent textView3 = new Intent(InicioSesionActivity.this, RegistroActivity.class);
                startActivity(textView3);
            }
        });

    }
}