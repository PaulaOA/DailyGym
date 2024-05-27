package com.example.dailygym;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Range;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {
    private static final String nombre_bbdd = "rutinas_database";
    private static final int version_bbdd = 7;
    private static final String TABLE_RUTINAS = "rutinas";
    private static final String TABLE_EJERCICIOS = "ejercicios";
    private static final String TABLE_REGISTROS = "registros";



    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_DIAS_ENTRENO = "diasEntreno";
    private static final String COLUMN_AUTOR = "autor";

    private static final String COLUMN_ID_EJERCICIO = "id_ejercicio";
    private static final String COLUMN_NOMBRE_EJERCICIO = "nombre_ejercicio";
    private static final String COLUMN_DESCRIPCION_EJERCICIO = "descripcion_ejercicio";
    private static final String COLUMN_MUSCULO_PRINCIPAL = "musculo_principal";
    private static final String COLUMN_ID_RUTINA = "id_rutina";
    private static final String COLUMN_ID_DIA_ENTRENO = "id_dia_entreno";

    private static final String COLUMN_ID_REGISTRO = "id_registro";
    private static final String COLUMN_PESO = "peso";
    private static final String COLUMN_REPETICIONES = "repeticiones";
    private static final String COLUMN_SERIES = "series";
    private static final String COLUMN_FECHA = "fecha";

    private static final String CREATE_TABLE_RUTINAS = "CREATE TABLE " + TABLE_RUTINAS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_DESCRIPCION + " TEXT,"
            + COLUMN_DIAS_ENTRENO + " TEXT,"
            + COLUMN_AUTOR + " TEXT"
            + ")";

    private static final String CREATE_TABLE_EJERCICIOS = "CREATE TABLE " + TABLE_EJERCICIOS + "("
            + COLUMN_ID_EJERCICIO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE_EJERCICIO + " TEXT,"
            + COLUMN_DESCRIPCION_EJERCICIO + " TEXT,"
            + COLUMN_MUSCULO_PRINCIPAL + " TEXT,"
            + COLUMN_ID_RUTINA + " INTEGER,"
            + COLUMN_ID_DIA_ENTRENO + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_REGISTROS = "CREATE TABLE " + TABLE_REGISTROS + "("
            + COLUMN_ID_REGISTRO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ID_EJERCICIO + " INTEGER,"
            + COLUMN_ID_RUTINA + " INTEGER,"
            + COLUMN_ID_DIA_ENTRENO + " INTEGER,"
            + COLUMN_PESO + " REAL,"
            + COLUMN_REPETICIONES + " INTEGER,"
            + COLUMN_SERIES + " INTEGER,"
            + COLUMN_FECHA + " DATE"
            + ")";

    /*public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre_bbdd, null, version_bbdd);
    }*/

    public BaseDatos(@Nullable Context context) {
        super(context, nombre_bbdd, null, version_bbdd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RUTINAS);
        db.execSQL(CREATE_TABLE_EJERCICIOS);
        db.execSQL(CREATE_TABLE_REGISTROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUTINAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EJERCICIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTROS);
        onCreate(db);
    }

    public long insert(String nombre, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_DESCRIPCION, descripcion);
        long id = db.insert(TABLE_RUTINAS, null, values);
        db.close();
        return id;
    }

    public void deleteRutina(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RUTINAS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public long insertRutina(Rutinas rutina) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, rutina.getNombreRutina());
        values.put(COLUMN_DESCRIPCION, rutina.getDescripcionRutina());
        values.put(COLUMN_AUTOR, rutina.getAutorRutina());

        Gson gson = new Gson();
        String diasEntrenoJson = gson.toJson(rutina.getDiasEntreno());
        values.put(COLUMN_DIAS_ENTRENO, diasEntrenoJson);

        long id = db.insert(TABLE_RUTINAS, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Rutinas> getAllRutinas() {
        List<Rutinas> rutinasList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RUTINAS, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE));
                String descripcion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION));
                String diasEntrenoJson = cursor.getString(cursor.getColumnIndex(COLUMN_DIAS_ENTRENO));
                String autor = cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR));

                Gson gson = new Gson();
                List<DiasEntreno> diasEntreno = gson.fromJson(diasEntrenoJson, new TypeToken<List<DiasEntreno>>(){}.getType());

                Rutinas rutina = new Rutinas(id, nombre, descripcion, autor, diasEntreno);
                rutinasList.add(rutina);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return rutinasList;
    }
    public long insertEjercicio(String nombre, String descripcion, String musculoPrincipal, int idRutina, int idDiaEntreno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_EJERCICIO, nombre);
        values.put(COLUMN_DESCRIPCION_EJERCICIO, descripcion);
        values.put(COLUMN_MUSCULO_PRINCIPAL, musculoPrincipal);
        values.put(COLUMN_ID_RUTINA, idRutina);
        values.put(COLUMN_ID_DIA_ENTRENO, idDiaEntreno);
        long id = db.insert(TABLE_EJERCICIOS, null, values);
        db.close();
        return id;
    }

    public void deleteEjercicio(int idEjercicio) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EJERCICIOS, COLUMN_ID_EJERCICIO + " = ?", new String[]{String.valueOf(idEjercicio)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Ejercicios> getAllEjercicios(int idRutina, int idDiaEntreno) {
        List<Ejercicios> ejerciciosList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID_EJERCICIO, COLUMN_NOMBRE_EJERCICIO, COLUMN_DESCRIPCION_EJERCICIO, COLUMN_MUSCULO_PRINCIPAL};
        String selection = COLUMN_ID_RUTINA + " = ? AND " + COLUMN_ID_DIA_ENTRENO + " = ?";
        String[] selectionArgs = {String.valueOf(idRutina), String.valueOf(idDiaEntreno)};
        Cursor cursor = db.query(TABLE_EJERCICIOS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idEjercicio = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_EJERCICIO));
                String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_EJERCICIO));
                String descripcion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_EJERCICIO));
                String musculoPrincipal = cursor.getString(cursor.getColumnIndex(COLUMN_MUSCULO_PRINCIPAL));

                Ejercicios ejercicio = new Ejercicios(idEjercicio, nombre, descripcion, musculoPrincipal);
                ejerciciosList.add(ejercicio);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return ejerciciosList;
    }

    @SuppressLint("Range")
    public List<Ejercicios> getEjerciciosPorDia(int idRutina, int idDiaEntreno) {
        List<Ejercicios> ejerciciosList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID_EJERCICIO, COLUMN_NOMBRE_EJERCICIO, COLUMN_DESCRIPCION_EJERCICIO, COLUMN_MUSCULO_PRINCIPAL};
        String selection = COLUMN_ID_RUTINA + " = ? AND " + COLUMN_ID_DIA_ENTRENO + " = ?";
        String[] selectionArgs = {String.valueOf(idRutina), String.valueOf(idDiaEntreno)};
        Cursor cursor = db.query(TABLE_EJERCICIOS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idEjercicio = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_EJERCICIO));
                String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_EJERCICIO));
                String descripcion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_EJERCICIO));
                String musculoPrincipal = cursor.getString(cursor.getColumnIndex(COLUMN_MUSCULO_PRINCIPAL));

                Ejercicios ejercicio = new Ejercicios(idEjercicio, nombre, descripcion, musculoPrincipal);
                ejerciciosList.add(ejercicio);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return ejerciciosList;
    }

    public long insertRegistro(int idEjercicio, int idRutina, int idDiaEntreno, double peso, int repeticiones, int series, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_EJERCICIO, idEjercicio);
        values.put(COLUMN_ID_RUTINA, idRutina);
        values.put(COLUMN_ID_DIA_ENTRENO, idDiaEntreno);
        values.put(COLUMN_PESO, peso);
        values.put(COLUMN_REPETICIONES, repeticiones);
        values.put(COLUMN_SERIES, series);
        values.put(COLUMN_FECHA, fecha);
        long id = db.insert(TABLE_REGISTROS, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Registro> getRegistrosPorEjercicio(int idEjercicio) {
        List<Registro> registrosList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID_REGISTRO, COLUMN_PESO, COLUMN_REPETICIONES, COLUMN_SERIES, COLUMN_FECHA};
        String selection = COLUMN_ID_EJERCICIO + " = ?";
        String[] selectionArgs = {String.valueOf(idEjercicio)};
        String orderBy = COLUMN_ID_REGISTRO + " DESC";
        String limit = "10";
        Cursor cursor = db.query(TABLE_REGISTROS, columns, selection, selectionArgs, null, null, orderBy, limit);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idRegistro = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_REGISTRO));
                double peso = cursor.getDouble(cursor.getColumnIndex(COLUMN_PESO));
                int repeticiones = cursor.getInt(cursor.getColumnIndex(COLUMN_REPETICIONES));
                int series = cursor.getInt(cursor.getColumnIndex(COLUMN_SERIES));
                String fecha = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA));

                Registro registro = new Registro(idRegistro, peso, repeticiones, series, fecha);
                registrosList.add(registro);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return registrosList;
    }

}
