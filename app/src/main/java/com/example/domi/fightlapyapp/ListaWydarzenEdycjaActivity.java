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
import Wydarzenie.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ListaWydarzenEdycjaActivity extends AppCompatActivity {
    private ListView listaWydarzenEdycjaLV;
    Intent i;
    private String[] listaWydarzen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_wydarzen_edycja);

        i= new Intent(getApplicationContext(), WybraneWydarzenieZListyEdycjaActivity.class);
        listaWydarzenEdycjaLV = (ListView) findViewById(R.id.listawydarzenedycja_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();
        db.close();

        WydarzenieObliczenia wydarzenieObliczenia= new WydarzenieObliczenia();

        if(!wydarzenieList.isEmpty()) {
            listaWydarzen = wydarzenieObliczenia.getListeWydarzen(wydarzenieList);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaWydarzen);
            listaWydarzenEdycjaLV.setAdapter(adapter);
        }

        listaWydarzenEdycjaLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
