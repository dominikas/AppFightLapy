package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    private ListView listaWydarzenTakZawodniczka;
    private ListView listaWydarzenNieZawodniczka;
    private ListView listaWydarzenTbcZawodniczka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrana_zawodniczka_zlisty);
        imieInaziwkoZawodniczki = (TextView) findViewById(R.id.wybrana_zaw_z_listy);
        numerTV=(TextView) findViewById(R.id.numerZawodniczki) ;
        idPozycjiTV=(TextView) findViewById(R.id.idPozycji);
        idZawodniczkiTV =(TextView) findViewById(R.id.idZawodniczki);
        idWydarzeniaTV=(TextView) findViewById(R.id.idWydarzenia);
        listaWydarzenTakZawodniczka = (ListView) findViewById(R.id.lista_wydarzen_tak_zawodniczki);
        listaWydarzenNieZawodniczka = (ListView) findViewById(R.id.lista_wydarzen_nie_zawodniczki);
        listaWydarzenTbcZawodniczka = (ListView) findViewById(R.id.lista_wydarzen_tbc_zawodniczki);

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

        //TODO do zawodniczkaObliczenia dodac
        for(Zawodniczka zaw:zawodniczkiList)
        {
            if((zaw.getImie()+" "+zaw.getNazwisko()).equals(product)) {
                idZaw = zaw.getId();
                indeksZaw=zawodniczkiList.indexOf(zaw);
            }
        }

        idZawodniczkiTV.setText(((zawodniczkiList.get(indeksZaw)).getId()).toString());
        numerTV.setText(((zawodniczkiList.get(indeksZaw)).getNumer()).toString());

        //TODO do ZawodniczkaObliczenia dodac
        Integer pozycjaInt=(zawodniczkiList.get(indeksZaw)).getIdPozycji();
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

        // wydarzenie - wyszukanie  z tabeli zawodniczek_wydarzen id kliknietej zawodniczki
        // i pobranie wydarzen, na ktore jest zapisana
        DatabaseHandler db1 = new DatabaseHandler(this);
        Log.d("id zawodniczki", idZaw.toString());

        wydarzeniaList=db1.getWszystkieWydarzeniaPoZawodniczce(idZaw);
        db1.close();

        WydarzeniaObliczenia wydarzeniaObliczenia=new WydarzeniaObliczenia();
        String[] wydarzeniaTak = wydarzeniaObliczenia.getWydarzeniaTakZawodniczka(wydarzeniaList);
        String[] wydarzeniaNie = wydarzeniaObliczenia.getWydarzeniaNieZawodniczka(wydarzeniaList);
        String[] wydarzeniaTbc = wydarzeniaObliczenia.getWydarzeniaTbcZawodniczka(wydarzeniaList);

        //wypisanie wszystkich wydarzen, na ktore zapisana jest wybrana zawodniczka
        if(wydarzeniaList.isEmpty())
            Log.d("pusta", "pusta");
        else {
            //idwydarzenia do usuniecia
            idWydarzeniaTV.setText(wydarzeniaList.get(wydarzeniaList.size() - 1).getOpis());

            ArrayAdapter adapterTak = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wydarzeniaTak);
            listaWydarzenTakZawodniczka.setAdapter(adapterTak);

            ArrayAdapter adapterNie = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wydarzeniaNie);
            listaWydarzenNieZawodniczka.setAdapter(adapterNie);

            ArrayAdapter adapterTbc = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wydarzeniaTbc);
            listaWydarzenTbcZawodniczka.setAdapter(adapterTbc);
        }

    }
}
