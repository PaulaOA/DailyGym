package com.example.dailygym;

public class UserProfile {
    private String nombre;
    private int edad;
    private String sexo;
    private double peso;
    private double altura;
    private String objetivo;

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
}
