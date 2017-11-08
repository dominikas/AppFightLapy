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

import databaseHandler.*;
import wydarzenie.Wydarzenie;


/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ListaWydarzenActivity extends AppCompatActivity {
    private ListView listaWydarzenLV;
    Intent i;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_wydarzen);

        i = new Intent(getApplicationContext(), WybraneWydarzenieZListyActivity.class);
        listaWydarzenLV = (ListView) findViewById(R.id.listawydarzen_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();
        db.close();
        String[] listItems = new String[wydarzenieList.size()];

        for (Wydarzenie wyd : wydarzenieList) {
            int indeks = wydarzenieList.indexOf(wyd);
            Integer indeks1 = (Integer) indeks;
            listItems[wydarzenieList.indexOf(wyd)] = wyd.getIdWydarzenia().toString() + " " + wyd.getOpis();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        listaWydarzenLV.setAdapter(adapter);

        listaWydarzenLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String opisCaly = ((TextView) view).getText().toString();
                Log.d("*** Opis caly ***", opisCaly);
                String product = opisCaly.substring(0, 1);
                Log.d("*** ID ***", product);
                i.putExtra("wydarzenie", product);
                startActivity(i);
            }
        });

        listaWydarzenLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                //pobranie danych wydarzenia, ktore ma byc usuniete
                String opisCaly = ((TextView) arg1).getText().toString();
                String idWydarzeniaStr = opisCaly.substring(0, 1);
                final Integer idWydarzenia = Integer.valueOf(idWydarzeniaStr);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setTitle("Usuwanie wydarzenia");
                alertDialogBuilder
                        .setMessage("Czy na pewno chcesz usunąć wydarzenie?")
                        .setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                usuniecieWydarzenia(idWydarzenia);
                                ListaWydarzenActivity.this.finish();
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

    private void usuniecieWydarzenia(Integer idWydarzenia) {
        Log.v("id zawodniczki ", idWydarzenia.toString());

        DatabaseHandler db = new DatabaseHandler(this);
        //TODO usuniecie wierszy z tab wydarzenia zawodniczki rekordow o id wydarzenia
        Wydarzenie wydarzenie=db.getDaneWydarzeniaPoIdWydarzenia(idWydarzenia.toString());
        db.deleteWydarzenie(wydarzenie);
        db.deleteWydarzenieTabelaWydarzeniaZawodniczka(wydarzenie);
        db.close();
    }
}
