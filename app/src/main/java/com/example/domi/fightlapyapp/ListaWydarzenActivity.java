package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import DatabaseHandler.*;
import Wydarzenie.Wydarzenie;

public class ListaWydarzenActivity extends AppCompatActivity {
    private ListView mListView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_wydarzen);

        i= new Intent(getApplicationContext(), WybraneWydarzenieZListyActivity.class);
        mListView = (ListView) findViewById(R.id.listawydarzen_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();
        db.close();
        String[] listItems = new String[wydarzenieList.size()];

        for (Wydarzenie wyd : wydarzenieList){
            int indeks = wydarzenieList.indexOf(wyd);
            Integer indeks1 = (Integer) indeks;
            //TODO zamiast ID wypisac opis -> wyszukowanie w db handler zmienic na wyszukiwanie po opisie
            //listItems[wydarzenieList.indexOf(wyd)] = wyd.getIdWydarzenia().toString();
            listItems[wydarzenieList.indexOf(wyd)] = wyd.getIdWydarzenia().toString()+" "+wyd.getOpis();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String opisCaly = ((TextView) view).getText().toString();
                Log.d("*** Opis caly ***", opisCaly);
                String product = opisCaly.substring(0,1);
                Log.d("*** ID ***", product);
                i.putExtra("wydarzenie", product);
                startActivity(i);
            }
        });

    }
}
