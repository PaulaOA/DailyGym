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
    private static final int version_bbdd = 3;
    private static final String TABLE_RUTINAS = "rutinas";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_DIAS_ENTRENO = "diasEntreno";

    private static final String CREATE_TABLE_RUTINAS = "CREATE TABLE " + TABLE_RUTINAS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_DESCRIPCION + " TEXT,"
            + COLUMN_DIAS_ENTRENO + " TEXT"
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUTINAS);
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

                Gson gson = new Gson();
                List<DiasEntreno> diasEntreno = gson.fromJson(diasEntrenoJson, new TypeToken<List<DiasEntreno>>(){}.getType());

                Rutinas rutina = new Rutinas(id, nombre, descripcion, diasEntreno);
                rutinasList.add(rutina);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return rutinasList;
    }
}
