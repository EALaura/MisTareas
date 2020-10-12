package com.example.mistareas.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mistareas.Model.Tarea;
import com.example.mistareas.R;

public class Completar_Tarea extends AppCompatActivity {
    public Button btnSi;
    public Button btnNo;
    public Button btnBorrar;
    public TextView tareaView;
    public Tarea tarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completar__tarea);
        findViews();
        popupAceptar();

        //Objeto recibido
        Bundle objetoEnviado = getIntent().getExtras();
        tarea = null;

        if (objetoEnviado != null) {
            tarea = (Tarea)objetoEnviado.getSerializable("Tarea");
            tareaView.setText(tarea.getTarea().toString() + tarea.getId());
        }

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

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void popupAceptar() {
        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho = medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto * 0.5));
    }
}