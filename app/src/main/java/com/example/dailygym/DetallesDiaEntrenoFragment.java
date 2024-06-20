package com.example.dailygym;

import android.annotation.SuppressLint;
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

    private EjerciciosAdapter ejerciciosAdapter;

    public DetallesDiaEntrenoFragment() {
    }

    public static DetallesDiaEntrenoFragment newInstance(DiasEntreno diaEntreno, int idRutina, int idDiaEntreno, String nombreDia) {
        DetallesDiaEntrenoFragment fragment = new DetallesDiaEntrenoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DIA_ENTRENO, diaEntreno);
        args.putInt(ARG_ID_RUTINA, idRutina);
        args.putInt(ARG_ID_DIA_ENTRENO, idDiaEntreno);
        args.putString(ARG_NOMBRE_DIA, nombreDia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_dia_entreno, container, false);

        assert getArguments() != null;
        String nombreDia = getArguments().getString(ARG_NOMBRE_DIA);
        RecyclerView recyclerViewEjerciciosDia = view.findViewById(R.id.recyclerViewEjerciciosDia);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEjerciciosDia.setLayoutManager(layoutManager);

        TextView tVNombreDia = view.findViewById(R.id.tVNombreDia);
        tVNombreDia.setText(nombreDia);

        int idRutina = getArguments().getInt(ARG_ID_RUTINA);
        int idDiaEntreno = getArguments().getInt(ARG_ID_DIA_ENTRENO);

        List<Ejercicios> ejerciciosList = getEjerciciosPorDia(idRutina, idDiaEntreno);

        ejerciciosAdapter = new EjerciciosAdapter(getContext(), ejerciciosList, idRutina, idDiaEntreno);
        recyclerViewEjerciciosDia.setAdapter(ejerciciosAdapter);

        Button btnAgregarEjercicios = view.findViewById(R.id.btnAgregarEjercicios);
        Button btnEliminarEjercicios = view.findViewById(R.id.btnEliminarEjercicios);
        btnAgregarEjercicios.setOnClickListener(v -> {
            Fragment agregarEjerciciosFragment = AgregarEjerciciosFragment.newInstance(idRutina, idDiaEntreno);
            ((MainActivity) requireActivity()).replaceFragment(agregarEjerciciosFragment, true);
        });

        btnEliminarEjercicios.setOnClickListener(v -> mostrarDialogoEliminarEjercicios(idRutina, idDiaEntreno));

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

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView)
                .setTitle("Eliminar ejercicios")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    List<Integer> ejerciciosAEliminar = new ArrayList<>();
                    for (int i = 0; i <checkboxContainer.getChildCount(); i++) {
                        CheckBox checkBox = (CheckBox) checkboxContainer.getChildAt(i);
                        if (checkBox.isChecked()) {
                            ejerciciosAEliminar.add((Integer) checkBox.getTag());
                        }
                    }
                    eliminarEjercicios(ejerciciosAEliminar);
                })
                .create()
                .show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void eliminarEjercicios(List<Integer> ejerciciosAEliminar) {
        BaseDatos baseDatos = new BaseDatos(getContext());
        for(int idEjercicio : ejerciciosAEliminar) {
            baseDatos.deleteEjercicio(idEjercicio);
        }
        Toast.makeText(getContext(), "Ejercicios eliminados correctamente", Toast.LENGTH_SHORT).show();
        assert getArguments() != null;
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