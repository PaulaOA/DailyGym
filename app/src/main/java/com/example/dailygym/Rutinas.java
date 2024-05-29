package com.example.dailygym;

import java.io.Serializable;
import java.util.List;

public class Rutinas implements Serializable {
    private int idRutina;
    private final String nombreRutina;
    private final String descripcionRutina;
    private List<DiasEntreno> diasEntreno;
    private String autorRutina;

    public Rutinas(int idRutina, String nombreRutina, String descripcionRutina, List<DiasEntreno> diasEntreno, String autorRutina) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.diasEntreno = diasEntreno;
        this.autorRutina = autorRutina;

    }

    public Rutinas(int idRutina, String nombreRutina, String descripcionRutina, String autorRutina) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.autorRutina = autorRutina;
    }

    public Rutinas(String nombreRutina, String descripcionRutina,  List<DiasEntreno> diasEntreno) {
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.diasEntreno = diasEntreno;
    }
    public Rutinas(int idRutina, String nombreRutina, String descripcionRutina) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
    }

    public Rutinas(int idRutina, String nombreRutina, String descripcionRutina, String autorRutina, List<DiasEntreno> diasEntreno) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.autorRutina = autorRutina;
        this.diasEntreno = diasEntreno;
    }

    public Rutinas(String nombreRutina, String descripcionRutina, String autorRutina, List<DiasEntreno> diasEntreno) {
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.autorRutina = autorRutina;
        this.diasEntreno = diasEntreno;
    }

    public int getIdRutina() {
        return idRutina;
    }
    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public List<DiasEntreno> getDiasEntreno() {
        return diasEntreno;
    }

    public String getDescripcionRutina() {
        return descripcionRutina;
    }

    public String getAutorRutina() {
        return autorRutina;
    }

}
