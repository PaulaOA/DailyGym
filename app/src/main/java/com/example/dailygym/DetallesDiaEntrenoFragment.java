package com.example.dailygym;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetallesDiaEntrenoFragment extends Fragment {
    private static final String ARG_DIA_ENTRENO = "diaEntreno";
    private static final String ARG_NOMBRE_RUTINA = "nombreRutina";
    private static final String ARG_NOMBRE_DIA = "nombreDia";
    private static final String ARG_ID_RUTINA = "idRutina";
    private static final String ARG_ID_DIA_ENTRENO = "idDiaEntreno";

    private RecyclerView recyclerViewEjerciciosDia;
    private EjerciciosAdapter ejerciciosAdapter;

    public DetallesDiaEntrenoFragment() {
    }

    public static DetallesDiaEntrenoFragment newInstance(DiasEntreno diaEntreno, int idRutina, int idDiaEntreno, String nombreRutina, String nombreDia) {
        DetallesDiaEntrenoFragment fragment = new DetallesDiaEntrenoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DIA_ENTRENO, diaEntreno);
        args.putInt(ARG_ID_RUTINA, idRutina);
        args.putInt(ARG_ID_DIA_ENTRENO, idDiaEntreno);
        args.putString(ARG_NOMBRE_RUTINA, nombreRutina);
        args.putString(ARG_NOMBRE_DIA, nombreDia);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int idRutina = getArguments().getInt(ARG_ID_RUTINA);
            int idDiaEntreno = getArguments().getInt(ARG_ID_DIA_ENTRENO);
            String nombreRutina = getArguments().getString(ARG_NOMBRE_RUTINA);
            String nombreDia = getArguments().getString(ARG_NOMBRE_DIA);
        } else {
            Toast.makeText(getActivity(), "Error al cargar los detalles del día de entrenamiento", Toast.LENGTH_SHORT).show();
        }
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_dia_entreno, container, false);

        String nombreRutina = getArguments().getString(ARG_NOMBRE_RUTINA);
        String nombreDia = getArguments().getString(ARG_NOMBRE_DIA);
        recyclerViewEjerciciosDia = view.findViewById(R.id.recyclerViewEjerciciosDia);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEjerciciosDia.setLayoutManager(layoutManager);

        TextView tVNombreRutina = view.findViewById(R.id.tVNombreRutina);
        TextView tVNombreDia = view.findViewById(R.id.tVNombreDia);
        tVNombreRutina.setText(nombreRutina);
        tVNombreDia.setText(nombreDia);

        int idRutina = getArguments().getInt(ARG_ID_RUTINA);
        int idDiaEntreno = getArguments().getInt(ARG_ID_DIA_ENTRENO);

        List<Ejercicios> ejerciciosList = getEjerciciosPorDia(idRutina, idDiaEntreno);

        ejerciciosAdapter = new EjerciciosAdapter(getContext(), ejerciciosList, idRutina, idDiaEntreno);
        recyclerViewEjerciciosDia.setAdapter(ejerciciosAdapter);

        Button btnAgregarEjercicios = view.findViewById(R.id.btnAgregarEjercicios);
        btnAgregarEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment agregarEjerciciosFragment = AgregarEjerciciosFragment.newInstance(idRutina, idDiaEntreno);
                ((MainActivity) requireActivity()).replaceFragment(agregarEjerciciosFragment, true);
            }
        });

        return view;
    }
    private List<Ejercicios> getEjerciciosPorDia(int idRutina, int idDiaEntreno) {
        BaseDatos baseDatos = new BaseDatos(getContext());
        return baseDatos.getEjerciciosPorDia(idRutina, idDiaEntreno);
    }
}