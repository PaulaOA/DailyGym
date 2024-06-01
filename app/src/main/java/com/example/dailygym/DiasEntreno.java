package com.example.dailygym;

import java.io.Serializable;
public class DiasEntreno implements Serializable {
    private final int idDiaEntreno;
    private final String nombreDia;

    public DiasEntreno(int idDiaEntreno, String nombreDia) {
        this.idDiaEntreno = idDiaEntreno;
        this.nombreDia = nombreDia;
    }

    public int getIdDiaEntreno() {
        return idDiaEntreno;
    }

    public String getNombreDia() {
        return nombreDia;
    }
}
