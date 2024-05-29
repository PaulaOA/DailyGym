package com.example.dailygym;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CrearRutina extends AppCompatActivity {
    private EditText editTextNombreRutina;
    private EditText editTextDescripcionRutina;
    private RecyclerView recyclerViewDias;
    private RadioGroup rgAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_rutina);

        editTextNombreRutina = findViewById(R.id.editTextNombreRutina);
        editTextDescripcionRutina = findViewById(R.id.editTextDescripcionRutina);
        rgAutor = findViewById(R.id.rgAutor);
        recyclerViewDias = findViewById(R.id.recyclerViewDias);
        Button btnGuardarRutina = findViewById(R.id.btnGuardarRutina);

        recyclerViewDias.setLayoutManager(new GridLayoutManager(this, 2));
        String[] diasSemana = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};
        MyAdapter adapter = new MyAdapter(diasSemana);
        recyclerViewDias.setAdapter(adapter);

        btnGuardarRutina.setOnClickListener(v -> {
            String nombreRutina = editTextNombreRutina.getText().toString().trim();
            String descripcionRutina = editTextDescripcionRutina.getText().toString().trim();

            int selectedId = rgAutor.getCheckedRadioButtonId();
            String autorRutina = "";
            if (selectedId == R.id.rbHombre){
                autorRutina = "Hombre";
            } else if (selectedId == R.id.rbMujer) {
                autorRutina = "Mujer";
            }

            MyAdapter myadapter = (MyAdapter) recyclerViewDias.getAdapter();
            boolean seleccionDias = false;

            if (myadapter != null) {
                for (int i = 0; i < myadapter.getItemCount(); i++) {
                    if (myadapter.isSelected(i)) {
                        seleccionDias = true;
                        break;
                    }
                }
            }

            if (nombreRutina.isEmpty() || descripcionRutina.isEmpty() || !seleccionDias || autorRutina.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Completa todos los datos para crear tu rutina", Toast.LENGTH_LONG).show();
                return;
            }

            if (guardarRutina(nombreRutina, descripcionRutina, autorRutina)) {
                mostrarDialogoRutinaCreada();
            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> mostrarDialogoConfirmacion());
    }

    private boolean guardarRutina(String nombre, String descripcion, String autorRutina) {
        MyAdapter adapter = (MyAdapter) recyclerViewDias.getAdapter();

        List<DiasEntreno> diasEntreno = new ArrayList<>();

        if (adapter != null) {
            for (int i = 0; i < adapter.getItemCount(); i++) {
                if (adapter.isSelected(i)) {
                    DiasEntreno diaEntreno = new DiasEntreno(i, adapter.getItem(i));
                    diasEntreno.add(diaEntreno);
                }
            }
        }

        Rutinas rutina = new Rutinas(nombre, descripcion, autorRutina, diasEntreno);

        long id = -1;
        try (BaseDatos db = new BaseDatos(this)) {
            id = db.insertRutina(rutina);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (id != -1) {
            rutina.setIdRutina((int) id);
            return true;
        } else {
            return false;
        }
    }
    private void mostrarDialogoConfirmacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Quiere cerrar esta ventana?");
        builder.setMessage("Los cambios no se guardarán");
        builder.setPositiveButton("Sí", (dialog, which) -> finish());
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void mostrarDialogoRutinaCreada() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Rutina creada!");
        builder.setMessage("Ya puedes escoger tu nueva rutina");
        builder.setPositiveButton("Aceptar", (dialog, which) -> finish());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}