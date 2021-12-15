package com.example.kardexdbp_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ActivityVerTabla extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[]header = {"Mes/Dia","Detalle","Entrada","Salida","Precio"};

    private ArrayList<String[]> rows = new ArrayList<>();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String[][][]añadir = new String[12][31][4];
    public String[]meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    //public String[][]meses = {{"Enero"}, {"Febrero"}, {"Marzo"}, {"Abril"}, {"Mayo"}, {"Junio"}, {"Julio"}, {"Agosto"}, {"Septiembre"}, {"Octubre"}, {"Noviembre"}, {"Diciembre"}};
    private TableDynamic tableDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);

        tableLayout = (TableLayout)  findViewById(R.id.table);


        tableDynamic = new TableDynamic(tableLayout, getApplicationContext());


        tableDynamic.addHeader(header);
        tableDynamic.addData(getClients());

    }


    private ArrayList<String[]> getClients()
    {
        for (int i = 0; i < meses.length; i++)
        {
            rows.add(new String[]{meses[i], "Pedro", "Lopez"});
        }

        rows.add(new String[]{"4", "Fiorella", "Canto"});
        return rows;
    }
    //        rows.add(new String[]{"list.get(i)", "a"});
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }
}