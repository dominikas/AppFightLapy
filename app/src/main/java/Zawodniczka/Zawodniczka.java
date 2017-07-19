package Zawodniczka;

import java.util.ArrayList;

import MyExceptions.*;

/**
 * Created by Domi on 2017-07-02.
 */

public class Zawodniczka{

    private Integer id_zawodniczki;
    private String imie;
    private String nazwisko;
    private Integer id_pozycji;
    private Integer numer;
    private ArrayList<Integer> listaWydarzen;

    public Zawodniczka(){

    }

    public Zawodniczka(Integer nowe_id, String nowe_imie, String nowe_nazwisko, Integer nowe_id_pozycji,Integer nowy_numer)
            /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception*/
    {
        //sprawdzenie danych przekazywanych -> wyjatki
        //weryfikacjaDanych(nowe_id, nowe_imie, nowe_nazwisko, nowe_id_pozycji, nowy_numer);

        this.id_zawodniczki=nowe_id;
        this.imie=nowe_imie;
        this.nazwisko=nowe_nazwisko;
        this.id_pozycji=nowe_id_pozycji;
        this.numer=nowy_numer;
        this.listaWydarzen= new ArrayList<Integer>();

        /*
        set_id(nowe_id);
        set_imie(nowe_imie);
        set_nazwisko(nowe_nazwisko);
        set_id_pozycji(nowe_id_pozycji);
        set_numer(nowy_numer);
        this.listaWydarzen= new ArrayList<Integer>();
        */
    }
    public Zawodniczka(String nowe_imie, String nowe_nazwisko, Integer nowe_id_pozycji,Integer nowy_numer){
        this.imie = nowe_imie;
        this.nazwisko = nowe_nazwisko;
        this.id_pozycji = nowe_id_pozycji;
        this.numer = nowy_numer;
        this.listaWydarzen = new ArrayList<Integer>();
    }
    /*public void dodaj_zawodniczke()
    {

    }
    */
    public Integer get_id(){
        return this.id_zawodniczki;
    }

    public String get_imie(){
        return this.imie;
    }

    public String get_nazwisko(){
        return this.nazwisko;
    }

    public Integer get_id_pozycji(){
        return this.id_pozycji;
    }

    public Integer get_numer(){
        return this.numer;
    }

    public ArrayList<Integer> get_listaWydarzen(){
        return this.listaWydarzen;
    }

    public void set_id(Integer nowe_id) //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
/*
        if(nowe_id==null)
        {
            throw new Puste_Pole_Exception("ID zawodniczki");
        }

        if(nowe_id.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID zawodniczki");
        }

        // czy juz takie id istnieje
*/
        this.id_zawodniczki=nowe_id;
    }

    public void set_imie(String nowe_imie) //throws Bledny_Format_Exception, Puste_Pole_Exception, Za_Dlugi_Exception
    {
/*
        if(nowe_imie==null)
        {
            throw new Puste_Pole_Exception("imię zawodniczki");
        }

        if(nowe_imie.length()>50)
        {
            throw new Za_Dlugi_Exception("imię zawodniczki");
        }

        if(nowe_imie.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("imię zawodniczki");
        }
*/
        this.imie=nowe_imie;
    }

    public void set_nazwisko(String nowe_nazwisko) //throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception
    {
/*
        if(nowe_nazwisko==null)
        {
            throw new Puste_Pole_Exception("nazwisko zawodniczki");
        }

        if(nowe_nazwisko.length()>50)
        {
            throw new Za_Dlugi_Exception("nazwisko zawodniczki");
        }

        if(nowe_nazwisko.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("nazwisko zawodniczki");
        }
*/
        this.nazwisko=nowe_nazwisko;
    }

    public void set_id_pozycji(Integer nowe_id_pozycji) //throws Puste_Pole_Exception, Za_Dlugi_Exception, Niedozwolony_Id_Exception
    {
/*
        if(nowe_id_pozycji==null)
        {
            throw new Puste_Pole_Exception("ID pozycji");
        }

        if(nowe_id_pozycji.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID pozycji");
        }

        //niedozwolone sie ID
        if(nowe_id_pozycji<0 || nowe_id_pozycji>5)
        {
            throw new Niedozwolony_Id_Exception(nowe_id_pozycji,"pozycji");
        }
        */
        this.id_pozycji=nowe_id_pozycji;
    }

    public void set_numer(Integer nowy_numer) //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
/*
        if(nowy_numer==null)
        {
            throw new Puste_Pole_Exception("numer");
        }

        if(nowy_numer.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("numer");
        }

        //powtarzajace sie pole
        */
        this.numer=nowy_numer;
    }

    public void dodajWydarzenieDoZawodniczki(Integer id_wydarzenia) //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
        /*
        //if(this.liczbaWydarzenZawodniczki()!=0)
        if(id_wydarzenia==null)
        {
            throw new Puste_Pole_Exception("ID wydarzenia");
        }

        if(id_wydarzenia.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID wydarzenia");
        }

        //powtarzajace się id
*/
        this.listaWydarzen.add(id_wydarzenia);
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

    private void weryfikacjaDanych(Integer nowe_id, String nowe_imie, String nowe_nazwisko, Integer nowe_id_pozycji,Integer nowy_numer)
            throws Puste_Pole_Exception, Bledny_Format_Exception, Za_Dlugi_Exception
    {
        //weryfikacja ID
        if(nowe_id==null)
        {
            throw new Puste_Pole_Exception("ID zawodniczki");
        }

        if(nowe_id.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID zawodniczki");
        }

        //weryfikacja imienia
        if(nowe_imie==null)
        {
            throw new Puste_Pole_Exception("imię zawodniczki");
        }

        if(nowe_imie.length()>50)
        {
            throw new Za_Dlugi_Exception("imię zawodniczki");
        }

        if(nowe_imie.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("imię zawodniczki");
        }

        //weryfikacja nazwiska
        if(nowe_nazwisko==null)
        {
            throw new Puste_Pole_Exception("nazwisko zawodniczki");
        }

        if(nowe_nazwisko.length()>50)
        {
            throw new Za_Dlugi_Exception("nazwisko zawodniczki");
        }

        if(nowe_nazwisko.matches("[a-zA-Z]+")==false)
        {
            throw new Bledny_Format_Exception("nazwisko zawodniczki");
        }

        //weryfikacja ID pozycji
        if(nowe_id_pozycji==null)
        {
            throw new Puste_Pole_Exception("ID pozycji");
        }

        if(nowe_id_pozycji.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("ID pozycji");
        }

        //weryfikacja numeru
        if(nowy_numer==null)
        {
            throw new Puste_Pole_Exception("numer");
        }

        if(nowy_numer.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("numer");
        }
    }
}
