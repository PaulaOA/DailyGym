package com.example.dailygym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class rutinasFragment extends Fragment {
    public rutinasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rutinas, container, false);

        BaseDatos baseDatos = new BaseDatos(getContext());
        List<Rutinas> rutinasList = baseDatos.getAllRutinas();

        RecyclerView recyclerViewRutinas = rootView.findViewById(R.id.recyclerViewListaRutinas);
        recyclerViewRutinas.setLayoutManager(new LinearLayoutManager(getContext()));
        Adaptador adaptadorListaRutinas = new Adaptador(getContext(), rutinasList);
        recyclerViewRutinas.setAdapter(adaptadorListaRutinas);

        Button btnCrearRutina = rootView.findViewById(R.id.btnCrearRutina);
        btnCrearRutina.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CrearRutina.class);
            startActivity(intent);
        });

        TextView textViewTusRutinas = rootView.findViewById(R.id.textViewTusRutinas);
        textViewTusRutinas.setOnClickListener(v -> ((MainActivity) requireActivity()).replaceFragment(new TusRutinasFragment(), true));

        actualizarRutinaPrincipalUI(rootView);
        return rootView;
        }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Rutinas");

        View view = getView();
        if (view != null) {
            actualizarRutinaPrincipalUI(view);
        }
    }

    private void actualizarRutinaPrincipalUI(View view) {
        Rutinas rutinaPrincipal = getRutinaPrincipal();
        CardView cardViewRutinaPrincipal = view.findViewById(R.id.cardViewRutinaPrincipal);
        TextView textViewNombreRutinaPrincipal = view.findViewById(R.id.textViewNombreRutinaPrincipal);
        TextView textViewDescripcionRutinaPrincipal = view.findViewById(R.id.textViewDescripcionRutinaPrincipal);
        TextView textViewPorDefecto = view.findViewById(R.id.textViewPorDefecto);
        ImageView imagenFondo = view.findViewById(R.id.imagenFondo);

        if (rutinaPrincipal != null) {
            textViewNombreRutinaPrincipal.setText(rutinaPrincipal.getNombreRutina());
            textViewDescripcionRutinaPrincipal.setText(rutinaPrincipal.getDescripcionRutina());

            textViewNombreRutinaPrincipal.setVisibility(View.VISIBLE);
            textViewDescripcionRutinaPrincipal.setVisibility(View.VISIBLE);
            textViewPorDefecto.setVisibility(View.GONE);
            String autorRutina =  rutinaPrincipal.getAutorRutina();

            if (autorRutina != null && autorRutina.equals("Hombre")) {
                imagenFondo.setImageResource(R.drawable.hombre);
            } else if (autorRutina != null && autorRutina.equals("Mujer")){
                imagenFondo.setImageResource(R.drawable.mujer);
            }

        } else {
            textViewNombreRutinaPrincipal.setVisibility(View.GONE);
            textViewDescripcionRutinaPrincipal.setVisibility(View.GONE);
            textViewPorDefecto.setVisibility(View.VISIBLE);
            imagenFondo.setImageResource(R.color.white);
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