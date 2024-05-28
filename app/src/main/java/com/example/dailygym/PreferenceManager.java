package com.example.dailygym;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_FILE_NAME = "UserProfilePref";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_EDAD = "edad";
    private static final String KEY_SEXO = "sexo";
    private static final String KEY_PESO = "peso";
    private static final String KEY_ALTURA = "altura";
    private static final String KEY_OBJETIVOS = "objetivos";

    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserProfile(UserProfile userProfile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NOMBRE, userProfile.getNombre());
        editor.putInt(KEY_EDAD, userProfile.getEdad());
        editor.putString(KEY_SEXO, userProfile.getSexo());
        editor.putFloat(KEY_PESO, (float) userProfile.getPeso());
        editor.putFloat(KEY_ALTURA, (float) userProfile.getAltura());
        editor.putString(KEY_OBJETIVOS, userProfile.getObjetivos());
        editor.apply();
    }

    public UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setNombre(sharedPreferences.getString(KEY_NOMBRE, ""));
        userProfile.setEdad(sharedPreferences.getInt(KEY_EDAD, 0));
        userProfile.setSexo(sharedPreferences.getString(KEY_SEXO, ""));
        userProfile.setPeso(sharedPreferences.getFloat(KEY_PESO, 0));
        userProfile.setAltura(sharedPreferences.getFloat(KEY_ALTURA, 0));
        userProfile.setObjetivos(sharedPreferences.getString(KEY_OBJETIVOS, ""));
        return userProfile;
    }
}
