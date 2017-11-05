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

public class ListaZawodniczekEdycjaActivity extends AppCompatActivity {

    private ListView mListView;
    Intent i;
    Integer indeks1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zawodniczek_edycja);

        i= new Intent(getApplicationContext(), WybranaZawodniczkaZListyEdycjaActivity.class);

        mListView = (ListView) findViewById(R.id.listazawodniczekedycja_list_view);

        Log.d("Reading: ", "Reading all contacts..");
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();
        String[] listItems = new String[zawodniczkiList.size()];


        for (Zawodniczka zaw : zawodniczkiList){
            listItems[zawodniczkiList.indexOf(zaw)] = zaw.getImie()+" "+zaw.getNazwisko();
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = ((TextView) view).getText().toString();
                //String product = indeks1.toString();
                Log.d("indeks wybranej zawodn ", product);
                // Launching new Activity on selecting single List Item
                // sending data to new activity
                i.putExtra("imie i nazwisko", product);
                startActivity(i);
            }
        });

    }
}
