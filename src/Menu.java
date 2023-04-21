import java.util.Objects;
import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    int count;
    int k = 0;
//    int indexUser;
    public int printStartMenu(){
        System.out.println("\t MENU OPTIONS \n \t1-sing in \t 2-sing up\t 3- Exit");
        int command;
        command = input.nextInt();
        return command;
    }

    public void mainMenu(Database database){
        int command = printStartMenu();

        while (command != 3) {

            switch (command) {
                case 1 -> {
                    System.out.println("\t<< sing in >>");
                    singIn(database);
                }
                case 2 -> {
                    System.out.println("\t << sing up >>");
                    count++;
                    singUp(database);
                }
                default -> {
                }
            }

            command = printStartMenu();

        }

    }
    public void singUp(Database database){
        System.out.print("This is username >>\t");
        String userName = input.next();
        boolean bool = checkUsername(userName, database);
        while (!bool){
            System.out.println("This username had used. Please enter new username");
            userName = input.next();
            bool = checkUsername(userName, database);
        }
        System.out.print("This is password >>\t");
        String password = input.next();
        database.passengers.passengersInfo[k++] = new Passenger(userName, password);
    }
    int temp = 0;
    public void singIn(Database database){
        if (temp == 0)
            database.flights.defaultFlights();
        temp = 1;
        System.out.print("\nthis is username >> \t");
        String userName = input.next();

        System.out.print("this is password >>\t");
        String password = input.next();

        if(Objects.equals(userName, database.admins.adminsInfo[0].getUserName()) && Objects.equals(password, database.admins.adminsInfo[0].getPassword()))
            database.adminMenu.adminMenu(database);

        for(int i = 0; i < k; i++)
            if (Objects.equals(database.passengers.passengersInfo[i].getUserName(), userName) && Objects.equals(database.passengers.passengersInfo[i].getPassword(), password))
                database.passengersMenu.passengerMenu(i, database);
    }

    public boolean checkUsername(String username, Database database){
        for (int i = 0; i < k; i++) {
            if(Objects.equals(database.passengers.passengersInfo[i].getUserName(), username))
                return false;

        }
        return true;
    }

}

