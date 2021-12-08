package com.example.kardexdbp_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

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



//Codigo para crear una coleccion junto a una biblioteca.
    public void Firebase_crear(View view)
    {
        // Para crear una biblioteca
        Map<String, Object> city = new HashMap<>();
        city.put("name", "Los Angeles");
        city.put("state", "CA");
        city.put("country", "USA");
        //Se crea la coleccion ("cities") y despues una biblioteca ("LA")
        //Quedaria asi Firebase\cities\LA
        db.collection("cities").document("LA")
                .set(city)      //Se coloca los datos de la biblioteca


                //De aqui para abajo es para ver si se ejecuto correctamente o si no ;v
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Exito :)", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error :'v", "Error writing document", e);
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