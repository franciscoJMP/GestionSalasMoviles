package com.example.aplicacionfinalmovilesgestionsalas.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;

import java.util.ArrayList;
import java.util.List;

public class Ctrl_Artistas extends BD_Salas{
    public Ctrl_Artistas(@Nullable Context context) {
        super(context);
    }
    public ArrayList<Artistas> listarArtistas(){
        SQLiteDatabase db=getReadableDatabase();
        Artistas artistas;
        List<Artistas> listaArtistas=new ArrayList<>();
        if(db!=null){
            String[] campos = {"dniPasaporte","Nombre","Direccion","Poblacion","Provincia","Pais","MovilTrabajo","MovilPersonal","TelefonoFijo","Email","WebBlog","FechaNacimiento"};
            Cursor c = db.query("Artistas",campos,null,null,null,null,"dniPasaporte");
            if(c.moveToFirst()){
                do{
                    artistas=new Artistas(c.getString(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5),
                            c.getString(6),
                            c.getString(7),
                            c.getString(8),
                            c.getString(9),
                            c.getString(10),
                            c.getString(11));
                    listaArtistas.add(artistas);
                }while(c.moveToNext());
            }
            c.close();
        }
        db.close();
        return (ArrayList) listaArtistas;
    }

    public long insertarArtista(Artistas a){
        long nreg_afectados=-1;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            ContentValues valores = new ContentValues();
            valores.put("dniPasaporte",a.getDniPasaporte());
            valores.put("Nombre",a.getNombre());
            valores.put("Direccion",a.getDireccion());
            valores.put("Poblacion",a.getPoblacion());
            valores.put("Provincia",a.getProvincia());
            valores.put("Pais",a.getPais());
            valores.put("MovilTrabajo",a.getMovilTrabajo());
            valores.put("MovilPersonal",a.getMovilPersonal());
            valores.put("TelefonoFijo",a.getTelefonoFijo());
            valores.put("Email",a.getEmail());
            valores.put("WebBlog",a.getWebBlog());
            valores.put("FechaNacimiento",a.getFechaNacimiento());
            nreg_afectados=db.insert("Artistas",null,valores);

        }
        db.close();
        return nreg_afectados;
    }
    public long modificarArtistas(Artistas a){
        long nreg_afectados=-1;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            ContentValues valores = new ContentValues();
            valores.put("Nombre",a.getNombre());
            valores.put("Direccion",a.getDireccion());
            valores.put("Poblacion",a.getPoblacion());
            valores.put("Provincia",a.getProvincia());
            valores.put("Pais",a.getPais());
            valores.put("MovilTrabajo",a.getMovilTrabajo());
            valores.put("MovilPersonal",a.getMovilPersonal());
            valores.put("TelefonoFijo",a.getTelefonoFijo());
            valores.put("Email",a.getEmail());
            valores.put("WebBlog",a.getWebBlog());
            valores.put("FechaNacimiento",a.getFechaNacimiento());

            nreg_afectados=db.update("Artistas",valores,"dniPasaporte='"+a.getDniPasaporte()+"'",null);

        }
        db.close();
        return nreg_afectados;
    }
    public long borrarArtistas(Artistas a){
        long nreg_afectados=-1;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            nreg_afectados=db.delete("Artistas","dniPasaporte='"+a.getDniPasaporte()+"'",null);

        }
        db.close();
        return nreg_afectados;
    }
    public Artistas buscarArtista(String id){
        SQLiteDatabase db=getReadableDatabase();
        Artistas artistas=null;

        if(db!=null){
            String[] campos = {"dniPasaporte","Nombre","Direccion","Poblacion","Provincia","Pais","MovilTrabajo","MovilPersonal","TelefonoFijo","Email","WebBlog","FechaNacimiento"};
            Cursor c = db.query("Artistas",campos,"dniPasaporte='"+id+"'",null,null,null,null);
            if(c.getCount()!=0){
                c.moveToFirst();
                artistas=new Artistas(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11));

            } else{
                artistas=null;
            }
            c.close();
        }
        db.close();
        return artistas;

    }

}
