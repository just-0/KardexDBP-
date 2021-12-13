package com.example.kardexdbp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActualizarActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Spinner spinner1;
    private EditText et1, et2, et3;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        et1 = (EditText) findViewById(R.id.MesActualizarText);
        et2 = (EditText) findViewById(R.id.DiaActualizarText);
        et3 = (EditText) findViewById(R.id.ValorActualizarText);

        String [] opciones = {"Detalle", "Entrada", "Salida", "Precio"};
        ArrayAdapter <String> adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);


    }
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }

    //Metodo del boton
    public void actualizar(View view)
    {
        String valor1_String = et1.getText().toString();
        String valor2_String = et2.getText().toString();
        String valor3_String = et3.getText().toString();
        String seleccion = spinner1.getSelectedItem().toString();
        DocumentReference washingtonRef = db.collection(valor1_String).document(valor2_String);
        if(seleccion != "Detalle"){
            int valor = Integer.parseInt(valor3_String);
            washingtonRef.update(seleccion, valor);
        }
        else{
            washingtonRef.update(seleccion, valor3_String);
        }
        //Agarra El id del documento

        //Actualiza un dato, en este caso le pone XDXDXDXD
    }

}