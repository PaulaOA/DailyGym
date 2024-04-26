package com.example.dailygym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TusRutinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TusRutinasFragment extends Fragment {

    private RecyclerView recyclerViewTusRutinas;
    private RutinasAdapter rutinasAdapter;
    private BaseDatos baseDatos;

    public TusRutinasFragment() {
    }

    public static TusRutinasFragment newInstance(String param1, String param2) {
        TusRutinasFragment fragment = new TusRutinasFragment();
        Bundle args = new Bundle();

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
        View view = inflater.inflate(R.layout.fragment_tus_rutinas, container, false);

        baseDatos = new BaseDatos(getContext());
        recyclerViewTusRutinas = view.findViewById(R.id.recyclerViewTusRutinas);
        recyclerViewTusRutinas.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Rutinas> rutinasList = baseDatos.getAllRutinas();
        rutinasAdapter = new RutinasAdapter(rutinasList, getContext());
        recyclerViewTusRutinas.setAdapter(rutinasAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Tus Rutinas");
    }
}