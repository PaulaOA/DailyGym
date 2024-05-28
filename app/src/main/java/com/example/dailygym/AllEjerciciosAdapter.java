package com.example.dailygym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ListResourceBundle;

public class AllEjerciciosAdapter extends RecyclerView.Adapter<AllEjerciciosAdapter.AllEjerciciosViewHolder> {
    private List<Ejercicios> ejerciciosList;
    private Context context;

    public AllEjerciciosAdapter(Context context, List<Ejercicios> ejerciciosList) {
        this.context = context;
        this.ejerciciosList = ejerciciosList;
    }

    @NonNull
    @Override
    public AllEjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registros_ejercicio, parent, false);
        return new AllEjerciciosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllEjerciciosViewHolder holder, int position) {
        final Ejercicios ejercicio = ejerciciosList.get(position);
        holder.nombreEjercicio.setText(ejercicio.getNombreEjercicio());

        BaseDatos baseDatos = new BaseDatos(context);
        List<Registro> registrosList = baseDatos.getRegistrosPorEjercicio(ejercicio.getIdEjercicio());
        AllRegistrosAdapter allRegistrosAdapter = new AllRegistrosAdapter(registrosList);
        holder.recyclerViewRegistrosPorEjercicio.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerViewRegistrosPorEjercicio.setAdapter(allRegistrosAdapter);

        holder.cardViewRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!registrosList.isEmpty()) {
                    holder.sinRegistrosTextView.setVisibility(View.GONE);
                    if (holder.recyclerViewRegistrosPorEjercicio.getVisibility() == View.VISIBLE) {
                        holder.recyclerViewRegistrosPorEjercicio.setVisibility(View.GONE);
                    } else {
                        holder.recyclerViewRegistrosPorEjercicio.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (holder.sinRegistrosTextView.getVisibility() == View.GONE) {
                        holder.sinRegistrosTextView.setVisibility(View.VISIBLE);
                    } else {
                        holder.sinRegistrosTextView.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ejerciciosList.size();
    }

    public class AllEjerciciosViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewRegistros;
        TextView nombreEjercicio;
        RecyclerView recyclerViewRegistrosPorEjercicio;
        TextView sinRegistrosTextView;


        public AllEjerciciosViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewRegistros = itemView.findViewById(R.id.cardViewRegistros);
            nombreEjercicio = itemView.findViewById(R.id.textViewNombreEjercicioRegistro);
            recyclerViewRegistrosPorEjercicio = itemView.findViewById(R.id.recyclerViewRegistrosPorEjercicio);
            sinRegistrosTextView = itemView.findViewById(R.id.sinRegistrostextView);
        }
    }

}
