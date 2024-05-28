package com.example.dailygym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class registrosFragment extends Fragment {

    public registrosFragment() {
    }

    public static registrosFragment newInstance(String param1, String param2) {
        registrosFragment fragment = new registrosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registros, container, false);
        BaseDatos baseDatos = new BaseDatos(requireContext());

        List<Ejercicios> ejerciciosList = baseDatos.getAllEjercicios();

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewRegistroEjercicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        AllEjerciciosAdapter allEjerciciosAdapter = new AllEjerciciosAdapter(getContext(), ejerciciosList);
        recyclerView.setAdapter(allEjerciciosAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Registros");
    }
}