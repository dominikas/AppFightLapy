package Wydarzenie;

import java.util.ArrayList;
import java.util.List;

import Wydarzenie.Wydarzenie;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class WydarzenieObliczenia {

    public String[] getWydarzeniaTakZawodniczka(List<Wydarzenie> wydarzeniaList){

        int licznikTak=0;
        for (Wydarzenie wyd : wydarzeniaList){
            if(wyd.getObecnosc()==1){
                licznikTak++;
            }
        }

        String[] listaZawodniczekTak = new String[licznikTak];
        licznikTak=0;
        for (Wydarzenie wyd : wydarzeniaList) {
            if (wyd.getObecnosc() == 1) {
                listaZawodniczekTak[licznikTak] = wyd.getOpis() + " " + wyd.getData();
                licznikTak++;
            }
        }

        return listaZawodniczekTak;
    }

    public String[] getWydarzeniaNieZawodniczka(List<Wydarzenie> wydarzeniaList){

        int licznikNie=0;
        for (Wydarzenie wyd : wydarzeniaList){
            if(wyd.getObecnosc()==2){
                licznikNie++;
            }
        }

        String[] listaZawodniczekNie = new String[licznikNie];
        licznikNie=0;
        for (Wydarzenie wyd : wydarzeniaList) {
            if (wyd.getObecnosc() == 2) {
                listaZawodniczekNie[licznikNie] = wyd.getOpis() + " " + wyd.getData();
                licznikNie++;
            }
        }

        return listaZawodniczekNie;
    }

    public String[] getWydarzeniaTbcZawodniczka(List<Wydarzenie> wydarzeniaList){

        int licznikTbc=0;
        for (Wydarzenie wyd : wydarzeniaList){
            if(wyd.getObecnosc()==3){
                licznikTbc++;
            }
        }

        String[] listaZawodniczekTbc = new String[licznikTbc];
        licznikTbc=0;
        for (Wydarzenie wyd : wydarzeniaList) {
            if (wyd.getObecnosc() == 3) {
                listaZawodniczekTbc[licznikTbc] = wyd.getOpis() + " " + wyd.getData();
                licznikTbc++;
            }
        }

        return listaZawodniczekTbc;
    }

    public String zamianaIdWydarzeniaNaZnaki(Integer typWydarzenia){

        String typWydarzeniaString = new String();

        switch (typWydarzenia) {
            case (1):
                typWydarzeniaString = "Trening";
                break;
            case (2):
                typWydarzeniaString = "Mecz";
                break;
            case (3):
                typWydarzeniaString = "Inne";
                break;

        }

        return typWydarzeniaString;
    }

    public Integer zmianaStatusuNaID(String status){

        Integer idStatusu = 0;

        switch (status) {
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

        return idStatusu;
    }

    public Integer zmianaWydarzeniaStringNaId(String typWydarzenia) {

        Integer idWydarzenia = 0;

        switch (typWydarzenia) {
            case ("Trening"):
                idWydarzenia = 1;
                break;
            case ("Mecz"):
                idWydarzenia = 2;
                break;
            case ("Inne"):
                idWydarzenia = 3;
                break;
        }

        return idWydarzenia;
    }

    public Integer wyszukanieWydarzeniaZListyPoId(List<Wydarzenie> wydarzenieList, String wybraneWydarzenie){


        Integer licznikWyd = 0;
        Integer idWydarzenia = 666;
        for (Wydarzenie wyd : wydarzenieList) {
            if (wyd.getOpis().equals(wybraneWydarzenie)) {

                idWydarzenia = wyd.getIdWydarzenia();
                licznikWyd++;
            }
        }
        return idWydarzenia;
    }

    public String[] getListeWydarzen(ArrayList<Wydarzenie> wydarzenieList){

        String[] listaWydarzen = new String[wydarzenieList.size()];

        for (Wydarzenie wyd : wydarzenieList){
            int indeks = wydarzenieList.indexOf(wyd);
            Integer indeks1 = (Integer) indeks;
            listaWydarzen[wydarzenieList.indexOf(wyd)] = wyd.getIdWydarzenia().toString()+" "+wyd.getOpis();
        }

        return listaWydarzen;
    }
}
