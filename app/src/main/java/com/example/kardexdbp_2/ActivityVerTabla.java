package com.example.kardexdbp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityVerTabla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);
    }
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }
}