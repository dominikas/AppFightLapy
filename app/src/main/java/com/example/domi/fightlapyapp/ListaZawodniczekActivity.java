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

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;

public class ListaZawodniczekActivity extends AppCompatActivity {

    private ListView mListView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zawodniczek);

        i= new Intent(getApplicationContext(), WybranaZawodniczkaZListy.class);

        mListView = (ListView) findViewById(R.id.listazawodniczek_list_view);

        Log.d("Reading: ", "Reading all contacts..");
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();
        String[] listItems = new String[zawodniczkiList.size()];

        for (Zawodniczka zaw : zawodniczkiList){
            String log = "Id: " + zaw.get_id() + " ,Name: " + zaw.get_imie() + " ,Nazwisko: " + zaw.get_nazwisko()+" ,id pozycji "+zaw.get_id_pozycji()+" ,numer "+zaw.get_numer();
            Log.d("Ostatnia: ", log);
            int indeks = zawodniczkiList.indexOf(zaw);
            Integer indeks1 = (Integer) indeks;
            Log.d("Indeks", indeks1.toString());

            listItems[zawodniczkiList.indexOf(zaw)] = zaw.get_imie()+" "+zaw.get_nazwisko();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = ((TextView) view).getText().toString();
                // Launching new Activity on selecting single List Item
                // sending data to new activity
                i.putExtra("imie i nazwisko", product);
                startActivity(i);
            }
        });
    }
}
