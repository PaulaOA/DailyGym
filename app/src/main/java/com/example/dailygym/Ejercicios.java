package com.example.dailygym;

public class Ejercicios {
    private String idEjercicio;
    private String nombreEjercicio;
    private String descripcionEjercicio;
    private String musculoPrincipal;

    public Ejercicios(String idEjercicio, String nombreEjercicio, String descripcionEjercicio, String musculoPrincipal) {
        this.idEjercicio = idEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.musculoPrincipal = musculoPrincipal;
    }

    public String getIdEjercicio() {
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
