package com.example.eva_proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Registrar1 extends AppCompatActivity {

    TextView txtNombre, txtCodigo, txtPrecio, txtCantidad, txtDescripcion;
    Button btnVolver, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar1);

        //Vincular componentes
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        txtPrecio = (TextView)findViewById(R.id.txtPrecio);
        txtCantidad = (TextView)findViewById(R.id.txtCantidad);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcion);
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    public void Volver(View view){
        finish();
    }

    //Metodo para dar de alta los productos
    public void Registrar(View view){
        //Se crea objeto de la clase AdminSQLiteOpenHelper
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        //Indicamos que va a abrir la base de datos en modo de lectura/escritura con el metodo getWritableDatabase();
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String precio = txtPrecio.getText().toString();
        String cantidad = txtCantidad.getText().toString();
        String descripcion = txtDescripcion.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !precio.isEmpty() && !cantidad.isEmpty() && !descripcion.isEmpty()){

            //Comprobamos que el codigo introducido no haya sido regitrado anteriormente
            String query = "select * from articulos where codigo=" + codigo;
            Cursor cursor = BaseDeDatos.rawQuery(query, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Toast.makeText(this, "Ya existe un producto con este Codigo, elija otro", Toast.LENGTH_SHORT).show();
                    cursor.moveToNext();
                }
            }else{
            //Creamos un objeto de la clase ContentValues para guardar los valores del usuario en la BDD
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("precio", precio);
            registro.put("cantidad", cantidad);
            registro.put("descripcion", descripcion);

            //Guardamos los valores dentro de la tabla "articulos"
            BaseDeDatos.insert("articulos", null, registro);
            //Cerramos la BDD y se limpian los campos
            BaseDeDatos.close();
            txtNombre.setText("");
            txtCodigo.setText("");
            txtPrecio.setText("");
            txtCantidad.setText("");
            txtDescripcion.setText("");

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}