package com.example.aplicacionfinalmovilesgestionsalas.Modelos;

import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Date;

public class Exposiciones implements Serializable {
    private int idExposicion;
    private String nombreExp;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;

    public Exposiciones() {

    }

    public Exposiciones(int idExposicion, String nombreExp, String descripcion, String fechaInicio, String fechaFin) {
        this.idExposicion = idExposicion;
        this.nombreExp = nombreExp;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
