package com.example.domi.fightlapyapp.zawodniczkaActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.domi.fightlapyapp.R;
import com.example.domi.fightlapyapp.zawodniczkaActivities.DodanieZawodniczkiActivity;
import com.example.domi.fightlapyapp.zawodniczkaActivities.ListaZawodniczekActivity;
import com.example.domi.fightlapyapp.zawodniczkaActivities.ListaZawodniczekEdycjaActivity;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

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