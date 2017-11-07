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
import Zawodniczka.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ListaZawodniczekActivity extends AppCompatActivity {

    private ListView listaZawodniczek;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zawodniczek);

        i= new Intent(getApplicationContext(), WybranaZawodniczkaZListy.class);

        listaZawodniczek = (ListView) findViewById(R.id.listazawodniczek_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();

        ZawodniczkaObliczenia zawodniczkaObliczenia = new ZawodniczkaObliczenia();
        String[] listaZawodniczek = zawodniczkaObliczenia.getListaZawodniczek(zawodniczkiList);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaZawodniczek);
        this.listaZawodniczek.setAdapter(adapter);

        this.listaZawodniczek.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = ((TextView) view).getText().toString();
                i.putExtra("imie i nazwisko", product);
                startActivity(i);
            }
        });
    }
}
