package com.example.domi.fightlapyapp;

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

import java.util.ArrayList;
import java.util.List;

import DatabaseHandler.DatabaseHandler;

import Zawodniczka.*;

public class ListaZapisanychZawodniczek extends AppCompatActivity {

    Intent i;
    private String product;
    private ListView listaZawodniczekTakLV;
    private ListView listaZawodniczekTbcLV;
    private ListView listaZawodniczekNieLV;
    final Context context = this;

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

        String[] listItemsTak = zawodniczkaObliczenia.getZawodniczkiTakWydarzenie(wybraneZawodniczki);
        String[] listItemsNie = zawodniczkaObliczenia.getZawodniczkiNieWydarzenie(wybraneZawodniczki);
        String[] listItemsTbc = zawodniczkaObliczenia.getZawodniczkiTbcWydarzenie(wybraneZawodniczki);

            //wypisanie zawodniczek, ktore są zapisane na wydarzenie
            if (wybraneZawodniczki.isEmpty())
                Log.d("pusta", "pusta");
            else {

                ArrayAdapter adapterTak = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItemsTak);
                listaZawodniczekTakLV.setAdapter(adapterTak);

                ArrayAdapter adapterTbc = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listItemsTbc);
                listaZawodniczekTbcLV.setAdapter(adapterTbc);

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
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    usuniecieZawodniczkiZWydarzenia(idZawodniczki);
                                    ListaZapisanychZawodniczek.this.finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                usuniecieZawodniczkiZWydarzenia(idZawodniczki);
                                ListaZapisanychZawodniczek.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
        Log.d("Delete: ", "...");
        db.deleteZawodniczkeZWydarzenia(idZawodniczki);
        db.close();
        Log.d("Delete: ", "...");
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
