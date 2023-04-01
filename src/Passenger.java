import java.util.Scanner;

public class Passenger {
    Scanner input = new Scanner(System.in);

    private String password;
    private String user;

    private int value;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Passenger(String password, String user) {
        this.password = password;
        this.user = user;
    }

    public Passenger() {
    }

    public int printPassengerMenu() {
        System.out.println("----------------------------------------------------");
        System.out.println("\t\t PASSENGER MENU OPTIONS");
        System.out.println("<1> Change password\n<2> Search flights tickets\n<3> Booking tickets\n<4> Ticket cancellation");
        System.out.println("<5> Booked tickets\n<6> Add charge \n<0> sing out");

        int command;
        command = input.nextInt();
        return command;
    }

    public void passengerMenu() {

        int command = printPassengerMenu();
        while (command != 7 && command != 0) {
            switch (command) {
                case 1:
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <1> Change password >> \n");
                    break;
                case 2:
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <2> Search flights tickets >> \n");
                    break;
                case 3:
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <3> Booking tickets >> \n");
                    break;
                case 4:
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <4> Ticket cancellation >> \n");
                    break;
                case 5:
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <5> Booked tickets >>\n ");
                    break;
                case 6:
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <6> Add charge >> \n");
                    break;
                default:
                    break;
            }
            command = printPassengerMenu();
        }
    }
}

