import java.util.Objects;
import java.util.Scanner;

public class PassengersMenu {
    Scanner input = new Scanner(System.in);
    String[] fields = new String[3];

    public String printPassengerMenu() {

        System.out.println("\n----------------------------------<< PASSENGER MENU OPTIONS >>------------------------------------------");
        System.out.println("<< 1-Change password >>\n<< 2-Search flight tickets >>\n<< 3-Booking tickets >>\n<< 4-Ticket cancellation >>");
        System.out.println("<< 5-Booked tickets >>\n<< 6-Add charge >>\n<< 0-sing out >>");
        System.out.print(">>\t");

        return input.next();
    }

    public void passengerMenu(int indexUser, Database database) {

        String command = printPassengerMenu();

        while (!Objects.equals(command, "0")) {

            switch (command) {

                case "1" -> {
                    System.out.println("----------------------------------<< Change password >>------------------------------------------");
                    changePasswordInfo(indexUser, database);
                }
                case "2" -> {
                    System.out.println("----------------------------------<< Search flight tickets >>------------------------------------------");
                    searchFlightsInfo(database);
                }
                case "3" -> {
                    System.out.println("----------------------------------<< Booking tickets >>------------------------------------------");
                    bookingTicketInfo(database, indexUser);
                }
                case "4" -> {
                    System.out.println("----------------------------------<< Ticket cancellation >>------------------------------------------");
                    cancellationInfo(database, indexUser);
                }
                case "5" -> {
                    System.out.println("----------------------------------<< Booked tickets >>------------------------------------------");
                    bookedTickets(database, indexUser);
                }
                case "6" -> {
                    System.out.println("----------------------------------<< Add charge >>------------------------------------------");
                    addChargeInfo(indexUser, database);
                }
                default -> System.out.println("<< Please try again >>");
            }
            command = printPassengerMenu();
        }
    }

    public void changePasswordInfo(int index, Database database) {

        System.out.print("\n<< Please enter old password. >>\t");
        String password = input.next();

        while (!Objects.equals(password, database.passengers.passengersInfo[index].getPassword())) {

            System.out.println("-->> Your password is not correct -->>");
            System.out.print("\n<< Please try again >>\t");

            password = input.next();
        }

        System.out.println("====================================================================================================");
        System.out.print("<< Please enter the new password. >>\t");
        String newPassword1 = input.next();

        System.out.print("\n<< Please enter the new password again. >>\t");
        String newPassword2 = input.next();

        while (!Objects.equals(newPassword1, newPassword2)) {
            System.out.println("====================================================================================================");
            System.out.print("\n-->> The passwords aren't match -->>\n");
            System.out.print("\n<< Please enter the new password >>\t");

            newPassword1 = input.next();
            System.out.print("\n<< Please enter the new password again >>\t");
            newPassword2 = input.next();
        }
        database.passengers.changePassword(index, newPassword1);

        System.out.println("=====================================================================================================");
        System.out.print("\n<< Your password has been changed successfully. >>\n");

    }

    public void searchFlightsInfo(Database database) {

        System.out.print("<< Please enter origin >>\t");
        fields[0] = input.next();

        System.out.print("\n<< Please enter destination >>\t");
        fields[1] = input.next();

        System.out.print("\n<< Please enter date >>\t");
        fields[2] = input.next();

        database.passengers.searchFlights(fields, database);
    }

    public void bookingTicketInfo(Database database, int indexUser) {

        System.out.println("<< Please enter flightId >>\n");
        String flightId = input.next();

        for (int i = 0; i < database.flights.countFlights + 3; i++) {
            if (Objects.equals(flightId, database.flights.flightsInfo[i].getFlightId())) {
                if (database.passengers.passengersInfo[indexUser].getCredit() < Integer.parseInt(database.flights.flightsInfo[i].getPrice())) {
                    System.out.println("-->> Your charge is not enough. -->>");
                    break;
                }

                if (Objects.equals(database.flights.flightsInfo[i].getSeats(), "0")) {
                    System.out.println("-->> No seats exist so you can't book it. -->>");
                    break;
                }

                database.flights.flightsInfo[i].setSeats(String.valueOf(Integer.parseInt(database.flights.flightsInfo[i].getSeats()) - 1));

                database.passengers.passengersInfo[indexUser].setCredit(database.passengers.passengersInfo[indexUser].getCredit() - Integer.parseInt(database.flights.flightsInfo[i].getPrice()));

                database.tickets.selectTicketId(i, indexUser, database);

                System.out.println("<< Your ticketId is >> \t|" + database.tickets.ticketsInfo[database.tickets.countTickets - 1].getTicketId() + "|");
                System.out.println("<< Your charge is >> \t|" + database.passengers.passengersInfo[indexUser].getCredit() + "|");
            }
        }
    }

    public void cancellationInfo(Database database, int indexUser) {
        System.out.print("<< Please enter your ticketId >>\t");
        String id = input.next();

        int index = database.tickets.searchTicketId(Integer.parseInt(id));

        String flightId = database.tickets.ticketsInfo[index].getFlight().getFlightId();

        for (int i = 0; i < database.flights.countFlights; i++) {
            if (Objects.equals(database.flights.flightsInfo[i].getFlightId(), flightId)) {
                database.flights.flightsInfo[i].setSeats(String.valueOf(Integer.parseInt(database.flights.flightsInfo[i].getSeats()) + 1));
                database.passengers.passengersInfo[indexUser].setCredit(database.passengers.passengersInfo[indexUser].getCredit() + Integer.parseInt(database.flights.flightsInfo[i].getPrice()));
                break;
            }
        }

        database.tickets.removeTicket(index);
    }

    public void addChargeInfo(int index, Database database) {

        System.out.print("\n<< Your charge is >>\t|" + database.passengers.passengersInfo[index].getCredit() + "|");
        System.out.print("\n<< How much do you want to add to your credit? >>\t");

        String value = input.next();
//        boolean bool = false;
//        database.adminMenu.checkInput(value);
//        while (!bool){
//            System.out.print("\nHow much do you want to add to your credit? \t");
//             value = input.next();
//            bool = database.adminMenu.checkInput(value);
//        }

        database.passengers.addCharge(index, Integer.parseInt(value));
        System.out.println("\n-->> Currently, your credit is -->>\t|" + database.passengers.passengersInfo[index].getCredit() + "|");

    }

    public void bookedTickets(Database database, int indexUser) {
        String userName;
        userName = database.passengers.passengersInfo[indexUser].getUserName();

        System.out.print("\t________________________________________________________________________________");
        System.out.print("\n\t|  FlightId  |  origin  |  Destination  |  Date  |  Time  |  Price  |  seats  |  ticketId  |");

        int count = 0;

        for (int i = 0; i < database.tickets.ticketsInfo.length; i++) {
            if (database.tickets.ticketsInfo[i] == null) {
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

                    count++;

                }
            }
        }
        if (count == 0) {
            System.out.println("\n====================================================================================================");
            System.out.println("<< You have not booked any tickets yet. >>");
        }
    }
}
