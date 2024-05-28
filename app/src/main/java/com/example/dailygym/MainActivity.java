package com.example.dailygym;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dailygym.databinding.ActivityMainBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;
    private Fragment rutinasCurrentFragment;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rutinasCurrentFragment = new rutinasFragment();
        replaceFragment(rutinasCurrentFragment, false);

        binding.menuNavegacion.setSelectedItemId(R.id.btnRutinas);

        binding.menuNavegacion.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.btnPerfil){
                replaceFragment(new perfilFragment(), false);
            } else if (itemId == R.id.btnRegistros) {
                replaceFragment(new registrosFragment(), false);
            } else if (itemId == R.id.btnRutinas) {
                if (currentFragment != rutinasCurrentFragment) {
                    replaceFragment(rutinasCurrentFragment, false);
                }
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
        currentFragment = fragment;
    }

    public void setToolbarText(String text) {
        TextView textView = findViewById(R.id.textTituloBar);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void replaceRutinasFragment(Fragment fragment, boolean addToBackStack) {
        rutinasCurrentFragment = fragment;
        replaceFragment(fragment, addToBackStack);
    }
}