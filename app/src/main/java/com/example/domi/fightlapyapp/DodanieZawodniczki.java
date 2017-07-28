package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.view.View;
import android.view.View.OnClickListener;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;
import android.util.Log;

public class DodanieZawodniczki extends AppCompatActivity {
    private Spinner spinner1;
    private EditText imieET;
    private EditText nazwiskoET;
    private EditText numerET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_zawodniczki);
        //addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        imieET = (EditText) findViewById(R.id.imie);
        nazwiskoET = (EditText) findViewById(R.id.nazwisko);
        numerET = (EditText) findViewById(R.id.numer_zawodniczki);

        findViewById(R.id.zapisz).setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {
                int czyOk=0;

                final String imie_zawodniczki = imieET.getText().toString();

                if (!czyImieLubNazwiskoOK(imie_zawodniczki)) {
                    imieET.setError("Błędna wartość w polu imie");
                    czyOk++;
                    }

                final String nazwisko_zawodniczki = nazwiskoET.getText().toString();
                if (!czyImieLubNazwiskoOK(nazwisko_zawodniczki)) {
                    nazwiskoET.setError("Błędna wartość w polu nazwisko");
                    czyOk++;
                }

                final String numer_zawodniczki = numerET.getText().toString();
                if (!czyNumerOK(numer_zawodniczki)) {
                    numerET.setError("Błędna wartość w polu numer");
                    czyOk++;
                }

                if(czyOk==0) {
                    zapisz_zawodniczke(v);
                }
            }


        });
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner_pozycja);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private boolean czyImieLubNazwiskoOK(String imieLubNazwisko_zawodniczki)
    {
        String IMIENAZWISKO_PATTERN = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(IMIENAZWISKO_PATTERN);
        Matcher matcher = pattern.matcher(imieLubNazwisko_zawodniczki);
        return matcher.matches();
    }

    private boolean czyNumerOK(String numer_zawodniczki)
    {
        String NUMER_PATTERN = "[0-9]+";

        Pattern pattern = Pattern.compile(NUMER_PATTERN);
        Matcher matcher = pattern.matcher(numer_zawodniczki);
        return matcher.matches();
    }

    private void zapisz_zawodniczke(View view) {

        String imie_zawodniczki = imieET.getText().toString();
        String nazwisko_zawodniczki = nazwiskoET.getText().toString();
        String numer_zawodniczki = numerET.getText().toString();
        Integer numerInt = Integer.parseInt(numer_zawodniczki);
        String pozycja_zawodniczki = String.valueOf(spinner1.getSelectedItem());

        Integer idPozycji = jakaToPozycja(pozycja_zawodniczki);

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        Zawodniczka zawodniczkaTestowa = new Zawodniczka(imie_zawodniczki, nazwisko_zawodniczki, idPozycji, numerInt);
        db.dodajZawodniczke(zawodniczkaTestowa);
        db.close();
        /*
        Log.d("Reading: ", "Reading all contacts..");
        List<Zawodniczka> zawodniczki = db.getWszystkieZawodniczki();
        int ostatnia=zawodniczki.size();
        Zawodniczka zaw = zawodniczki.get(ostatnia-1);
        //for (Zawodniczka zaw : zawodniczki) {
            String log = "Id: " + zaw.get_id() + " ,Name: " + zaw.get_imie() + " ,Nazwisko: " + zaw.get_nazwisko()+" ,id pozycji "+zaw.get_id_pozycji()+" ,numer "+zaw.get_numer();
            Log.d("Ostatnia: ", log);
            db.close();
            */
        //}

        Intent intent = new Intent(this, udany_zapis_zawodniczki.class);
        startActivity(intent);
    }

    private Integer jakaToPozycja(String pozycja)
    {
        Integer idPozycji=0;

        switch(pozycja){
            case ("Rozegranie"):
                idPozycji=1;
                break;
            case("Atak"):
                idPozycji=2;
                break;
            case("Przyjęcie"):
                idPozycji=3;
                break;
            case("Środek"):
                idPozycji=4;
                break;
            case("Libero"):
                idPozycji=5;
                break;
        }

        return idPozycji;
    }

    private boolean czyWszystkoOK(Integer licznik)
    {
        boolean czOk=true;
        if(licznik!=0)
            czOk=false;

        return czOk;
    }
}
