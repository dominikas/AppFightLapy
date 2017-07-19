package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        Zawodniczka zawodniczkaTestowa = new Zawodniczka("Anna", "Nowa", 55, 2);
        db.dodajZawodniczke(zawodniczkaTestowa);

        Log.d("Reading: ", "Reading all contacts..");
        List<Zawodniczka> zawodniczki = db.getWszystkieZawodniczki();

        for (Zawodniczka zaw : zawodniczki) {
            String log = "Id: " + zaw.get_id() + " ,Name: " + zaw.get_imie() + " ,Numer: " + zaw.get_numer();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }
        */
    }

    public void dodajZawodniczke(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
