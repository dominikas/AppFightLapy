package com.example.domi.fightlapyapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;

import DatabaseHandler.DatabaseHandler;
import Zawodniczka.Zawodniczka;
import Wydarzenie.Wydarzenie;

public class ZapisZawodniczkiNaWydarzenieActivity extends AppCompatActivity {
    private TextView wybranaZawodniczkaET;
    private TextView wybraneWydarzenieET;
    private TextView statusET;

    AlertDialog alertDialogZawodniczka;
    AlertDialog alertDialogWydarzenie;
    AlertDialog alertDialogStatus;

    String[] valuesZawodniczka;
    String[] valuesWydarzenie;
    String[] valuesStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapis_zawodniczki_na_wydarzenie);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        ArrayList<Wydarzenie> wydarzenieList=db.getWszystkieWydarzenia();
        db.close();
        valuesZawodniczka=new String[zawodniczkiList.size()];
        valuesWydarzenie=new String[wydarzenieList.size()];

        for (Zawodniczka zaw : zawodniczkiList){
            valuesZawodniczka[zawodniczkiList.indexOf(zaw)] = zaw.get_imie();
        }

        for (Wydarzenie wyd : wydarzenieList){
            valuesWydarzenie[wydarzenieList.indexOf(wyd)] = wyd.get_opis();
        }

        valuesStatus = new String[3];
        valuesStatus[0]="Tak";
        valuesStatus[1]="Nie";
        valuesStatus[2]="TBC";

        wybranaZawodniczkaET=(TextView) findViewById(R.id.wybrana_zawodniczka);
        wybranaZawodniczkaET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialogWithRadioButtonGroupZawodniczka() ;
                }
        });

        wybraneWydarzenieET=(TextView) findViewById(R.id.wybrane_wydarzenie);
        wybraneWydarzenieET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialogWithRadioButtonGroupWydarzenie();
            }
        });

        statusET=(TextView) findViewById(R.id.status);
        statusET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialogWithRadioButtonGroupStatus();
            }
        });
    }

    public void CreateAlertDialogWithRadioButtonGroupZawodniczka(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz zawodniczke");
        builder.setSingleChoiceItems(valuesZawodniczka, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                alertDialogZawodniczka.dismiss();
            }
        });
        alertDialogZawodniczka = builder.create();
        alertDialogZawodniczka.show();

    }

    public void CreateAlertDialogWithRadioButtonGroupWydarzenie(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz wydarzenie");
        builder.setSingleChoiceItems(valuesWydarzenie, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                alertDialogWydarzenie.dismiss();
            }
        });
        alertDialogWydarzenie = builder.create();
        alertDialogWydarzenie.show();

    }

    public void CreateAlertDialogWithRadioButtonGroupStatus(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz status");
        builder.setSingleChoiceItems(valuesStatus, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                alertDialogStatus.dismiss();
            }
        });
        alertDialogStatus = builder.create();
        alertDialogStatus.show();
    }
}
