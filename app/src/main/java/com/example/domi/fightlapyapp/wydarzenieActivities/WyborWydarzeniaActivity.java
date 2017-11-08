package com.example.domi.fightlapyapp.wydarzenieActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.domi.fightlapyapp.R;
import com.example.domi.fightlapyapp.wydarzenieActivities.DodanieWydarzeniaActivity;
import com.example.domi.fightlapyapp.wydarzenieActivities.ListaWydarzenActivity;
import com.example.domi.fightlapyapp.wydarzenieActivities.ListaWydarzenEdycjaActivity;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

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

    public void listaWydarzenEdycja(View view)
    {
        Intent intent = new Intent(this, ListaWydarzenEdycjaActivity.class);
        startActivity(intent);
    }

}
