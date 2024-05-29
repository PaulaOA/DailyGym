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

public class Adaptador extends ArrayAdapter<Rutinas> {

    static class ViewHolder {
        TextView nombreRutina;
        TextView descripcionRutina;
        ImageView imagenRutina;
    }

    public Adaptador(@NonNull Context context, Rutinas[] datosRutina) {
        super(context, R.layout.vista_rutina, datosRutina);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.vista_rutina, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nombreRutina = convertView.findViewById(R.id.nombreRutina);
            viewHolder.descripcionRutina = convertView.findViewById(R.id.descripcionRutina);
            viewHolder.imagenRutina = convertView.findViewById(R.id.imagenRutina);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Rutinas rutina = getItem(position);

        if(rutina != null) {
            viewHolder.nombreRutina.setText(rutina.getNombreRutina());
            viewHolder.descripcionRutina.setText(rutina.getDescripcionRutina());

            switch (rutina.getAutorRutina()) {
                case "Mujer":
                    viewHolder.imagenRutina.setImageResource(R.drawable.gym_mujer);
                    break;
                case "Hombre":
                    viewHolder.imagenRutina.setImageResource(R.drawable.gym_hombre);
                    break;
            }
        }

        return convertView;
    }
}
