import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    Passenger [] info = new Passenger[100];
    Scanner input = new Scanner(System.in);
    Admin adminInfo = new Admin();
    Passenger data = new Passenger();

    int k = 0;
    //    Admin adminMenu = new Admin("44211857", "mahya2004");
    public int printStartMenu(){
//       System.out.println("\tWELCOME TO AIRLINE RESERVATION SYSTEM ");
        System.out.println("\t MENU OPTIONS \n \t1-sing in \t 2-sing up\t 3- Exit");
        int command;
        command = input.nextInt();
        return command;
    }

    public void mainMenu(){
        int command = printStartMenu();

        while (command != 3) {

            switch (command) {
                case 1:
                    System.out.println("\t<< sing in >>");
                    information2();
                    break;
                case 2:
                    System.out.println("\t << sing up >>");
                    information();
                    break;

                default:
                    break;
            }

            command = printStartMenu();

        }

    }
    //   int i = 0;
    public void information(){
//        Scanner input = new Scanner(System.in);
        System.out.print("this is password >> \t");
        String password = input.next();
        System.out.print("this is user >>\t");
        String user = input.next();
        info[k++] = new Passenger(password, user);
        System.out.println("*************************************");
        for (int i = 0; i < k; i++) {
            System.out.println("this is password  " + info[i].getPassword() );
            System.out.println("this is user " + info[i].getUser());

        }

    }
    public void information2(){
        System.out.print("\nthis is password >> \t");
        String password = input.next();
        System.out.print("this is user >>\t");
        String user = input.next();

        if(Objects.equals(password, adminInfo.getPassword()) && Objects.equals(user, adminInfo.getUserName()))
            adminInfo.adminMenu();

        for(int i = 0; i < k; i++)
            if (Objects.equals(info[i].getPassword(), password) && Objects.equals(info[i].getUser(), user))
                data.passengerMenu();

    }


}

