
package com.example.kardexdbp_2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class InicioActivity extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    //Metodo de Boton Agregar Collection

    public void CambiarCollectionActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, CollectionActivity.class);
        startActivity(i);
    }
    public void CambiarBuscarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, BuscarActivity.class);
        startActivity(i);
    }
    public void CambiarBorrarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, BorrarActivity.class);
        startActivity(i);
    }
    public void CambiarActualizarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, ActualizarActivity.class);
        startActivity(i);
    }

    public void CambiarVerTablaActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, ActivityVerTabla.class);
        startActivity(i);
    }




}