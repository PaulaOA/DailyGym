package com.example.dailygym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarEjerciciosFragment extends Fragment {
    private static final String ARG_ID_RUTINA = "idRutina";
    private static final String ARG_ID_DIA_ENTRENO = "idDiaEntreno";

    public AgregarEjerciciosFragment() {
    }

    public static AgregarEjerciciosFragment newInstance(int idRutina, int idDiaEntreno) {
        AgregarEjerciciosFragment fragment = new AgregarEjerciciosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_RUTINA, idRutina);
        args.putInt(ARG_ID_DIA_ENTRENO, idDiaEntreno);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int idRutina = getArguments().getInt(ARG_ID_RUTINA);
            int idDiaEntreno = getArguments().getInt(ARG_ID_DIA_ENTRENO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_ejercicios, container, false);

        EditText editTextNombreEjercicio = view.findViewById(R.id.editTextNombreEjercicio);
        EditText editTextDescripcionEjercicio = view.findViewById(R.id.editTextDescripcionEjercicio);
        EditText editTextMusculoPrincipal = view.findViewById(R.id.editTextMusculoPrincipal);
        Button btnGuardarEjercicio = view.findViewById(R.id.btnGuardarEjercicio);

        int idRutina = getArguments().getInt(ARG_ID_RUTINA);
        int idDiaEntreno = getArguments().getInt(ARG_ID_DIA_ENTRENO);

        btnGuardarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEjercicio = editTextNombreEjercicio.getText().toString();
                String descripcionEjercicio = editTextDescripcionEjercicio.getText().toString();
                String musculoPrincipal = editTextMusculoPrincipal.getText().toString();

                if (!nombreEjercicio.isEmpty() && !descripcionEjercicio.isEmpty() && !musculoPrincipal.isEmpty()) {
                    long idEjercicio = insertarEjercicioEnBaseDeDatos (nombreEjercicio, descripcionEjercicio, musculoPrincipal, idRutina, idDiaEntreno);
                        if (idEjercicio != -1) {
                            Toast.makeText(getActivity(), "Ejercicio guardado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Error al guardar el ejercicio", Toast.LENGTH_SHORT).show();
                        }

                } else {
                    Toast.makeText(getActivity(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private long insertarEjercicioEnBaseDeDatos(String nombre, String descripcion, String musculoPrincipal, int idRutina, int idDiaEntreno) {
        BaseDatos baseDatos = new BaseDatos(getActivity());
        return baseDatos.insertEjercicio(nombre, descripcion, musculoPrincipal, idRutina, idDiaEntreno);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");
    }
}