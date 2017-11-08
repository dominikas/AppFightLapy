package com.example.domi.fightlapyapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import myExceptions.*;
import wydarzenie.*;

import static org.junit.Assert.*;

/**
 * Created by Domi on 2017-07-10.
 */

public class WydarzenieTests {
    Wydarzenie wydarzenie;
    @Rule public ExpectedException thrown = ExpectedException.none();

    @Before
    public void stworzenie_wydarzenia(){
         wydarzenie = new Wydarzenie(1,"2017-01-01","Skarzynskiego 8", "opis", 15, 1);
        ///return wydarzenie;
    }

    @Test
    public void ok_dodanie_wydarzenia() throws Exception{
        //Wydarzenie wydarzenie = new Wydarzenie(1,"2017-01-01","Skarzynskiego 8", "opis", 15, 1);

        assertEquals("1", wydarzenie.getIdWydarzenia().toString());
        assertEquals("2017-01-01",wydarzenie.getData());
        assertEquals("Skarzynskiego 8", wydarzenie.getMiejsce());
        assertEquals("opis",wydarzenie.getOpis());
        assertEquals("15",wydarzenie.getCena().toString());
        assertEquals("1",wydarzenie.getIdTypuWydarzenia().toString());

    }
// walidacje danych dokonczyc
    @Test(expected = Puste_Pole_Exception.class)
    public void id_wydarzenia_null() throws Exception{
        wydarzenie.setIdWydarzenia(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void data_null() throws Exception{
        wydarzenie.setData(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void miejsce_null() throws Exception{
        wydarzenie.setMiejsce(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void opis_null() throws Exception{
        wydarzenie.setOpis(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void cena_null() throws Exception{
        wydarzenie.setCena(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void id_wydarzenia_typu_null() throws Exception{
        wydarzenie.setIdTypuWydarzenia(null);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void id_wydarzenia_za_dlugie() throws Exception{
        wydarzenie.setIdWydarzenia(1234);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void miejsce_za_dlugie() throws Exception{
        wydarzenie.setMiejsce("dedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigei");
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void opis_za_dlugi() throws Exception{
        wydarzenie.setOpis("dedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigei");
    }

    @Test(expected = Niedozwolony_Id_Exception.class)
    public void id_typu_wydarzenia_spoza_slownika() throws Exception{
        wydarzenie.setIdTypuWydarzenia(1234);
    }

    //testy listy zawodniczek
}
