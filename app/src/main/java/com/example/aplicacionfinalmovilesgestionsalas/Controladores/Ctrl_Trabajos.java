package com.example.aplicacionfinalmovilesgestionsalas.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;

import java.util.ArrayList;
import java.util.List;

public class Ctrl_Trabajos extends BD_Salas {
    public Ctrl_Trabajos(@Nullable Context context) {
        super(context);
    }

    public ArrayList<Trabajos> listarTrabajos() {
        SQLiteDatabase db = getReadableDatabase();
        Trabajos t;
        List<Trabajos> listaTrabajos = new ArrayList<>();
        if (db != null) {
            String[] campos = {"NombreTrabajo", "Descripcion", "Tamanio", "Peso", "dniPasaporte", "Foto"};
            Cursor c = db.query("Trabajos", campos, null, null, null, null, "NombreTrabajo");
            if (c.moveToFirst()) {
                do {
                    t = new Trabajos(c.getString(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5));
                    listaTrabajos.add(t);
                } while (c.moveToNext());
            }
            c.close();
        }
        db.close();
        return (ArrayList) listaTrabajos;
    }

    public long insertarTrabajos(Trabajos t) {
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("NombreTrabajo", t.getNombreTrabajo());
            valores.put("Descripcion", t.getDescripcion());
            valores.put("Tamanio", t.getTamanio());
            valores.put("Peso", t.getPeso());
            valores.put("dniPasaporte", t.getDniPasaporte());
            valores.put("Foto", t.getFoto());
            nreg_afectados = db.insert("Trabajos", null, valores);

        }
        db.close();
        return nreg_afectados;
    }

    public long borrarTrabajoArtistas(String dniPasaporte) {
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            nreg_afectados = db.delete("Trabajos", "dniPasaporte= '" + dniPasaporte+"'", null);

        }
        db.close();
        return nreg_afectados;
    }

    public ArrayList obtenerTrabajosList(String dniPasaporte) {
        SQLiteDatabase db = getReadableDatabase();
        Trabajos t;
        List<Trabajos> listaTrabajos = new ArrayList<>();
        if (db != null) {
            String[] campos = {"NombreTrabajo", "Descripcion", "Tamanio", "Peso", "dniPasaporte", "Foto"};
            Cursor c = db.query("Trabajos", campos, "dniPasaporte= '"+dniPasaporte+"'", null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    t = new Trabajos(c.getString(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5));
                    listaTrabajos.add(t);
                } while (c.moveToNext());
            }
            c.close();
        }
        db.close();
        return (ArrayList) listaTrabajos;
    }
    public long borrarTrabajos(Trabajos t){
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            nreg_afectados = db.delete("Trabajos", "NombreTrabajo= '" + t.getNombreTrabajo()+"'", null);

        }
        db.close();
        return nreg_afectados;
    }
    public Trabajos obtenerTrabajosUniqueObject(String dniPasaporte){
        SQLiteDatabase db = getReadableDatabase();
        Trabajos t=null;

        if (db != null) {
            String[] campos = {"NombreTrabajo", "Descripcion", "Tamanio", "Peso", "dniPasaporte", "Foto"};
            Cursor c = db.query("Trabajos", campos, "dniPasaporte= '"+dniPasaporte+"'", null, null, null, null);
            if (c.getCount()!=0) {
                c.moveToFirst();
                    t = new Trabajos(c.getString(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5));


            }
            c.close();
        }
        db.close();
        return t;

    }

}

