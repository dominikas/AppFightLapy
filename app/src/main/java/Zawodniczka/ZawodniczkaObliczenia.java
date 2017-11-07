package Zawodniczka;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ZawodniczkaObliczenia {

    public Zawodniczka wyszukanieZawodniczkiZListy(ArrayList<Zawodniczka> zawodniczkiList, String product){
        Log.d("***Wyszukuje o PRODUCT ", product);

        Integer idZaw=666;
        Integer indeksZaw=666;

        //TODO zmienic na while
        for(Zawodniczka zaw:zawodniczkiList)
        {
            if((zaw.getImie()+" "+zaw.getNazwisko()).equals(product)) {
                idZaw = zaw.getId();
                indeksZaw=zawodniczkiList.indexOf(zaw);
                Log.d("***Znalazlam ", indeksZaw.toString());
            }
        }
        Zawodniczka wyszukanaZawodniczka = zawodniczkiList.get(indeksZaw);
        Log.d("***Zawodniczka ", wyszukanaZawodniczka.getImie()+" "+wyszukanaZawodniczka.getNazwisko());
        return wyszukanaZawodniczka;
    }

    public String zamianaIdPozycjiNaZnaki(Integer idPozycji){

        String pozycjaString=new String();

        switch(idPozycji){
            case(1):
                pozycjaString="Rozegranie";
                break;
            case(2):
                pozycjaString="Atak";
                break;
            case(3):
                pozycjaString="Przyjęcie";
                break;
            case(4):
                pozycjaString="Środek";
                break;
            case(5):
                pozycjaString="Libero";
                break;
        }

        return pozycjaString;
    }

    public Integer zmianaPozycjiStringNaId(String pozycja) {
        Integer idPozycji=0;

        switch(pozycja){
            case ("Rozegranie"):
                idPozycji=1;
                break;
            case("Atak"):
                idPozycji=2;
                break;
            case("Przyjęcie"):
                idPozycji=3;
                break;
            case("Środek"):
                idPozycji=4;
                break;
            case("Libero"):
                idPozycji=5;
                break;
        }

        return idPozycji;
    }

    public String[] getTablicaZawodniczek(ArrayList<Zawodniczka> zawodniczkiList){

        String[] listaZawodniczek = new String[zawodniczkiList.size()];

        for (Zawodniczka zaw : zawodniczkiList){
            listaZawodniczek[zawodniczkiList.indexOf(zaw)] = zaw.getImie()+" "+zaw.getNazwisko();
        }

        return listaZawodniczek;
    }

    public String[] getZawodniczkiTakWydarzenie(List<Zawodniczka> wybraneZawodniczki){

        int licznikTak = 0;

        for (Zawodniczka zawod : wybraneZawodniczki) {
            if (zawod.getObecnosc() == 1) {
                licznikTak++;
            }
        }
        String[] listItemsTak = new String[licznikTak];

        licznikTak=0;
        for (Zawodniczka zaw : wybraneZawodniczki) {
            if (zaw.getObecnosc() == 1) {
                listItemsTak[licznikTak] = zaw.getImie() + " " + zaw.getNazwisko();
                Log.i("*****Dodaje zawodniczke", " obecna*****");
                licznikTak++;
            }
        }

        return listItemsTak;
    }

    public String[] getZawodniczkiNieWydarzenie(List<Zawodniczka> wybraneZawodniczki){

        int licznikNie = 0;

        for (Zawodniczka zawod : wybraneZawodniczki) {
            if (zawod.getObecnosc() == 2) {
                licznikNie++;
            }
        }
        String[] listItemsNie = new String[licznikNie];
        licznikNie=0;

        for (Zawodniczka zaw : wybraneZawodniczki) {
            if (zaw.getObecnosc() == 2) {
                listItemsNie[licznikNie] = zaw.getImie() + " " + zaw.getNazwisko();
                Log.i("*****Dodaje zawodniczke", " nie*****");
                licznikNie++;
            }
        }

        return listItemsNie;
    }

    public String[] getZawodniczkiTbcWydarzenie(List<Zawodniczka> wybraneZawodniczki){

        int licznikTbc = 0;

        for (Zawodniczka zawod : wybraneZawodniczki) {
            if (zawod.getObecnosc() == 3) {
                licznikTbc++;
            }
        }

        String[] listItemsTbc = new String[licznikTbc];

        licznikTbc=0;
        for (Zawodniczka zaw : wybraneZawodniczki) {
            if (zaw.getObecnosc() == 3) {
                listItemsTbc[licznikTbc] = zaw.getImie() + " " + zaw.getNazwisko();
                Log.i("*****Dodaje zawodniczke", " tbc*****");
                licznikTbc++;
            }
        }

        return listItemsTbc;
    }

    public String[] getListaZawodniczek(ArrayList<Zawodniczka> zawodniczkiList){

        String[] listaZawodniczek = new String[zawodniczkiList.size()];

        for (Zawodniczka zaw : zawodniczkiList){

            int indeks = zawodniczkiList.indexOf(zaw);
            Integer indeks1 = (Integer) indeks;

            listaZawodniczek[zawodniczkiList.indexOf(zaw)] = zaw.getImie()+" "+zaw.getNazwisko();
        }

        return listaZawodniczek;
    }
}
