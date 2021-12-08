
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



public class MainActivity extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo de Boton Agregar Collection

    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, CollectionActivity.class);
        startActivity(i);
    }




//Codigo para crear una coleccion junto a una biblioteca.
    public void Firebase_crear(View view)
    {
        // Para crear una biblioteca
        Map<String, Object> city = new HashMap<>();
        city.put("name", "Los Angeles");
        city.put("state", "CA");
        city.put("country", "USA");
        city.put("A", 1);
        //Se crea la coleccion ("cities") y despues una biblioteca ("LA")
        //Quedaria asi Firebase\cities\LA
        db.collection("cities").document("LA")
                .set(city)      //Se coloca los datos de la biblioteca
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }


    public void borrar(View view)
    {
        //Agarra El id del documento
        DocumentReference washingtonRef = db.collection("cities").document("LA");
        washingtonRef.update("country", FieldValue.delete());//Actualiza un dato, en este caso lo borra


    }


    public void actualizar(View view)
    {
        //Agarra El id del documento
        DocumentReference washingtonRef = db.collection("cities").document("LA");
        washingtonRef.update("country", "XDXDXDXDXD");//Actualiza un dato, en este caso le pone XDXDXDXD


    }
}