package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WyborWydarzeniaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_wydarzenia);
    }
    public void dodanieWydarzenia(View view)
    {
        Intent intent = new Intent(this, DodanieWydarzeniaActivity.class);
        startActivity(intent);
    }

    public void listaWydarzen(View view)
    {
        Intent intent = new Intent(this, ListaWydarzenActivity.class);
        startActivity(intent);
    }
}
