package com.example.domi.fightlapyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;

public class ListaZawodniczekActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zawodniczek);

        mListView = (ListView) findViewById(R.id.listazawodniczek_list_view);

        Log.d("Reading: ", "Reading all contacts..");
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();
        String[] listItems = new String[zawodniczkiList.size()];

        //for(int i = 0; i < zawodniczkiList.size(); i++)
        for (Zawodniczka zaw : zawodniczkiList){
            //Zawodniczka zawodniczka = zawodniczkiList.get(i);
            String log = "Id: " + zaw.get_id() + " ,Name: " + zaw.get_imie() + " ,Nazwisko: " + zaw.get_nazwisko()+" ,id pozycji "+zaw.get_id_pozycji()+" ,numer "+zaw.get_numer();
            Log.d("Ostatnia: ", log);
            int indeks = zawodniczkiList.indexOf(zaw);
            Integer indeks1 = (Integer) indeks;
            Log.d("Indeks", indeks1.toString());
            listItems[zawodniczkiList.indexOf(zaw)] = zaw.get_imie();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }
}
