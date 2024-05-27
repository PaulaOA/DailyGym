package com.example.dailygym;

import java.io.Serializable;
import java.util.List;

public class Rutinas implements Serializable {
    private int idRutina;
    private String nombreRutina;
    private String descripcionRutina;
    private List<DiasEntreno> diasEntreno;
    private List<List<Ejercicios>> ejercicios;
    private String autorRutina;
    private int imagenRutinaId;

    public Rutinas(int idRutina, String nombreRutina, String descripcionRutina, List<DiasEntreno> diasEntreno, List<List<Ejercicios>> ejercicios, String autorRutina, int imagenRutinaId) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.descripcionRutina = descripcionRutina;
        this.diasEntreno = diasEntreno;
        this.autorRutina = autorRutina;
        this.imagenRutinaId = imagenRutinaId;
        this.ejercicios = ejercicios;

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

    public int getImagenRutinaId() {
        return imagenRutinaId;
    }

    public String getAutorRutina() {
        return autorRutina;
    }

    public List<List<Ejercicios>> getEjercicios() {
        return ejercicios;
    }

}
