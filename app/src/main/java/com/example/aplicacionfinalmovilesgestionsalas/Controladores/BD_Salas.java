package com.example.aplicacionfinalmovilesgestionsalas.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;

import java.util.ArrayList;
import java.util.List;

public class BD_Salas extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 9;
    private static final String NOMBRE_BASEDATOS = "BD_Exposiciones.db";
    private static final String NOMBRE_TABLA_Exposiciones = "Exposiciones";
    private static final String NOMBRE_TABLA_Exponen = "Exponen";
    private static final String NOMBRE_TABLA_Artistas = "Artistas";
    private static final String NOMBRE_TABLA_Trabajos = "Trabajos";
    private static final String NOMBRE_TABLA_Comentarios = "Comentarios";

    private static final String ins_Exposiciones = "CREATE TABLE " +
            NOMBRE_TABLA_Exposiciones + " ( IdExposicion int primary key, NombreExp varchar(100),Descripcion varchar(100), FechaInicio Date,FechaFin Date)";
    private static final String ins_Exponen = "CREATE TABLE " +
            NOMBRE_TABLA_Exponen + " ( idExposicion varchar(20), dniPasaporte varchar(20), primary key(idExposicion,dniPasaporte)," +
            " CONSTRAINT EXPONEN_EXPOSICIONES_FK\n" +
            "    FOREIGN KEY (idExposicion)\n" +
            "    REFERENCES Exposiciones(IdExposicion)," +
            " CONSTRAINT EXPONEN_ARTISTAS_FK\n" +
            "    FOREIGN KEY (dniPasaporte)\n" +
            "    REFERENCES Artistas(dniPasaporte))";

    private static final String ins_Artistas = "CREATE TABLE " +
            NOMBRE_TABLA_Artistas+ " (dniPasaporte varchar(15) primary key,Nombre varchar(20), Direccion varchar(15), Poblacion varchar(20), " +
            "Provincia varchar(10), Pais varchar(10), MovilTrabajo varchar(20), MovilPersonal varchar(20), TelefonoFijo varchar(20), " +
            "Email varchar(20), WebBlog varchar(20), FechaNacimiento varchar(20))";

    private static final String ins_Trabajos = "CREATE TABLE " +
            NOMBRE_TABLA_Trabajos+ " (NombreTrabajo varchar(20) primary key, Descripcion varchar(20), Tamanio varchar(20), " +
            "Peso varchar(15), dniPasaporte varchar(20), Foto varchar(20)," +
            " CONSTRAINT TRABAJOS_ARTISTAS_FK \n" +
            "    FOREIGN KEY (dniPasaporte)\n" +
            "    REFERENCES Artistas(dniPasaporte))";

    private static final String ins_Comentarios= "CREATE TABLE " +
            NOMBRE_TABLA_Comentarios+ " ( idExposicion int(20), NombreTrab varchar(20), Comentario varchar(100), primary key(idExposicion,NombreTrab)," +
            " CONSTRAINT COMENTARIOS_EXPOSICIONES_FK\n" +
            "    FOREIGN KEY (idExposicion)\n" +
            "    REFERENCES Exposiciones(IdExposicion)," +
            " CONSTRAINT COMENTARIOS_TRABAJOS_FK\n" +
            "    FOREIGN KEY (NombreTrab)\n" +
            "    REFERENCES Trabajos(NombreTrabajo))";

    public BD_Salas(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ins_Exposiciones);
        db.execSQL(ins_Artistas);
        db.execSQL(ins_Exponen);
        db.execSQL(ins_Trabajos);
        db.execSQL(ins_Comentarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String upExposiciones="DROP TABLE IF EXISTS " +NOMBRE_TABLA_Exposiciones;
            String upArtistas="DROP TABLE IF EXISTS " +NOMBRE_TABLA_Artistas;
            String upExponen="DROP TABLE IF EXISTS " +NOMBRE_TABLA_Exponen;
            String upTrabajos="DROP TABLE IF EXISTS " +NOMBRE_TABLA_Trabajos;
            String upComentarios="DROP TABLE IF EXISTS " +NOMBRE_TABLA_Comentarios;
            db.execSQL(upExposiciones);
            db.execSQL(upArtistas);
            db.execSQL(upExponen);
            db.execSQL(upTrabajos);
            db.execSQL(upComentarios);


            onCreate(db);
    }





}
