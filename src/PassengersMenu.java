import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengersMenu {
    Scanner input = new Scanner(System.in);
    String[] fields = new String[3];

    public int printPassengerMenu() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("\t\t PASSENGER MENU OPTIONS");
        System.out.println("<1> Change password\n<2> Search flights tickets\n<3> Booking tickets\n<4> Ticket cancellation");
        System.out.println("<5> Booked tickets\n<6> Add charge \n<0> sing out");

        int command;
        command = input.nextInt();
        return command;
    }
    public void passengerMenu(int indexUser, Database database) {

        int command = printPassengerMenu();
        while (command != 0) {
            switch (command) {
                case 1 -> {
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <1> Change password >> \n");
                    changePasswordInfo(indexUser, database);
                }
                case 2 -> {
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <2> Search flights tickets >> \n");
                    searchFlightsInfo(database);
                }
                case 3 -> {
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <3> Booking tickets >> \n");
                    bookingTicketInfo(database, indexUser);
                }
                case 4 -> {
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <4> Ticket cancellation >> \n");
                    cancellationInfo(database, indexUser);
                }
                case 5 -> {
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <5> Booked tickets >>\n ");
                    bookedTickets(database, indexUser);
                }
                case 6 -> {
                    System.out.println("----------------------------------------------------");
                    System.out.println("\n\t <6> Add charge >> \n");
                    addChargeInfo(indexUser, database);
                }
                default -> {
                }
            }
            command = printPassengerMenu();
        }
    }
    public void changePasswordInfo(int index, Database database){
        System.out.print("\nPlease enter old password : \t");
        String password = input.next();
        if (!Objects.equals(password, database.passengers.passengersInfo[index].getPassword()))
            System.out.println("");

        System.out.print("\n Please enter the new password : \t");
        String newPassword1 = input.next();
        System.out.print("\nPlease enter the new password again :\t");
        String newPassword2 = input.next();

        while (!Objects.equals(newPassword1, newPassword2)){
            System.out.print("The passwords aren't match\n");
            System.out.print("\n Please enter the new password : \t");
            newPassword1 = input.next();
            System.out.print("\nPlease enter the new password again :\t");
            newPassword2 = input.next();
        }
        database.passengers.changePassword(index, newPassword1);
        System.out.print("\nYour password has been changed successfully :D\n");

    }
    public void searchFlightsInfo(Database database){

        System.out.print("Please enter origin :\t");
        fields[0] = input.next();

        System.out.print("\nPlease enter destination :\t");
        fields[1] = input.next();

        System.out.print("\nPlease enter date :\t");
        fields[2] = input.next();

        database.passengers.searchFlights(fields, database);
    }
    public void bookingTicketInfo(Database database, int indexUser){
        System.out.println("please enter flightId :\n");
        String flightId = input.next();
//        fields[0] = flightId;
//        database.passengers.searchFlights(fields,database);
//        charge is enough
//        seats is enough
        for (int i = 0; i < database.flights.count + 3; i++) {
            if (Objects.equals(flightId, database.flights.flightsInfo[i].getFlightId())) {
                if (database.passengers.passengersInfo[indexUser].getCredit() < Integer.parseInt(database.flights.flightsInfo[i].getPrice())){
                    System.out.println("Your charge is not enough ");
                    break;
                }

                if (Objects.equals(database.flights.flightsInfo[i].getSeats(), "0")){
                    System.out.println("");
                    break;
                }
                database.flights.flightsInfo[i].setSeats(String.valueOf(Integer.parseInt(database.flights.flightsInfo[i].getSeats()) - 1));
                database.passengers.passengersInfo[indexUser].setCredit(database.passengers.passengersInfo[indexUser].getCredit() - Integer.parseInt(database.flights.flightsInfo[i].getPrice()));
                database.tickets.selectTicketId(i, indexUser, database);

//                break;
                System.out.println("your ticketId is :\t" + database.tickets.ticketsInfo[database.tickets.countTickets - 1].getTicketId());
                System.out.println("your charge is :\t" + database.passengers.passengersInfo[indexUser].getCredit());
            }
        }

//        System.out.println("your ticketId is :\t" + database.tickets.tickets[database.tickets.k] );
//        System.out.println("your charge is :\t" + database.passengers.passengersInfo[database.menu.indexUser].getCredit());
    }
    public void cancellationInfo(Database database, int indexUser){
        System.out.print("please enter your ticketId : \t");
        String id = input.next();
        int index = database.tickets.searchTicketId(Integer.parseInt(id));
        String flightId = database.tickets.ticketsInfo[index].getFlight().getFlightId();

        for (int i = 0; i < database.flights.count; i++) {
            if(Objects.equals(database.flights.flightsInfo[i].getFlightId(), flightId)){

                database.flights.flightsInfo[i].setSeats(String.valueOf(Integer.parseInt(database.flights.flightsInfo[i].getSeats()) + 1));
                database.passengers.passengersInfo[indexUser].setCredit(database.passengers.passengersInfo[indexUser].getCredit() + Integer.parseInt(database.flights.flightsInfo[i].getPrice()));
                break;
            }
        }
        database.tickets.removeTicket(index);
    }
    public void addChargeInfo(int index, Database database){
        System.out.print("\nYour charge is :\t" + database.passengers.passengersInfo[index].getCredit());
        System.out.print("\nHow much do you want to add to your credit? \t");
        String value = input.next();
        boolean bool = false;
//        = database.adminMenu.checkInput(value);
//        while (!bool){
//            System.out.print("\nHow much do you want to add to your credit? \t");
//             value = input.next();
//            bool = database.adminMenu.checkInput(value);
//        }
        database.passengers.addCharge(index, Integer.parseInt(value));
        System.out.println("\nCurrently, your credit is :\t" + database.passengers.passengersInfo[index].getCredit());

    }
    public void bookedTickets(Database database, int indexUser){
        String userName = null;
//        for (int i = 0; i < database.passengers.passengersInfo.length; i++) {
//                if (i == database.menu.indexUser) {
//                    userName = database.passengers.passengersInfo[i].getUserName();
//                    break;
//                }
//        }
        userName = database.passengers.passengersInfo[indexUser].getUserName();
        System.out.print("\t________________________________________________________________________________");
        System.out.print("\n\t|  FlightId  |  origin  |  Destination  |  Date  |  Time  |  Price  |  seats  |  ticketId  |");

        for (int i = 0; i < database.tickets.ticketsInfo.length; i++) {
            if (database.tickets.ticketsInfo[i] == null) {
//                System.out.println("not found");
                break;
            } else {
                if (Objects.equals(userName, database.tickets.ticketsInfo[i].getPassenger().getUserName())) {

                    System.out.print("\n\t________________________________________________________________________________");
                    System.out.print("\n\t|\t" + database.tickets.ticketsInfo[i].getFlight().getFlightId() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getFlight().getOrigin() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getFlight().getDestination() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getFlight().getDate() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getFlight().getTime() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getFlight().getPrice() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getFlight().getSeats() +
                            "\t|\t" + database.tickets.ticketsInfo[i].getTicketId() + "\t|");

                }
            }
        }
    }


}
