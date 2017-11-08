package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.domi.fightlapyapp.wydarzenieActivities.WyborWydarzeniaActivity;
import com.example.domi.fightlapyapp.zawodniczkaActivities.WyborZawodniczkiActivity;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void wyborZawodniczki(View view){
        Intent intent = new Intent(this, WyborZawodniczkiActivity.class);
        startActivity(intent);
    }

    public void wyborWydarzenia(View view){
        Intent intent = new Intent(this, WyborWydarzeniaActivity.class);
        startActivity(intent);
    }

    public void zapisZawodniczkiNaWydarzenie(View view){
        Intent intent = new Intent(this, ZapisZawodniczkiNaWydarzenieActivity.class);
        startActivity(intent);
    }

    public void wyborFacebooka(View view){
        Intent intent = new Intent(this, FacebookActivity.class);
        startActivity(intent);
    }

}
