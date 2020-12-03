package com.example.aplicacionfinalmovilesgestionsalas.Modelos;

import java.io.Serializable;

public class Artistas implements Serializable {
    private String dniPasaporte;
    private String nombre;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String pais;
    private String movilTrabajo;
    private String movilPersonal;
    private String telefonoFijo;
    private String email;
    private String webBlog;
    private String fechaNacimiento;

    public Artistas() {
    }

    public Artistas(String dniPasaporte, String nombre, String direccion, String poblacion, String provincia, String pais, String movilTrabajo, String movilPersonal, String telefonoFijo, String email, String webBlog, String fechaNacimiento) {
        this.dniPasaporte = dniPasaporte;
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
        this.movilTrabajo = movilTrabajo;
        this.movilPersonal = movilPersonal;
        this.telefonoFijo = telefonoFijo;
        this.email = email;
        this.webBlog = webBlog;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDniPasaporte() {
        return dniPasaporte;
    }

    public void setDniPasaporte(String dniPasaporte) {
        this.dniPasaporte = dniPasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMovilTrabajo() {
        return movilTrabajo;
    }

    public void setMovilTrabajo(String movilTrabajo) {
        this.movilTrabajo = movilTrabajo;
    }

    public String getMovilPersonal() {
        return movilPersonal;
    }

    public void setMovilPersonal(String movilPersonal) {
        this.movilPersonal = movilPersonal;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebBlog() {
        return webBlog;
    }

    public void setWebBlog(String webBlog) {
        this.webBlog = webBlog;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
