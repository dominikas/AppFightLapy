package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import DatabaseHandler.*;
import Wydarzenie.*;

public class DodanieWydarzeniaActivity extends AppCompatActivity {

    private TextView dataTV;
    private DatePickerDialog datePickerDialog;
    private TextView time;
    private TimePickerDialog timePickerDialog;
    private EditText miejsceET;
    private EditText cenaET;
    private Spinner typWydarzeniaSpinner;
    private EditText opisET;

    private TextInputLayout miejsceInputLayout;
    private TextInputLayout cenaInputLayout;
    private TextInputLayout tytulInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_wydarzenia);

        miejsceInputLayout = (TextInputLayout) findViewById(R.id.input_layout_miejsce);
        cenaInputLayout = (TextInputLayout) findViewById(R.id.input_layout_cena);
        tytulInputLayout = (TextInputLayout) findViewById(R.id.input_layout_tytul);

        miejsceET = (EditText) findViewById(R.id.miejsce);
        cenaET = (EditText) findViewById(R.id.cena_wydarzenia);
        opisET = (EditText) findViewById(R.id.tytul);
        typWydarzeniaSpinner = (Spinner) findViewById(R.id.spinner_wydarzenie);

        dataTV = (TextView) findViewById(R.id.data);
        dataTV.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                // dataTV picker dialog
                datePickerDialog = new DatePickerDialog(DodanieWydarzeniaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dataTV.setText(dayOfMonth + "."
                                        + (monthOfYear + 1) + "." + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //  initiate the edit text
        time = (TextView) findViewById(R.id.godzina);
        // perform click event listener on edit text
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(DodanieWydarzeniaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Godzina");
                timePickerDialog.show();

            }
        });

        findViewById(R.id.zapisz).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String typWydarzenia = String.valueOf(typWydarzeniaSpinner.getSelectedItem());
                String dataWydarzenia = dataTV.getText().toString();
                String godzinaWydarzenia = time.getText().toString();
                String miejsceWydarzenia = miejsceET.getText().toString();
                String cenaWydarzenia = cenaET.getText().toString();
                String opisWydarzenia = opisET.getText().toString();

                boolean czyOk = czyDaneSaOk(miejsceWydarzenia, opisWydarzenia, cenaWydarzenia, typWydarzenia, dataWydarzenia, godzinaWydarzenia);

                if (czyOk) {
                    zapiszWydarzenie(v);
                }
            }
        });
    }

    private void zapiszWydarzenie(View v) {

        String typWydarzenia = String.valueOf(typWydarzeniaSpinner.getSelectedItem());
        String dataWydarzenia = dataTV.getText().toString();
        String godzinaWydarzenia = time.getText().toString();
        String miejsceWydarzenia = miejsceET.getText().toString();
        String opisWydarzenia = opisET.getText().toString();
        String cenaWydarzenia = cenaET.getText().toString();
        Integer cenaInt = Integer.parseInt(cenaWydarzenia);

        Integer idWydarzenia = jakieToWydarzenie(typWydarzenia);

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        Wydarzenie wydarzenieTestowe = new Wydarzenie(idWydarzenia, dataWydarzenia, godzinaWydarzenia, miejsceWydarzenia, opisWydarzenia, cenaInt);

        db.dodajWydarzenie(wydarzenieTestowe);
        db.close();

        Intent intent = new Intent(this, UdanyZapisWydarzenia.class);
        startActivity(intent);
    }

    //TODO do klasy WalidacjaPol
    private boolean czyPoleOk(String wartoscPola) {
        boolean czyOk = true;
        if (wartoscPola.isEmpty()) {
            czyOk = false;
        } else if (wartoscPola.length() > 50) {
            czyOk = false;
        }

        return czyOk;
    }

    private Integer jakieToWydarzenie(String typWydarzenia) {
        Integer idWydarzenia = 0;

        switch (typWydarzenia) {
            case ("Trening"):
                idWydarzenia = 1;
                break;
            case ("Mecz"):
                idWydarzenia = 2;
                break;
            case ("Inne"):
                idWydarzenia = 3;
                break;
        }

        return idWydarzenia;
    }

    public void addListenerOnSpinnerItemSelection() {
        typWydarzeniaSpinner = (Spinner) findViewById(R.id.spinner_wydarzenie);
        typWydarzeniaSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean czyDaneSaOk(String miejsce, String opis, String cena, String typWydarzenia, String dataWydarzenia, String godzinaWydarzenia){

        boolean czyOk = true;

        if (!czyPoleOk(miejsce)) {
            miejsceInputLayout.setError(getString(R.string.err_msg_miejsce));

            requestFocus(miejsceET);
            czyOk=false;
        }
        else
            miejsceInputLayout.setErrorEnabled(false);

        if (!czyPoleOk(cena)) {
            cenaInputLayout.setError(getString(R.string.err_msg_cena));
            czyOk=false;
        }
        else
            cenaInputLayout.setErrorEnabled(false);

        if (!czyPoleOk(opis)) {
            tytulInputLayout.setError(getString(R.string.err_msg_opis));
            requestFocus(opisET);
            czyOk=false;
        }
        else
            tytulInputLayout.setErrorEnabled(false);

        if(typWydarzenia.isEmpty()){
            //TODO dodać okienko, ze typ jest pusty
            czyOk=false;
        }

        if(dataWydarzenia.isEmpty()){
            //TODO dodać okienko, ze data jest pusta
            czyOk=false;
        }

        if(godzinaWydarzenia.isEmpty()){
            //TODO dodać okienko, ze godzina jest pusta
            czyOk=false;
        }

        return czyOk;
    }
}

