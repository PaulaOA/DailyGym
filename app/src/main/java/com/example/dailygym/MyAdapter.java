package com.example.dailygym;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final String[] diasSemana;
    private final boolean[] diasSeleccionados;
    private final Handler handler = new Handler(Looper.getMainLooper());

    public MyAdapter(String[] diasSemana) {
        this.diasSemana = diasSemana;
        this.diasSeleccionados = new boolean[diasSemana.length];
    }

    public String getItem(int position) {
        return diasSemana[position];
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox_dias, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String dia = diasSemana[position];
        holder.dia.setText(dia);
        holder.dia.setChecked(diasSeleccionados[position]);

    }

    @Override
    public int getItemCount() {
        return diasSemana.length;
    }

    public boolean isSelected(int position) {
        return diasSeleccionados[position];
    }

    public void setSelected (int position, boolean selected) {
        diasSeleccionados[position] = selected;
        handler.post(() -> notifyItemChanged(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox dia;

        public ViewHolder(View itemView, MyAdapter adapter) {
            super(itemView);
            this.dia = itemView.findViewById(R.id.checkBoxDia);

            this.dia.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    adapter.setSelected (position,isChecked);
                }
            });
        }
    }
}
