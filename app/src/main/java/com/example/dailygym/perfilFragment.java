package com.example.dailygym;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class perfilFragment extends Fragment {
    private EditText editTextNombre, editTextEdad, editTextPeso, editTextAltura;
    private Spinner spinnerSexo, spinnerObjetivo;
    private Button btnGuardarPerfil;

    public perfilFragment() {
    }

    public static perfilFragment newInstance(String param1, String param2) {
        perfilFragment fragment = new perfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        editTextNombre = view.findViewById(R.id.editTextNombrePerfil);
        editTextEdad = view.findViewById(R.id.editTextEdadPerfil);
        editTextPeso = view.findViewById(R.id.editTextPeso);
        editTextAltura = view.findViewById(R.id.editTextAltura);
        btnGuardarPerfil = view.findViewById(R.id.btnGuardarPerfil);

        spinnerSexo = view.findViewById(R.id.spinnerSexoPerfil);
        spinnerObjetivo = view.findViewById(R.id.spinnerObjetivoPerfil);

        btnGuardarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPerfil();
            }
        });

        List<String> opcionesSexo = new ArrayList<>();
        opcionesSexo.add("Mujer");
        opcionesSexo.add("Hombre");
        opcionesSexo.add("Otro");
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesSexo);
        spinnerSexo.setAdapter(adapterSexo);

        List<String> opcionesObjetivo = new ArrayList<>();
        opcionesObjetivo.add("Ganar masa muscular");
        opcionesObjetivo.add("Pérdida de grasa");
        opcionesObjetivo.add("Reconstrucción corporal");
        opcionesObjetivo.add("Aumentar fuerza");
        opcionesObjetivo.add("Mejorar resistencia");
        ArrayAdapter<String> adapterObjetivo = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesObjetivo);
        spinnerObjetivo.setAdapter(adapterObjetivo);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setToolbarText("Perfil");

        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        UserProfile userProfile = preferenceManager.getUserProfile();
        if (userProfile != null) {
            editTextNombre.setText(userProfile.getNombre());
            editTextEdad.setText(String.valueOf(userProfile.getEdad()));
            editTextPeso.setText(String.valueOf(userProfile.getPeso()));
            editTextAltura.setText(String.valueOf(userProfile.getAltura()));

            String sexo = userProfile.getSexo();
            if (sexo != null) {
                ArrayAdapter<CharSequence> adapterSexo = (ArrayAdapter<CharSequence>) spinnerSexo.getAdapter();
                int positionSexo = adapterSexo.getPosition(sexo);
                spinnerSexo.setSelection(positionSexo);
            }

            String objetivo = userProfile.getObjetivos();
            if (objetivo != null) {
                ArrayAdapter<CharSequence> adapterObjetivo = (ArrayAdapter<CharSequence>) spinnerObjetivo.getAdapter();
                int positionObjetivo = adapterObjetivo.getPosition(objetivo);
                spinnerObjetivo.setSelection(positionObjetivo);
            }
        }
    }

    private void guardarPerfil() {
        String nombre = editTextNombre.getText().toString();
        int edad = Integer.parseInt(editTextEdad.getText().toString());
        double peso = Double.parseDouble(editTextPeso.getText().toString());
        double altura = Double.parseDouble(editTextAltura.getText().toString());
        String sexo = spinnerSexo.getSelectedItem().toString();
        String objetivo = spinnerObjetivo.getSelectedItem().toString();

        UserProfile userProfile = new UserProfile();
        userProfile.setNombre(nombre);
        userProfile.setEdad(edad);
        userProfile.setPeso(peso);
        userProfile.setAltura(altura);
        userProfile.setSexo(sexo);
        userProfile.setObjetivos(objetivo);

        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        preferenceManager.saveUserProfile(userProfile);

        Toast.makeText(requireContext(), "Datos de Perfil guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}