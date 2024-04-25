package com.example.dailygym;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CrearRutina extends AppCompatActivity {

    private List<DiasEntreno> diasSeleccionados = new ArrayList<>();
    private EditText editTextNombreRutina;
    private EditText editTextDescripcionRutina;
    private RecyclerView recyclerViewDias;
    private Button btnGuardarRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutina);

        editTextNombreRutina = findViewById(R.id.editTextNombreRutina);
        editTextDescripcionRutina = findViewById(R.id.editTextDescripcionRutina);
        recyclerViewDias = findViewById(R.id.recyclerViewDias);
        btnGuardarRutina = findViewById(R.id.btnGuardarRutina);

        recyclerViewDias.setLayoutManager(new GridLayoutManager(this, 2));
        String[] diasSemana = {"Lunes","Viernes","Martes","Sábado","Miércoles","Domingo","Jueves"};
        MyAdapter adapter = new MyAdapter(diasSemana);
        recyclerViewDias.setAdapter(adapter);

        btnGuardarRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreRutina = editTextNombreRutina.getText().toString().trim();
                String descripcionRutina = editTextDescripcionRutina.getText().toString().trim();
                MyAdapter adapter = (MyAdapter) recyclerViewDias.getAdapter();
                boolean seleccionDias = false;
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    if (adapter.isSelected(i)) {
                        seleccionDias = true;
                        break;
                    }
                }
                if (nombreRutina.isEmpty() || descripcionRutina.isEmpty() || !seleccionDias) {
                    Toast.makeText(getApplicationContext(), "Completa todos los datos para crear tu rutina", Toast.LENGTH_LONG).show();
                    return;
                }

                boolean rutinaGuardada = guardarRutina();
                if (rutinaGuardada) {
                    mostrarDialogoRutinaCreada();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoConfirmacion();
            }
        });
    }

    private boolean guardarRutina() {
        String nombre = editTextNombreRutina.getText().toString();
        String descripcion = editTextDescripcionRutina.getText().toString();

        MyAdapter adapter = (MyAdapter) recyclerViewDias.getAdapter();

        List<DiasEntreno> diasEntreno = new ArrayList<>();

        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (adapter.isSelected(i)) {
                DiasEntreno diaEntreno = new DiasEntreno(i, adapter.getItem(i), "Descripción del día");
                diasEntreno.add(diaEntreno);
                }
            }
        Rutinas rutina = new Rutinas(nombre, descripcion, diasEntreno);
        return true;
    }
    private void mostrarDialogoConfirmacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Quiere cerrar esta ventana?");
        builder.setMessage("Los cambios no se guardarán");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void mostrarDialogoRutinaCreada() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Rutina creada!");
        builder.setMessage("Ya puedes escoger tu nueva rutina");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}