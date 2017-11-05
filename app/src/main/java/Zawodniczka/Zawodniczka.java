package Zawodniczka;

import java.util.ArrayList;

import MyExceptions.*;

/**
 * Created by Domi on 2017-07-02.
 */

public class Zawodniczka{

    private Integer idZawodniczki;
    private String imie;
    private String nazwisko;
    private Integer idPozycji;
    private Integer numer;
    private ArrayList<Integer> listaWydarzen;
    private int obecnosc;

    public Zawodniczka(){

    }

    public Zawodniczka(Integer noweId, String noweImie, String noweNazwisko, Integer noweIdPozycji,Integer nowyNumer)
            /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception*/
    {
        //sprawdzenie danych przekazywanych -> wyjatki
        //weryfikacjaDanych(noweId, noweImie, noweNazwisko, noweIdPozycji, nowyNumer);

        this.idZawodniczki =noweId;
        this.imie=noweImie;
        this.nazwisko=noweNazwisko;
        this.idPozycji =noweIdPozycji;
        this.numer=nowyNumer;
        this.listaWydarzen= new ArrayList<Integer>();

        /*
        setId(noweId);
        setImie(noweImie);
        setNazwisko(noweNazwisko);
        setIdPozycji(noweIdPozycji);
        setNumer(nowyNumer);
        this.listaWydarzen= new ArrayList<Integer>();
        */
    }
    public Zawodniczka(String noweImie, String noweNazwisko, Integer noweIdPozycji,Integer nowyNumer){
        this.imie = noweImie;
        this.nazwisko = noweNazwisko;
        this.idPozycji = noweIdPozycji;
        this.numer = nowyNumer;
        this.listaWydarzen = new ArrayList<Integer>();
    }
    /*public void dodaj_zawodniczke()
    {

    }
    */

    public int getObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(int obecnosc) {
        this.obecnosc = obecnosc;
    }

    public Integer getId(){
        return this.idZawodniczki;
    }

    public String getImie(){
        return this.imie;
    }

    public String getNazwisko(){
        return this.nazwisko;
    }

    public Integer getIdPozycji(){
        return this.idPozycji;
    }

    public Integer getNumer(){
        return this.numer;
    }

    public ArrayList<Integer> getListaWydarzen(){
        return this.listaWydarzen;
    }

    public void setId(Integer noweId) //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
/*
        if(noweId==null)
        {
            throw new Puste_Pole_Exception("ID zawodniczki");
        }

        if(noweId.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID zawodniczki");
        }

        // czy juz takie id istnieje
*/
        this.idZawodniczki =noweId;
    }

    public void setImie(String noweImie) //throws Bledny_Format_Exception, Puste_Pole_Exception, Za_Dlugi_Exception
    {
/*
        if(noweImie==null)
        {
            throw new Puste_Pole_Exception("imię zawodniczki");
        }

        if(noweImie.length()>50)
        {
            throw new Za_Dlugi_Exception("imię zawodniczki");
        }

        if(noweImie.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("imię zawodniczki");
        }
*/
        this.imie=noweImie;
    }

    public void setNazwisko(String noweNazwisko) //throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception
    {
/*
        if(noweNazwisko==null)
        {
            throw new Puste_Pole_Exception("nazwisko zawodniczki");
        }

        if(noweNazwisko.length()>50)
        {
            throw new Za_Dlugi_Exception("nazwisko zawodniczki");
        }

        if(noweNazwisko.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("nazwisko zawodniczki");
        }
*/
        this.nazwisko=noweNazwisko;
    }

    public void setIdPozycji(Integer noweIdPozycji) //throws Puste_Pole_Exception, Za_Dlugi_Exception, Niedozwolony_Id_Exception
    {
/*
        if(noweIdPozycji==null)
        {
            throw new Puste_Pole_Exception("ID pozycji");
        }

        if(noweIdPozycji.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID pozycji");
        }

        //niedozwolone sie ID
        if(noweIdPozycji<0 || noweIdPozycji>5)
        {
            throw new Niedozwolony_Id_Exception(noweIdPozycji,"pozycji");
        }
        */
        this.idPozycji =noweIdPozycji;
    }

    public void setNumer(Integer nowyNumer) //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
/*
        if(nowyNumer==null)
        {
            throw new Puste_Pole_Exception("numer");
        }

        if(nowyNumer.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("numer");
        }

        //powtarzajace sie pole
        */
        this.numer=nowyNumer;
    }

    public void dodajWydarzenieDoZawodniczki(Integer idWydarzenia) //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
        /*
        //if(this.liczbaWydarzenZawodniczki()!=0)
        if(idWydarzenia==null)
        {
            throw new Puste_Pole_Exception("ID wydarzenia");
        }

        if(idWydarzenia.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID wydarzenia");
        }

        //powtarzajace się id
*/
        this.listaWydarzen.add(idWydarzenia);
    }

    private int liczbaWydarzenZawodniczki() //throws Brak_Wydarzen_Exception
    {
        int liczba=0;
        /*
        if(this.listaWydarzen.size()==0)
            throw new Brak_Wydarzen_Exception();
        else
            liczba=this.listaWydarzen.size();
*/
        return liczba;
    }

    private void weryfikacjaDanych(Integer noweId, String noweImie, String noweNazwisko, Integer noweIdPozycji,Integer nowyNumer)
            throws Puste_Pole_Exception, Bledny_Format_Exception, Za_Dlugi_Exception
    {
        //weryfikacja ID
        if(noweId==null)
        {
            throw new Puste_Pole_Exception("ID zawodniczki");
        }

        if(noweId.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID zawodniczki");
        }

        //weryfikacja imienia
        if(noweImie==null)
        {
            throw new Puste_Pole_Exception("imię zawodniczki");
        }

        if(noweImie.length()>50)
        {
            throw new Za_Dlugi_Exception("imię zawodniczki");
        }

        if(noweImie.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("imię zawodniczki");
        }

        //weryfikacja nazwiska
        if(noweNazwisko==null)
        {
            throw new Puste_Pole_Exception("nazwisko zawodniczki");
        }

        if(noweNazwisko.length()>50)
        {
            throw new Za_Dlugi_Exception("nazwisko zawodniczki");
        }

        if(noweNazwisko.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("nazwisko zawodniczki");
        }

        //weryfikacja ID pozycji
        if(noweIdPozycji==null)
        {
            throw new Puste_Pole_Exception("ID pozycji");
        }

        if(noweIdPozycji.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID pozycji");
        }

        //weryfikacja numeru
        if(nowyNumer==null)
        {
            throw new Puste_Pole_Exception("numer");
        }

        if(nowyNumer.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("numer");
        }
    }
}
