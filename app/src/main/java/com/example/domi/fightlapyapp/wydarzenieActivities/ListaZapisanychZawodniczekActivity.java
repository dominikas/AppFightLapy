package com.example.domi.fightlapyapp.wydarzenieActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domi.fightlapyapp.R;

import java.util.ArrayList;
import java.util.List;

import databaseHandler.DatabaseHandler;

import zawodniczka.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ListaZapisanychZawodniczekActivity extends AppCompatActivity {

    Intent i;
    private String product;
    private ListView listaZawodniczekTakLV;
    private ListView listaZawodniczekTbcLV;
    private ListView listaZawodniczekNieLV;
    final Context context = this;
    private String[] listItemsTak;
    private String[] listItemsNie;
    private String[] listItemsTbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zapisanych_zawodniczek);

        Intent i = getIntent();
        product = i.getStringExtra("lista_zawodniczek");
        Log.d("lista zawodniczek ", product);
        listaZawodniczekTakLV = (ListView) findViewById(R.id.lista_zapisanych_zawodniczek);
        listaZawodniczekTbcLV = (ListView) findViewById(R.id.lista_zawodniczek_tbc);
        listaZawodniczekNieLV = (ListView) findViewById(R.id.lista_zawodniczek_nie);

        //wyszuaknie i wypisanie zawodniczek zapisanych na wybrane wydarzenie
        DatabaseHandler db1 = new DatabaseHandler(this);

        List<Zawodniczka> wybraneZawodniczki = db1.getDaneZawodniczekPoIdWydarzenia(product);
        Integer liczbaZaw = wybraneZawodniczki.size();

        db1.close();
        ZawodniczkaObliczenia zawodniczkaObliczenia = new ZawodniczkaObliczenia();
        if(!wybraneZawodniczki.isEmpty() && zawodniczkaObliczenia.getZawodniczkiTakWydarzenie(wybraneZawodniczki).length!=0) {
            listItemsTak = zawodniczkaObliczenia.getZawodniczkiTakWydarzenie(wybraneZawodniczki);
            ArrayAdapter adapterTak = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItemsTak);
            listaZawodniczekTakLV.setAdapter(adapterTak);
        }
        if(!wybraneZawodniczki.isEmpty() && zawodniczkaObliczenia.getZawodniczkiNieWydarzenie(wybraneZawodniczki).length!=0) {
            listItemsNie = zawodniczkaObliczenia.getZawodniczkiNieWydarzenie(wybraneZawodniczki);
            ArrayAdapter adapterTbc = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listItemsTbc);
            listaZawodniczekTbcLV.setAdapter(adapterTbc);

        }
        if(!wybraneZawodniczki.isEmpty() && zawodniczkaObliczenia.getZawodniczkiTbcWydarzenie(wybraneZawodniczki).length!=0) {
            listItemsTbc = zawodniczkaObliczenia.getZawodniczkiTbcWydarzenie(wybraneZawodniczki);
            ArrayAdapter adapterNie = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listItemsNie);
            listaZawodniczekNieLV.setAdapter(adapterNie);
        }

            listaZawodniczekTakLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {

                    //pobranie danych zawodniczki, ktora ma byc usunieta
                    String imieINazwisko = ((TextView) arg1).getText().toString();
                    final Integer idZawodniczki = wyszukanieDanychZawodniczki(imieINazwisko);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    alertDialogBuilder.setTitle("Usuwanie zawodniczki z wydarzenia");
                    alertDialogBuilder
                            .setMessage("Czy na pewno chcesz usunąć zawodniczkę z wydarzenia?")
                            .setCancelable(false)
                            .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    usuniecieZawodniczkiZWydarzenia(idZawodniczki);
                                    ListaZapisanychZawodniczekActivity.this.finish();
                                }
                            })
                            .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    Log.v("long clicked", "pos: " + pos);

                    return true;
                }
            });

        listaZawodniczekTbcLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                //pobranie danych zawodniczki, ktora ma byc usunieta
                String imieINazwisko = ((TextView) arg1).getText().toString();
                final Integer idZawodniczki = wyszukanieDanychZawodniczki(imieINazwisko);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setTitle("Usuwanie zawodniczki z wydarzenia");
                alertDialogBuilder
                        .setMessage("Czy na pewno chcesz usunąć zawodniczkę z wydarzenia?")
                        .setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                usuniecieZawodniczkiZWydarzenia(idZawodniczki);
                                ListaZapisanychZawodniczekActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                Log.v("long clicked", "pos: " + pos);

                return true;
            }
        });
        }


    private void usuniecieZawodniczkiZWydarzenia(Integer idZawodniczki){
        Log.v("id zawodniczki ", idZawodniczki.toString());

        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteZawodniczkeZWydarzenia(idZawodniczki);
        db.close();

    }

    private Integer wyszukanieDanychZawodniczki(String imieINazwisko){

            DatabaseHandler db = new DatabaseHandler(this);
            ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
            db.close();

        ZawodniczkaObliczenia zawodniczkaObliczenia=new ZawodniczkaObliczenia();
        Zawodniczka zawodniczka = zawodniczkaObliczenia.wyszukanieZawodniczkiZListy(zawodniczkiList,imieINazwisko);
        Integer idZaw=zawodniczka.getId();

        return idZaw;
    }
}