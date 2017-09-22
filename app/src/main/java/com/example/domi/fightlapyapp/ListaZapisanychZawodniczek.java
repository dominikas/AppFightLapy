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
import Zawodniczka.Zawodniczka;

public class ListaZapisanychZawodniczek extends AppCompatActivity {

    Intent i;
    private String product;
    private ListView listaZawodniczekLV;
    final Context context = this;

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
        //spra cos idzie w product
        List<Zawodniczka> wybraneZawodniczki = db1.getDaneZawodniczekPoIdWydarzenia(product);
        Integer liczbaZaw=wybraneZawodniczki.size();

        //Log.d("liczba za na liscie", liczbaZaw.toString());

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



        listaZawodniczekLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                //pobranie danych zawodniczki, ktora ma byc usunieta
                String imieINazwisko = ((TextView) arg1).getText().toString();
                final Integer idZawodniczki=wyszukanieDanychZawodniczki(imieINazwisko);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Usuwanie zawodniczki z wydarzenia");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Czy na pewno chcesz usunąć zawodniczkę z wydarzenia?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                usuniecieZawodniczkiZWydarzenia(idZawodniczki);
                                ListaZapisanychZawodniczek.this.finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                Log.v("long clicked","pos: " + pos);

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

            Integer idZaw=666;
            Integer indeksZaw=666;
            //Log.d("Co zostalo przeniesione", indeksZaw.toString());

            DatabaseHandler db = new DatabaseHandler(this);
            ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
            //List<Wydarzenie> wydarzeniaList = new ArrayList<>();

            db.close();

            for(Zawodniczka zaw:zawodniczkiList)
            {
                if((zaw.get_imie()+" "+zaw.get_nazwisko()).equals(imieINazwisko)) {
                    //if((zaw.get_id().toString().equals(product))) {
                    idZaw = zaw.get_id();
                    indeksZaw=zawodniczkiList.indexOf(zaw);
                }
            }
            Log.v("id zawodniczki wysz ", idZaw.toString());
            return idZaw;
    }
}
