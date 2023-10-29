package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Gmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);

        ImageView imgv_arrow = findViewById(R.id.imgv_arrow);
        imgv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imgv_arrow = new Intent(Gmail.this, InicioSesion.class);
                startActivity(imgv_arrow);
            }
        });
    }
}