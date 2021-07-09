package com.example.eva_proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Buscar1 extends AppCompatActivity {

    TextView txtNombre, txtPrecio, txtCantidad, txtDescripcion, txtCodigo;
    Button btnVolver, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar1);

        //Vincular componentes
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        txtPrecio = (TextView)findViewById(R.id.txtPrecio);
        txtCantidad = (TextView)findViewById(R.id.txtCantidad);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcion);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnVolver = findViewById(R.id.btnVolver);
    }

    public void Volver(View view){
        finish();
    }

    //Metodo para consultar un articulo o producto
    public void Buscar(View view){
        //Se crea objeto de la clase AdminSQLiteOpenHelper
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        //Busqueda del articulo mediante el codigo
        String codigo = txtCodigo.getText().toString();

        if(!codigo.isEmpty()){
            //Se crea un objeto de la clase Cursor
            //El metodo rawQuery nos permite utilizar un select
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, precio, cantidad, descripcion from articulos where codigo =" + codigo, null);
            //En caso de encontrar valores de la consulta, se muestran
            if (fila.moveToFirst()){
                txtNombre.setText(fila.getString(0));
                txtPrecio.setText(fila.getString(1));
                txtCantidad.setText(fila.getString(2));
                txtDescripcion.setText(fila.getString(3));
                BaseDeDatos.close();
            //En caso de NO encontrar el articulo
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes introducir el codigo del articulo", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }
}