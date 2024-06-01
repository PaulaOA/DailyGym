package com.example.dailygym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
public class TusRutinasFragment extends Fragment {

    private RutinasAdapter rutinasAdapter;
    private BaseDatos baseDatos;

    public TusRutinasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tus_rutinas, container, false);

        baseDatos = new BaseDatos(getContext());
        RecyclerView recyclerViewTusRutinas = view.findViewById(R.id.recyclerViewTusRutinas);
        recyclerViewTusRutinas.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Rutinas> rutinasList = baseDatos.getAllRutinas();
        rutinasAdapter = new RutinasAdapter(rutinasList, getContext());
        recyclerViewTusRutinas.setAdapter(rutinasAdapter);

        rutinasAdapter.setOnDeleteClickListener(position -> {
            Rutinas rutina = rutinasList.get(position);
            baseDatos.deleteRutina(rutina.getIdRutina());
            rutinasList.remove(position);
            rutinasAdapter.notifyItemRemoved(position);
        });

        recyclerViewTusRutinas.setAdapter(rutinasAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");
    }
}