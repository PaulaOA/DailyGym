package com.example.dailygym;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ConfirmDeleteDialogFragment extends DialogFragment {

    public interface ConfirmDeleteListener {
        void onConfirmDelete(int position);
    }

    private final ConfirmDeleteListener listener;
    private final int position;

    public ConfirmDeleteDialogFragment(ConfirmDeleteListener listener, int position) {
        this.listener = listener;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Desea eliminar el registro?")
                .setPositiveButton("Sí", (dialog, id) -> listener.onConfirmDelete(position))
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        return builder.create();
    }
}
