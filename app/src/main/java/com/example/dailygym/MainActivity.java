package com.example.dailygym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dailygym.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.menuNavegacion.setSelectedItemId(R.id.btnRutinas);
        replaceFragment(new rutinasFragment(), false);

        binding.menuNavegacion.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.btnPerfil){
                replaceFragment(new perfilFragment(), false);
            } else if (itemId == R.id.btnRegistros) {
                replaceFragment(new registrosFragment(), false);
            } else if (itemId == R.id.btnRutinas) {
                    replaceFragment(new rutinasFragment(), false);
            }
            return true;
        });

    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorMain, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void setToolbarText(String text) {
        TextView textView = findViewById(R.id.textTituloBar);
        if (textView != null) {
            textView.setText(text);
        }
    }
}