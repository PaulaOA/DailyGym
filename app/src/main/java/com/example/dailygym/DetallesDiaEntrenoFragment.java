package com.example.dailygym;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
        Button btnEliminarEjercicios = view.findViewById(R.id.btnEliminarEjercicios);
        btnAgregarEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment agregarEjerciciosFragment = AgregarEjerciciosFragment.newInstance(idRutina, idDiaEntreno);
                ((MainActivity) requireActivity()).replaceRutinasFragment(agregarEjerciciosFragment, true);
            }
        });

        btnEliminarEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoEliminarEjercicios(idRutina, idDiaEntreno);
            }
        });

        return view;
    }
    private List<Ejercicios> getEjerciciosPorDia(int idRutina, int idDiaEntreno) {
        BaseDatos baseDatos = new BaseDatos(getContext());
        return baseDatos.getEjerciciosPorDia(idRutina, idDiaEntreno);
    }

    private void mostrarDialogoEliminarEjercicios(int idRutina, int idDiaEntreno) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_eliminar_ejercicios, null);

        LinearLayout checkboxContainer = dialogView.findViewById(R.id.checkboxContainer);

        List<Ejercicios> ejerciciosList = getEjerciciosPorDia(idRutina, idDiaEntreno);

        for (Ejercicios ejercicio : ejerciciosList) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(ejercicio.getNombreEjercicio());
            checkBox.setTextSize(18);
            checkBox.setTag(ejercicio.getIdEjercicio());
            checkboxContainer.addView(checkBox);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView)
                .setTitle("Eliminar ejercicios")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Integer> ejerciciosAEliminar = new ArrayList<>();
                        for (int i = 0; i <checkboxContainer.getChildCount(); i++) {
                            CheckBox checkBox = (CheckBox) checkboxContainer.getChildAt(i);
                            if (checkBox.isChecked()) {
                                ejerciciosAEliminar.add((Integer) checkBox.getTag());
                            }
                        }
                        eliminarEjercicios(ejerciciosAEliminar);
                    }
                })
                .create()
                .show();
    }

    private void eliminarEjercicios(List<Integer> ejerciciosAEliminar) {
        BaseDatos baseDatos = new BaseDatos(getContext());
        for(int idEjercicio : ejerciciosAEliminar) {
            baseDatos.deleteEjercicio(idEjercicio);
        }
        Toast.makeText(getContext(), "Ejercicios eliminados correctamente", Toast.LENGTH_SHORT).show();
        ejerciciosAdapter.setEjerciciosList(getEjerciciosPorDia(
              getArguments().getInt(ARG_ID_RUTINA),
              getArguments().getInt(ARG_ID_DIA_ENTRENO)
        ));
        ejerciciosAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");
    }
}