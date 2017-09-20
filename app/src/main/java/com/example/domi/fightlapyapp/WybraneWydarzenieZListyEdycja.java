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
import java.util.List;

import DatabaseHandler.DatabaseHandler;
import Wydarzenie.Wydarzenie;
import Zawodniczka.Zawodniczka;

public class WybraneWydarzenieZListyEdycja extends AppCompatActivity {

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

    private String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybrane_wydarzenie_zlisty_edycja);

        inputLayoutMiejsce = (TextInputLayout) findViewById(R.id.input_layout_miejsce_edycja);
        inputLayoutCena = (TextInputLayout) findViewById(R.id.input_layout_cena_edycja);
        inputLayoutTytul = (TextInputLayout) findViewById(R.id.input_layout_tytul_edycja);

        miejsceET = (EditText) findViewById(R.id.miejsce_edycja);
        cenaET = (EditText) findViewById(R.id.cena_wydarzenia_edycja);
        opisET = (EditText) findViewById(R.id.tytul_edycja);
        typWydarzeniaSpinner = (Spinner) findViewById(R.id.spinner_wydarzenie_edycja);

        date = (TextView) findViewById(R.id.data_edycja);

        Intent i = getIntent();
        product = i.getStringExtra("wydarzenie_edycja");
        wyszukanieDanychWydarzenia(product);

        date.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(WybraneWydarzenieZListyEdycja.this,
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
        time = (TextView) findViewById(R.id.godzina_edycja);
        // perform click event listener on edit text
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(WybraneWydarzenieZListyEdycja.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Godzina");
                timePickerDialog.show();

            }
        });
//TO DO odkomentowac, gdy ma zadzialac zapis

        findViewById(R.id.zapisz_edycja).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String rodzajWydarzeniaNew=String.valueOf(typWydarzeniaSpinner.getSelectedItem());
                Integer typWydarzenia = jakieToWydarzenie(rodzajWydarzeniaNew);

                String dataWydarzeniaNew=date.getText().toString();
                String miejsceWydarzenieNew = miejsceET.getText().toString();
                Integer cenaWydarzeniaNew= Integer.valueOf(cenaET.getText().toString());
                String opisWydarzeniaNew=opisET.getText().toString();

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
                    zapisEdytowanegoWydarzenia(v);

                }
            }
        });

    }

    private void wyszukanieDanychWydarzenia(String idWydarzenia) {
        Log.d("szukam"," szukam");
        //pobranie danych wybranego wydarzenia
        DatabaseHandler db = new DatabaseHandler(this);
        Wydarzenie wybraneWydarzenie = db.getDaneWydarzeniaPoIdWydarzenia(idWydarzenia);
        db.close();

        miejsceET.setText(wybraneWydarzenie.get_miejsce().toString());
        miejsceET.setSelection(miejsceET.getText().length());

        cenaET.setText(wybraneWydarzenie.get_cena().toString());
        cenaET.setSelection(cenaET.getText().length());

        opisET.setText(wybraneWydarzenie.get_opis().toString());
        opisET.setSelection(opisET.getText().length());

        Integer typWydarzenia = wybraneWydarzenie.get_id_typu_wydarzenia();
        typWydarzeniaSpinner.setSelection(typWydarzenia);

        date.setText(wybraneWydarzenie.get_data().toString());

        Log.i("Godzina ",wybraneWydarzenie.get_godzina().toString());
        //TO DO poorawic uzupelnianie godziny aktualnej wydarzenia
        //time.setText(wybraneWydarzenie.get_godzina().toString());

    }

    private void zapisEdytowanegoWydarzenia(View v){

//        String wybraneWydarzenieNew=wybraneWydarzenieET.getText().toString();

        String rodzajWydarzeniaNew=String.valueOf(typWydarzeniaSpinner.getSelectedItem());
        Integer typWydarzenia = jakieToWydarzenie(rodzajWydarzeniaNew);

        String dataWydarzeniaNew=date.getText().toString();
        String miejsceWydarzenieNew = miejsceET.getText().toString();
        Integer cenaWydarzeniaNew= Integer.valueOf(cenaET.getText().toString());
        String opisWydarzeniaNew=opisET.getText().toString();
        String godzineWydarzeniaNew=time.getText().toString();

        //pobranie danych wybranego wydarzenia
        DatabaseHandler db = new DatabaseHandler(this);
        Wydarzenie edytowaneWydarzenie = new Wydarzenie();

        edytowaneWydarzenie.set_id_wydarzenia(Integer.valueOf(product));
        edytowaneWydarzenie.set_id_typu_wydarzenia(typWydarzenia);
        edytowaneWydarzenie.set_data(dataWydarzeniaNew);
        edytowaneWydarzenie.set_miejsce(miejsceWydarzenieNew);
        edytowaneWydarzenie.set_cena(cenaWydarzeniaNew);
        edytowaneWydarzenie.set_opis(opisWydarzeniaNew);
        edytowaneWydarzenie.set_godzina(godzineWydarzeniaNew);

        db.updateWydarzenie(edytowaneWydarzenie);

        db.close();

        Intent intent = new Intent(this, UdanyEdycjaWydarzenia.class);
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
