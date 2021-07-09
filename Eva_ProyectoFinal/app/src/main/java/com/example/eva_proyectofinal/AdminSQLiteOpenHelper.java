package com.example.eva_proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//Clase con la cual se administra una base de datos
public class AdminSQLiteOpenHelper extends  SQLiteOpenHelper{

    String table = "create table articulos(codigo int primary key, nombre text, precio real, cantidad real, descripcion text)";
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //Creacion de la tabla donde se guardan los articulos
        BaseDeDatos.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
