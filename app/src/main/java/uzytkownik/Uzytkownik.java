package uzytkownik;

/**
 * Created by Dominika Saide on 2017-11-10.
 */

public class Uzytkownik {

    private Integer userID;
    private String userEmail;
    private Integer zawodniczkaID;
    private Integer userRole;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer userID, String userEmail, Integer zawodniczkaID, Integer userRole) {

        setUserID(userID);
        setUserEmail(userEmail);
        setZawodniczkaID(zawodniczkaID);
        setUserRole(userRole);
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getZawodniczkaID() {
        return zawodniczkaID;
    }

    public void setZawodniczkaID(Integer zawodniczkaID) {
        this.zawodniczkaID = zawodniczkaID;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}
