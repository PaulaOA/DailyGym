package com.example.dailygym;

import java.io.Serializable;

public class Ejercicios implements Serializable {
    private final int idEjercicio;
    private final String nombreEjercicio;
    private final String descripcionEjercicio;
    private final String musculoPrincipal;

    public Ejercicios(int idEjercicio, String nombreEjercicio, String descripcionEjercicio, String musculoPrincipal) {
        this.idEjercicio = idEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.musculoPrincipal = musculoPrincipal;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public String getDescripcionEjercicio() {
        return descripcionEjercicio;
    }

    public String getMusculoPrincipal() {
        return musculoPrincipal;
    }
}
