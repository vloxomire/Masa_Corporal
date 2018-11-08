package com.example.masa_corporal.Dao_BasedeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.masa_corporal.Models_getter_y_setters.MasaCorporalModels;

public class SqlLite_masaCorporal extends SQLiteOpenHelper {

    private static final String DBNAME="MasaCorporal";
    private static final Integer DBVERSION=1;
    private Context contexto;
    private SQLiteDatabase conectarse;

    public SqlLite_masaCorporal(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE `MasaCorporal` ( `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `Masa Corporal` INTEGER NOT NULL, `fecha y hora` INTEGER NOT NULL )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String query="CREATE TABLE `MasaCorporal` ( `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `Masa Corporal` INTEGER NOT NULL, `fecha y hora` INTEGER NOT NULL )";
        sqLiteDatabase.execSQL(query);
    }
    private void conectar(){
        conectarse = getWritableDatabase();
    }
    private void desconectarse(){
        conectarse.close();
    }
    public void guardarMasaCorporalSin(MasaCorporalModels masaObjeto){  //falta el model MasaCorporal
        this.conectar();    //metodo creado
        ContentValues fila = new ContentValues();
        fila.put("Masa Corporal",masaObjeto.getMasaCorporal());   //es parte del models
        conectarse.insert("MasaCorporal",null,fila);
        this.desconectarse();
    }
}
