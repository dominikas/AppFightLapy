package zawodniczka;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dominika Saide on 2017-11-07.
 */

public class WalidacjaDanychZawodniczki {

    public boolean czyImieLubNazwiskoOK(String imieLubNazwiskoZawodniczki)
    {
        String IMIENAZWISKO_PATTERN = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(IMIENAZWISKO_PATTERN);
        Matcher matcher = pattern.matcher(imieLubNazwiskoZawodniczki);
        return matcher.matches();
    }

    public boolean czyNumerOK(String numerZawodniczki)
    {
        String NUMER_PATTERN = "[0-9]+";

        Pattern pattern = Pattern.compile(NUMER_PATTERN);
        Matcher matcher = pattern.matcher(numerZawodniczki);
        return matcher.matches();
    }
}
