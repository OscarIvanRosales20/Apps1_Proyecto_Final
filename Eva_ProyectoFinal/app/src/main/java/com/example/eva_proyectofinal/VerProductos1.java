package com.example.eva_proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class VerProductos1 extends AppCompatActivity {

    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver1);

        btnVolver = findViewById(R.id.btnVolver);
        Listar();//Manda llamar al metodo para su uso desde un inicio
    }

    //Metodo para Mostrar los Articulos en la Lista
    public void Listar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        if(BaseDeDatos!=null){ //Si mi base de datos no esta vacia
            //Utiliza un cursor para recorrer los elementos de la base de datos(Scanner)
            Cursor c = BaseDeDatos.rawQuery("select * from articulos", null);
            int cantidad = c.getCount(); //Cantidad de registros en la base de datos
            int i = 0;
            String[] arreglo = new String[cantidad];//Arreglo donde se guardada cada elemento
            if(c.moveToFirst()){ //Si existe un primer elemento en BDD
                do{
                    //Define los valores a mostrar
                    String linea = c.getInt(0)
                            + " -- " + c.getString(1)
                            + " -- " + c.getString(2)
                            + " -- " + c.getString(3)
                            + " -- " + c.getString(4);
                    arreglo[i] = linea; //Se agrega los valores al arreglo teniendo como limite "i"
                    i++;

                }while(c.moveToNext());//
            }
            //Adaptador para una lista
            ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
            //Vincula un elemento lista con un widget
            ListView lista = (ListView)findViewById(R.id.lstVwArticulos);
            //A la lista se le carga el adaptador
            lista.setAdapter(adapter);
        }
    }

    public void Volver(View view){
        finish();
    }
}