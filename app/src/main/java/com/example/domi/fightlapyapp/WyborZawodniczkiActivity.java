package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WyborZawodniczkiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_zawodniczki);
    }
    public void dodanieZawodniczki(View view)
    {
        Intent intent = new Intent(this, DodanieZawodniczkiActivity.class);
        startActivity(intent);
    }

    public void listaZawodniczek(View view)
    {
        Intent intent = new Intent(this, ListaZawodniczekActivity.class);
        startActivity(intent);
    }

    public void edycjaZawodniczki(View view)
    {
        Intent intent = new Intent(this, ListaZawodniczekEdycjaActivity.class);
        startActivity(intent);
    }
}
