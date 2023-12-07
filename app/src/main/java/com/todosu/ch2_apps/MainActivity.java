package com.todosu.ch2_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.todosu.ch2_apps.models.Evento;
import com.todosu.ch2_apps.models.Usuario;
import com.todosu.ch2_apps.models.registroEvento;


public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imgv1);
        Animation fadaIn = new AlphaAnimation(0,1);
        fadaIn.setDuration(3000);
        fadaIn.setFillAfter(true);
        imageView.startAnimation(fadaIn);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, InicioSesionActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_DELAY);
        Data.loadData(this);
        //startData();

    }

//    private void startData() {
//        Data.registrarUsuario(new Usuario("example@mail.com", "Juan", "BOG", "123456"), this);
//        UsuarioConectado.setUsuarioConectadoSegunEmailUsuario("example@mail.com");
//        Data.agregarEventoDelUsuarioConectado(new Evento(1, "Evento 1", "Categoria 1", LocalDate.of(2021, 1, 1), 100));
//        Data.agregarRegistroDelEventoDelUsuarioConectado("Evento 1", new registroEvento(41, 47, 8, 4 , 10));
//        Data.agregarRegistroDelEventoDelUsuarioConectado("Evento 1", new registroEvento(40, 40, 40, 40 , 40));
//
//        Data.agregarEventoDelUsuarioConectado(new Evento(2, "Evento 2", "Categoria 2", LocalDate.of(2021, 2, 2), 200));
//        Data.agregarRegistroDelEventoDelUsuarioConectado("Evento 2", new registroEvento(51, 37, 68, 4 , 90));
//        Data.agregarRegistroDelEventoDelUsuarioConectado("Evento 2", new registroEvento(50, 50, 50, 50 , 50));
//
//    }
}