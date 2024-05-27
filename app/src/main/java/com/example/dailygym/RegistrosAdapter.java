package com.example.dailygym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegistrosAdapter extends RecyclerView.Adapter<RegistrosAdapter.RegistroViewHolder> {
    private List<Registro> registros;
    private Context context;

    public RegistrosAdapter(Context context, List<Registro> registros) {
        this.context = context;
        this.registros = registros;
    }
    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro, parent, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        Registro registro = registros.get(position);
        holder.bind(registro);
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPeso, textViewRepeticiones, textViewSeries, textViewFecha;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPeso = itemView.findViewById(R.id.textViewPeso);
            textViewRepeticiones = itemView.findViewById(R.id.textViewRepeticiones);
            textViewSeries = itemView.findViewById(R.id.textViewSeries);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
        }

        public void bind(Registro registro) {
            textViewPeso.setText("Peso: " + registro.getPeso() + " kg");
            textViewRepeticiones.setText("Repeticiones: " + registro.getRepeticiones());
            textViewSeries.setText("Series: " + registro.getSeries());
            textViewFecha.setText(registro.getFecha());
        }
    }
}
