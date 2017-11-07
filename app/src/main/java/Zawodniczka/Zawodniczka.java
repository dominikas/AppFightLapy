package Zawodniczka;

import java.util.ArrayList;

import MyExceptions.*;

/**
 * Created by Dominika Saide on 2017-07-02.
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

    public Zawodniczka(Integer noweId, String noweImie, String noweNazwisko, Integer noweIdPozycji,Integer nowyNumer){

        setId(noweId);
        setImie(noweImie);
        setNazwisko(noweNazwisko);
        setIdPozycji(noweIdPozycji);
        setNumer(nowyNumer);
        this.listaWydarzen= new ArrayList<Integer>();
    }

    public Zawodniczka(String noweImie, String noweNazwisko, Integer noweIdPozycji,Integer nowyNumer){

        setImie(noweImie);
        setNazwisko(noweNazwisko);
        setIdPozycji(noweIdPozycji);
        setNumer(nowyNumer);
        this.listaWydarzen = new ArrayList<Integer>();
    }

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

    public void setId(Integer noweId){
        this.idZawodniczki =noweId;
    }

    public void setImie(String noweImie){
        this.imie=noweImie;
    }

    public void setNazwisko(String noweNazwisko)
    {
        this.nazwisko=noweNazwisko;
    }

    public void setIdPozycji(Integer noweIdPozycji){
        this.idPozycji =noweIdPozycji;
    }

    public void setNumer(Integer nowyNumer){
        this.numer=nowyNumer;
    }

    public void dodajWydarzenieDoZawodniczki(Integer idWydarzenia){
        this.listaWydarzen.add(idWydarzenia);
    }

    private int liczbaWydarzenZawodniczki(){
        int liczba=0;
        return liczba;
    }

    public Integer jakaToPozycja(String pozycja)
    {
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
}
