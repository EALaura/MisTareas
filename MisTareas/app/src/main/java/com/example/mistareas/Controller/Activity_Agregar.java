package com.example.mistareas.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistareas.R;
import com.example.mistareas.Utilities.Utilities;

import java.util.Random;

public class Activity_Agregar extends AppCompatActivity {
    public TextView campoTarea;
    public Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        popAgregar();
        findViewsIDS();
        implementButtons();

    }

    public void findViewsIDS() {
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        campoTarea = (TextView)findViewById(R.id.edit_tarea);
    }
    public void implementButtons(){
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campoTarea.getText().length() > 1) {
                    registrarTarea();
                    startActivity(new Intent(Activity_Agregar.this, MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Agregue su tarea", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registrarTarea() {
        //Conexion SQLite
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_tareas", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.CAMPO_ID, ""+((int) (Math.random()*(100000-1+1)+100000)));
        values.put(Utilities.CAMPO_TAREA, campoTarea.getText().toString());
        values.put(Utilities.CAMPO_STATUS, "0");

        Long idResultante = db.insert(Utilities.TABLA_TAREA, Utilities.CAMPO_ID, values);

        //Toast.makeText(getApplicationContext(), "ID REGISTRO:"  + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void popAgregar(){
        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho = medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto * 0.40));
    }

}