package MyExceptions;

/**
 * Created by Domi on 2017-07-10.
 */

public class Bledny_Format_Exception extends Exception {

    public Bledny_Format_Exception(String nazwa_pola)
    {
        System.out.println("Wartosc pola " + nazwa_pola + " ma bledny format");
    }
}
