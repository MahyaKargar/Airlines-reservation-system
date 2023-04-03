import java.util.Objects;
import java.util.Scanner;

public class Admin {
    Scanner input = new Scanner(System.in);
    Flight [] flights = new Flight[100];
    int count = 1;


    private String userName = "mahya2004" ;
    private String password = "44211857" ;

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

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Admin() {
    }

    public void adminMenu(){
        flights();
        System.out.println("\t\t Admin Menu Options\n<1> Add\n<2> Update\n<3> Remove\n<4> Flight schedules\n<0> sing out");
        int command;
        command = input.nextInt();
        while (command != 0) {
            switch (command) {
                case 1:
                    System.out.println("Add");
                    int countAdd;
                    System.out.print("How many flights do you one to add? \t");
                    countAdd = input.nextInt();
                    addFlights(countAdd);
                    flightSchedule();
                    break;
                case 2:
                    System.out.println("Update");
                    flightSchedule();
                    System.out.print("How many flights do you one to update? \t");
                    int countUpdate = input.nextInt();
                    updateFlights(countUpdate);
                    break;
                case 3:
                    System.out.println("Remove");
                    System.out.print("How many flights do you one to remove? \t");
                    int countRemove = input.nextInt();
                    removeFlights(countRemove);
                    flightSchedule();
                    break;
                case 4:
                    System.out.println("Flight schedules");
                    flightSchedule();
                    break;
                default:
                    break;

            }
            System.out.println("\t\t Admin Menu Options\n<1> Add\n<2> Update\n<3> Remove\n<4> Flight schedules\n<0> sing out");
            command = input.nextInt();
        }
    }

    public void flights(){
        flights[0] = new Flight("WX-12","Yazd", "Tehran", "1402-2-12", "12:30", 700000, 51);
        flights[1] = new Flight("WZ-15", "Mashhad", "shiraz", "1402-2-13", "08:30", 900000, 220);
        flights[2] = new Flight("BG-22", "Shiraz", "Tabris", "1402-2-13", "22:30", 1100000, 36);

    }

    public void addFlights(int count1) {
        for (int i = 0; i < count1; i++) {
        System.out.print("\nPlease enter flightId :\t");
        String flightId = input.next();
        System.out.print("\nPlease enter origin :\t");
        String origin = input.next();
        System.out.print("\nPlease enter destination :\t");
        String destination = input.next();
        System.out.print("\nPlease enter date:\t");
        String date = input.next();
        System.out.print("\nPlease enter time :\t");
        String time = input.next();
        System.out.print("\nPlease enter price :\t");
        int price = input.nextInt();
        System.out.print("\nPlease enter seats :\t");
        int seats = input.nextInt();
        flights[2 + count] = new Flight(flightId, origin, destination, date, time, price, seats);
        count++;
        }
    }


    public void updateFlights(int count1){
        for (int k = 0; k < count1; k++) {
            System.out.print("Please enter FlightId :\t");
            String flightId = input.next();
            for (int i = 0; i < 2 + count; i++) {
                if (Objects.equals(flights[i].getFlightId(), flightId)) {
                    System.out.print("\n Which of the following fields do you want to change? \t");
                    System.out.print("\n 1-FlightId\t2-Origin\t3-Destination\t4-Date\t5-Time\t6-Price\t7-Seats");
                    int number = input.nextInt();
                    String data;
                    int info;
                    switch (number) {
                        case 1 -> {
                            System.out.print("\n Currently, the flightId is " + flights[i].getFlightId());
                            System.out.print("\nPlease enter new flightId: \t");
                            data = input.next();
                            flights[i].setFlightId(data);
                            flightSchedule();
                        }
                        case 2 -> {
                            System.out.print("\n Currently, the origin is " + flights[i].getOrigin());
                            System.out.print("\nPlease enter new origin: \t");
                            data = input.next();
                            flights[i].setOrigin(data);
                            flightSchedule();
                        }
                        case 3 -> {
                            System.out.print("\n Currently, the destination is " + flights[i].getDestination());
                            System.out.print("\nPlease enter new destination: \t");
                            data = input.next();
                            flights[i].setDestination(data);
                            flightSchedule();
                        }
                        case 4 -> {
                            System.out.print("\n Currently, the date is " + flights[i].getDate());
                            System.out.print("\nPlease enter new flightId: \t");
                            data = input.next();
                            flights[i].setDate(data);
                            flightSchedule();
                        }
                        case 5 -> {
                            System.out.print("\n Currently, the time is " + flights[i].getTime());
                            System.out.print("\nPlease enter new time: \t");
                            data = input.next();
                            flights[i].setTime(data);
                            flightSchedule();
                        }
                        case 6 -> {
                            System.out.print("\n Currently, the price is " + flights[i].getPrice());
                            System.out.print("\nPlease enter new price: \t");
                            info = input.nextInt();
                            flights[i].setPrice(info);
                            flightSchedule();
                        }
                        case 7 -> {
                            System.out.print("\n Currently, the seats is " + flights[i].getSeats());
                            System.out.print("\nPlease enter new seats: \t");
                            info = input.nextInt();
                            flights[i].setSeats(info);
                            flightSchedule();
                        }
                        default -> {
                        }
                    }

                }
            }
        }
    }

    public void removeFlights(int count1) {
        for (int i = 0; i < count1; i++) {

            System.out.print("Please enter FlightId :\t");
            String flightId = input.next();
            int index;
            for (int j = 0; j < 2 + count; j++) {
                if (Objects.equals(flights[j].getFlightId(), flightId)) {
                    index = j;
                    for (int k = index; k < 2 + count; k++) {
                        flights[k] = flights[k + 1];

                    }
                    count--;
                    break;
                }

            }
        }
    }
    public void flightSchedule(){
        System.out.print("\t___________________________________________________________________________");
        System.out.print("\n\t|  FlightId  |  origin  |  Destination  |  Date  |  Time  |  Price  |  seats  |");
        for (int i = 0; i < 2 + count; i++) {
            System.out.print("\n\t___________________________________________________________________________");
            System.out.print("\n\t|\t" + flights[i].getFlightId() + "\t|\t" + flights[i].getOrigin() + "\t|\t" + flights[i].getDestination() + "\t|\t"
                    + flights[i].getDate() + "\t|\t" + flights[i].getTime() + "\t|\t" + flights[i].getPrice() + "\t|\t" + flights[i].getSeats() + "\t|");

        }
        System.out.println();
    }

}




