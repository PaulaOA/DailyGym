package com.example.dailygym;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rutinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rutinasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View btnCrearRutina;

    public rutinasFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rutinasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static rutinasFragment newInstance(String param1, String param2) {
        rutinasFragment fragment = new rutinasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

        return rootView;
        }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");
    }
}