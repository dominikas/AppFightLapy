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
import Zawodniczka.*;
import Wydarzenie.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ZapisZawodniczkiNaWydarzenieActivity extends AppCompatActivity {
    private TextView wybranaZawodniczkaET;
    private TextView wybraneWydarzenieET;
    private TextView statusET;

    private AlertDialog zawodniczkaAlertDialog;
    private AlertDialog wydarzenieAlertDialog;
    private AlertDialog statusAlertDialog;

    private String[] zawodniczkaValues;
    private String[] wydarzenieValues;
    private String[] statusValues;

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
        zawodniczkaValues = new String[zawodniczkiList.size()];

        ZawodniczkaObliczenia zawodniczkaObliczenia=new ZawodniczkaObliczenia();
        zawodniczkaValues=zawodniczkaObliczenia.getListaZawodniczek(zawodniczkiList);

        statusValues = new String[3];
        statusValues[0] = "Tak";
        statusValues[1] = "Nie";
        statusValues[2] = "TBC";

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

                String zawodniczka = wybranaZawodniczkaET.getText().toString();
                String wydarzenie = wybraneWydarzenieET.getText().toString();
                String status = statusET.getText().toString();
                boolean czyOk = czyDaneSaOk(zawodniczka,wydarzenie,status);

                if (czyOk)
                    zapiszZawodniczkiNaWydarzenie();
            }
        });

    }

    public void CreateAlertDialogWithRadioButtonGroupZawodniczka() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz zawodniczke");
        builder.setSingleChoiceItems(zawodniczkaValues, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lwZaw = (zawodniczkaAlertDialog).getListView();
                Object checkedZaw = lwZaw.getAdapter().getItem(lwZaw.getCheckedItemPosition());
                wybranaZawodniczka = checkedZaw.toString();
                Log.d("zawodniczka ", wybranaZawodniczka);
                wybranaZawodniczkaET.setText(wybranaZawodniczka);
                zawodniczkaAlertDialog.dismiss();
            }
        });
        zawodniczkaAlertDialog = builder.create();
        zawodniczkaAlertDialog.show();
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
            Log.d("**liczba wydarzen",Integer.valueOf(wydarzenieList.size()).toString());

            WydarzenieObliczenia wydarzenieObliczenia = new WydarzenieObliczenia();
            if(wydarzenieList.size()>0){
                wydarzenieValues=wydarzenieObliczenia.getListeWydarzen(wydarzenieList);
            }
        }
        builder.setSingleChoiceItems(wydarzenieValues, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lwWyd = (wydarzenieAlertDialog).getListView();
                Object checkedWyd = lwWyd.getAdapter().getItem(lwWyd.getCheckedItemPosition());
                wybraneWydarzenie = checkedWyd.toString();
                Log.d("wydarzenie ", wybraneWydarzenie);
                wybraneWydarzenieET.setText(wybraneWydarzenie);
                wydarzenieAlertDialog.dismiss();
            }
        });

        wydarzenieAlertDialog = builder.create();
        wydarzenieAlertDialog.show();
    }

    private void CreateAlertDialogWithRadioButtonGroupStatus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZapisZawodniczkiNaWydarzenieActivity.this);
        builder.setTitle("Wybierz status");
        builder.setSingleChoiceItems(statusValues, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                ListView lwStatus = (statusAlertDialog).getListView();

                Object checkedStatus = lwStatus.getAdapter().getItem(lwStatus.getCheckedItemPosition());
                wybranyStatus = checkedStatus.toString();
                Log.d("status ", wybranyStatus);
                statusET.setText(wybranyStatus);
                statusAlertDialog.dismiss();
            }
        });
        statusAlertDialog = builder.create();
        statusAlertDialog.show();
    }

    private void zapiszZawodniczkiNaWydarzenie() {


        WydarzenieObliczenia wydarzenieObliczenia = new WydarzenieObliczenia();
        Integer idStatusu = wydarzenieObliczenia.zmianaStatusuNaID(wybranyStatus);

        DatabaseHandler db = new DatabaseHandler(this);
        List<Wydarzenie> wydarzenieList = db.getWszystkieWydarzenia();

        Zawodniczka zawodniczka=jakaToZawodniczka(wybranaZawodniczka);

        Integer idWydarzenia=wydarzenieObliczenia.wyszukanieWydarzeniaZListyPoId(wydarzenieList, wybraneWydarzenie);

        db.zapiszZawodniczkeNaWydarzenie(zawodniczka.getId(), idWydarzenia, idStatusu);
        db.close();

        Intent intent = new Intent(this, UdanyZapisZawodniczkiNaWydarzenieActivity.class);
        startActivity(intent);
    }

    private void wypisanieWlasciwychWydarzen(String idZawodniczki) {

        List<Wydarzenie> wydarzenieList=new ArrayList<Wydarzenie>();

        //jaka to zawodniczka
        Log.d("Kogo szukam ", wybranaZawodniczka);
        Integer szukanaZawodniczka=(jakaToZawodniczka(wybranaZawodniczka)).getId();

        //1. Czy jest na cos zapisana?
        DatabaseHandler db = new DatabaseHandler(this);
        Integer czyJestZapis=db.czyJestNaCosZapisana(szukanaZawodniczka);

        if(czyJestZapis.equals(1)){
            //jezeli jest zapisana
            //2. czy jest zapisana na wszystko?
            Integer liczbaWszystkichWydarzen=db.getLiczbaWydarzen();
            if(liczbaWszystkichWydarzen.equals(db.liczbaNaIleJestZapisana(szukanaZawodniczka))){
                wydarzenieValues[0] = "Na wszystko zapisana";
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

        wydarzenieValues =new String[wydarzenieList.size()];
        for (Wydarzenie wyd : wydarzenieList) {
            wydarzenieValues[wydarzenieList.indexOf(wyd)] = wyd.getOpis();
        }
    }

    private Zawodniczka jakaToZawodniczka(String szukanaZawodniczka){

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        ZawodniczkaObliczenia zawodniczkaObliczenia=new ZawodniczkaObliczenia();
        Zawodniczka zawodniczka=zawodniczkaObliczenia.wyszukanieZawodniczkiZListy(zawodniczkiList,szukanaZawodniczka);

        return zawodniczka;
    }

    private boolean czyDaneSaOk(String zawodniczka, String wydarzenie, String status){

        boolean czyOk = true;

        if (zawodniczka.isEmpty()) {
            wybranaZawodniczkaET.setError(getString(R.string.err_msg_zawodniczka));
            czyOk=false;
        }

        if (wydarzenie.isEmpty()) {
            wybraneWydarzenieET.setError(getString(R.string.err_msg_wydarzenie));
            czyOk=false;
        }

        if (status.isEmpty()) {
            statusET.setError(getString(R.string.err_msg_status));
            czyOk=false;
        }

        return czyOk;
    }
}
