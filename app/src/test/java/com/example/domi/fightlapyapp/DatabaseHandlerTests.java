package com.example.domi.fightlapyapp;
import DatabaseHandler.*;
import Zawodniczka.*;
import Wydarzenie.*;
import MyExceptions.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.junit.Test;

import java.util.List;

/**
 * Created by Domi on 2017-07-16.
 */

public class DatabaseHandlerTests extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) /*throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception*/
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        Zawodniczka zawodniczkaTestowa = new Zawodniczka(1,"Anna","Nowa",2,55);
        db.dodajZawodniczke(zawodniczkaTestowa);

        Log.d("Reading: ", "Reading all contacts..");
        List<Zawodniczka> zawodniczki = db.getWszystkieZawodniczki();

        for (Zawodniczka zaw : zawodniczki) {
            String log = "Id: "+zaw.get_id()+" ,Name: " + zaw.get_imie() + " ,Numer: " + zaw.get_numer();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}