package DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE " + TABLE_PLAYERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_LAST_NAME + " TEXT NOT NULL,"
            + KEY_ID_POSITION + " TEXT NOT NULL,"
            + KEY_NUMBER + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE " + TABLE_EVENTS + "("
            + KEY_ID_EVENT + " INTEGER PRIMARY KEY,"
            + KEY_ID_TYPE + " TEXT NOT NULL,"
            + KEY_DATE + " TEXT NOT NULL,"
            + KEY_HOUR + " TEXT NOT NULL,"
            + KEY_PLACE + " TEXT NOT NULL,"
            + KEY_DESC + " TEXT NOT NULL,"
            + KEY_PRICE + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_PLAYER_EVENT = "CREATE TABLE " + TABLE_PLAYER_EVENT + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
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
        //values.put(KEY_ID, zawodniczka.getId());
        values.put(KEY_NAME, zawodniczka.getImie());
        values.put(KEY_LAST_NAME, zawodniczka.getNazwisko());
        values.put(KEY_ID_POSITION, zawodniczka.getIdPozycji());
        values.put(KEY_NUMBER, zawodniczka.getNumer());
        //values.put(KEY_ID_EVENT, zawodniczka.);
        // Inserting Row
        db.insert(TABLE_PLAYERS, null, values);
        db.close(); // Closing database connection
    }

    public Zawodniczka getZawodcznika(int id) throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_LAST_NAME, KEY_ID_POSITION, KEY_NUMBER}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        //(cursor.getInt(cursor.getColumnIndex(KEY_ID))),
        Zawodniczka zawodniczka = new Zawodniczka(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)),
                cursor.getInt(cursor.getColumnIndex(KEY_ID_POSITION)),
                cursor.getInt(cursor.getColumnIndex(KEY_NUMBER)));

        return zawodniczka;
    }

    public ArrayList<Zawodniczka> getWszystkieZawodniczki() /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception, Niedozwolony_Id_Exception*/ {
        ArrayList<Zawodniczka> zawodniczkaList = new ArrayList<Zawodniczka>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Zawodniczka zawodniczka = new Zawodniczka();
                zawodniczka.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                zawodniczka.setImie(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                zawodniczka.setNazwisko(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                zawodniczka.setIdPozycji(cursor.getInt(cursor.getColumnIndex(KEY_ID_POSITION)));
                zawodniczka.setNumer(cursor.getInt(cursor.getColumnIndex(KEY_NUMBER)));
                ;
                // Adding contact to list
                zawodniczkaList.add(zawodniczka);
            } while (cursor.moveToNext());
        }

        //dodalam
        db.close();

        // return all players list
        return zawodniczkaList;
    }

    public int getLiczbaZawodniczek() {

        String countQuery = "SELECT  * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //dodalam
        db.close();

        // return count
        return cursor.getCount();
    }

    public void updateZawodniczka(Zawodniczka zawodniczka) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Log.d("Update Indeks : ", zawodniczka.getId().toString());
        values.put(KEY_ID, zawodniczka.getId());
        values.put(KEY_NAME, zawodniczka.getImie());
        values.put(KEY_LAST_NAME, zawodniczka.getNazwisko());
        values.put(KEY_ID_POSITION, zawodniczka.getIdPozycji());
        values.put(KEY_NUMBER, zawodniczka.getNumer());

        // updating row
        db.update(TABLE_PLAYERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(zawodniczka.getId())});
        db.close();

    }

    public void deleteZawodniczka(Zawodniczka zawodniczka) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, KEY_ID + " = ?",
                new String[]{String.valueOf(zawodniczka.getId())});
        db.close();
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

        Cursor cursor = db.query(TABLE_EVENTS, new String[]{KEY_ID_EVENT,
                        KEY_ID_TYPE, KEY_DATE, KEY_HOUR, KEY_PLACE, KEY_DESC, KEY_PRICE}, KEY_ID_EVENT + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Wydarzenie wydarzenie = new Wydarzenie(cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT)),
                cursor.getInt(cursor.getColumnIndex(KEY_ID_TYPE)),
                cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                cursor.getString(cursor.getColumnIndex(KEY_HOUR)),
                cursor.getString(cursor.getColumnIndex(KEY_PLACE)),
                cursor.getString(cursor.getColumnIndex(KEY_DESC)),
                cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));

        //dodalam
        //db.close();

        return wydarzenie;
    }

    public ArrayList<Wydarzenie> getWszystkieWydarzenia() /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception, Niedozwolony_Id_Exception*/ {
        ArrayList<Wydarzenie> wydarzenieList = new ArrayList<Wydarzenie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Wydarzenie wydarzenie = new Wydarzenie();
                wydarzenie.setIdWydarzenia(cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT)));
                wydarzenie.setIdTypuWydarzenia(cursor.getInt(cursor.getColumnIndex(KEY_ID_TYPE)));
                wydarzenie.setData(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                wydarzenie.setGodzina(cursor.getString(cursor.getColumnIndex(KEY_HOUR)));
                wydarzenie.setMiejsce(cursor.getString(cursor.getColumnIndex(KEY_PLACE)));
                wydarzenie.setOpis(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
                wydarzenie.setCena(cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));

                wydarzenieList.add(wydarzenie);
            } while (cursor.moveToNext());

            //dodalam
            //db.close();
        }

        // return events list
        return wydarzenieList;
    }

    public int getLiczbaWydarzen() {

        String countQuery = "SELECT  * FROM " + TABLE_EVENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
        //dodalam
        //db.close();
        // return count
        return cursor.getCount();
    }

    public void updateWydarzenie(Wydarzenie wydarzenie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_TYPE, wydarzenie.getIdTypuWydarzenia());
        values.put(KEY_DATE, wydarzenie.getData());
        values.put(KEY_HOUR, wydarzenie.getGodzina());
        values.put(KEY_PLACE, wydarzenie.getMiejsce());
        values.put(KEY_DESC, wydarzenie.getOpis());
        values.put(KEY_PRICE, wydarzenie.getCena());
        // updating row
        db.update(TABLE_EVENTS, values, KEY_ID_EVENT + " = ?",
                new String[]{String.valueOf(wydarzenie.getIdWydarzenia())});

        db.close();
    }

    public void deleteWydarzenie(Wydarzenie wydarzenie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ID_EVENT + " = ?",
                new String[]{String.valueOf(wydarzenie.getIdWydarzenia())});
        db.close();
    }

    public void zapiszZawodniczkeNaWydarzenie(Integer idZawodniczki, Integer idWydarzenia, Integer idStatusu) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("dodaje zaw ", idZawodniczki.toString());
        Log.d("dodaje wyd", idWydarzenia.toString());

        ContentValues values = new ContentValues();

        values.put(KEY_PLAYER_ID, idZawodniczki);
        values.put(KEY_EVENT_ID, idWydarzenia);
        values.put(KEY_PRESENCE, idStatusu);

        // Inserting Row
        db.insert(TABLE_PLAYER_EVENT, null, values);
        db.close(); // Closing datab
    }

    /*
        public Integer getIdZaw(int id){
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_PLAYER_EVENT, new String[] { KEY_ID,
                            KEY_PLAYER_ID, KEY_EVENT_ID, KEY_PRESENCE}, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

           Integer idZaw = cursor.getInt(cursor.getColumnIndex(KEY_PLAYER_ID));

            return idZaw;
        }
    */
    public Integer getIdWyd(int id) {

        Integer idWyd;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER_EVENT, new String[]{KEY_ID,
                        KEY_PLAYER_ID, KEY_EVENT_ID, KEY_PRESENCE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        idWyd = cursor.getInt(cursor.getColumnIndex(KEY_EVENT_ID));
/*
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER_EVENT + " where IDZawodniczki="+id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        idWyd= cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT));;
*/
        //dodalam
        //db.close();

        return idWyd;
    }

/*
    public Integer getIdStatus(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER_EVENT, new String[] { KEY_ID,
                        KEY_PLAYER_ID, KEY_EVENT_ID, KEY_PRESENCE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Integer idStatus = cursor.getInt(cursor.getColumnIndex(KEY_PRESENCE));

        return idStatus;
    }
    */
//public void getWszystkieWydarzeniaPoZawodniczce(Integer idZawodniczki) {

    public List<Wydarzenie> getWszystkieWydarzeniaPoZawodniczce(Integer idZawodniczki) {

        List<Wydarzenie> wydarzenia = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS + " tp, "
                + TABLE_EVENTS + " te, " + TABLE_PLAYER_EVENT + " tpe WHERE tp."
                + KEY_ID + " = '" + idZawodniczki + "'" + " AND tp." + KEY_ID
                + " = " + "tpe." + KEY_PLAYER_ID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Wydarzenie wydarzenie = new Wydarzenie();
                wydarzenie.setIdWydarzenia(cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT)));
                wydarzenie.setIdTypuWydarzenia(cursor.getInt(cursor.getColumnIndex(KEY_ID_TYPE)));
                wydarzenie.setData(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                wydarzenie.setGodzina(cursor.getString(cursor.getColumnIndex(KEY_HOUR)));
                wydarzenie.setMiejsce(cursor.getString(cursor.getColumnIndex(KEY_PLACE)));
                wydarzenie.setOpis(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
                wydarzenie.setCena(cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));

                wydarzenia.add(wydarzenie);
            } while (cursor.moveToNext());
        }

        //dodalam
        //db.close();
        return wydarzenia;
    }

    public Wydarzenie getDaneWydarzeniaPoIdWydarzenia(String idWydarzenia) {

        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " te WHERE te."
                + KEY_ID_EVENT + " = '" + idWydarzenia + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Wydarzenie wydarzenie = new Wydarzenie();

        if (cursor.moveToFirst()) {

            wydarzenie.setIdWydarzenia(cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT)));
            wydarzenie.setIdTypuWydarzenia(cursor.getInt(cursor.getColumnIndex(KEY_ID_TYPE)));
            wydarzenie.setData(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            wydarzenie.setGodzina(cursor.getString(cursor.getColumnIndex(KEY_HOUR)));
            wydarzenie.setMiejsce(cursor.getString(cursor.getColumnIndex(KEY_PLACE)));
            wydarzenie.setOpis(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
            wydarzenie.setCena(cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));
        }

        //dodalam
        //db.close();

        return wydarzenie;
    }

    public List<Zawodniczka> getDaneZawodniczekPoIdWydarzenia(String idWydarzenia) {

        // byc moze da sie uproscic, ale na razie dziala
        String selectQuery = "SELECT "+ KEY_PLAYER_ID +", "+ KEY_PRESENCE+ " FROM " + TABLE_PLAYER_EVENT + " WHERE "
                + KEY_EVENT_ID + " = '" + idWydarzenia + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Integer> zawodniczkiID=new ArrayList<Integer>();
        List<Integer> zawodniczkiObecnosc=new ArrayList<Integer>();

        if (cursor.moveToFirst()) {
            do {
                Integer idZaw=cursor.getInt(cursor.getColumnIndex(KEY_PLAYER_ID));
                Integer idObecnosci=cursor.getInt(cursor.getColumnIndex(KEY_PRESENCE));
                Log.d("id zawodniczki", idZaw.toString());
                zawodniczkiID.add(idZaw);
                zawodniczkiObecnosc.add(idObecnosci);

            } while (cursor.moveToNext());
        }
        Log.d("liczba zawodniczek ", Integer.valueOf(zawodniczkiID.size()).toString());
        db.close();

        List<Zawodniczka> zawodniczkiList = new ArrayList<>();
        SQLiteDatabase db1 = this.getReadableDatabase();

        for(Integer listaZawodniczek:zawodniczkiID){

            Integer idZawodniczki=zawodniczkiID.get(zawodniczkiID.indexOf(listaZawodniczek));
            Integer idPresence=zawodniczkiObecnosc.get(zawodniczkiID.indexOf(listaZawodniczek));

            String selectQuery1 = "SELECT * FROM " + TABLE_PLAYERS + " tp WHERE tp."
                    + KEY_ID + " = '" + idZawodniczki + "'";

            Cursor cursor1 = db1.rawQuery(selectQuery1, null);

            if (cursor1.moveToFirst()) {
                do {
                    Zawodniczka zawodniczka = new Zawodniczka();
                    zawodniczka.setId(cursor1.getInt(cursor1.getColumnIndex(KEY_ID)));
                    zawodniczka.setImie(cursor1.getString(cursor1.getColumnIndex(KEY_NAME)));
                    zawodniczka.setNazwisko(cursor1.getString(cursor1.getColumnIndex(KEY_LAST_NAME)));
                    zawodniczka.setIdPozycji(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_POSITION)));
                    zawodniczka.setNumer(cursor1.getInt(cursor1.getColumnIndex(KEY_NUMBER)));
                    zawodniczka.setObecnosc(idPresence);
                    Log.d("imie zaw ", zawodniczka.getImie());

                    zawodniczkiList.add(zawodniczka);

                } while (cursor1.moveToNext());
            }
        }

        //dodalam
        //db.close();
        Integer liczbaZaw = zawodniczkiList.size();
        Log.i("GET DANE ZAW", liczbaZaw.toString());

        return zawodniczkiList;
    }

    public void getWszystkoTabelaZawodniczkaWydarzenie() {
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Integer idPl = cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT));
                Log.d("ID Wydarzenia", idPl.toString());
                String idEv = cursor.getString(cursor.getColumnIndex(KEY_DESC));
                Log.d("ID opis", idEv);
            } while (cursor.moveToNext());
        }
        //dodalam, moze byc niepotrzebne?
        db.close();
    }

    public void deleteZawodniczkeZWydarzenia(Integer idZawodniczki) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER_EVENT, KEY_PLAYER_ID + " = ?",
                new String[]{idZawodniczki.toString()});

        String selectQuery = "SELECT * FROM " + TABLE_PLAYER_EVENT;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Integer idPl = cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT));
                Log.d("ID Wydarzenia", idPl.toString());
                //String idEv = cursor.getString(cursor.getColumnIndex(KEY_DESC));
                //Log.d("ID opis", idEv);
            } while (cursor.moveToNext());

            //db.close();
        }
    }

    public List<Wydarzenie> getWydarzeniaNaKtoreNieJestZapisanaZawodniczka(Integer idZawodniczki) {

        ArrayList<Wydarzenie> wydarzenieList = new ArrayList<Wydarzenie>();

        //wyszukanie, na co jest zapisana
        String selectQuery = "SELECT * FROM " + TABLE_PLAYERS + " tp JOIN " + TABLE_PLAYER_EVENT + " tpe ON (tp." + KEY_ID + "= tpe." + KEY_PLAYER_ID +
                ") JOIN " + TABLE_EVENTS + " te on ( tpe." + KEY_EVENT_ID + " = te." + KEY_ID_EVENT + ") WHERE tp." + KEY_ID + " = '" + idZawodniczki + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String wydarzeniaNaKtoreJestZapisana=new String();

        if (cursor.moveToFirst()) {
            do {
                wydarzeniaNaKtoreJestZapisana+="'"+cursor.getString(cursor.getColumnIndex(KEY_ID_EVENT))+"',";
            } while (cursor.moveToNext());
        }

        String selectQuery1;
        Cursor cursor1;
        if(!wydarzeniaNaKtoreJestZapisana.isEmpty()) {
            //jezeli jest na cos zapisana
            Log.d("getWydarJestZapisana", wydarzeniaNaKtoreJestZapisana);
            wydarzeniaNaKtoreJestZapisana = wydarzeniaNaKtoreJestZapisana.substring(0, wydarzeniaNaKtoreJestZapisana.length() - 1);
            //wyszukanie, na co nie jest zapisana
            selectQuery1 = //"SELECT * FROM "+TABLE_EVENTS +" EXCEPT "+
                    "SELECT * FROM " + TABLE_EVENTS + " WHERE " + KEY_ID_EVENT + " NOT IN (" + wydarzeniaNaKtoreJestZapisana + ")";

            cursor1 = db.rawQuery(selectQuery1, null);
            Log.d("getWydar query", selectQuery1);

            if(cursor1.getCount()!=0) {
                if (cursor1.moveToFirst()) {
                    do {
                        Wydarzenie wydarzenie = new Wydarzenie();
                        wydarzenie.setIdWydarzenia(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_EVENT)));
                        wydarzenie.setIdTypuWydarzenia(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_TYPE)));
                        wydarzenie.setData(cursor1.getString(cursor1.getColumnIndex(KEY_DATE)));
                        wydarzenie.setGodzina(cursor1.getString(cursor1.getColumnIndex(KEY_HOUR)));
                        wydarzenie.setMiejsce(cursor1.getString(cursor1.getColumnIndex(KEY_PLACE)));
                        wydarzenie.setOpis(cursor1.getString(cursor1.getColumnIndex(KEY_DESC)));
                        wydarzenie.setCena(cursor1.getInt(cursor1.getColumnIndex(KEY_PRICE)));
                        Log.d("idNaCoNieJest ", Integer.valueOf(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_EVENT))).toString());
                        wydarzenieList.add(wydarzenie);
                    } while (cursor1.moveToNext());
                }
            }
        }

        Log.d("getWydarzeNieJestZap ", Integer.valueOf(wydarzenieList.size()).toString());
    return wydarzenieList;
    }

    public Integer czyJestNaCosZapisana(Integer idZawodniczki){
        String selectQuery = "SELECT * FROM " + TABLE_PLAYER_EVENT + " WHERE "
                + KEY_PLAYER_ID + " = '" + idZawodniczki + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Integer czyJestZapisana=0;

        Log.d("Sprawdzam,"," czy jest zapisana");
        if (cursor.moveToFirst()) {
            do {
                Log.d("chech,co jest w curos", Integer.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_ID_EVENT))).toString());
            } while (cursor.moveToNext());
        }

        if(cursor!=null && cursor.getCount()==0) {
            czyJestZapisana = 0;
        }
        else if(cursor!=null && cursor.getCount()>0){
            czyJestZapisana = 1;
        }

        Log.d("return ",czyJestZapisana.toString());

        return czyJestZapisana;
    }

    public Integer liczbaNaIleJestZapisana(Integer idZawodniczki){

        ArrayList<Wydarzenie> wydarzenieList = new ArrayList<Wydarzenie>();

        String selectQuery = "SELECT * FROM " + TABLE_PLAYERS + " tp JOIN " + TABLE_PLAYER_EVENT + " tpe ON (tp." + KEY_ID + "= tpe." + KEY_PLAYER_ID +
                ") JOIN " + TABLE_EVENTS + " te on ( tpe." + KEY_EVENT_ID + " = te." + KEY_ID_EVENT + ") WHERE tp." + KEY_ID + " = '" + idZawodniczki + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery(selectQuery, null);

        if(cursor1.getCount()!=0) {
            if (cursor1.moveToFirst()) {
                do {
                    Wydarzenie wydarzenie = new Wydarzenie();
                    wydarzenie.setIdWydarzenia(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_EVENT)));
                    wydarzenie.setIdTypuWydarzenia(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_TYPE)));
                    wydarzenie.setData(cursor1.getString(cursor1.getColumnIndex(KEY_DATE)));
                    wydarzenie.setGodzina(cursor1.getString(cursor1.getColumnIndex(KEY_HOUR)));
                    wydarzenie.setMiejsce(cursor1.getString(cursor1.getColumnIndex(KEY_PLACE)));
                    wydarzenie.setOpis(cursor1.getString(cursor1.getColumnIndex(KEY_DESC)));
                    wydarzenie.setCena(cursor1.getInt(cursor1.getColumnIndex(KEY_PRICE)));
                    Log.d("idNaCoNieJest ", Integer.valueOf(cursor1.getInt(cursor1.getColumnIndex(KEY_ID_EVENT))).toString());
                    wydarzenieList.add(wydarzenie);
                } while (cursor1.moveToNext());
            }
        }

        return wydarzenieList.size();
    }
}


