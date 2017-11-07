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

public class ListaZawodniczekEdycjaActivity extends AppCompatActivity {

    private ListView listaZawodniczekEdycja;
    Intent i;
    Integer indeks1;

    /**
     * Created by Dominika Saide on 2017-11-05.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zawodniczek_edycja);

        i= new Intent(getApplicationContext(), WybranaZawodniczkaZListyEdycjaActivity.class);

        listaZawodniczekEdycja = (ListView) findViewById(R.id.listazawodniczekedycja_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();

        ZawodniczkaObliczenia zawodniczkaObliczenia=new ZawodniczkaObliczenia();
        String[] listaZawodniczek=zawodniczkaObliczenia.getTablicaZawodniczek(zawodniczkiList);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaZawodniczek);
        listaZawodniczekEdycja.setAdapter(adapter);

        listaZawodniczekEdycja.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = ((TextView) view).getText().toString();
                Log.d("indeks wybranej zawodn ", product);
                i.putExtra("imie i nazwisko", product);
                startActivity(i);
            }
        });

    }
}
