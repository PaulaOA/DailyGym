package com.example.dailygym;

import java.util.List;

public class DiasEntreno {
    private int idDiaEntreno;
    private String nombreDia;
    private String descripcionDia;
    private List<Ejercicios> ejerciciosDia;

    public DiasEntreno(int idDiaEntreno, String nombreDia, String descripcionDia, List<Ejercicios> ejerciciosDia) {
        this.idDiaEntreno = idDiaEntreno;
        this.nombreDia = nombreDia;
        this.descripcionDia = descripcionDia;
        this.ejerciciosDia = ejerciciosDia;
    }

    public DiasEntreno(int idDiaEntreno, String nombreDia, String descripcionDia) {
        this.idDiaEntreno = idDiaEntreno;
        this.nombreDia = nombreDia;
        this.descripcionDia = descripcionDia;
    }

    public int getIdDiaEntreno() {
        return idDiaEntreno;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public String getDescripcionDia() {
        return descripcionDia;
    }

    public List<Ejercicios> getEjerciciosDia() {
        return ejerciciosDia;
    }
}
