package myExceptions;

/**
 * Created by Domi on 2017-07-10.
 */

public class Puste_Pole_Exception extends Exception {

    public Puste_Pole_Exception(String nazwa_pola){
        System.out.println("Pole " + nazwa_pola + " nie moze byc puste");
    }
}
