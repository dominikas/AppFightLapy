package databaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import myExceptions.Bledny_Format_Exception;
import myExceptions.Puste_Pole_Exception;
import myExceptions.Za_Dlugi_Exception;
import wydarzenie.Wydarzenie;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class DatabaseHandlerEvents extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FightLapyDateBase1";
    private static final String TABLE_EVENTS = "TabelaWydarzen";
    private static final String KEY_ID = "id";
    private static final String KEY_ID_TYPE = "typ";
    private static final String KEY_DATE = "data";
    private static final String KEY_HOUR = "godzina";
    private static final String KEY_PLACE = "miejsce";
    private static final String KEY_DESC = "opis";
    private static final String KEY_PRICE = "cena";

    public DatabaseHandlerEvents(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ID_TYPE + " TEXT NOT NULL,"
                + KEY_DATE + " TEXT NOT NULL,"
                + KEY_HOUR + " TEXT NOT NULL,"
                + KEY_PLACE + " TEXT NOT NULL,"
                + KEY_DESC + " TEXT NOT NULL,"
                + KEY_PRICE + " TEXT NOT NULL)");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }

    public void dodajWydarzenie(Wydarzenie wydarzenie) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(KEY_ID, zawodniczka.getId());
            values.put(KEY_ID_TYPE, wydarzenie.getIdTypuWydarzenia());
            values.put(KEY_DATE, wydarzenie.getData());
            values.put(KEY_HOUR, wydarzenie.getGodzina());
            values.put(KEY_PLACE, wydarzenie.getMiejsce());
            values.put(KEY_DESC, wydarzenie.getOpis());
            values.put(KEY_PRICE, wydarzenie.getCena());
            // Inserting Row
            db.insert(TABLE_EVENTS, null, values);
            db.close(); // Closing database connection
    }

    public Wydarzenie getWydarzenie(int id) throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID,
                        KEY_ID_TYPE, KEY_DATE, KEY_HOUR, KEY_PLACE, KEY_DESC, KEY_PRICE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Wydarzenie wydarzenie = new Wydarzenie(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                Integer.parseInt(cursor.getString(6)));

        return wydarzenie;
    }

    public ArrayList<Wydarzenie> getWszystkieWydarzenia() /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception, Niedozwolony_Id_Exception*/{
        ArrayList<Wydarzenie> wydarzenieList = new ArrayList<Wydarzenie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Wydarzenie wydarzenie = new Wydarzenie();
                wydarzenie.setIdWydarzenia(Integer.parseInt(cursor.getString(0)));
                wydarzenie.setIdTypuWydarzenia(Integer.parseInt(cursor.getString(1)));
                wydarzenie.setData(cursor.getString(2));
                wydarzenie.setGodzina(cursor.getString(3));
                wydarzenie.setMiejsce(cursor.getString(4));
                wydarzenie.setOpis(cursor.getString(5));
                wydarzenie.setCena(Integer.parseInt(cursor.getString(6)));

                wydarzenieList.add(wydarzenie);
            } while (cursor.moveToNext());
        }

        // return contact list
        return wydarzenieList;
    }

    public int getLiczbaWydarzen() {

        String countQuery = "SELECT  * FROM " + TABLE_EVENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateWydarzenie(Wydarzenie wydarzenie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_TYPE, wydarzenie.getIdTypuWydarzenia());
        values.put(KEY_DATE, wydarzenie.getData());
        values.put(KEY_HOUR, wydarzenie.getGodzina());
        values.put(KEY_PLACE, wydarzenie.getMiejsce());
        values.put(KEY_DESC, wydarzenie.getOpis());
        values.put(KEY_PRICE, wydarzenie.getCena());
        // updating row
        return db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(wydarzenie.getIdWydarzenia()) });
    }

    public void deleteWydarzenie(Wydarzenie wydarzenie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(wydarzenie.getIdWydarzenia()) });
        db.close();
    }


}