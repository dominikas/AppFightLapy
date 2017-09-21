package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;

public class ListaZapisanychZawodniczek extends AppCompatActivity {

    Intent i;
    private String product;
    private ListView listaZawodniczekLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zapisanych_zawodniczek);


        Intent i = getIntent();
        product = i.getStringExtra("lista_zawodniczek");
        Log.d("lista zawodniczek ", product);
        listaZawodniczekLV = (ListView) findViewById(R.id.lista_zapisanych_zawodniczek);

        //wyszuaknie i wypisanie zawodniczek zapisanych na wybrane wydarzenie
        DatabaseHandler db1 = new DatabaseHandler(this);
        List<Zawodniczka> wybraneZawodniczki = db1.getDaneZawodniczekPoIdWydarzenia(product);
        Integer liczbaZaw=wybraneZawodniczki.size();

        db1.close();

        String[] listItems = new String[wybraneZawodniczki.size()];
        for (Zawodniczka zaw : wybraneZawodniczki) {
            listItems[wybraneZawodniczki.indexOf(zaw)] = zaw.get_imie() + " " + zaw.get_nazwisko();
        }

        //wypisanie wszystkich wydarzen, na ktore zapisana jest wybrana zawodniczka
        if (wybraneZawodniczki.isEmpty())
            Log.d("pusta", "pusta");
        else {
            Log.d("liczba zawodniczek", liczbaZaw.toString());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
            listaZawodniczekLV.setAdapter(adapter);
        }

    }

}
