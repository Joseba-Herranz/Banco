package com.example.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "banco_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Banco";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_DESTINY = "destiny";
    private static final String COLUMN_CUANTITY = "cuantity";
    private static final String COLUMN_DATE = "date";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_USER_ID + " TEXT, " +
                    COLUMN_DESTINY + " INTEGER, " +
                    COLUMN_CUANTITY + " INTEGER, " +
                    COLUMN_DATE + " TEXT, " +
                    "PRIMARY KEY (" + COLUMN_USER_ID + ", " + COLUMN_DESTINY + "))"; // Clave primaria compuesta por el ID de usuario y el nombre de la serie

    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implementar lógica de actualización si es necesario
    }

    public void addTransferencia(String userId, Transfer transferencia) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_DESTINY, transferencia.getDestiny());
        values.put(COLUMN_CUANTITY, transferencia.getCuantity());
        values.put(COLUMN_DATE, transferencia.getDate());


        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if (result == -1) {
            Log.d("DataManager", "Error al guardar la serie.");
        } else {
            Log.d("DataManager", "Serie guardada correctamente.");
        }
    }

    public List<Transfer> getUser(String userId) {
        List<Transfer> seriesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = { userId };
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_USER_ID + " = ?", selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            int destiny = cursor.getInt(cursor.getColumnIndex(COLUMN_DESTINY));
            int season = cursor.getInt(cursor.getColumnIndex(COLUMN_CUANTITY));
            String chapter = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));

            seriesList.add(new Transfer(destiny, season, chapter));
        }
        cursor.close();
        db.close();
        return seriesList;
    }

    public List<Transfer> getTransfer(String userId) {
        List<Transfer> seriesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = { userId };
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_USER_ID + " = ?", selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            int destiny = cursor.getInt(cursor.getColumnIndex(COLUMN_DESTINY));
            int season = cursor.getInt(cursor.getColumnIndex(COLUMN_CUANTITY));
            String chapter = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));


            seriesList.add(new Transfer(destiny, season, chapter));
        }
        cursor.close();
        db.close();
        return seriesList;
    }

    public void inicializarDatosPorDefecto() {
        addTransferencia("123456", new Transfer(1254244, 1000, "20/01/2008"));

        addTransferencia("123456", new Transfer(1254334, 100, "20/01/2009"));

        addTransferencia("456789", new Transfer(7894500, 2544, "21/02/2030"));

        addTransferencia("78945", new Transfer(45678944, 35, "20/01/2077"));

    }

    public void updateSerie(String userId, Transfer transferencia) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_DESTINY, transferencia.getDestiny());
        values.put(COLUMN_CUANTITY, transferencia.getCuantity());
        values.put(COLUMN_DATE, transferencia.getDate());

        String selection = COLUMN_USER_ID + " = ? AND " + COLUMN_DESTINY + " = ?";
        String[] selectionArgs = { userId, String.valueOf(transferencia.getDestiny())};

        int count = db.update(TABLE_NAME, values, selection, selectionArgs);

        db.close();
    }

}