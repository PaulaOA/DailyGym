package com.example.dailygym;

public class Registro {
    private int idRegistro;
    private double peso;
    private int repeticiones;
    private int series;
    private String fecha;

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

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
