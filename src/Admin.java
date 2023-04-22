
public class Admin {
    private final String userName;
    private final String password;

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
