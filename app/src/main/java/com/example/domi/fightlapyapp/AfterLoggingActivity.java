package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.domi.fightlapyapp.userActivities.EdycjaDanychUsera;
import com.example.domi.fightlapyapp.wydarzenieActivities.ListaWydarzenActivity;
import com.example.domi.fightlapyapp.wydarzenieActivities.WyborWydarzeniaActivity;
import com.example.domi.fightlapyapp.zawodniczkaActivities.WyborZawodniczkiActivity;
import com.example.domi.fightlapyapp.userActivities.ZapisUseraNaWydarzenieActivity;

public class AfterLoggingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_logging);
    }

    public void wyborEdycjiDanychUzytkownika(View view){
        Intent intent = new Intent(this, EdycjaDanychUsera.class);
        startActivity(intent);
    }

    public void wyborWydarzenia(View view){
        Intent intent = new Intent(this, ListaWydarzenActivity.class);
        startActivity(intent);
    }

    public void zapisUzytkownikaNaWydarzenie(View view){
        Intent intent = new Intent(this, ZapisUseraNaWydarzenieActivity.class);
        startActivity(intent);
    }
}
