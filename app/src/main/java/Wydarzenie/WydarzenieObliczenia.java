package Wydarzenie;

import java.util.List;

import Wydarzenie.Wydarzenie;

/**
 * Created by Domi on 2017-11-05.
 */

public class WydarzenieObliczenia {

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

    public Integer wyszukanieWydarzenie(List<Wydarzenie> wydarzenieList, String wybraneWydarzenie){


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
}
