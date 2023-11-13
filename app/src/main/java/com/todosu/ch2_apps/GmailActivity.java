package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);

        ImageView imgv_arrow = findViewById(R.id.imgv_arrow);
        imgv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imgv_arrow = new Intent(GmailActivity.this, InicioSesionActivity.class);
                startActivity(imgv_arrow);
            }
        });
    }
}