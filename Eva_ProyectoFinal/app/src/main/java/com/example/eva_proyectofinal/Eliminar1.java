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

public class Eliminar1 extends AppCompatActivity {

    TextView txtNombre, txtCodigo, txtPrecio, txtCantidad, txtDescripcion;
    Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar1);

        //Vincular componentes
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        txtPrecio = (TextView)findViewById(R.id.txtPrecio);
        txtCantidad = (TextView)findViewById(R.id.txtCantidad);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcion);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        btnEliminar.setEnabled(false);
    }

    public void Volver(View view){
        finish();
    }

    //Metodo para consultar un articulo o producto
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = txtCodigo.getText().toString();

        if(!codigo.isEmpty()){
            //Metodo con el el cual consulta una base de datos
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, precio, cantidad, descripcion from articulos where codigo =" + codigo, null);

            if (fila.moveToFirst()){
                txtNombre.setText(fila.getString(0));
                txtPrecio.setText(fila.getString(1));
                txtCantidad.setText(fila.getString(2));
                txtDescripcion.setText(fila.getString(3));
                BaseDeDatos.close();

                btnEliminar.setEnabled(true);
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes introducir el codigo del articulo", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }

    //Metodo para borrar un articulo o producto
    public void Eliminar(View view){
        AdminSQLiteOpenHelper Admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = Admin.getWritableDatabase();

        String codigo = txtCodigo.getText().toString();

        if(!codigo.isEmpty()){
            //Se declara una variable de tipo ENTERO porque el metodo delete() retorna un ENTERO
            //que indica la cantidad de registros borrados, si borra algun registro devuelve 1
            int cantidad = BaseDeDatos.delete
                    ("articulos", "codigo=" + codigo, null);
            BaseDeDatos.close();

            //Limpia los Widgets
            txtNombre.setText("");
            txtPrecio.setText("");
            txtCantidad.setText("");
            txtCodigo.setText("");
            txtDescripcion.setText("");

            if (cantidad == 1){
                Toast.makeText(this, "Articulo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                btnEliminar.setEnabled(false);
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes de introducir un codigo", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }
}