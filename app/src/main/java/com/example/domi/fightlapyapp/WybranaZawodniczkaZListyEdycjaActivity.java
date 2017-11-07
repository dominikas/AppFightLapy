package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class WybranaZawodniczkaZListyEdycjaActivity extends AppCompatActivity {

    private EditText imieEdycjaET;
    private EditText nazwiskoEdycjaET;
    private String product=new String();
    private EditText numerEdycjaET;
    private Spinner pozycjaEdycjaET;
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

        imieEdycjaET = (EditText) findViewById(R.id.imie_edycja);
        nazwiskoEdycjaET =(EditText) findViewById(R.id.nazwisko_edycja);
        pozycjaEdycjaET =(Spinner) findViewById(R.id.spinner_pozycja);
        numerEdycjaET =(EditText) findViewById(R.id.numer_zawodniczki) ;
        inputLayoutImie = (TextInputLayout) findViewById(R.id.input_layout_imie_edycja);
        inputLayoutNazwisko=(TextInputLayout) findViewById(R.id.input_layout_nazwisko_edycja);
        inputLayoutNumer=(TextInputLayout) findViewById(R.id.input_layout_numer);

        Intent i = getIntent();
        product = i.getStringExtra("imie i nazwisko");

        final Zawodniczka edytowanaZawodniczka=wyszukanieDanychZawodniczki(product);

        addListenerOnSpinnerItemSelection();

        findViewById(R.id.zapisz_edycja_zawodniczka).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                final String imieZawodniczki = imieEdycjaET.getText().toString();
                final String nazwiskoZawodniczki = nazwiskoEdycjaET.getText().toString();
                final String numerZawodniczki = numerEdycjaET.getText().toString();

                boolean czyOk= czyDaneZawodniczkiSaOk(imieZawodniczki, nazwiskoZawodniczki, numerZawodniczki);

                if(czyOk) {
                    Log.d("Zapisuje dane zaw id ", Integer.valueOf(edytowanaZawodniczka.getId()).toString());
                    zapisDanychEdytowanejZawodniczki(v, edytowanaZawodniczka.getId());
                }
            }

        });

    }

    private Zawodniczka wyszukanieDanychZawodniczki(String product){

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();

        db.close();
        ZawodniczkaObliczenia zawodniczkaObliczenia = new ZawodniczkaObliczenia();
        Zawodniczka edytowanaZawodniczka=zawodniczkaObliczenia.wyszukanieZawodniczkiZListy(zawodniczkiList,product);

        imieEdycjaET.setText(edytowanaZawodniczka.getImie());
        imieEdycjaET.setSelection(imieEdycjaET.getText().length());

        nazwiskoEdycjaET.setText(edytowanaZawodniczka.getNazwisko());
        nazwiskoEdycjaET.setSelection(nazwiskoEdycjaET.getText().length());

        numerEdycjaET.setText(edytowanaZawodniczka.getNumer().toString());
        numerEdycjaET.setSelection(numerEdycjaET.getText().length());

        Integer pozycjaInt=edytowanaZawodniczka.getIdPozycji();
        String pozycjaString=zawodniczkaObliczenia.zamianaIdPozycjiNaZnaki(pozycjaInt);

        //TODO spinner sprawdzic
        //spinner1.setSelection(pozycjaInt);

        return edytowanaZawodniczka;
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner_pozycja);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public boolean czyDaneZawodniczkiSaOk(String imieZawodniczki, String nazwiskoZawodniczki, String numerZawodniczki){

        boolean czyOk=true;
        WalidacjaDanychZawodniczki walidacjaDanychZawodniczki=new WalidacjaDanychZawodniczki();

        if (!walidacjaDanychZawodniczki.czyImieLubNazwiskoOK(imieZawodniczki) || imieZawodniczki.isEmpty()) {
            imieEdycjaET.setError(getString(R.string.err_msg_imie));
            requestFocus(imieEdycjaET);
            czyOk=false;
        }
        else
            inputLayoutImie.setErrorEnabled(false);

        if (!walidacjaDanychZawodniczki.czyImieLubNazwiskoOK(nazwiskoZawodniczki) || nazwiskoZawodniczki.isEmpty()) {
            nazwiskoEdycjaET.setError(getString(R.string.err_msg_nazwisko));
            requestFocus(nazwiskoEdycjaET);
            czyOk=false;
        }
        else
            inputLayoutNazwisko.setErrorEnabled(false);

        if (!walidacjaDanychZawodniczki.czyNumerOK(numerZawodniczki) || numerZawodniczki.isEmpty()) {
            inputLayoutNumer.setError(getString(R.string.err_msg_numer));
            requestFocus(numerEdycjaET);
            czyOk=false;
        }
        else
            inputLayoutNumer.setEnabled(false);

        return czyOk;
    }

    private void zapisDanychEdytowanejZawodniczki(View view, Integer idZaw) {

        String imieZawodniczki = imieEdycjaET.getText().toString();
        String nazwiskoZawodniczki = nazwiskoEdycjaET.getText().toString();
        String numerZawodniczki = numerEdycjaET.getText().toString();
        Integer numerInt = Integer.parseInt(numerZawodniczki);
        String pozycjaZawodniczki = String.valueOf(spinner1.getSelectedItem());
        String idZawodniczki = product;

        ZawodniczkaObliczenia zawodniczkaObliczenia=new ZawodniczkaObliczenia();
        Integer idPozycji = zawodniczkaObliczenia.zmianaPozycjiStringNaId(pozycjaZawodniczki);

        DatabaseHandler db = new DatabaseHandler(this);
        Zawodniczka edytowanaZawodniczka = new Zawodniczka();
        edytowanaZawodniczka.setId(idZaw);
        edytowanaZawodniczka.setImie(imieZawodniczki);
        edytowanaZawodniczka.setNazwisko(nazwiskoZawodniczki);
        edytowanaZawodniczka.setNumer(numerInt);
        edytowanaZawodniczka.setIdPozycji(idPozycji);
        db.updateZawodniczka(edytowanaZawodniczka);

        db.close();

        Intent intent = new Intent(this, UdanaEdycjaZawodniczkiActivity.class);
        startActivity(intent);

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}

