package com.example.kardexdbp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class BorrarActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    public EditText ed_mes;
    public EditText ed_dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
    }
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }

    public void Borrar_(View view)
    {
        ed_mes = (EditText) findViewById(R.id.MesBorrarText);
        ed_dia = (EditText) findViewById(R.id.DiaBorrarText);
        db.collection(ed_mes.getText().toString()).document(ed_dia.getText().toString()).delete();


    }


}