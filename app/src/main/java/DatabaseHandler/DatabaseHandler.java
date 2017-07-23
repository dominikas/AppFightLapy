package DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import MyExceptions.*;
import Zawodniczka.*;
/**
 * Created by Domi on 2017-07-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FightLapyDateBase";
    private static final String TABLE_PLAYERS = "TabelaZawodniczek";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "imie";
    private static final String KEY_LAST_NAME = "nazwisko";
    private static final String KEY_ID_POSITION = "pozycjiID";
    private static final String KEY_NUMBER = "numer";
    //private static final String KEY_ID_EVENT = "idWydarzenia";

    private static final String TABLE_POSITIONS = "SlownikPozycji";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_PLAYERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_LAST_NAME + " TEXT NOT NULL,"
                + KEY_ID_POSITION + " TEXT NOT NULL,"
                + KEY_NUMBER + " TEXT NOT NULL)");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);

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


}
