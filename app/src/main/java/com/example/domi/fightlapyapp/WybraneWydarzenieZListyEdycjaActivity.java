package com.example.domi.fightlapyapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import DatabaseHandler.DatabaseHandler;
import Wydarzenie.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class WybraneWydarzenieZListyEdycjaActivity extends AppCompatActivity {

    private TextView date;
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

    private String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrane_wydarzenie_zlisty_edycja);

        miejsceInputLayout = (TextInputLayout) findViewById(R.id.input_layout_miejsce_edycja);
        cenaInputLayout = (TextInputLayout) findViewById(R.id.input_layout_cena_edycja);
        tytulInputLayout = (TextInputLayout) findViewById(R.id.input_layout_tytul_edycja);

        miejsceET = (EditText) findViewById(R.id.miejsce_edycja);
        cenaET = (EditText) findViewById(R.id.cena_wydarzenia_edycja);
        opisET = (EditText) findViewById(R.id.tytul_edycja);
        typWydarzeniaSpinner = (Spinner) findViewById(R.id.spinner_wydarzenie_edycja);

        date = (TextView) findViewById(R.id.data_edycja);

        Intent i = getIntent();
        product = i.getStringExtra("wydarzenie");
        Wydarzenie wybraneWydarzenie=wyszukanieDanychWydarzenia(product);

        date.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(WybraneWydarzenieZListyEdycjaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "."
                                        + (monthOfYear + 1) + "." + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        time = (TextView) findViewById(R.id.godzina_edycja);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(WybraneWydarzenieZListyEdycjaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("Godzina");
                timePickerDialog.show();

            }
        });

        findViewById(R.id.zapisz_edycja).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String rodzajWydarzenia=String.valueOf(typWydarzeniaSpinner.getSelectedItem());
                WydarzenieObliczenia wydarzenieObliczenia = new WydarzenieObliczenia();
                //Integer typWydarzenia = wydarzenieObliczenia.zmianaWydarzeniaStringNaId(rodzajWydarzenia);

                boolean czyOk = czyDaneWydarzeniaSaOk();

                if (czyOk) {
                    zapisEdytowanegoWydarzenia(v);
                }
            }
        });
    }

    private Wydarzenie wyszukanieDanychWydarzenia(String idWydarzenia) {

        //pobranie danych wybranego wydarzenia
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Wybrane wydarzenie id", idWydarzenia);
        Wydarzenie wybraneWydarzenie = db.getDaneWydarzeniaPoIdWydarzenia(idWydarzenia);
        db.close();

        miejsceET.setText(wybraneWydarzenie.getMiejsce().toString());
        miejsceET.setSelection(miejsceET.getText().length());

        cenaET.setText(wybraneWydarzenie.getCena().toString());
        cenaET.setSelection(cenaET.getText().length());

        opisET.setText(wybraneWydarzenie.getOpis().toString());
        opisET.setSelection(opisET.getText().length());

        Integer typWydarzenia = wybraneWydarzenie.getIdTypuWydarzenia();

        typWydarzeniaSpinner.setSelection(typWydarzenia-1);

        date.setText(wybraneWydarzenie.getData().toString());

        Log.i("Godzina ",wybraneWydarzenie.getGodzina().toString());

        return wybraneWydarzenie;

    }

    private void zapisEdytowanegoWydarzenia(View v){

        String rodzajWydarzeniaNew=String.valueOf(typWydarzeniaSpinner.getSelectedItem());
        WydarzenieObliczenia wydarzenieObliczenia = new WydarzenieObliczenia();
        Integer typWydarzenia = wydarzenieObliczenia.zmianaWydarzeniaStringNaId(rodzajWydarzeniaNew);

        String dataWydarzeniaNew=date.getText().toString();
        String miejsceWydarzenieNew = miejsceET.getText().toString();
        Integer cenaWydarzeniaNew= Integer.valueOf(cenaET.getText().toString());
        String opisWydarzeniaNew=opisET.getText().toString();
        String godzineWydarzeniaNew=time.getText().toString();

        //pobranie danych wybranego wydarzenia
        DatabaseHandler db = new DatabaseHandler(this);
        Wydarzenie edytowaneWydarzenie = new Wydarzenie();

        edytowaneWydarzenie.setIdWydarzenia(Integer.valueOf(product));
        edytowaneWydarzenie.setIdTypuWydarzenia(typWydarzenia);
        edytowaneWydarzenie.setData(dataWydarzeniaNew);
        edytowaneWydarzenie.setMiejsce(miejsceWydarzenieNew);
        edytowaneWydarzenie.setCena(cenaWydarzeniaNew);
        edytowaneWydarzenie.setOpis(opisWydarzeniaNew);
        edytowaneWydarzenie.setGodzina(godzineWydarzeniaNew);

        db.updateWydarzenie(edytowaneWydarzenie);
        db.close();

        Intent intent = new Intent(this, UdanyEdycjaWydarzeniaActivity.class);
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean czyDaneWydarzeniaSaOk(){

        boolean czyOk=true;

        final String miejsce = miejsceET.getText().toString();
        if (!czyPoleOk(miejsce)) {
            miejsceInputLayout.setError(getString(R.string.err_msg_miejsce));
            requestFocus(miejsceET);
            czyOk=false;
        }
        else
            miejsceInputLayout.setErrorEnabled(false);

        final String cena = cenaET.getText().toString();
        if (!czyPoleOk(cena)) {
            cenaInputLayout.setError(getString(R.string.err_msg_cena));
            czyOk=false;
        }
        else
            cenaInputLayout.setErrorEnabled(false);

        final String opis = opisET.getText().toString();
        if (!czyPoleOk(opis)) {
            tytulInputLayout.setError(getString(R.string.err_msg_opis));
            requestFocus(opisET);
            czyOk=false;
        }
        else
            tytulInputLayout.setErrorEnabled(false);

        return czyOk;
    }
}
