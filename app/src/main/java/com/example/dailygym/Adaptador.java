package com.example.dailygym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adaptador extends ArrayAdapter {

    Rutinas[] rutinas;
    public Adaptador(@NonNull Context context, Rutinas[] datosRutina) {
        super(context, R.layout.vista_rutina, datosRutina);
        this.rutinas = datosRutina;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflador = LayoutInflater.from(getContext());
        View elemento = inflador.inflate(R.layout.vista_rutina, parent, false);

        TextView nombreRutina = (TextView) elemento.findViewById(R.id.nombreRutina);
        nombreRutina.setText(rutinas[position].getNombreRutina());

        TextView descripcionRutina = (TextView) elemento.findViewById(R.id.descripcionRutina);
        descripcionRutina.setText(rutinas[position].getDescripcionRutina());

        ImageView imagenRutina = (ImageView) elemento.findViewById(R.id.imagenRutina);

        switch (rutinas[position].getAutorRutina()){
            case "Mujer": imagenRutina.setImageResource(R.drawable.gym_mujer);
            break;
            case "Hombre": imagenRutina.setImageResource(R.drawable.gym_hombre);
            break;
        }

        return elemento;
    }
}
