
public class Passenger {

    private String userName;
    private String password;
    private int credit ;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Passenger(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}

