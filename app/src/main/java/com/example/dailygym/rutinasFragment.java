package com.example.dailygym;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rutinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rutinasFragment extends Fragment {
    private View btnCrearRutina;

    public rutinasFragment() {
    }

    public static rutinasFragment newInstance(String param1, String param2) {
        rutinasFragment fragment = new rutinasFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_rutinas, container, false);

        Rutinas[] rutinas = new Rutinas[]{
                new Rutinas(1, "Hipertrofia Glúteos", "Aumenta tus glúteos en 12 semanas", "Mujer"),
                new Rutinas(2, "Reto Espalda", "Fortalece tu espalda", "Hombre"),
                new Rutinas(3, "Método FullBody", "Entrena todo tu cuerpo", "Mujer"),
                new Rutinas(4, "Training Plan", "Ponte en forma", "Hombre")
        };

        ListView listaRutinas = (ListView) rootView.findViewById(R.id.listaRutinas);
        Adaptador adaptadorListaRutinas = new Adaptador(getContext() , rutinas);
        listaRutinas.setAdapter(adaptadorListaRutinas);

        Button btnCrearRutina = (Button) rootView.findViewById(R.id.btnCrearRutina);

        btnCrearRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CrearRutina.class);
                startActivity(intent);
            }
        });

        TextView textViewTusRutinas = rootView.findViewById(R.id.textViewTusRutinas);
        textViewTusRutinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).replaceFragment(new TusRutinasFragment());
            }
        });

        return rootView;
        }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");
    }
}