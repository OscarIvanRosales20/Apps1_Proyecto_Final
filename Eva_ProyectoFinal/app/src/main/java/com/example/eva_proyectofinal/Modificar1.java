package com.example.eva_proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Modificar1 extends AppCompatActivity {

    TextView txtNombre, txtCodigo, txtPrecio, txtCantidad, txtDescripcion;
    Button btnBuscar, btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar1);

        //Vincular componentes
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        txtPrecio = (TextView)findViewById(R.id.txtPrecio);
        txtCantidad = (TextView)findViewById(R.id.txtCantidad);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcion);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnModificar = findViewById(R.id.btnModificar);

        btnModificar.setEnabled(false);
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

                btnModificar.setEnabled(true);
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes introducir el codigo del articulo", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }

    //Metodo para modificar un articulo o producto
    public void Modificar(View view){
        AdminSQLiteOpenHelper Admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = Admin.getWritableDatabase();

        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String precio = txtPrecio.getText().toString();
        String cantidad = txtCantidad.getText().toString();
        String descripcion = txtDescripcion.getText().toString();

        if (!codigo.isEmpty() && !nombre.isEmpty() && !precio.isEmpty() && !cantidad.isEmpty() && !descripcion.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("precio", precio);
            registro.put("cantidad", cantidad);
            registro.put("descripcion", descripcion);

            //Se declara una variable de tipo ENTERO porque el metodo update() retorna un ENTERO
            //que indica la cantidad de registros modificados, si modifica algun registro devuelve 1
            int cantidad2 = BaseDeDatos.update
                    ("articulos", registro, "codigo=" + codigo, null);
            BaseDeDatos.close();

            if (cantidad2 == 1){
                Toast.makeText(this, "Articulo modificado correctamente", Toast.LENGTH_SHORT).show();
                btnModificar.setEnabled(false);
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }
}