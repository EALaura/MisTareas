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

import com.example.mistareas.Model.Tarea;
import com.example.mistareas.R;
import com.example.mistareas.Utilities.Utilities;

public class Completar_Tarea extends AppCompatActivity {
    public Button btnSi;
    public Button btnNo;
    public Button btnBorrar;
    public TextView tareaView;
    public Tarea tarea;
    public ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completar__tarea);
        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_tareas", null, 1);
        findViews();
        popupAceptar();
        implementsButton();
        //Objeto recibido
        recibirObjeto();
    }

    private void findViews() {
        btnSi = (Button) findViewById(R.id.btnViewSi);
        btnNo = (Button) findViewById(R.id.btnViewNo);
        btnBorrar = (Button) findViewById(R.id.btnViewBorrar);
        tareaView = (TextView) findViewById(R.id.tvTarea);
    }

    private void implementsButton() {
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarStatus();
                Toast.makeText(getApplicationContext(), "Terminaste una tarea", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Completar_Tarea.this, MainActivity.class));
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Completar_Tarea.this, MainActivity.class));
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarTarea();

                Toast.makeText(getApplicationContext(), "Se borro la tarea", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Completar_Tarea.this, MainActivity.class));

            }
        });
    }

    public void editarStatus() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {tarea.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilities.CAMPO_TAREA, tarea.getTarea().toString());
        values.put(Utilities.CAMPO_STATUS, "1");

        db.update(Utilities.TABLA_TAREA, values, Utilities.CAMPO_ID + "=?", parametros);
        db.close();
    }

    public void borrarTarea() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {tarea.getId().toString()};

        db.delete(Utilities.TABLA_TAREA, Utilities.CAMPO_ID + "=?", parametros);
        db.close();
    }

    public void recibirObjeto() {
        Bundle objetoEnviado = getIntent().getExtras();
        tarea = null;

        if (objetoEnviado != null) {
            tarea = (Tarea) objetoEnviado.getSerializable("Tarea");
            tareaView.setText(tarea.getTarea().toString() + tarea.getId());
        }
    }

    private void popupAceptar() {
        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho = medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int) (alto * 0.5));
    }
}