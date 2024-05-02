package com.example.dailygym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

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
                ((MainActivity) requireActivity()).replaceFragment(new TusRutinasFragment(), true);
            }
        });

        //nuevo
        actualizarRutinaPrincipalUI(rootView);


        return rootView;
        }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");

        //nuevo
        View view = getView();
        if (view != null) {
            actualizarRutinaPrincipalUI(view);
        }
    }

    //nuevo
    private void actualizarRutinaPrincipalUI(View view) {
        Rutinas rutinaPrincipal = getRutinaPrincipal();
        CardView cardViewRutinaPrincipal = view.findViewById(R.id.cardViewRutinaPrincipal);
        TextView textViewNombreRutinaPrincipal = view.findViewById(R.id.textViewNombreRutinaPrincipal);
        TextView textViewDescripcionRutinaPrincipal = view.findViewById(R.id.textViewDescripcionRutinaPrincipal);
        TextView textViewPorDefecto = view.findViewById(R.id.textViewPorDefecto);
        Button btnEntrenar = view.findViewById(R.id.btnEntrenar);
        String autorRutina =  rutinaPrincipal.getAutorRutina();
        ImageView imagenFondo = view.findViewById(R.id.imagenFondo);

        if (rutinaPrincipal != null) {
            textViewNombreRutinaPrincipal.setText(rutinaPrincipal.getNombreRutina());
            textViewDescripcionRutinaPrincipal.setText(rutinaPrincipal.getDescripcionRutina());

            textViewNombreRutinaPrincipal.setVisibility(View.VISIBLE);
            textViewDescripcionRutinaPrincipal.setVisibility(View.VISIBLE);
            textViewPorDefecto.setVisibility(View.GONE);
            btnEntrenar.setVisibility(View.VISIBLE);

            if (autorRutina != null && autorRutina.equals("Hombre")) {
                imagenFondo.setImageResource(R.drawable.hombre);
            } else if (autorRutina != null && autorRutina.equals("Mujer")){
                imagenFondo.setImageResource(R.drawable.mujer);
            }

        } else {
            textViewNombreRutinaPrincipal.setVisibility(View.GONE);
            textViewDescripcionRutinaPrincipal.setVisibility(View.GONE);
            textViewPorDefecto.setVisibility(View.VISIBLE);
            btnEntrenar.setVisibility(View.GONE);
            imagenFondo.setBackgroundResource(R.color.white);
        }
        cardViewRutinaPrincipal.setVisibility(View.VISIBLE);
    }

    private Rutinas getRutinaPrincipal() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int rutinaPrincipalId = sharedPreferences.getInt("rutina_principal_id", -1);
        if (rutinaPrincipalId != -1) {
            BaseDatos db = new BaseDatos(getContext());
            List<Rutinas> rutinasList = db.getAllRutinas();
            for (Rutinas rutina : rutinasList) {
                if (rutina.getIdRutina() == rutinaPrincipalId) {
                    return rutina;
                }
            }
        }
        return null;
    }
}