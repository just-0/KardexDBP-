
package com.example.kardexdbp_2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //Para que se vea la pantalla completa, sin la barra de arriba
        setContentView(R.layout.activity_main);

        //Agregar Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView deTextView = findViewById(R.id.DetextView);
        TextView WalterTextView = findViewById(R.id.WaltertextView);
        TextView JustoTextView = findViewById(R.id.JustotextView);
        ImageView logoImageView = findViewById(R.id.logoimageView);

        deTextView.setAnimation(animacion2);
        WalterTextView.setAnimation(animacion2);
        JustoTextView.setAnimation(animacion2);
        logoImageView.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }

}