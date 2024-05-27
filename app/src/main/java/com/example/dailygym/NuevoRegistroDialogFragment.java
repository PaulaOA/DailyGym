package com.example.dailygym;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NuevoRegistroDialogFragment extends DialogFragment {

    private int idEjercicio;
    private int idRutina;
    private int idDiaEntreno;
    private RegistroGuardadoListener registroGuardadoListener;


    public NuevoRegistroDialogFragment() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_nuevo_registro, container, false);

        EditText editTextRepeticiones = view.findViewById(R.id.editTextRepeticiones);
        EditText editTextPeso = view.findViewById(R.id.editTextPeso);
        EditText editTextSeries = view.findViewById(R.id.editTextSeries);
        Button btnGuardarRegistro = view.findViewById(R.id.btnGuardarRegistro);

        if (getArguments() != null) {
            idEjercicio = getArguments().getInt("idEjercicio");
            idRutina = getArguments().getInt("idRutina");
            idDiaEntreno = getArguments().getInt("idDiaEntreno");
        }

        btnGuardarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String repeticiones = editTextRepeticiones.getText().toString();
                String peso = editTextPeso.getText().toString();
                String series = editTextSeries.getText().toString();

                if (!repeticiones.isEmpty() && !peso.isEmpty() && !series.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String fechaActual = sdf.format(new Date());

                    long idRegistro = new BaseDatos(getActivity()).insertRegistro(idEjercicio, idRutina, idDiaEntreno, Double.parseDouble(peso), Integer.parseInt(repeticiones), Integer.parseInt(series), fechaActual);
                    if (idRegistro != -1) {
                        Toast.makeText(getActivity(), "Registro guardado: Repeticiones = " + repeticiones + ", Peso = " + peso + ", Series = " + series, Toast.LENGTH_SHORT).show();
                        dismiss();
                        Bundle result = new Bundle();
                        getParentFragmentManager().setFragmentResult("registroGuardado", result);
                    } else {
                        Toast.makeText(getActivity(), "Error al guardar el registro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface RegistroGuardadoListener {
        void onRegistroGuardado();
    }
}
