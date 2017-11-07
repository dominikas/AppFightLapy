package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

import android.view.View;
import android.view.View.OnClickListener;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class DodanieZawodniczkiActivity extends AppCompatActivity {

    private Spinner spinner1;
    private EditText imieET;
    private EditText nazwiskoET;
    private EditText numerET;
    private TextInputLayout imieInputLayout, nazwiskoInputLayout, numerInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_zawodniczki);

        imieInputLayout = (TextInputLayout) findViewById(R.id.input_layout_imie);
        nazwiskoInputLayout =(TextInputLayout) findViewById(R.id.input_layout_nazwisko);
        numerInputLayout =(TextInputLayout) findViewById(R.id.input_layout_numer);

        imieET = (EditText) findViewById(R.id.imie);
        nazwiskoET = (EditText) findViewById(R.id.nazwisko);
        numerET = (EditText) findViewById(R.id.numer_zawodniczki);

        addListenerOnSpinnerItemSelection();

        findViewById(R.id.zapisz).setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                String imie = imieET.getText().toString();
                String nazwisko = nazwiskoET.getText().toString();
                String numerZawodniczki = numerET.getText().toString();

                boolean czyOk=czyDaneSaOk(imie, nazwisko, numerZawodniczki);

                if(czyOk) {
                    zapiszZawodniczke(v);
                }
            }
        });
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner_pozycja);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private void zapiszZawodniczke(View view) {

        Zawodniczka zawodniczka = new Zawodniczka();
        String imieZawodniczki = imieET.getText().toString();
        String nazwiskoZawodniczki = nazwiskoET.getText().toString();
        String numerZawodniczki = numerET.getText().toString();
        Integer numerInt = Integer.parseInt(numerZawodniczki);
        String pozycjaZawodniczki = String.valueOf(spinner1.getSelectedItem());
        Integer idPozycji = zawodniczka.jakaToPozycja(pozycjaZawodniczki);
        DatabaseHandler db = new DatabaseHandler(this);

        zawodniczka.setImie(imieZawodniczki);
        zawodniczka.setNazwisko(nazwiskoZawodniczki);
        zawodniczka.setNumer(numerInt);
        zawodniczka.setIdPozycji(idPozycji);

        db.dodajZawodniczke(zawodniczka);
        db.close();

        Intent intent = new Intent(this, UdanyZapisZawodniczki.class);
        startActivity(intent);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean czyDaneSaOk(String imie, String nazwisko, String numer){

        boolean czyOk=true;
        WalidacjaDanychZawodniczki walidacjaDanychZawodniczki=new WalidacjaDanychZawodniczki();

        if (!walidacjaDanychZawodniczki.czyImieLubNazwiskoOK(imie) || imie.isEmpty()) {
            imieInputLayout.setError(getString(R.string.err_msg_imie));
            requestFocus(imieET);
            czyOk=false;
        }
        else
            imieInputLayout.setErrorEnabled(false);

        if (!walidacjaDanychZawodniczki.czyImieLubNazwiskoOK(nazwisko) || nazwisko.isEmpty()) {
            nazwiskoInputLayout.setError(getString(R.string.err_msg_nazwisko));
            requestFocus(nazwiskoET);
            czyOk=false;
        }
        else
            nazwiskoInputLayout.setErrorEnabled(false);

        if (!walidacjaDanychZawodniczki.czyNumerOK(numer) || numer.isEmpty()) {
            numerInputLayout.setError(getString(R.string.err_msg_numer));
            requestFocus(numerET);
            czyOk=false;
        }
        else
            numerInputLayout.setEnabled(false);

        return czyOk;
    }
}
