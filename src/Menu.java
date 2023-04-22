import java.util.Objects;
import java.util.Scanner;
public class Menu {
    Scanner input = new Scanner(System.in);
    int countUser;
    int k = 0;

    /**
     * print the menu >>
     * @return command that has chosen by users and admin to sing in or sing up
     */
    public String printStartMenu(){

        System.out.println("------------------------------------<< MENU OPTIONS >>---------------------------------------------");
        System.out.println("\n\t\t\t\t<< 1-Sing in >> \t\t << 2-Sing up >>\t\t << 3-Exit >>");
        System.out.print(">>\t");

        return input.next();
    }

    /**
     *get the command from the start menu >>
     * / sing in by entering command : 1
     * / sing up by entering command : 2
     * / Exit by entering command : 3
     * @param database  the class that includes information.
     */

    public void mainMenu(Database database){

        String command = printStartMenu();
        while (!Objects.equals(command, "3")) {

            switch (command) {

                case "1" -> {
                    System.out.println("--------------------------------------<< Sing in >>-------------------------------------------------");
                    singIn(database);
                }
                case "2" -> {
                    System.out.println("--------------------------------------<< Sing up >>-------------------------------------------------");
                    countUser++;
                    singUp(database);
                }
                default ->
                    System.out.println("<< Please try again >>");
            }
            command = printStartMenu();

        }

    }
    int temp = 0;

    /**
     * The user or admin can enter their information  including username and password, if they have already signed up.
     * @param database the class that includes information
     */
    public void singIn(Database database){

        if (temp == 0)
            database.flights.defaultFlights();

        temp = 1;

        System.out.print("This is username >>\t");
        String userName = input.next();

        System.out.print("This is password >>\t");
        String password = input.next();

        boolean bool = false;

        if(Objects.equals(userName, database.admins.adminsInfo[0].getUserName()) && Objects.equals(password, database.admins.adminsInfo[0].getPassword())) {
            database.adminMenu.adminMenu(database);
            bool = true;
        }

        for(int i = 0; i < k; i++)
            if (Objects.equals(database.passengers.passengersInfo[i].getUserName(), userName) && Objects.equals(database.passengers.passengersInfo[i].getPassword(), password)) {
                database.passengersMenu.passengerMenu(i, database);
                bool = true;
            }

        if (!bool)
            System.out.println("\n<< No account found with this information. >>");


    }

    /**
     * users enter password and username to be created account for themselves.
     * @param database the class that includes information
     */
    public void singUp(Database database){

        System.out.print("This is username >>\t");
        String userName = input.next();

        boolean bool = checkUsername(userName, database);
        while (!bool){
            System.out.print("\n<< This username had used. Please enter new username >>\t");
            userName = input.next();
            bool = checkUsername(userName, database);
        }

        System.out.print("This is password >>\t");
        String password = input.next();

        database.passengers.passengersInfo[k++] = new Passenger(userName, password);

        System.out.println("\n<< Your information has been successfully registered. >>\n");
    }

    /**
     *check the username to be different from other users >>
     * @param data the passenger's username.
     * @param database the class that includes information.
     * @return false if the username exist otherwise true.
     */
    public boolean checkUsername(String data, Database database){
        for (int i = 0; i < k; i++) {
            if(Objects.equals(database.passengers.passengersInfo[i].getUserName(), data))
                return false;

        }
        return true;
    }
}

