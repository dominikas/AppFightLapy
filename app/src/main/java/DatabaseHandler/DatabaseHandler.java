package DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import MyExceptions.*;
import Wydarzenie.Wydarzenie;
import Zawodniczka.*;
/**
 * Created by Domi on 2017-07-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FightLapyDateBase";
    //tabela zawodniczek
    private static final String TABLE_PLAYERS = "TabelaZawodniczek";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "imie";
    private static final String KEY_LAST_NAME = "nazwisko";
    private static final String KEY_ID_POSITION = "pozycjiID";
    private static final String KEY_NUMBER = "numer";
    //private static final String KEY_ID_EVENT = "idWydarzenia";

    //tabela wydarzen
    private static final String TABLE_EVENTS = "TabelaWydarzen1";
    private static final String KEY_ID_EVENT = "id";
    private static final String KEY_ID_TYPE = "typ";
    private static final String KEY_DATE = "data";
    private static final String KEY_HOUR = "godzina";
    private static final String KEY_PLACE = "miejsce";
    private static final String KEY_DESC = "opis";
    private static final String KEY_PRICE = "cena";

    //tabela zawodniczka wydarzenie
    private static final String TABLE_PLAYER_EVENT = "TabelaZawodniczkaWydarzenie";
    private static final String KEY_PLAYER_ID = "IDZawodniczki";
    private static final String KEY_EVENT_ID = "IDWydarzenia";
    private static final String KEY_PRESENCE = "dyspozycja";

    private static final String CREATE_TABLE_PLAYERS="CREATE TABLE " + TABLE_PLAYERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_LAST_NAME + " TEXT NOT NULL,"
            + KEY_ID_POSITION + " TEXT NOT NULL,"
            + KEY_NUMBER + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_EVENTS="CREATE TABLE " + TABLE_EVENTS + "("
            + KEY_ID_EVENT + " INTEGER PRIMARY KEY,"
            + KEY_ID_TYPE + " TEXT NOT NULL,"
            + KEY_DATE + " TEXT NOT NULL,"
            + KEY_HOUR + " TEXT NOT NULL,"
            + KEY_PLACE + " TEXT NOT NULL,"
            + KEY_DESC + " TEXT NOT NULL,"
            + KEY_PRICE + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_PLAYER_EVENT="CREATE TABLE " + TABLE_PLAYER_EVENT + "("
            + KEY_PLAYER_ID + " INTEGER,"
            + KEY_EVENT_ID + " INTEGER,"
            + KEY_PRESENCE + " TEXT NOT NULL)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PLAYERS);
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_PLAYER_EVENT);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_EVENT);
        // Create tables again
        onCreate(db);
    }

    public void dodajZawodniczke(Zawodniczka zawodniczka) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(KEY_ID, zawodniczka.get_id());
            values.put(KEY_NAME, zawodniczka.get_imie());
            values.put(KEY_LAST_NAME, zawodniczka.get_nazwisko());
            values.put(KEY_ID_POSITION, zawodniczka.get_id_pozycji());
            values.put(KEY_NUMBER, zawodniczka.get_numer());
        //values.put(KEY_ID_EVENT, zawodniczka.);
            // Inserting Row
            db.insert(TABLE_PLAYERS, null, values);
            db.close(); // Closing database connection
    }

    public Zawodniczka getZawodcznika(int id) throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[] { KEY_ID,
                        KEY_NAME, KEY_LAST_NAME, KEY_ID_POSITION, KEY_NUMBER }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Zawodniczka zawodniczka = new Zawodniczka(cursor.getString(0),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));
                //cursor.getString(4));
                //Integer.parseInt(cursor.getString(3)),
                //Integer.parseInt(cursor.getString(4)));
        // return contact
        return zawodniczka;
    }

    public ArrayList<Zawodniczka> getWszystkieZawodniczki() /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception, Niedozwolony_Id_Exception*/{
        ArrayList<Zawodniczka> zawodniczkaList = new ArrayList<Zawodniczka>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Zawodniczka zawodniczka = new Zawodniczka();
                zawodniczka.set_id(Integer.parseInt(cursor.getString(0)));
                zawodniczka.set_imie(cursor.getString(1));
                zawodniczka.set_nazwisko(cursor.getString(2));
                zawodniczka.set_id_pozycji(Integer.parseInt(cursor.getString(3)));
                zawodniczka.set_numer(Integer.parseInt(cursor.getString(4)));;
                // Adding contact to list
                zawodniczkaList.add(zawodniczka);
            } while (cursor.moveToNext());
        }

        // return contact list
        return zawodniczkaList;
    }

    public int getLiczbaZawodniczek() {

        String countQuery = "SELECT  * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateZawodniczka(Zawodniczka zawodniczka) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, zawodniczka.get_imie()); // Contact Name
        values.put(KEY_LAST_NAME, zawodniczka.get_nazwisko()); // Contact Phone Number
        values.put(KEY_ID_POSITION, zawodniczka.get_id_pozycji());
        values.put(KEY_NUMBER, zawodniczka.get_numer());

        // updating row
        return db.update(TABLE_PLAYERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(zawodniczka.get_id()) });
    }

    public void deleteZawodniczka(Zawodniczka zawodniczka) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, KEY_ID + " = ?",
                new String[] { String.valueOf(zawodniczka.get_id()) });
        db.close();
    }

    public void dodajWydarzenie(Wydarzenie wydarzenie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID, zawodniczka.get_id());
        values.put(KEY_ID_TYPE, wydarzenie.get_id_typu_wydarzenia());
        values.put(KEY_DATE, wydarzenie.get_data());
        values.put(KEY_HOUR, wydarzenie.get_godzina());
        values.put(KEY_PLACE, wydarzenie.get_miejsce());
        values.put(KEY_DESC, wydarzenie.get_opis());
        values.put(KEY_PRICE, wydarzenie.get_cena());
        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }

    public Wydarzenie getWydarzenie(int id) throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_ID_EVENT,
                        KEY_ID_TYPE, KEY_DATE, KEY_HOUR, KEY_PLACE, KEY_DESC, KEY_PRICE }, KEY_ID_EVENT + "=?",
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
                wydarzenie.set_id_wydarzenia(Integer.parseInt(cursor.getString(0)));
                wydarzenie.set_id_typu_wydarzenia(Integer.parseInt(cursor.getString(1)));
                wydarzenie.set_data(cursor.getString(2));
                wydarzenie.set_godzina(cursor.getString(3));
                wydarzenie.set_miejsce(cursor.getString(4));
                wydarzenie.set_opis(cursor.getString(5));
                wydarzenie.set_cena(Integer.parseInt(cursor.getString(6)));

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
        values.put(KEY_ID_TYPE, wydarzenie.get_id_typu_wydarzenia());
        values.put(KEY_DATE, wydarzenie.get_data());
        values.put(KEY_HOUR, wydarzenie.get_godzina());
        values.put(KEY_PLACE, wydarzenie.get_miejsce());
        values.put(KEY_DESC, wydarzenie.get_opis());
        values.put(KEY_PRICE, wydarzenie.get_cena());
        // updating row
        return db.update(TABLE_EVENTS, values, KEY_ID_EVENT + " = ?",
                new String[] { String.valueOf(wydarzenie.get_id_wydarzenia()) });
    }

    public void deleteWydarzenie(Wydarzenie wydarzenie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ID_EVENT + " = ?",
                new String[] { String.valueOf(wydarzenie.get_id_wydarzenia()) });
        db.close();
    }
}
