package myExceptions;

/**
 * Created by Domi on 2017-07-10.
 */

public class Niedozwolony_Id_Exception extends Exception {

    public Niedozwolony_Id_Exception(Integer id, String czego_id)
    {
        System.out.println("ID " + id + " jest niedozwolone dla " + czego_id);
    }
}
