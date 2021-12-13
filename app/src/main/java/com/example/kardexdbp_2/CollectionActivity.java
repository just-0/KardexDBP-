package com.example.kardexdbp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CollectionActivity extends AppCompatActivity {

    public FirebaseFirestore A = FirebaseFirestore.getInstance();
    private EditText CollectionName, DocumentName, Precio, Detalle, Entrada, Salida;
    private Button Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        CollectionName = (EditText) findViewById(R.id.CollectionText);
        DocumentName = (EditText) findViewById(R.id.DocumentText);
        Detalle = (EditText) findViewById((R.id.DetalleText));
        Entrada = (EditText) findViewById(R.id.EntradaText);
        Precio = (EditText) findViewById(R.id.PrecioText);
        Salida = (EditText) findViewById(R.id.SalidaText);
    }

    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }
    public void FirebaseCrearCollection(View view)
    {

        // Para crear una biblioteca
        Map<String, Object> Exist = new HashMap<>();
        Exist.put("Detalle", Detalle.getText().toString());
        Exist.put("Entrada", Integer.parseInt(Entrada.getText().toString()));
        Exist.put("Precio", Integer.parseInt(Precio.getText().toString()));
        Exist.put("Salida", Integer.parseInt(Salida.getText().toString()));


        String NombreColeccion = CollectionName.getText().toString();
        String NombreDocumento = DocumentName.getText().toString();

        //A.collection(NombreColeccion).document(NombreDocumento).set(city);
        A.collection(NombreColeccion).document(NombreDocumento).set(Exist);

    }
}