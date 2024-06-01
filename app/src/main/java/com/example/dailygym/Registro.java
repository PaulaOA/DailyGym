package com.example.dailygym;

public class Registro {
    private final int idRegistro;
    private final double peso;
    private final int repeticiones;
    private final int series;
    private final String fecha;

    public Registro(int idRegistro, double peso, int repeticiones, int series, String fecha) {
        this.idRegistro = idRegistro;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.series = series;
        this.fecha = fecha;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public double getPeso() {
        return peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public String getFecha() {
        return fecha;
    }
}