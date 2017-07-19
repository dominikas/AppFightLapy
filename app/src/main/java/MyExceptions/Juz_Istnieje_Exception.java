package MyExceptions;

/**
 * Created by Domi on 2017-07-10.
 */

public class Juz_Istnieje_Exception extends Exception{

    public Juz_Istnieje_Exception(String czego_id)
    {
        System.out.println("ID " + czego_id + " wystepuje juz na liscie");
    }
}
