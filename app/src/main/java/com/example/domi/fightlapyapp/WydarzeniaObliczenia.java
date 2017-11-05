package com.example.domi.fightlapyapp;

import java.util.List;

import Wydarzenie.Wydarzenie;

/**
 * Created by Domi on 2017-11-05.
 */

public class WydarzeniaObliczenia {

    public String[] getWydarzeniaTakZawodniczka(List<Wydarzenie> wydarzeniaList){

        int licznikTak=0;
        for (Wydarzenie wyd : wydarzeniaList){
            if(wyd.getObecnosc()==1){
                licznikTak++;
            }
        }

        String[] listItems = new String[licznikTak];
        licznikTak=0;
        for (Wydarzenie wyd : wydarzeniaList) {
            if (wyd.getObecnosc() == 1) {
                listItems[licznikTak] = wyd.getOpis() + " " + wyd.getData();
                licznikTak++;
            }
        }

        return listItems;
    }

    public String[] getWydarzeniaNieZawodniczka(List<Wydarzenie> wydarzeniaList){

        int licznikNie=0;
        for (Wydarzenie wyd : wydarzeniaList){
            if(wyd.getObecnosc()==2){
                licznikNie++;
            }
        }

        String[] listItems = new String[licznikNie];
        licznikNie=0;
        for (Wydarzenie wyd : wydarzeniaList) {
            if (wyd.getObecnosc() == 2) {
                listItems[licznikNie] = wyd.getOpis() + " " + wyd.getData();
                licznikNie++;
            }
        }

        return listItems;
    }

    public String[] getWydarzeniaTbcZawodniczka(List<Wydarzenie> wydarzeniaList){

        int licznikTbc=0;
        for (Wydarzenie wyd : wydarzeniaList){
            if(wyd.getObecnosc()==3){
                licznikTbc++;
            }
        }

        String[] listItems = new String[licznikTbc];
        licznikTbc=0;
        for (Wydarzenie wyd : wydarzeniaList) {
            if (wyd.getObecnosc() == 3) {
                listItems[licznikTbc] = wyd.getOpis() + " " + wyd.getData();
                licznikTbc++;
            }
        }

        return listItems;
    }
}
