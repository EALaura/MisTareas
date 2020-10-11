package com.example.mistareas.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mistareas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        implementsButton();
    }

    private void findViews(){
        fav = (FloatingActionButton)findViewById(R.id.btnAgregar);
    }

    private void implementsButton(){
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "BUTTON Flot", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, PopAdd.class));
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