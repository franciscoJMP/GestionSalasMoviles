package com.example.aplicacionfinalmovilesgestionsalas.Modelos;

import java.io.Serializable;

public class Comentarios implements Serializable {
    private int idExposicion;
    private String nombreTrabajo;
    private String comentario;

    public Comentarios() {
    }

    public Comentarios(int idExposicion, String nombreTrabajo, String comentario) {
        this.idExposicion = idExposicion;
        this.nombreTrabajo = nombreTrabajo;
        this.comentario = comentario;
    }

    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
