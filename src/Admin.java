import java.util.Scanner;

public class Admin {
    Scanner input = new Scanner(System.in);
    private String password = "44211857" ;
    private String userName = "mahya2004" ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Admin(String password, String userName) {
//        this.password = password;
//        this.userName = userName;
//    }

    public Admin() {
    }

    public void adminMenu(){
        System.out.println("\t\t Admin Menu Options\n<1> Add\n<2> Update\n<3> Remove\n<4> Flight schedules\n<0> sing out");
        int command;
        command = input.nextInt();
//        return command;
        while (command != 5 && command != 0) {
            switch (command) {
                case 1:
                    System.out.println("Add");
                    break;
                case 2:
                    System.out.println("Update");
                    break;
                case 3:
                    System.out.println("Remove");
                    break;
                case 4:
                    System.out.println("Flight schedules");
                    break;
                default:
                    break;

            }
            System.out.println("\t\t Admin Menu Options\n<1> Add\n<2> Update\n <3> Remove\n <4> Flight schedules\n <0> sing out");
            command = input.nextInt();
        }
    }
}

