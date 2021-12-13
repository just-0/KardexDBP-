package com.example.kardexdbp_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BuscarActivity extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public TextView tv_Detalle;
    public TextView tv_Entrada;
    public TextView tv_Salida;
    public TextView tv_Precio;


    public EditText ed_mes;
    public EditText ed_dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);


    }
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }


    public void Buscar_(View view)
    {
        tv_Detalle = (TextView) findViewById(R.id.DetalletextView);
        tv_Entrada = (TextView) findViewById(R.id.EntradatextView);
        tv_Salida = (TextView) findViewById(R.id.SalidatextView);
        tv_Precio = (TextView) findViewById(R.id.PreciotextView);


        ed_mes = (EditText) findViewById(R.id.MesText);
        ed_dia = (EditText) findViewById(R.id.DiaText);




        DocumentReference docRef = db.collection(ed_mes.getText().toString()).document(ed_dia.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        int [] numeros ;
                        numeros = new int[2];


                        tv_Detalle.setText(""+document.get("Detalle"));
                        tv_Entrada.setText(""+document.get("Entrada"));
                        tv_Salida.setText(""+document.get("Salida"));
                        tv_Precio.setText(""+document.get("Precio"));
                        Log.d("Succefully->", "DocumentSnapshot data: " + document.get("Detalle"));
                    } else {
                        Log.d("Error dato->", "No such document");
                    }
                } else {
                    Log.d("Error Firebase->", "get failed with ", task.getException());
                }
            }
        });

    }


}