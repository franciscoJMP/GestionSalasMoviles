package com.example.aplicacionfinalmovilesgestionsalas.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacionfinalmovilesgestionsalas.Controladores.BD_Salas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exponen;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;

import java.util.ArrayList;
import java.util.List;

public class Ctrl_Exponen extends BD_Salas {

    public Ctrl_Exponen(@Nullable Context context) {

        super(context);
    }

    public long insertarExponen(Exponen exp){
        long nreg_afectados=-1;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            ContentValues valores = new ContentValues();
            valores.put("idExposicion",exp.getIdExposicion());
            valores.put("dniPasaporte",exp.getDniPasaporte());
            nreg_afectados=db.insert("Exponen",null,valores);

        }
        db.close();
        return nreg_afectados;
    }
    public ArrayList obtenerArtistasExponen(Exposiciones e){
        SQLiteDatabase db=getReadableDatabase();
        List<String> listaArtistas=new ArrayList<>();
        if(db!=null){
            String[] campos = {"IdExposicion","dniPasaporte"};
            Cursor c = db.query("Exponen",campos,"IdExposicion="+e.getIdExposicion(),null,null,null,null);
            if(c.moveToFirst()){
                do{
                    listaArtistas.add(c.getString(1));
                }while(c.moveToNext());
            }
            c.close();
        }
        db.close();
        return (ArrayList) listaArtistas;
    }
    public long borrarExponenExposiciones(int idExposicion){
        long nreg_afectados=-1;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            nreg_afectados=db.delete("Exponen","IdExposicion="+idExposicion,null);

        }
        db.close();
        return nreg_afectados;
    }
    public long borrarExponenArtistas(String dniPasaporte){
        long nreg_afectados=-1;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            nreg_afectados=db.delete("Exponen","dniPasaporte= '"+dniPasaporte+"'",null);

        }
        db.close();
        return nreg_afectados;
    }

}
