package com.example.domi.fightlapyapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

    private String wybranaZawodniczka;
    private String wybraneWydarzenie;
    private String wybranyStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapis_zawodniczki_na_wydarzenie);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        ArrayList<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();
        db.close();
        valuesZawodniczka = new String[zawodniczkiList.size()];
        //  valuesWydarzenie = new String[wydarzenieList.size()];

        for (Zawodniczka zaw : zawodniczkiList){
            valuesZawodniczka[zawodniczkiList.indexOf(zaw)] = zaw.getImie();
        }

        /*
        for (Wydarzenie wyd : wydarzenieList) {
            valuesWydarzenie[wydarzenieList.indexOf(wyd)] = wyd.getOpis();
        }
*/
        valuesStatus = new String[3];
        valuesStatus[0] = "Tak";
        valuesStatus[1] = "Nie";
        valuesStatus[2] = "TBC";

        wybranaZawodniczkaET = (TextView) findViewById(R.id.wybrana_zawodniczka);
        wybranaZawodniczkaET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialogWithRadioButtonGroupZawodniczka();
            }
        });

        wybraneWydarzenieET = (TextView) findViewById(R.id.wybrane_wydarzenie);
        wybraneWydarzenieET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialogWithRadioButtonGroupWydarzenie();
            }
        });

        statusET = (TextView) findViewById(R.id.status);
        statusET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAlertDialogWithRadioButtonGroupStatus();
            }
        });


        findViewById(R.id.zapisz_zaw_na_wyd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int czyDobrze = 0;
                if (wybranaZawodniczkaET.getText().toString().isEmpty()) {
                    wybranaZawodniczkaET.setError("Puste pole zawodniczki");
                    czyDobrze++;
                }

                if (wybraneWydarzenieET.getText().toString().isEmpty()) {
                    wybraneWydarzenieET.setError("Puste pole wydarzenia");
                    czyDobrze++;
                }

                if (statusET.getText().toString().isEmpty()) {
                    statusET.setError("Puste pole status");
                    czyDobrze++;
                }

                if (czyDobrze == 0)
                    zapiszZawodniczkiNaWydarzenie();
            }
        });

    }

    public void CreateAlertDialogWithRadioButtonGroupZawodniczka() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz zawodniczke");
        builder.setSingleChoiceItems(valuesZawodniczka, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lwZaw = (alertDialogZawodniczka).getListView();
                Object checkedZaw = lwZaw.getAdapter().getItem(lwZaw.getCheckedItemPosition());
                wybranaZawodniczka = checkedZaw.toString();
                Log.d("zawodniczka ", wybranaZawodniczka);
                wybranaZawodniczkaET.setText(wybranaZawodniczka);
                alertDialogZawodniczka.dismiss();
            }
        });
        alertDialogZawodniczka = builder.create();
        alertDialogZawodniczka.show();
    }

    public void CreateAlertDialogWithRadioButtonGroupWydarzenie() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz wydarzenie");
        if(!wybranaZawodniczkaET.getText().toString().isEmpty()) {
            wypisanieWlasciwychWydarzen(wybranaZawodniczka.toString());
        }
        else
        {
            DatabaseHandler db = new DatabaseHandler(this);
            ArrayList<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();
            for (Wydarzenie wyd : wydarzenieList) {
                valuesWydarzenie[wydarzenieList.indexOf(wyd)] = wyd.getOpis();
            }
        }
        builder.setSingleChoiceItems(valuesWydarzenie, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lwWyd = (alertDialogWydarzenie).getListView();
                Object checkedWyd = lwWyd.getAdapter().getItem(lwWyd.getCheckedItemPosition());
                wybraneWydarzenie = checkedWyd.toString();
                Log.d("wydarzenie ", wybraneWydarzenie);
                wybraneWydarzenieET.setText(wybraneWydarzenie);
                alertDialogWydarzenie.dismiss();
            }
        });

        alertDialogWydarzenie = builder.create();
        alertDialogWydarzenie.show();
    }

    public void CreateAlertDialogWithRadioButtonGroupStatus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz status");
        builder.setSingleChoiceItems(valuesStatus, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lwStatus = (alertDialogStatus).getListView();
                Object checkedStatus = lwStatus.getAdapter().getItem(lwStatus.getCheckedItemPosition());
                wybranyStatus = checkedStatus.toString();
                Log.d("status ", wybranyStatus);
                statusET.setText(wybranyStatus);
                alertDialogStatus.dismiss();
            }
        });
        alertDialogStatus = builder.create();
        alertDialogStatus.show();
    }

    public void zapiszZawodniczkiNaWydarzenie() {

        Integer idStatusu = 0;

        switch (wybranyStatus) {
            case ("Tak"):
                idStatusu = 1;
                break;
            case ("Nie"):
                idStatusu = 2;
                break;
            case ("TBC"):
                idStatusu = 3;
                break;
        }

        DatabaseHandler db = new DatabaseHandler(this);

        Integer idZawodniczki = 666;
        Integer idWydarzenia = 666;

        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        List<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();

        Integer licznikZaw = 0;

        idZawodniczki=jakaToZawodniczka(wybranaZawodniczka);

        Integer licznikWyd = 0;
        for (Wydarzenie wyd : wydarzenieList) {
            if (wyd.getOpis().equals(wybraneWydarzenie)) {

                idWydarzenia = wyd.getIdWydarzenia();
                licznikWyd++;
            }
        }

        //Log.d("stan licznika id wyd", licznikWyd.toString());
        Log.d("wybrane wydarzenie", idWydarzenia.toString());

        db.zapiszZawodniczkeNaWydarzenie(idZawodniczki, idWydarzenia, idStatusu);
        //wydarzenieList=db.getWszystkieWydarzeniaPoZawodniczce(idZawodniczki);

        db.close();

        Intent intent = new Intent(this, UdanyZapisZawodniczkiNaWydarzenieActivity.class);
        startActivity(intent);
    }

    public void wypisanieWlasciwychWydarzen(String idZawodniczki) {

        List<Wydarzenie> wydarzenieList=new ArrayList<Wydarzenie>();

        //jaka to zawodniczka
        Integer szukanaZawodniczka=jakaToZawodniczka(idZawodniczki);

        //1. Czy jest na cos zapisana?
        DatabaseHandler db = new DatabaseHandler(this);
        Integer czyJestZapis=db.czyJestNaCosZapisana(szukanaZawodniczka);

        if(czyJestZapis.equals(1)){
            //jezeli jest zapisana
            //2. czy jest zapisana na wszystko?
            Integer liczbaWszystkichWydarzen=db.getLiczbaWydarzen();
            if(liczbaWszystkichWydarzen.equals(db.liczbaNaIleJestZapisana(szukanaZawodniczka))){
                //TO DO zmienic co ma sie wyswietlac
                valuesWydarzenie[0] = "Na wszystko zapisana";
            }
            else{
                wydarzenieList=db.getWydarzeniaNaKtoreNieJestZapisanaZawodniczka(szukanaZawodniczka);
            }
        }
        else{
            //jezeli nie jest zapisana
            Log.d("Nie jest ", "na nic zapisana");
            wydarzenieList=db.getWszystkieWydarzenia();
            Log.d("Liczba wydarzen ", Integer.valueOf(wydarzenieList.size()).toString());
        }


        valuesWydarzenie=new String[wydarzenieList.size()];
        for (Wydarzenie wyd : wydarzenieList) {
            valuesWydarzenie[wydarzenieList.indexOf(wyd)] = wyd.getOpis();
        }
    }

    public Integer jakaToZawodniczka(String szukanaZawodniczka){

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();

        Integer idZawodniczki = 0;
        for (Zawodniczka zaw : zawodniczkiList) {
            if (zaw.getImie().equals(wybranaZawodniczka)) {
                idZawodniczki = zaw.getId();
            }
        }
        return idZawodniczki;
    }
}
