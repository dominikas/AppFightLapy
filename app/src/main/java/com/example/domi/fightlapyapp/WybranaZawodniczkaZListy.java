package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import DatabaseHandler.DatabaseHandler;
import Wydarzenie.Wydarzenie;
import Zawodniczka.Zawodniczka;

public class WybranaZawodniczkaZListy extends AppCompatActivity {
    private TextView imieInaziwkoZawodniczki;
    private String product=new String();
    private TextView numerTV;
    private TextView idPozycjiTV;
    private TextView idZawodniczkiTV;
    private TextView idWydarzeniaTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrana_zawodniczka_zlisty);
        imieInaziwkoZawodniczki = (TextView) findViewById(R.id.wybrana_zaw_z_listy);
        numerTV=(TextView) findViewById(R.id.numerZawodniczki) ;
        idPozycjiTV=(TextView) findViewById(R.id.idPozycji);
        idZawodniczkiTV =(TextView) findViewById(R.id.idZawodniczki);
        idWydarzeniaTV=(TextView) findViewById(R.id.idWydarzenia);

        Intent i = getIntent();
        // getting attached intent data
         product = i.getStringExtra("imie i nazwisko");
        // displaying selected product name

        imieInaziwkoZawodniczki.setText(product);
        wyszukanieDanychZawodniczki(product);

    }

    private void wyszukanieDanychZawodniczki(String product){
        Integer idZaw=666;
        Integer indeksZaw=666;

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        List<Wydarzenie> wydarzeniaList = new ArrayList<>();

        db.close();

        for(Zawodniczka zaw:zawodniczkiList)
        {
            if((zaw.get_imie()+" "+zaw.get_nazwisko()).equals(product)) {
                idZaw = zaw.get_id();
                indeksZaw=zawodniczkiList.indexOf(zaw);
            }
        }

        idZawodniczkiTV.setText(((zawodniczkiList.get(indeksZaw)).get_id()).toString());
        numerTV.setText(((zawodniczkiList.get(indeksZaw)).get_numer()).toString());

        Integer pozycjaInt=(zawodniczkiList.get(indeksZaw)).get_id_pozycji();
        String pozycjaString=new String();

        switch(pozycjaInt){
            case (1):
                pozycjaString="Rozegranie";
                break;
            case(2):
                pozycjaString="Atak";
                break;
            case(3):
                pozycjaString="Przyjęcie";
                break;
            case(4):
                pozycjaString="Środek";
                break;
            case(5):
                pozycjaString="Libero";
                break;
        }

        idPozycjiTV.setText(pozycjaString);

        // wydarzenie - wyszukanie  ztabeli za_wydarze id zawodniczki i pobranie odpowiadjacego jej id wydarzenia
        DatabaseHandler db1 = new DatabaseHandler(this);
        //idWydarzeniaTV.setText((db1.getIdWyd(idZaw)).toString());
        Log.d("id zawodniczki", idZaw.toString());

        wydarzeniaList=db1.getWszystkieWydarzeniaPoZawodniczce(idZaw);
        db1.close();
        if(wydarzeniaList.isEmpty())
            Log.d("pusta", "pusta");
        else
            idWydarzeniaTV.setText(wydarzeniaList.get(wydarzeniaList.size()-1).get_opis());

    }
}
