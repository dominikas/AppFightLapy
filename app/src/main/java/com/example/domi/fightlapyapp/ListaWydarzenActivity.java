package com.example.domi.fightlapyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import DatabaseHandler.DatabaseHandlerEvents;
import Wydarzenie.Wydarzenie;
import Zawodniczka.Zawodniczka;

public class ListaWydarzenActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_wydarzen);
        mListView = (ListView) findViewById(R.id.listawydarzen_list_view);

        Log.d("Reading: ", "Reading all events..");
        DatabaseHandlerEvents db = new DatabaseHandlerEvents(this);
        ArrayList<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();
        db.close();
        String[] listItems = new String[wydarzenieList.size()];

        //for(int i = 0; i < zawodniczkiList.size(); i++)
        for (Wydarzenie wyd : wydarzenieList){
            //Zawodniczka zawodniczka = zawodniczkiList.get(i);
            String log = "Id: " + wyd.get_id_typu_wydarzenia() + " ,Opis: " + wyd.get_opis() + " ,Data: " + wyd.get_data();
            Log.d("Ostatnia: ", log);
            int indeks = wydarzenieList.indexOf(wyd);
            Integer indeks1 = (Integer) indeks;
            Log.d("Indeks", indeks1.toString());
            listItems[wydarzenieList.indexOf(wyd)] = wyd.get_opis();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }
}
