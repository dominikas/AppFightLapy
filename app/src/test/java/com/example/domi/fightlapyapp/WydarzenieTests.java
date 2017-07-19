package com.example.domi.fightlapyapp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import MyExceptions.*;
import Zawodniczka.*;
import Wydarzenie.*;

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

        assertEquals("1", wydarzenie.get_id_wydarzenia().toString());
        assertEquals("2017-01-01",wydarzenie.get_data());
        assertEquals("Skarzynskiego 8", wydarzenie.get_miejsce());
        assertEquals("opis",wydarzenie.get_opis());
        assertEquals("15",wydarzenie.get_cena().toString());
        assertEquals("1",wydarzenie.get_id_typu_wydarzenia().toString());

    }
// walidacje danych dokonczyc
    @Test(expected = Puste_Pole_Exception.class)
    public void id_wydarzenia_null() throws Exception{
        wydarzenie.set_id_wydarzenia(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void data_null() throws Exception{
        wydarzenie.set_data(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void miejsce_null() throws Exception{
        wydarzenie.set_miejsce(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void opis_null() throws Exception{
        wydarzenie.set_opis(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void cena_null() throws Exception{
        wydarzenie.set_cena(null);
    }

    @Test(expected = Puste_Pole_Exception.class)
    public void id_wydarzenia_typu_null() throws Exception{
        wydarzenie.set_id_typu_wydarzenia(null);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void id_wydarzenia_za_dlugie() throws Exception{
        wydarzenie.set_id_wydarzenia(1234);
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void miejsce_za_dlugie() throws Exception{
        wydarzenie.set_miejsce("dedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigei");
    }

    @Test(expected = Za_Dlugi_Exception.class)
    public void opis_za_dlugi() throws Exception{
        wydarzenie.set_opis("dedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigeidedfefefefefefwefFEWOGKWemgerigei");
    }

    @Test(expected = Niedozwolony_Id_Exception.class)
    public void id_typu_wydarzenia_spoza_slownika() throws Exception{
        wydarzenie.set_id_typu_wydarzenia(1234);
    }

    //testy listy zawodniczek
}
