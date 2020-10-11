package com.example.mistareas.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.mistareas.R;

public class PopAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_add);

        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho = medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto * 0.5));
    }
}