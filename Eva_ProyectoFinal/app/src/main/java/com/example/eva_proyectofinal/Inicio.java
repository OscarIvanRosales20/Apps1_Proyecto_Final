package com.example.eva_proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eva_proyectofinal.ui.login.LoginActivity;

public class Inicio extends AppCompatActivity {

    Button btnRegistrarProd, btnVerProd, btnModificarProd, btnBuscarProd, btnEliminarProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnRegistrarProd = findViewById(R.id.btnRegistarProd);
        btnBuscarProd = findViewById(R.id.btnBuscarProd);
        btnEliminarProd = findViewById(R.id.btnEliminarProd);
        btnModificarProd = findViewById(R.id.btnModificarProd);
        btnVerProd = findViewById(R.id.btnVerProd);
    }

    public void onClickCerrarSesion(View view){
        finish();
    }

    public void onClickBuscar(View view){
        Intent intentBuscar = new Intent(this, Buscar1.class);
        startActivity(intentBuscar);
    }

    public void onClickRegistrar(View view){
        Intent intentRegistrar = new Intent(this, Registrar1.class);
        startActivity(intentRegistrar);
    }

    public void onClickVer(View view){
        Intent intentVer = new Intent(this, VerProductos1.class);
        startActivity(intentVer);
    }

    public void onClickModificar(View view){
        Intent intentModificar = new Intent(this, Modificar1.class);
        startActivity(intentModificar);
    }

    public void onClickEliminar(View view){
        Intent intentEliminar = new Intent(this, Eliminar1.class);
        startActivity(intentEliminar);
    }
}