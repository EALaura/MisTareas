package com.example.mistareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.info){
            Toast.makeText(this, "INFO", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, PopupInfo.class));
            return true;
        }

        return super.onOptionsItemSelected(option_menu);
    }
}