package Wydarzenie;

import java.util.ArrayList;

import MyExceptions.Bledny_Format_Exception;
import MyExceptions.Juz_Istnieje_Exception;
import MyExceptions.Niedozwolony_Id_Exception;
import MyExceptions.Puste_Pole_Exception;
import MyExceptions.Za_Dlugi_Exception;

/**
 * Created by Domi on 2017-07-10.
 */

public class Wydarzenie {

    private Integer id_wydarzenia;
    private String data;
    private String godzina;
    private String miejsce;
    private String opis;
    private ArrayList<Integer> listaZawodniczek;
    private Integer cena;
    private Integer id_typu_wydarzenia;

    public Wydarzenie(){}

    public Wydarzenie(Integer nowe_id_wydarzenia, Integer nowe_id_typu_wyd, String nowa_data, String nowa_godzina, String nowe_miejsce, String nowy_opis, Integer nowa_cena){

        walidacjaDanych(nowe_id_wydarzenia, nowa_data, nowe_miejsce, nowy_opis, nowa_cena, nowe_id_typu_wyd);

        this.id_wydarzenia=nowe_id_wydarzenia;
        this.data=nowa_data;
        this.godzina=nowa_godzina;
        this.miejsce=nowe_miejsce;
        this.opis=nowy_opis;
        this.listaZawodniczek=new ArrayList<Integer>();
        this.cena=nowa_cena;
        this.id_typu_wydarzenia=nowe_id_typu_wyd;
    }

    public Wydarzenie(Integer nowe_id_typu_wyd, String nowa_data, String nowa_godzina, String nowe_miejsce, String nowy_opis, Integer nowa_cena){

        this.data=nowa_data;
        this.godzina=nowa_godzina;
        this.miejsce=nowe_miejsce;
        this.opis=nowy_opis;
        this.listaZawodniczek=new ArrayList<Integer>();
        this.cena=nowa_cena;
        this.id_typu_wydarzenia=nowe_id_typu_wyd;
    }


    private void walidacjaDanych(Integer nowe_id_wydarzenia, String nowa_data, String nowe_miejsce, String nowy_opis, Integer nowa_cena, Integer nowe_id_typu_wyd)
    {
        //  do dokonczenia
        /*
        if(nowe_id_wydarzenia==null)
        {
            throw new Puste_Pole_Exception("id wydarzenia");
        }

        //sprawdzenie za dlugiej wartosci
        if(nowe_id_wydarzenia.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("id wydarzenia");
        }


        if(nowa_data==null)
        {
            throw new Puste_Pole_Exception("data");
        }

        if(nowe_miejsce==null)
        {
            throw new Puste_Pole_Exception("miejsce");
        }

        if(nowe_miejsce.length()>50)
        {
            throw new Za_Dlugi_Exception("miejsce");
        }

        if(nowy_opis==null)
        {
            throw new Puste_Pole_Exception("opis");
        }

        if(nowy_opis.length()>100)
        {
            throw new Puste_Pole_Exception("opis");
        }

        if(id_zawodniczki==null)
        {
            throw new Puste_Pole_Exception("id zawodniczki");
        }

        if(id_zawodniczki.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("id zawodniczki");
        }

        if(this.listaZawodniczek.contains(id_zawodniczki))
        {
            throw new Juz_Istnieje_Exception("zawodniczki");
        }

        if(nowa_cena==null)
        {
            throw new Puste_Pole_Exception("cena");
        }

        if(nowy_typ_wydarzenia==null)
        {
            throw new Puste_Pole_Exception("id wydarzenia");
        }

        //id inne niz ze slownika
        if(nowy_typ_wydarzenia!=1 || nowy_typ_wydarzenia!=2)
        {
            throw new Niedozwolony_Id_Exception(nowy_typ_wydarzenia,"wydarzenia");
        }
        */
    }

    public Integer get_id_wydarzenia() {return this.id_wydarzenia; }

    public String get_data() {return this.data;}

    public String get_godzina() {return this.godzina;}

    public String get_miejsce() {return this.miejsce;}

    public String get_opis() {return this.opis;}

    public ArrayList<Integer> get_lista_zawodniczek() {return this.listaZawodniczek;}

    public Integer get_cena() {return this.cena;}

    public Integer get_id_typu_wydarzenia() {return this.id_typu_wydarzenia;}

    public void set_id_wydarzenia(Integer nowe_id_wydarzenia)
            //throws Puste_Pole_Exception, Za_Dlugi_Exception
            {
            /*
        //sprawdzenie nulla
        if(nowe_id_wydarzenia==null)
        {
            throw new Puste_Pole_Exception("id wydarzenia");
        }

        //sprawdzenie za dlugiej wartosci
        if(nowe_id_wydarzenia.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("id wydarzenia");
        }
*/
        this.id_wydarzenia=nowe_id_wydarzenia;
    }

    public void set_data(String nowa_data)
            //throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception
    {
    /*
        //sprawdzenie daty
        if(nowa_data==null)
        {
            throw new Puste_Pole_Exception("data");
        }

        //sprawdzenie formatu daty
*/
        this.data=nowa_data;
    }

    public void set_godzina(String nowa_godzina)
            //throws Puste_Pole_Exception, Za_Dlugi_Exception, Bledny_Format_Exception
    {
        /*
        //sprawdzenie daty
        if(nowa_godzina==null)
        {
            throw new Puste_Pole_Exception("data");
        }

        //sprawdzenie formatu daty
        */
        this.godzina=nowa_godzina;
    }

    public void set_miejsce(String nowe_miejsce)
            //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
        /*
        //sprawdzenie miejsca
        if(nowe_miejsce==null)
        {
            throw new Puste_Pole_Exception("miejsce");
        }

        if(nowe_miejsce.length()>50)
        {
            throw new Za_Dlugi_Exception("miejsce");
        }
*/
        this.miejsce=nowe_miejsce;
    }

    public void set_opis(String nowy_opis)
            //throws Puste_Pole_Exception, Za_Dlugi_Exception
    {
    /*
        //sprawdzenie nulla/dlugosci opisu
        if(nowy_opis==null)
        {
            throw new Puste_Pole_Exception("opis");
        }

        if(nowy_opis.length()>100)
        {
            throw new Za_Dlugi_Exception("opis");
        }
*/
        this.opis=nowy_opis;}

    public void dodajZawodniczkeDoWydarzenia(Integer id_zawodniczki)
            //throws Puste_Pole_Exception, Za_Dlugi_Exception, Juz_Istnieje_Exception
    {
        /*
        //sprawdzenie nulla/czy id jest juz na liscie
        if(id_zawodniczki==null)
        {
            throw new Puste_Pole_Exception("id zawodniczki");
        }

        if(id_zawodniczki.toString().length()>3)
        {
            throw new Za_Dlugi_Exception("id zawodniczki");
        }

        if(this.listaZawodniczek.contains(id_zawodniczki))
        {
            throw new Juz_Istnieje_Exception("zawodniczki");
        }
*/
        this.listaZawodniczek.add(id_zawodniczki);
    }

    public void set_cena(Integer nowa_cena)
            //throws Puste_Pole_Exception
    {
        /*
        //sprawdzenie nulla
        if(nowa_cena==null)
        {
            throw new Puste_Pole_Exception("cena");
        }
        */
        this.cena=nowa_cena;
    }

    public void set_id_typu_wydarzenia(Integer nowy_typ_wydarzenia)
    //        throws Puste_Pole_Exception, Niedozwolony_Id_Exception
    {
      /*
        //sprawdzenie nulla
        if(nowy_typ_wydarzenia==null)
        {
            throw new Puste_Pole_Exception("id wydarzenia");
        }

        //id inne niz ze slownika
        if(nowy_typ_wydarzenia!=1 || nowy_typ_wydarzenia!=2)
        {
            throw new Niedozwolony_Id_Exception(nowy_typ_wydarzenia,"wydarzenia");
        }
*/
        this.id_typu_wydarzenia=nowy_typ_wydarzenia;
    }

}
