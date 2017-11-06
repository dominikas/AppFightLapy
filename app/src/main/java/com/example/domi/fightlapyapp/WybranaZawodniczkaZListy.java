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
import Wydarzenie.*;
import Zawodniczka.*;

public class WybranaZawodniczkaZListy extends AppCompatActivity {
    private TextView imieNazwiskoZawodniczki;
    private String product=new String();
    private TextView numerTV;
    private TextView idPozycjiTV;
    private TextView idZawodniczkiTV;
    private ListView listaWydarzenTakZawodniczka;
    private ListView listaWydarzenNieZawodniczka;
    private ListView listaWydarzenTbcZawodniczka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrana_zawodniczka_zlisty);
        imieNazwiskoZawodniczki = (TextView) findViewById(R.id.wybrana_zaw_z_listy);
        numerTV=(TextView) findViewById(R.id.numerZawodniczki) ;
        idPozycjiTV=(TextView) findViewById(R.id.idPozycji);
        idZawodniczkiTV =(TextView) findViewById(R.id.idZawodniczki);
        listaWydarzenTakZawodniczka = (ListView) findViewById(R.id.lista_wydarzen_tak_zawodniczki);
        listaWydarzenNieZawodniczka = (ListView) findViewById(R.id.lista_wydarzen_nie_zawodniczki);
        listaWydarzenTbcZawodniczka = (ListView) findViewById(R.id.lista_wydarzen_tbc_zawodniczki);

        Intent i = getIntent();
        product = i.getStringExtra("imie i nazwisko");
        wyszukanieDanychZawodniczki(product);
    }


    private void wyszukanieDanychZawodniczki(String product){

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();

        ZawodniczkaObliczenia zawodniczkaObliczenia = new ZawodniczkaObliczenia();

        Zawodniczka wyszukanaZawodniczka=zawodniczkaObliczenia.wyszukanieZawodniczkiZListy(zawodniczkiList,product);

        imieNazwiskoZawodniczki.setText(wyszukanaZawodniczka.getImie()+" "+wyszukanaZawodniczka.getNazwisko());
        idZawodniczkiTV.setText((wyszukanaZawodniczka.getId()).toString());
        numerTV.setText((wyszukanaZawodniczka.getNumer()).toString());

        String pozycjaString=zawodniczkaObliczenia.zamianaIdPozycjiNaZnaki(wyszukanaZawodniczka.getIdPozycji());
        idPozycjiTV.setText(pozycjaString);

        // wydarzenie - wyszukanie  z tabeli zawodniczek_wydarzen id kliknietej zawodniczki
        // i pobranie wydarzen, na ktore jest zapisana
        DatabaseHandler db1 = new DatabaseHandler(this);
        List<Wydarzenie> wydarzeniaList=db1.getWszystkieWydarzeniaPoZawodniczce(wyszukanaZawodniczka.getId());
        db1.close();

        WydarzenieObliczenia wydarzenieObliczenia =new WydarzenieObliczenia();
        String[] wydarzeniaTak = wydarzenieObliczenia.getWydarzeniaTakZawodniczka(wydarzeniaList);
        String[] wydarzeniaNie = wydarzenieObliczenia.getWydarzeniaNieZawodniczka(wydarzeniaList);
        String[] wydarzeniaTbc = wydarzenieObliczenia.getWydarzeniaTbcZawodniczka(wydarzeniaList);

        //wypisanie wszystkich wydarzen, na ktore zapisana jest wybrana zawodniczka
        if(wydarzeniaList.isEmpty())
            Log.d("pusta", "pusta");
        else {
            ArrayAdapter adapterTak = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wydarzeniaTak);
            listaWydarzenTakZawodniczka.setAdapter(adapterTak);

            ArrayAdapter adapterNie = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wydarzeniaNie);
            listaWydarzenNieZawodniczka.setAdapter(adapterNie);

            ArrayAdapter adapterTbc = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wydarzeniaTbc);
            listaWydarzenTbcZawodniczka.setAdapter(adapterTbc);
        }

    }
}
