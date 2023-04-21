
public class Admin {

    private String userName;
    private String password;
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

    public Admin() {
    }


}
