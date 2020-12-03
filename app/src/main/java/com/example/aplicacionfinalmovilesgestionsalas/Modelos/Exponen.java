package com.example.aplicacionfinalmovilesgestionsalas.Modelos;

import java.io.Serializable;

public class Exponen implements Serializable {
    private int idExposicion;
    private String dniPasaporte;

    public Exponen() {
    }

    public Exponen(int idExposicion, String dniPasaporte) {
        this.idExposicion = idExposicion;
        this.dniPasaporte = dniPasaporte;
    }

    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    public String getDniPasaporte() {
        return dniPasaporte;
    }

    public void setDniPasaporte(String dniPasaporte) {
        this.dniPasaporte = dniPasaporte;
    }
}
