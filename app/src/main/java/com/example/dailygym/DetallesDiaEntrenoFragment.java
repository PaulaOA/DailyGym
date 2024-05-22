package com.example.dailygym;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesDiaEntrenoFragment extends Fragment {
    private static final String ARG_DIA_ENTRENO = "diaEntreno";
    private static final String ARG_NOMBRE_RUTINA = "nombreRutina";
    private static final String ARG_NOMBRE_DIA = "nombreDia";

    public DetallesDiaEntrenoFragment() {
        // Required empty public constructor
    }

    public static DetallesDiaEntrenoFragment newInstance(DiasEntreno diaEntreno, String nombreRutina, String nombreDia) {
        DetallesDiaEntrenoFragment fragment = new DetallesDiaEntrenoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DIA_ENTRENO, diaEntreno);
        args.putString(ARG_NOMBRE_RUTINA, nombreRutina);
        args.putString(ARG_NOMBRE_DIA, nombreDia);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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

        TextView tVNombreRutina = view.findViewById(R.id.tVNombreRutina);
        TextView tVNombreDia = view.findViewById(R.id.tVNombreDia);
        tVNombreRutina.setText(nombreRutina);
        tVNombreDia.setText(nombreDia);

        Button btnAgregarEjercicios = view.findViewById(R.id.btnAgregarEjercicios);
        btnAgregarEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment agregarEjerciciosFragment = new AgregarEjerciciosFragment();
                ((MainActivity) requireActivity()).replaceFragment(agregarEjerciciosFragment, true);
            }
        });

        return view;
    }
}