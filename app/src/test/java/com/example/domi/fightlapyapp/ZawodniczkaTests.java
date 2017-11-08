package com.example.domi.fightlapyapp;

import org.junit.Before;
import org.junit.Test;

import myExceptions.*;
import zawodniczka.*;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ZawodniczkaTests {

    private Zawodniczka nowa;

    @Before
public void nowa_zawodniczka() throws Exception {

         nowa = new Zawodniczka(0, "Anna", "Kowalska", 1, 2);
}

    @Test
    public void stworzenie_nowej_zawodniczki() throws Exception {

        assertEquals("0", (nowa.getId()).toString());
        assertEquals("Anna", nowa.getImie());
        assertEquals("Kowalska", nowa.getNazwisko());
        assertEquals("1", (nowa.getIdPozycji()).toString());
        assertEquals("2", (nowa.getNumer().toString()));
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void idNull_stworzenie_nowej_zawodniczki() throws Exception {
    //   Zawodniczka nowa = new Zawodniczka(null, "Anna", "Kowalska", 1, 2);
        nowa.setId(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void imieNull_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, null, "Kowalska", 1, 2);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void nazwiskoNull_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "Anna", null, 1, 2);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void pozycjaNull_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "Anna", "Nowak", null, 5);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void numerNull_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "Anna", "Nowak", 5, null);
    }

    @Test(expected = Bledny_Format_Exception.class)
    public void imie_z_cyframi_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "oko;", "Nowak", 5, 8);
    }

    @Test(expected = Bledny_Format_Exception.class)
    public void nazwisko_z_cyframi_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "oko", "N8owak", 5, 8);
    }

    @Test
    public void dodanie_wydarzenia_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "oko", "Nowak", 5, 8);

        nowa.dodajWydarzenieDoZawodniczki(858);
        assertEquals("858",(nowa.getListaWydarzen().get(0)).toString());
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void id_za_dlugie_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(1122, "Anna", "Kowalska", 1, 2);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void imie_za_dlugie_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "fwewegwegegrwegeargaerafwewegwegegrwegeargaerafwewegwegeg", "Kowalska", 1, 2);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void nazwisko_za_dlugie_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "Anna", "fwewegwegegrwegeargaerafwewegwegegrwegeargaerafwewegwegeg", 1, 2);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void pozycja_za_dluga_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "Anna", "Nowak", 1234, 5);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void numer_za_dlugi_stworzenie_nowej_zawodniczki() throws Exception {
        Zawodniczka nowa = new Zawodniczka(3, "Anna", "Nowak", 5, 12345);
    }
}