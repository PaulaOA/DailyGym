package com.example.dailygym;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.RutinasViewHolder> {

    private final List<Rutinas> rutinasList;
    private final Context context;
    private OnDeleteClickListener listener;

    public interface OnDeleteClickListener {
        void onDeleteClick (int position);
    }

    public RutinasAdapter(List<Rutinas> rutinasList, Context context) {
        this.rutinasList = rutinasList;
        this.context = context;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RutinasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tus_rutinas, parent, false);
        return new RutinasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinasViewHolder holder, int position) {
        Rutinas rutina = rutinasList.get(position);
        holder.textViewNombreRutina.setText(rutina.getNombreRutina());
        holder.textViewDescripcionRutina.setText(rutina.getDescripcionRutina());

        StringBuilder diasEntrenoStr = new StringBuilder();
        List<DiasEntreno> diasEntreno = rutina.getDiasEntreno();
        if (diasEntreno != null) {
            for (DiasEntreno diaEntreno : diasEntreno) {
                diasEntrenoStr.append(diaEntreno.getNombreDia()).append(", ");
            }
        }
        if (diasEntrenoStr.length() > 0) {
            diasEntrenoStr.deleteCharAt(diasEntrenoStr.length() - 2);
        }
        holder.textViewDiasRutina.setText(diasEntrenoStr.toString());

        if (rutina.getAutorRutina().equals("Mujer")) {
            holder.imagenRutinaCreada.setImageResource(R.drawable.gym_mujer);
        } else {
            holder.imagenRutinaCreada.setImageResource(R.drawable.gym_hombre);
        }

        holder.itemView.setOnClickListener(v -> {
            Rutinas rutinaSeleccionada = rutinasList.get(holder.getAbsoluteAdapterPosition());

            DetallesRutinaFragment detallesFragment = DetallesRutinaFragment.newInstance(rutinaSeleccionada);
            ((MainActivity) context).replaceFragment(detallesFragment, true);
        });
    }

    @Override
    public int getItemCount() {
        return rutinasList.size();
    }

    public class RutinasViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombreRutina;
        TextView textViewDescripcionRutina;
        TextView textViewDiasRutina;
        ImageView btnBorrarRutina;

        ImageView imagenRutinaCreada;

        public RutinasViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreRutina = itemView.findViewById(R.id.textViewNombreRutinaUsuario);
            textViewDescripcionRutina = itemView.findViewById(R.id.textViewDescripcionRutinaUsuario);
            textViewDiasRutina = itemView.findViewById(R.id.textViewDiasRutina);
            btnBorrarRutina = itemView.findViewById(R.id.btnBorrarRutina);
            imagenRutinaCreada = itemView.findViewById(R.id.imagenRutinaCreada);

            btnBorrarRutina.setOnClickListener(v -> {
                final int position = getAbsoluteAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Eliminar rutina");
                    builder.setMessage("¿Desea eliminar esta rutina?");
                    builder.setPositiveButton("Sí", (dialog, which) -> listener.onDeleteClick(position));
                    builder.setNegativeButton("Cancelar", null);
                    builder.show();
                }
            });
        }
    }
}