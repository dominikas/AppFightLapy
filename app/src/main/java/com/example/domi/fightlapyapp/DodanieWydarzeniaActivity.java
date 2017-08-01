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

import java.util.ArrayList;
import java.util.Calendar;

import DatabaseHandler.*;
import Wydarzenie.*;

public class DodanieWydarzeniaActivity extends AppCompatActivity {

    private TextView date;
    private DatePickerDialog datePickerDialog;
    private TextView time;
    private TimePickerDialog timePickerDialog;
    private EditText miejsceET;
    private EditText cenaET;
    private Spinner typWydarzeniaSpinner;
    private EditText opisET;

    private TextInputLayout inputLayoutMiejsce;
    private TextInputLayout inputLayoutCena;
    private TextInputLayout inputLayoutTytul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_wydarzenia);

        inputLayoutMiejsce = (TextInputLayout) findViewById(R.id.input_layout_miejsce);
        inputLayoutCena = (TextInputLayout) findViewById(R.id.input_layout_cena);
        inputLayoutTytul = (TextInputLayout) findViewById(R.id.input_layout_tytul);

        miejsceET = (EditText) findViewById(R.id.miejsce);
        cenaET = (EditText) findViewById(R.id.cena_wydarzenia);
        opisET = (EditText) findViewById(R.id.tytul);
        typWydarzeniaSpinner = (Spinner) findViewById(R.id.spinner_wydarzenie);

        date = (TextView) findViewById(R.id.data);
        date.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(DodanieWydarzeniaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "."
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
                String dataWydarzenia =date.getText().toString();
                String godzinaWydarzenia = time.getText().toString();
                String miejsceWydarzenia = miejsceET.getText().toString();
                String opisWydarzenia = opisET.getText().toString();
                String cenaWydarzenia = cenaET.getText().toString();

                int czyOk = 0;

                final String miejsce = miejsceET.getText().toString();

                if (!czyPoleOk(miejsce)) {
                    inputLayoutMiejsce.setError("Błedna wartość w polu miejsce");
                    //miejsceET.setError("Błędna wartość w polu miejsce");
                    requestFocus(miejsceET);
                    czyOk++;
                }
                else
                    inputLayoutMiejsce.setErrorEnabled(false);

                final String cena = cenaET.getText().toString();
                if (!czyPoleOk(cena)) {
                    inputLayoutCena.setError("Błędna wartość w polu cena");
                    //cenaET.setError("Błędna wartość w polu cena");
                    czyOk++;
                }
                else
                    inputLayoutCena.setErrorEnabled(false);

                final String opis = opisET.getText().toString();
                if (!czyPoleOk(opis)) {
                    inputLayoutTytul.setError("Błedna wartość w polu opis");
                    //opisET.setError("Błędna wartość w polu opis");
                    requestFocus(opisET);
                    czyOk++;
                }
                else
                    inputLayoutTytul.setErrorEnabled(false);

                if (czyOk == 0) {
                    zapiszWydarzenie(v);
                    Log.d("id wydarzenia ", typWydarzenia);
                    Log.d("Data", dataWydarzenia);
                    Log.d("Godzina ", godzinaWydarzenia);
                    Log.d("Miejsce ", miejsceWydarzenia);
                    Log.d("Opis ", opisWydarzenia);
                    Log.d("Cena ", cenaWydarzenia);
                }

            }


        });
    }

    private void zapiszWydarzenie(View v) {

        String typWydarzenia = String.valueOf(typWydarzeniaSpinner.getSelectedItem());
        String dataWydarzenia = date.getText().toString();
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

        Intent intent = new Intent(this, Udany_zapis_wydarzenia.class);
        startActivity(intent);
    }

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
}

