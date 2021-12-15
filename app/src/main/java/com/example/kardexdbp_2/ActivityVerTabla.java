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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);

        tableLayout = (TableLayout)  findViewById(R.id.table);


        TableDynamic tableDynamic = new TableDynamic(tableLayout, getApplicationContext());



        for (int i = 0; i < meses.length; i++)
        {
            for (int j = 1; j < 32;j++)
            {

                DocumentReference docRef = db.collection(meses[i]).document(String.valueOf(j-1));
                int finalI = i;
                int finalJ = j;
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            añadir[finalI][finalJ -1][0] = ""+document.get("Detalle");
                            añadir[finalI][finalJ -1][1] = ""+document.get("Entrada");
                            añadir[finalI][finalJ -1][2] = ""+document.get("Salida");
                            añadir[finalI][finalJ -1][3] = ""+document.get("Precio");

                            Log.d("Succefully->", "DocumentSnapshot data: " + document.get("Detalle"));

                        }
                    }
                });
            }
        }




        tableDynamic.addHeader(header);
        tableDynamic.addData(getClients());






    }
    private ArrayList<String[]>getClients()
    {

        for (int i = 0; i < meses.length; i++)
        {
            for (int j = 1; j < 32;j++)
            {
                rows.add(new String[]{meses[i], "a",añadir[i][j -1][0] ,añadir[i][j -1][1],añadir[i][j -1][2],añadir[i][j -1][3]  } );

            }
        }






        rows.add(new String[]{"list.get(i)", "a"});

        return rows;
    }
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }
}