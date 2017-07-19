package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;
import android.util.Log;

import org.w3c.dom.Text;

public class DisplayMessageActivity extends AppCompatActivity {
    private Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_message);

        //addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        EditText imie = (EditText) findViewById(R.id.imie);
        EditText nazwisko = (EditText) findViewById(R.id.nazwisko);
        EditText numer = (EditText) findViewById(R.id.numer);

        //String imie_zawodniczki= textView.getText().toString();

        //textView.setText(message);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void zapisz_zawodniczke(View view) {
        EditText imie = (EditText) findViewById(R.id.imie);
        String imie_zawodniczki = imie.getText().toString();
        EditText nazwisko = (EditText) findViewById(R.id.nazwisko);
        String nazwisko_zawodniczki = nazwisko.getText().toString();
        EditText numer = (EditText) findViewById(R.id.numer);
        String numer_zawodniczki = numer.getText().toString();

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        Zawodniczka zawodniczkaTestowa = new Zawodniczka(imie_zawodniczki, nazwisko_zawodniczki, 3, 88);
        db.dodajZawodniczke(zawodniczkaTestowa);

        Log.d("Reading: ", "Reading all contacts..");
        List<Zawodniczka> zawodniczki = db.getWszystkieZawodniczki();
        for (Zawodniczka zaw : zawodniczki) {
            String log = "Id: " + zaw.get_id() + " ,Name: " + zaw.get_imie() + " ,Numer: " + zaw.get_numer();
            // Writing Contacts to log
            Log.d("Name: ", log);
            db.close();
        }
        // get the selected dropdown list value
    /*
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        //btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DisplayMessageActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
    */
    }
}
