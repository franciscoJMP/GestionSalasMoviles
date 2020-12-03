package com.example.aplicacionfinalmovilesgestionsalas.Controladores;

import android.content.Context;

import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Artistas;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Comentarios;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exponen;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Exposiciones;
import com.example.aplicacionfinalmovilesgestionsalas.Modelos.Trabajos;

import java.util.ArrayList;
import java.util.Iterator;

public class Funciones {
    private Context contexto;
    private Ctrl_Exposiciones ctrl_exposiciones;
    private Ctrl_Artistas ctrl_artistas;
    private Ctrl_Exponen ctrl_exponen;
    private Ctrl_Trabajos ctrl_trabajos;
    private Ctrl_Comentarios ctrl_comentarios;

    public Funciones(Context contexto) {
        this.contexto = contexto;
    }
    public ArrayList listarExposiciones(){
        ctrl_exposiciones=new Ctrl_Exposiciones(this.contexto);
        ArrayList<Exposiciones> listaExposiciones=ctrl_exposiciones.listarExposiciones();
        return listaExposiciones;

    }
    public long insertarExposicion(Exposiciones e){
        ctrl_exposiciones=new Ctrl_Exposiciones(this.contexto);
        return ctrl_exposiciones.insertarExposicion(e);
    }
    public long modificarExposicion(Exposiciones e ){
        ctrl_exposiciones=new Ctrl_Exposiciones(this.contexto);
        return ctrl_exposiciones.modificarExposicion(e);
    }
    public long borrarExposicion(Exposiciones e ){
        ctrl_exposiciones=new Ctrl_Exposiciones(this.contexto);
        return ctrl_exposiciones.borrarExposicion(e);
    }
    public ArrayList listarArtistas(){
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        ArrayList<Artistas> listaArtistas=ctrl_artistas.listarArtistas();
        return listaArtistas;
    }
    public long insertarArtista(Artistas a){
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        return ctrl_artistas.insertarArtista(a);
    }
    public long modificarArtista(Artistas a){
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        return ctrl_artistas.modificarArtistas(a);
    }
    public long borrarArtista(Artistas a){
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        return ctrl_artistas.borrarArtistas(a);
    }
    public long insertarExponen(Exponen exp){
        ctrl_exponen=new Ctrl_Exponen(this.contexto);
        return ctrl_exponen.insertarExponen(exp);
    }
    public ArrayList obtenerArtistasExponen(Exposiciones e){
        ctrl_exponen=new Ctrl_Exponen(this.contexto);
        return ctrl_exponen.obtenerArtistasExponen(e);
    }
    public ArrayList obtenerArtistasById(ArrayList listaArtistasId){
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        ArrayList<Artistas> listaArtistas = new ArrayList<>();

        for(Iterator it=listaArtistasId.iterator();it.hasNext();){
            String id = (String) it.next();
            Artistas a = ctrl_artistas.buscarArtista(id);
            listaArtistas.add(a);

        }
        return listaArtistas;
    }
    public Artistas obtenerArtistasTrabajosById(String dniPasaporte){
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        Artistas a = ctrl_artistas.buscarArtista(dniPasaporte);
        return a;
    }

    public long borrarExponenExposiciones(int idExposicion){
        ctrl_exponen=new Ctrl_Exponen(this.contexto);
        return ctrl_exponen.borrarExponenExposiciones(idExposicion);
    }
    public long borrarExponenArtistas(String dniPasaporte){
        ctrl_exponen=new Ctrl_Exponen(this.contexto);
        return ctrl_exponen.borrarExponenArtistas(dniPasaporte);
    }
    public long insertarTrabajo(Trabajos t){
        ctrl_trabajos=new Ctrl_Trabajos(this.contexto);
        return ctrl_trabajos.insertarTrabajos(t);
    }


    public Artistas buscarArtista(String dniPasaporte) {
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        return ctrl_artistas.buscarArtista(dniPasaporte);
    }
    public ArrayList listarTrabajos(){
        ctrl_trabajos=new Ctrl_Trabajos(this.contexto);
        return ctrl_trabajos.listarTrabajos();
    }
    public long borrarTrabajosArtista(String dniPasaporte){
        ctrl_trabajos=new Ctrl_Trabajos(this.contexto);
        return ctrl_trabajos.borrarTrabajoArtistas(dniPasaporte);
    }
    public ArrayList obtenerTrabajosExposiciones(int idExposicion){
        ctrl_trabajos=new Ctrl_Trabajos(this.contexto);
        ctrl_artistas=new Ctrl_Artistas(this.contexto);
        ctrl_exponen=new Ctrl_Exponen(this.contexto);
        ctrl_exposiciones=new Ctrl_Exposiciones(this.contexto);

        Exposiciones e = ctrl_exposiciones.obtenerExposicionId(idExposicion);
        ArrayList<String> listaExponen=ctrl_exponen.obtenerArtistasExponen(e);
        ArrayList<Trabajos> listaTrabajos = new ArrayList<>();
        for(Iterator it = listaExponen.iterator();it.hasNext();){
            String dniPasaporte = (String) it.next();
            Trabajos t = ctrl_trabajos.obtenerTrabajosUniqueObject(dniPasaporte);
            if(t!=null){
                listaTrabajos.add(t);
            }

        }
        return listaTrabajos;

    }
    public ArrayList listarComentarios(int idExposicion){
        ctrl_comentarios=new Ctrl_Comentarios(this.contexto);
        return ctrl_comentarios.listarComentarios(idExposicion);
    }
    public long insertarComentario(Comentarios c){
        ctrl_comentarios=new Ctrl_Comentarios(this.contexto);
        return ctrl_comentarios.insertarComentarios(c);
    }
    public long borrarComentario(Comentarios c){
        ctrl_comentarios=new Ctrl_Comentarios(this.contexto);
        return ctrl_comentarios.borrarComentarios(c);
    }
    public long borrarComentarioIdExposicion(Exposiciones e){
        ctrl_comentarios=new Ctrl_Comentarios(this.contexto);
        return ctrl_comentarios.borrarComentariosIdExposiciones(e);
    }
    public long borrarComentariosIdTrabajos(Trabajos t){
        ctrl_comentarios=new Ctrl_Comentarios(this.contexto);
        return ctrl_comentarios.borrarComentariosIdTrabajo(t);
    }
    public long borrarTrabajo(Trabajos t){
        ctrl_trabajos=new Ctrl_Trabajos(this.contexto);
        return ctrl_trabajos.borrarTrabajos(t);
    }
    public ArrayList obtenerTrabajosList(Artistas a){
        ctrl_trabajos=new Ctrl_Trabajos(this.contexto);
        return ctrl_trabajos.obtenerTrabajosList(a.getDniPasaporte());
    }
    public long modificarComentarios(Comentarios c){
        ctrl_comentarios=new Ctrl_Comentarios(this.contexto);
        return ctrl_comentarios.modificarComentarios(c);
    }
    public Exposiciones obtenerExposiciondeUnComentario(int idExpo){
        ctrl_exposiciones=new Ctrl_Exposiciones(this.contexto);
        return ctrl_exposiciones.obtenerExposicionComentarios(idExpo);
    }
}
