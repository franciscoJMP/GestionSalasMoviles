package com.example.aplicacionfinalmovilesgestionsalas.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Comentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;

import java.util.ArrayList;
import java.util.List;

public class Ctrl_Comentarios extends BD_Salas {
    public Ctrl_Comentarios(@Nullable Context context) {
        super(context);
    }
    public ArrayList<Comentarios> listarComentarios(int idExposicion){
        SQLiteDatabase db=getReadableDatabase();
        Comentarios comentarios;
        List<Comentarios> listaComentarios=new ArrayList<>();
        if(db!=null){
            String[] campos = {"IdExposicion","NombreTrab","Comentario"};
            Cursor c = db.query("Comentarios",campos,"idExposicion="+idExposicion,null,null,null,"idExposicion");
            if(c.moveToFirst()){
                do{
                    comentarios=new Comentarios(c.getInt(0),c.getString(1),c.getString(2));
                    listaComentarios.add(comentarios);
                }while(c.moveToNext());
            }
            c.close();
        }
        db.close();
        return (ArrayList) listaComentarios;
    }
    public long insertarComentarios(Comentarios comentarios) {
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("IdExposicion",comentarios.getIdExposicion());
            valores.put("NombreTrab",comentarios.getNombreTrabajo());
            valores.put("Comentario",comentarios.getComentario());
            nreg_afectados = db.insert("Comentarios", null, valores);

        }
        db.close();
        return nreg_afectados;
    }

    public long borrarComentarios(Comentarios c) {
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            nreg_afectados = db.delete("Comentarios", "idExposicion="+c.getIdExposicion()+" AND NombreTrab='"+c.getNombreTrabajo()+"'", null);

        }
        db.close();
        return nreg_afectados;
    }
    public long borrarComentariosIdExposiciones(Exposiciones e){
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            nreg_afectados = db.delete("Comentarios", "idExposicion= "+e.getIdExposicion(), null);

        }
        db.close();
        return nreg_afectados;
    }

    public long borrarComentariosIdTrabajo(Trabajos t){
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            nreg_afectados = db.delete("Comentarios", "NombreTrab='"+t.getNombreTrabajo()+"'", null);

        }
        db.close();
        return nreg_afectados;
    }
    public long modificarComentarios(Comentarios c) {
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("IdExposicion",c.getIdExposicion());
            valores.put("NombreTrab",c.getNombreTrabajo());
            valores.put("Comentario",c.getComentario());
            nreg_afectados = db.update("Comentarios",valores, "idExposicion="+c.getIdExposicion()+" AND NombreTrab='"+c.getNombreTrabajo()+"'", null);


        }
        db.close();
        return nreg_afectados;
    }
}
