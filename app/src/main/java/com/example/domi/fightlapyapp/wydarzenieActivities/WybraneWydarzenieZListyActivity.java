package com.example.domi.fightlapyapp.wydarzenieActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domi.fightlapyapp.R;
import com.example.domi.fightlapyapp.wydarzenieActivities.ListaZapisanychZawodniczekActivity;

import databaseHandler.DatabaseHandler;
import wydarzenie.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

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

    private void wyszukanieDanychWydarzenia(String opisWydarzenia) {

        //pobranie danych wybranego wydarzenia
        DatabaseHandler db = new DatabaseHandler(this);
        Wydarzenie wybraneWydarzenie = db.getDaneWydarzeniaPoIdWydarzenia(opisWydarzenia);
        db.close();

        Integer typWydarzenia = wybraneWydarzenie.getIdTypuWydarzenia();
        WydarzenieObliczenia wydarzenieObliczenia = new WydarzenieObliczenia();
        String typWydarzeniaString = wydarzenieObliczenia.zamianaIdWydarzeniaNaZnaki(typWydarzenia);

        wybraneWydarzenieTV.setText(wybraneWydarzenie.getOpis());
        rodzajWydarzeniaTV.setText(typWydarzeniaString);
        dataWydarzeniaTV.setText("Data: "+wybraneWydarzenie.getData());

        godzinaWydarzeniaTV.setText("Godzina: "+wybraneWydarzenie.getGodzina().toString());
        miejsceWydarzeniaTV.setText("Miejsce: "+wybraneWydarzenie.getMiejsce());
        cenaWydarzeniaTV.setText("Cena: "+ wybraneWydarzenie.getCena().toString()+" zł");
    }

    public void listaZapianychZawodniczek(View view) {

        Intent intent = new Intent(this, ListaZapisanychZawodniczekActivity.class);
        intent.putExtra("lista_zawodniczek", product);
        startActivity(intent);
    }
}
