package Wydarzenie;

import java.util.ArrayList;

/**
 * Created by Dominika Saide on 2017-07-10.
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
    private Integer obecnosc;

    public Wydarzenie() {
    }

    public Integer getObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(Integer obecnosc) {
        this.obecnosc = obecnosc;
    }

    public Wydarzenie(Integer noweIdWydarzenia, Integer noweIdTypuWyd, String nowaData, String nowaGodzina, String noweMiejsce, String nowyOpis, Integer nowaCena) {

        setIdWydarzenia(noweIdWydarzenia);
        setData(nowaData);
        setGodzina(nowaGodzina);
        setMiejsce(noweMiejsce);
        setOpis(nowyOpis);
        this.listaZawodniczek = new ArrayList<Integer>();
        setCena(nowaCena);
        setIdTypuWydarzenia(noweIdTypuWyd);
    }

    public Wydarzenie(Integer noweIdTypuWyd, String nowaData, String nowaGodzina, String noweMiejsce, String nowyOpis, Integer nowaCena) {

        setData(nowaData);
        setGodzina(nowaGodzina);
        setMiejsce(noweMiejsce);
        setOpis(nowyOpis);
        this.listaZawodniczek = new ArrayList<Integer>();
        setCena(nowaCena);
        setIdTypuWydarzenia(noweIdTypuWyd);
    }

    public Integer getIdWydarzenia() {
        return this.idWydarzenia;
    }

    public String getData() {
        return this.data;
    }

    public String getGodzina() {
        return this.godzina;
    }

    public String getMiejsce() {
        return this.miejsce;
    }

    public String getOpis() {
        return this.opis;
    }

    public ArrayList<Integer> getListaZawodniczek() {
        return this.listaZawodniczek;
    }

    public Integer getCena() {
        return this.cena;
    }

    public Integer getIdTypuWydarzenia() {
        return this.idTypuWydarzenia;
    }

    public void setIdWydarzenia(Integer nowe_id_wydarzenia) {
        this.idWydarzenia = nowe_id_wydarzenia;
    }

    public void setData(String nowa_data) {
        this.data = nowa_data;
    }

    public void setGodzina(String nowa_godzina) {
        this.godzina = nowa_godzina;
    }

    public void setMiejsce(String nowe_miejsce) {
        this.miejsce = nowe_miejsce;
    }

    public void setOpis(String nowy_opis) {
        this.opis = nowy_opis;
    }

    public void dodajZawodniczkeDoWydarzenia(Integer id_zawodniczki) {
        this.listaZawodniczek.add(id_zawodniczki);
    }

    public void setCena(Integer nowa_cena) {
        this.cena = nowa_cena;
    }

    public void setIdTypuWydarzenia(Integer nowy_typ_wydarzenia) {
        this.idTypuWydarzenia = nowy_typ_wydarzenia;
    }

    public Integer jakieToWydarzenie(String typWydarzenia) {

        Integer idWydarzenia = 0;

        switch (typWydarzenia) {
            case ("Trening"):
                idWydarzenia = 1;
                break;
            case ("Mecz"):
                idWydarzenia = 2;
                break;
            case ("Inne"):
                idWydarzenia = 3;
                break;
        }

        return idWydarzenia;
    }
}
