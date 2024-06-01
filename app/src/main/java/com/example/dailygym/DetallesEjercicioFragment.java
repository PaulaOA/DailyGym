package com.example.dailygym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetallesEjercicioFragment extends Fragment {
    private static final String ARG_EJERCICIO = "ejercicio";
    private static final String ARG_ID_RUTINA = "idRutina";
    private static final String ARG_ID_DIA_ENTRENO = "idDiaEntreno";
    private Ejercicios ejercicio;
    private int idRutina;
    private int idDiaEntreno;
    private RegistrosAdapter registrosAdapter;
    private List<Registro> registrosList;

    public DetallesEjercicioFragment() {
    }

    public static DetallesEjercicioFragment newInstance(Ejercicios ejercicio, int idRutina, int idDiaEntreno) {
        DetallesEjercicioFragment fragment = new DetallesEjercicioFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EJERCICIO, ejercicio);
        args.putInt(ARG_ID_RUTINA, idRutina);
        args.putInt(ARG_ID_DIA_ENTRENO, idDiaEntreno);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ejercicio = (Ejercicios) getArguments().getSerializable(ARG_EJERCICIO);
            idRutina = getArguments().getInt(ARG_ID_RUTINA);
            idDiaEntreno = getArguments().getInt(ARG_ID_DIA_ENTRENO);
        }
        getParentFragmentManager().setFragmentResultListener("registroEliminado", this, (requestKey, result) -> cargarRegistros(ejercicio.getIdEjercicio()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_ejercicio, container, false);

        TextView textViewNombre = view.findViewById(R.id.textViewNombreDetalleEjercicio);
        TextView textViewDescripcion = view.findViewById(R.id.textViewDescripcionDetalleEjercicio);
        TextView textViewMusculoPrincipal = view.findViewById(R.id.textViewMusculoPrincipalDetalle);
        RecyclerView recyclerViewRegistros = view.findViewById(R.id.recyclerViewRegistros);
        recyclerViewRegistros.setLayoutManager(new LinearLayoutManager(getContext()));
        registrosList = new ArrayList<>();

        registrosAdapter = new RegistrosAdapter(getContext(), registrosList);
        recyclerViewRegistros.setAdapter(registrosAdapter);

        int idEjercicio = ejercicio.getIdEjercicio();
        cargarRegistros(idEjercicio);

        if (ejercicio != null) {
            textViewNombre.setText("Ejercicio: " + ejercicio.getNombreEjercicio());
            textViewDescripcion.setText(ejercicio.getDescripcionEjercicio());
            textViewMusculoPrincipal.setText(ejercicio.getMusculoPrincipal());
        }

        Button btnNuevoRegistro = view.findViewById(R.id.btnNuevoRegistro);
        btnNuevoRegistro.setOnClickListener(v -> abrirModalNuevoRegistro());
        return view;
    }

    private void cargarRegistros(int idEjercicio) {
        registrosList.clear();
        registrosList.addAll(new BaseDatos(getActivity()).getRegistrosPorEjercicio(idEjercicio));
        registrosAdapter.notifyDataSetChanged();
    }

    private void abrirModalNuevoRegistro() {
        NuevoRegistroDialogFragment dialogFragment = new NuevoRegistroDialogFragment();
        Bundle args = new Bundle();
        args.putInt("idEjercicio", ejercicio.getIdEjercicio());
        args.putInt("idRutina", idRutina);
        args.putInt("idDiaEntreno", idDiaEntreno);
        dialogFragment.setArguments(args);

        getParentFragmentManager().setFragmentResultListener("registroGuardado", this, (requestKey, result) -> cargarRegistros(ejercicio.getIdEjercicio()));
        dialogFragment.show(getParentFragmentManager(), "NuevoRegistroDialogFragment");
    }
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");
    }
}