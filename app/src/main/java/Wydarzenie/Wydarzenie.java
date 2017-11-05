package Wydarzenie;

import java.util.ArrayList;

/**
 * Created by Domi on 2017-07-10.
 */

public class Wydarzenie {

    private Integer idWydarzenia;
    private String data;
    private String godzina;
    private String miejsce;
    private String opis;
    private ArrayList<Integer> listaZawodniczek;
    private Integer cena;
    private Integer idTypuWydarzenia;

    public Wydarzenie(){}

    public Wydarzenie(Integer noweIdWydarzenia, Integer noweIdTypuWyd, String nowaData, String nowaGodzina, String noweMiejsce, String nowyOpis, Integer nowaCena){

        walidacjaDanych(noweIdWydarzenia, nowaData, noweMiejsce, nowyOpis, nowaCena, noweIdTypuWyd);

        this.idWydarzenia =noweIdWydarzenia;
        this.data=nowaData;
        this.godzina=nowaGodzina;
        this.miejsce=noweMiejsce;
        this.opis=nowyOpis;
        this.listaZawodniczek=new ArrayList<Integer>();
        this.cena=nowaCena;
        this.idTypuWydarzenia =noweIdTypuWyd;
    }

    public Wydarzenie(Integer noweIdTypuWyd, String nowaData, String nowaGodzina, String noweMiejsce, String nowyOpis, Integer nowaCena){

        this.data=nowaData;
        this.godzina=nowaGodzina;
        this.miejsce=noweMiejsce;
        this.opis=nowyOpis;
        this.listaZawodniczek=new ArrayList<Integer>();
        this.cena=nowaCena;
        this.idTypuWydarzenia =noweIdTypuWyd;
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

    public Integer getIdWydarzenia() {return this.idWydarzenia; }

    public String getData() {return this.data;}

    public String getGodzina() {return this.godzina;}

    public String getMiejsce() {return this.miejsce;}

    public String getOpis() {return this.opis;}

    public ArrayList<Integer> getListaZawodniczek() {return this.listaZawodniczek;}

    public Integer getCena() {return this.cena;}

    public Integer getIdTypuWydarzenia() {return this.idTypuWydarzenia;}

    public void setIdWydarzenia(Integer nowe_id_wydarzenia)
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
        this.idWydarzenia =nowe_id_wydarzenia;
    }

    public void setData(String nowa_data)
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

    public void setGodzina(String nowa_godzina)
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

    public void setMiejsce(String nowe_miejsce)
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

    public void setOpis(String nowy_opis)
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

    public void setCena(Integer nowa_cena)
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

    public void setIdTypuWydarzenia(Integer nowy_typ_wydarzenia)
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
        this.idTypuWydarzenia =nowy_typ_wydarzenia;
    }

}
