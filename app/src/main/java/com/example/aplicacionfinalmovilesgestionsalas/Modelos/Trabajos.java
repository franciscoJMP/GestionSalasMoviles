package com.example.aplicacionfinalmovilesgestionsalas.Modelos;

import java.io.Serializable;

public class Trabajos implements Serializable {
    private String nombreTrabajo;
    private String descripcion;
    private String tamanio;
    private String Peso;
    private String dniPasaporte;
    private String foto;

    public Trabajos() {
    }

    public Trabajos(String nombreTrabajo, String descripcion, String tamanio, String peso, String dniPasaporte, String foto) {
        this.nombreTrabajo = nombreTrabajo;
        this.descripcion = descripcion;
        this.tamanio = tamanio;
        Peso = peso;
        this.dniPasaporte = dniPasaporte;
        this.foto = foto;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getDniPasaporte() {
        return dniPasaporte;
    }

    public void setDniPasaporte(String dniPasaporte) {
        this.dniPasaporte = dniPasaporte;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
