package MyExceptions;

/**
 * Created by Domi on 2017-07-10.
 */

public class Za_Dlugi_Exception extends Exception {

    public Za_Dlugi_Exception(String nazwa_pola){
        System.out.println("Wartosc pola " + nazwa_pola + " jest za dluga");
    }
}
