package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import DatabaseHandler.DatabaseHandler;
import Wydarzenie.Wydarzenie;
import Zawodniczka.Zawodniczka;

public class WybraneWydarzenieZListyActivity extends AppCompatActivity {

    private TextView wybraneWydarzenieTV;
    private TextView rodzajWydarzeniaTV;
    private TextView dataWydarzeniaTV;
    private ListView listaZawodniczekTV;
    //private TextView opisWydarzeniaTV;
    private TextView cenaWydarzeniaTV;
    private TextView godzinaWydarzeniaTV;
    private TextView miejsceWydarzeniaTV;

    private String product;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrane_wydarzenie_zlisty);

        wybraneWydarzenieTV=(TextView) findViewById(R.id.wybrane_wyd_z_listy);
        rodzajWydarzeniaTV=(TextView) findViewById(R.id.rodzaj_wydarzenie);
        dataWydarzeniaTV=(TextView) findViewById(R.id.data_wydarzenia);
        //listaZawodniczekTV=(ListView) findViewById(R.id.lista_zapisanych_zawodniczek);
        //opisWydarzeniaTV=(TextView) findViewById(R.id.tytul);
        cenaWydarzeniaTV=(TextView) findViewById(R.id.cena_wydarzenia);
        godzinaWydarzeniaTV=(TextView) findViewById(R.id.godzina) ;
        miejsceWydarzeniaTV=(TextView) findViewById(R.id.miejsce);

        product=new String();

        //pobranie danych wybranego wydarzenia, ktore chcemu przedstawic
        Intent i = getIntent();
        product = i.getStringExtra("wydarzenie");
        Log.d("wybrane wydarzenie ", product);
        wyszukanieDanychWydarzenia(product);

    }

    private void wyszukanieDanychWydarzenia(String idWydarzenia) {

        //pobranie danych wybranego wydarzenia
        DatabaseHandler db = new DatabaseHandler(this);
        Wydarzenie wybraneWydarzenie = db.getDaneWydarzeniaPoIdWydarzenia(idWydarzenia);
        db.close();

        wybraneWydarzenieTV.setText(wybraneWydarzenie.get_opis());

        Integer typWydarzenia = wybraneWydarzenie.get_id_typu_wydarzenia();
        String typWydarzeniaString = new String();

        switch (typWydarzenia) {
            case (1):
                typWydarzeniaString = "Trening";
                break;
            case (2):
                typWydarzeniaString = "Mecz";
                break;
            case (3):
                typWydarzeniaString = "Inne";
                break;

        }
        rodzajWydarzeniaTV.setText(typWydarzeniaString);
        dataWydarzeniaTV.setText(wybraneWydarzenie.get_data());

        godzinaWydarzeniaTV.setText(wybraneWydarzenie.get_godzina().toString());
        miejsceWydarzeniaTV.setText(wybraneWydarzenie.get_miejsce());
        cenaWydarzeniaTV.setText(wybraneWydarzenie.get_cena().toString());
    }

    public void listaZapianychZawodniczek(View view) {

        Intent intent = new Intent(this, ListaZapisanychZawodniczek.class);
        intent.putExtra("lista_zawodniczek", product);
        startActivity(intent);
    }
}
