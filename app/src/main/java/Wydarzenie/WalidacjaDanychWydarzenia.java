package Wydarzenie;

/**
 * Created by Dominika Saide on 2017-11-07.
 */

public class WalidacjaDanychWydarzenia {

    public boolean czyPoleOk(String wartoscPola) {
        boolean czyOk = true;

        if (wartoscPola.isEmpty()) {
            czyOk = false;
        } else if (wartoscPola.length() > 50) {
            czyOk = false;
        }

        return czyOk;
    }

}
