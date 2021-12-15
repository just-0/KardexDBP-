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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);

        tableLayout = (TableLayout)  findViewById(R.id.table);


        TableDynamic tableDynamic = new TableDynamic(tableLayout, getApplicationContext());

        tableDynamic.addHeader(header);
        tableDynamic.addData(getClients());






    }
    private ArrayList<String[]>getClients()
    {
        List<String> list = new ArrayList<String>(){
            {
                add("Enero");
                add("Febrero");
                add("Marzo");
                add("Abril");
                add("Mayo");
                add("Junio");
                add("Julio");
                add("Agosto");
                add("Septiembre");
                add("Octubre");
                add("Noviembre");
                add("Diciembre");

            }
        };
        List<String> añadir = new ArrayList<String>();


        for (int i = 0;i <3; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                DocumentReference docRef = db.collection(list.get(i)).document(String.valueOf(j));
                int finalI = i;
                docRef.get().addOnCompleteListener(
                        new OnCompleteListener<DocumentSnapshot>() {

                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {
                        Task<DocumentSnapshot> as;

                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            rows.add(new String[]{"uiwu", "xdxdxd"});

                            Log.d("Succefully->", "DocumentSnapshot data: " + document.get("Detalle"));
                        } else {
                            Log.d("Error dato->", "No such document");
                        }

                    }
                });
            }
        }


        return rows;
    }
    public void CambiarActivity(View view){
        //Cambio de Activity
        Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
    }
}