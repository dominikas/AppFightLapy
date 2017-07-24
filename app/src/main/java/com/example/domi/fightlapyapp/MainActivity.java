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
    //public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void wyborZawodniczki(View view)
    {
        Intent intent = new Intent(this, WyborZawodniczkiActivity.class);
        startActivity(intent);
    }

    public void wyborWydarzenia(View view)
    {
        Intent intent = new Intent(this, WyborWydarzeniaActivity.class);
        startActivity(intent);
    }

    public void zapisZawodniczkiNaWydarzenie(View view)
    {
        Intent intent = new Intent(this, ZapisZawodniczkiNaWydarzenieActivity.class);
        startActivity(intent);
    }
}
