package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 4000;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imgv1);
        Animation fadaIn = new AlphaAnimation(0,1);
        fadaIn.setDuration(3000);
        fadaIn.setFillAfter(true);
        imageView.startAnimation(fadaIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, InicioSesion.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_DELAY);
    }
}