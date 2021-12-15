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

    static public ArrayList<String[]> rows = new ArrayList<>();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();



    public String[]meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private TableDynamic tableDynamic;



    static public String[][][]añadir = new String[12][31][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);

        tableLayout = (TableLayout)  findViewById(R.id.table);


        tableDynamic = new TableDynamic(tableLayout, getApplicationContext());



        for (int i = 0 ; i < 12; i++)
        {
            for ( int j = 1; j < 32 ; j++)
            {
                DocumentReference docRef = db.collection(meses[i]).document(String.valueOf(j));
                int finalJ = j;
                int finalI = i;
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            añadir[finalI][finalJ -1][0] = ""+document.get("Detalle");
                            añadir[finalI][finalJ -1][1] = ""+document.get("Entrada");
                            añadir[finalI][finalJ -1][2] = ""+document.get("Precio");
                            añadir[finalI][finalJ -1][3] = ""+document.get("Salida");
                            Log.d("Wait", "Cargando archivos!!!");
                        }
                    }
                });

            }
        }
        tableDynamic.addHeader(header);
    }
    public void save(View view)
    {

        tableDynamic.addData(getClients());



    }

    private ArrayList<String[]> getClients()
    {
        int existencias_actuales = 0,total_ventas= 0,total_rebastecimiento= 0;

        for (int i = 0; i< 12; i++)
        {
            for (int j = 1; j <32;j++)
            {
                if (añadir[i][j-1][0] != null )
                {
                    rows.add(new String[]{meses[i]+"/"+(j), añadir[i][j-1][0],añadir[i][j-1][1],añadir[i][j-1][3],añadir[i][j-1][2] });
                    existencias_actuales = existencias_actuales +   Integer.parseInt(añadir[i][j-1][1]) - Integer.parseInt(añadir[i][j-1][3]);
                    total_ventas = total_ventas + Integer.parseInt(añadir[i][j-1][3]);
                    total_rebastecimiento = total_rebastecimiento + Integer.parseInt(añadir[i][j-1][1]);
                }
            }
        }

        rows.add(new String[]{"          ", "          ","          ","          ","          "});
        rows.add(new String[]{"Existencias actuales", "Total de ventas","Total de Rebastecimientos","          ","          "});
        rows.add(new String[]{String.valueOf(existencias_actuales), String.valueOf(total_ventas),String.valueOf(total_rebastecimiento),"",""});

        return rows;
    }



    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }
}