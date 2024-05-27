package com.example.dailygym;

import java.io.Serializable;
import java.util.List;

public class DiasEntreno implements Serializable {
    private int idDiaEntreno;
    private String nombreDia;
    private String descripcionDia;

    public DiasEntreno(int idDiaEntreno, String nombreDia, String descripcionDia) {
        this.idDiaEntreno = idDiaEntreno;
        this.nombreDia = nombreDia;
        this.descripcionDia = descripcionDia;
    }
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

    public String getDescripcionDia() {
        return descripcionDia;
    }

}
