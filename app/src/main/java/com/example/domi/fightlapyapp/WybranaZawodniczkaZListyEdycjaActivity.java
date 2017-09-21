package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DatabaseHandler.DatabaseHandler;
import Wydarzenie.Wydarzenie;
import Zawodniczka.Zawodniczka;

public class WybranaZawodniczkaZListyEdycjaActivity extends AppCompatActivity {

    private EditText imieZawodniczkiEdycjaET;
    private EditText nazwiskoZawodniczkiEdycjaET;
    private String product=new String();
    private EditText numerEdycjaET;
    private EditText idPozycjiEdycjaET;
    private String idZawodniczki;

    private TextInputLayout inputLayoutImie, inputLayoutNazwisko, inputLayoutNumer;
    private Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrana_zawodniczka_zlisty_edycja);
        inputLayoutImie = (TextInputLayout) findViewById(R.id.input_layout_imie);
        inputLayoutNazwisko=(TextInputLayout) findViewById(R.id.input_layout_nazwisko);
        inputLayoutNumer=(TextInputLayout) findViewById(R.id.input_layout_numer);

        imieZawodniczkiEdycjaET = (EditText) findViewById(R.id.imie_edycja);
        nazwiskoZawodniczkiEdycjaET =(EditText) findViewById(R.id.nazwisko_edycja);
        //idPozycjiEdycjaET =(EditText) findViewById(R.id.idPozycji_edycja);
        numerEdycjaET =(EditText) findViewById(R.id.numer_zawodniczki) ;
        inputLayoutImie = (TextInputLayout) findViewById(R.id.input_layout_imie_edycja);
        inputLayoutNazwisko=(TextInputLayout) findViewById(R.id.input_layout_nazwisko_edycja);
        inputLayoutNumer=(TextInputLayout) findViewById(R.id.input_layout_numer);

        Intent i = getIntent();
        // getting attached intent data
        product = i.getStringExtra("imie i nazwisko");
        // displaying selected product name

        //idZawodniczki.setText(product);
        final int indeksZaw=wyszukanieDanychZawodniczki(product);

        addListenerOnSpinnerItemSelection();

        findViewById(R.id.zapisz_edycja_zawodniczka).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                int czyOk=0;

                final String imie_zawodniczki = imieZawodniczkiEdycjaET.getText().toString();

                if (!czyImieLubNazwiskoOK(imie_zawodniczki)) {
                    imieZawodniczkiEdycjaET.setError("Błędna wartość w polu imię");
                    requestFocus(imieZawodniczkiEdycjaET);
                    czyOk++;
                }
                else
                    inputLayoutImie.setErrorEnabled(false);


                final String nazwisko_zawodniczki = nazwiskoZawodniczkiEdycjaET.getText().toString();
                if (!czyImieLubNazwiskoOK(nazwisko_zawodniczki)) {
                    nazwiskoZawodniczkiEdycjaET.setError(getString(R.string.err_msg_nazwisko));
                    requestFocus(nazwiskoZawodniczkiEdycjaET);
                    czyOk++;
                }
                else
                    inputLayoutNazwisko.setErrorEnabled(false);

                final String numer_zawodniczki = numerEdycjaET.getText().toString();
                if (!czyNumerOK(numer_zawodniczki)) {
                    //numerET.setError("Błędna wartość w polu numer");
                    inputLayoutNumer.setError(getString(R.string.err_msg_numer));
                    requestFocus(numerEdycjaET);
                    czyOk++;
                }
                else
                    inputLayoutNumer.setEnabled(false);

                if(czyOk==0) {
                    zapisDanychEdytowanejZawodniczki(v, indeksZaw);
                }
            }


        });

    }

    private int wyszukanieDanychZawodniczki(String product){
        Integer idZaw=666;
        Integer indeksZaw=666;
        //Log.d("Co zostalo przeniesione", indeksZaw.toString());

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        //List<Wydarzenie> wydarzeniaList = new ArrayList<>();

        db.close();

        for(Zawodniczka zaw:zawodniczkiList)
        {
            if((zaw.get_imie()+" "+zaw.get_nazwisko()).equals(product)) {
            //if((zaw.get_id().toString().equals(product))) {
                idZaw = zaw.get_id();
                indeksZaw=zawodniczkiList.indexOf(zaw);
            }
        }

        Zawodniczka edytowanaZawodniczka= zawodniczkiList.get(indeksZaw);

        imieZawodniczkiEdycjaET.setText(edytowanaZawodniczka.get_imie());
        imieZawodniczkiEdycjaET.setSelection(imieZawodniczkiEdycjaET.getText().length());

        nazwiskoZawodniczkiEdycjaET.setText(edytowanaZawodniczka.get_nazwisko());
        nazwiskoZawodniczkiEdycjaET.setSelection(nazwiskoZawodniczkiEdycjaET.getText().length());

        numerEdycjaET.setText(edytowanaZawodniczka.get_numer().toString());
        numerEdycjaET.setSelection(numerEdycjaET.getText().length());

        Integer pozycjaInt=edytowanaZawodniczka.get_id_pozycji();
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

        return idZaw;
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner_pozycja);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private boolean czyImieLubNazwiskoOK(String imieLubNazwisko_zawodniczki) {
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

    private void zapisDanychEdytowanejZawodniczki(View view, Integer idZaw) {

        String imie_zawodniczki = imieZawodniczkiEdycjaET.getText().toString();
        String nazwisko_zawodniczki = nazwiskoZawodniczkiEdycjaET.getText().toString();
        String numer_zawodniczki = numerEdycjaET.getText().toString();
        Integer numerInt = Integer.parseInt(numer_zawodniczki);
        String pozycja_zawodniczki = String.valueOf(spinner1.getSelectedItem());
        String idZawodniczki = product;

        Integer idPozycji = jakaToPozycja(pozycja_zawodniczki);

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        Zawodniczka edytowanaZawodniczka = new Zawodniczka();
        edytowanaZawodniczka.set_id(idZaw);
        Log.d("Indeks zmienianej : ", idZaw.toString());
        edytowanaZawodniczka.set_imie(imie_zawodniczki);
        edytowanaZawodniczka.set_nazwisko(nazwisko_zawodniczki);
        edytowanaZawodniczka.set_numer(numerInt);
        edytowanaZawodniczka.set_id_pozycji(idPozycji);

        db.updateZawodniczka(edytowanaZawodniczka);

        db.close();

        Intent intent = new Intent(this, UdanaEdycjaZawodniczkiActivity.class);
        startActivity(intent);

    }

    private Integer jakaToPozycja(String pozycja) {
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

    private boolean czyWszystkoOK(Integer licznik) {
        boolean czOk=true;
        if(licznik!=0)
            czOk=false;

        return czOk;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    }

