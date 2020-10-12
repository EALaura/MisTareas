package com.example.mistareas.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mistareas.Model.Tarea;
import com.example.mistareas.R;
import com.example.mistareas.Utilities.Utilities;

import java.util.ArrayList;

public class Activity_Historial extends AppCompatActivity {
    public ConexionSQLiteHelper con;
    public ListView listViewHistorias;
    public ArrayList<String> listaInformacion;
    public ArrayList<Tarea> listaTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__historial);
        listViewHistorias = (ListView)findViewById(R.id.listViewHist);
        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_tareas", null, 1);
        consultarTareas();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewHistorias.setAdapter(adapter);
    }

    private void consultarTareas() {
        SQLiteDatabase db = con.getReadableDatabase();

        Tarea tarea = null;
        listaTareas = new ArrayList<Tarea>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.TABLA_TAREA, null);

        while (cursor.moveToNext()) {
            tarea = new Tarea();

            tarea.setId(cursor.getInt(0));
            tarea.setTarea(cursor.getString(1));
            tarea.setStatus(cursor.getInt(2));

            listaTareas.add(tarea);
        }
        obtenerLista();
        db.close();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaTareas.size(); i++) {
            String estado;
            if (listaTareas.get(i).getStatus() != 0){
                estado = "Completado";
                listaInformacion.add("\n"+  listaTareas.get(i).getTarea() +"\n\tEstado: " + estado + "\n");
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Activity_Historial.this, MainActivity.class));
    }
}