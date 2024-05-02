package com.example.dailygym;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetallesRutinaFragment extends Fragment {
   private static final String ARG_RUTINA = "rutina";
   private Rutinas rutina;


    public DetallesRutinaFragment() {
    }
    public static DetallesRutinaFragment newInstance(Rutinas rutina) {
        DetallesRutinaFragment fragment = new DetallesRutinaFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RUTINA, rutina);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rutina = (Rutinas) getArguments().getSerializable(ARG_RUTINA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_rutina, container, false);
        TextView textViewDetalleNombre = view.findViewById(R.id.textViewDetalleNombre);
        TextView textViewDetalleDescripcion = view.findViewById(R.id.textViewDetalleDescripcion);
        Button btnSeguirRutina = view.findViewById(R.id.btnSeguirRutina);
        LinearLayout layoutDetalles = view.findViewById(R.id.layoutDetalles);

        List<DiasEntreno> diasEntreno = rutina.getDiasEntreno();

        textViewDetalleNombre.setText(rutina.getNombreRutina());
        textViewDetalleDescripcion.setText(rutina.getDescripcionRutina());

        LinearLayout layoutDiasEntreno = view.findViewById(R.id.layoutDiasEntreno);

        if (diasEntreno != null) {
            for (DiasEntreno diaEntreno : diasEntreno) {
                View cardViewDia = inflater.inflate(R.layout.cardview_dia_entreno, layoutDiasEntreno, false);

                TextView textViewNombreDia = cardViewDia.findViewById(R.id.textViewNombreDia);
                textViewNombreDia.setText(diaEntreno.getNombreDia());

                layoutDiasEntreno.addView(cardViewDia);
            }
        }

        String autorRutina = rutina.getAutorRutina();
        if (autorRutina != null && autorRutina.equals("Hombre")) {
            layoutDetalles.setBackgroundResource(R.drawable.hombre);
        } else if (autorRutina != null && autorRutina.equals("Mujer")){
            layoutDetalles.setBackgroundResource(R.drawable.mujer);
        }

        //nuevo
        btnSeguirRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRutinaPrincipal(rutina);
                Toast.makeText(getActivity(), "Rutina principal establecida", Toast.LENGTH_SHORT).show();
            }
        });
        //nuevo
        // Inflate the layout for this fragment
        return view;
    }

    private void setRutinaPrincipal(Rutinas rutina) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("rutina_principal_id", rutina.getIdRutina());
        editor.apply();
    }
}