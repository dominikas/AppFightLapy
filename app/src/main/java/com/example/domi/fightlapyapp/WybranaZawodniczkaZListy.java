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
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrana_zawodniczka_zlisty);
        imieInaziwkoZawodniczki = (TextView) findViewById(R.id.wybrana_zaw_z_listy);
        numerTV=(TextView) findViewById(R.id.numerZawodniczki) ;
        idPozycjiTV=(TextView) findViewById(R.id.idPozycji);
        idZawodniczkiTV =(TextView) findViewById(R.id.idZawodniczki);
        idWydarzeniaTV=(TextView) findViewById(R.id.idWydarzenia);
        mListView = (ListView) findViewById(R.id.lista_wydarzen_zawodniczki);

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

        // wydarzenie - wyszukanie  z tabeli zawodniczek_wydarzen id kliknietej zawodniczki i pobranie wydarzen, na ktore jest zapisana
        DatabaseHandler db1 = new DatabaseHandler(this);
        //idWydarzeniaTV.setText((db1.getIdWyd(idZaw)).toString());
        Log.d("id zawodniczki", idZaw.toString());

        wydarzeniaList=db1.getWszystkieWydarzeniaPoZawodniczce(idZaw);
        String[] listItems = new String[wydarzeniaList.size()];
        db1.close();

        for (Wydarzenie wyd : wydarzeniaList){
  //          int indeks = wydarzeniaList.indexOf(wyd);
//            Integer indeks1 = (Integer) indeks;
            listItems[wydarzeniaList.indexOf(wyd)] = wyd.get_opis()+" "+wyd.get_data();
        }

        //wypisanie wszystkich wydarzen, na ktore zapisana jest wybrana zawodniczka
        if(wydarzeniaList.isEmpty())
            Log.d("pusta", "pusta");
        else {
            //idwydarzenia do usuniecia
            idWydarzeniaTV.setText(wydarzeniaList.get(wydarzeniaList.size() - 1).get_opis());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
            mListView.setAdapter(adapter);
        }

    }
}