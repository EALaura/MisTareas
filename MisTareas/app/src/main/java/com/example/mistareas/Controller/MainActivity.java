package com.example.mistareas.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mistareas.Model.Tarea;
import com.example.mistareas.R;
import com.example.mistareas.Utilities.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fav;
    public ConexionSQLiteHelper con;
    public ListView listViewTareas;
    public ArrayList<String> listaInformacion;
    public ArrayList<Tarea> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_tareas", null, 1);
        consultarTareas();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewTareas.setAdapter(adapter);
        implementsButton();
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
            if (listaTareas.get(i).getStatus() == 0){
                estado = "Sin Completar";
            }
            else {
                estado = "Completado";
            }
            listaInformacion.add("\n"+  listaTareas.get(i).getTarea() +"\n\tEstado: " + estado + "\n");
        }
    }

    private void findViews() {
        fav = (FloatingActionButton) findViewById(R.id.btnAgregar);
        listViewTareas = (ListView) findViewById(R.id.listViewTareas);
    }

    private void implementsButton() {
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "BUTTON Flot", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Activity_Agregar.class));
            }
        });

        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info = "Tarea: " + listaTareas.get(position).getTarea();
                info += "Estado: " + listaTareas.get(position).getStatus();
                info += "\nID:" + listaTareas.get(position).getId();
                //Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();

                //Mandar Datos a otro activity
                Tarea tareaE = listaTareas.get(position);
                Intent intent = new Intent(MainActivity.this, Completar_Tarea.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Tarea", tareaE);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mimenu) {
        getMenuInflater().inflate(R.menu.menu_activity, mimenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem option_menu) {
        int id = option_menu.getItemId();
        if (id == R.id.configuracion) {
            //Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.info) {
            //Toast.makeText(this, "INFO", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, PopupInfo.class));
            return true;
        }

        return super.onOptionsItemSelected(option_menu);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        consultarTareas();
    }
}