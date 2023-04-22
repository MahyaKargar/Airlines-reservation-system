import java.util.Objects;
import java.util.Scanner;

public class PassengersMenu {
    Scanner input = new Scanner(System.in);
    String[] fields = new String[3];

    /**
     * print the passengers' menu >>
     * @return command that has chosen by passenger to do his activity.
     */
    public String printPassengerMenu() {

        System.out.println("\n----------------------------------<< PASSENGER MENU OPTIONS >>------------------------------------------");
        System.out.println("<< 1-Change password >>\n<< 2-Search flight tickets >>\n<< 3-Booking tickets >>\n<< 4-Ticket cancellation >>");
        System.out.println("<< 5-Booked tickets >>\n<< 6-Add charge >>\n<< 0-sing out >>");
        System.out.print(">>\t");

        return input.next();
    }

    /**
     * get the command from the passengers' menu >>
     *  / Change password by entering command : 1
     *  / Search flight tickets by entering command : 2
     *  / Booking tickets by entering command : 3
     *  / Ticket cancellation by entering command : 4
     *  / Booked tickets by entering command : 5
     *  / Add charge by entering command : 6
     * @param indexUser the cell number of passengers' array.
     * @param database the class that includes information and checking the format of data.
     */
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

    /**
     * change password >>
     *passenger enter the previous password then by entering the new password.
     * @param index the cell number of passengers' array.
     * @param database the class that includes information and checking the format of data.
     */

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

    /**
     * to search flights, get the flights' information from the passenger >>
     * @param database  the class that includes information.
     */
    public void searchFlightsInfo(Database database) {

        System.out.print("<< Please enter origin >>\t");
        fields[0] = input.next();

        System.out.print("\n<< Please enter destination >>\t");
        fields[1] = input.next();

        System.out.print("\n<< Please enter date >>\t");
        fields[2] = input.next();

        database.passengers.searchFlights(fields, database);
    }

    /**
     * to booking ticket, get the flights' information from the passenger >>
     * @param database  the class that includes information.
     * @param indexUser  the cell number of passengers' array.
     */

    public void bookingTicketInfo(Database database, int indexUser) {

        System.out.println("<< Please enter flightId >>\n");
        String flightId = input.next();

        for (int i = 0; i < database.flights.countFlights + 19; i++) {
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

    /**
     * to cancel ticket, get the flights' information from the passenger >>
     * @param database the class that includes information.
     * @param indexUser the cell number of passengers' array.
     */

    public void cancellationInfo(Database database, int indexUser) {
        System.out.print("<< Please enter your ticketId >>\t");
        String id = input.next();

        int index = database.tickets.searchTicketId(Integer.parseInt(id));
        if (index == -1) {
            System.out.println("There isn't any flights with this ticketId ") ;

        }
        else {

            String flightId = database.tickets.ticketsInfo[index].getFlight().getFlightId();

            for (int i = 0; i < database.flights.countFlights + 19; i++) {
                if (Objects.equals(database.flights.flightsInfo[i].getFlightId(), flightId)) {
                    database.flights.flightsInfo[i].setSeats(String.valueOf(Integer.parseInt(database.flights.flightsInfo[i].getSeats()) + 1));
                    database.passengers.passengersInfo[indexUser].setCredit(database.passengers.passengersInfo[indexUser].getCredit() + Integer.parseInt(database.flights.flightsInfo[i].getPrice()));
                    break;
                }
            }

            database.tickets.removeTicket(index);
        }
    }

    /**
     * to add charge , get the flights' information from the passenger >>
     * @param index  the cell number of passengers' array.
     * @param database the class that includes information.
     */
    public void addChargeInfo(int index, Database database) {

        System.out.print("\n<< Your charge is >>\t|" + database.passengers.passengersInfo[index].getCredit() + "|");
        System.out.print("\n<< How much do you want to add to your credit? >>\t");

        String value = input.next();
        boolean bool = false;

        bool = database.checkInput(value, 1);

        while (!bool){
            System.out.println("============================================================================================");
            System.out.print("<< Please try again >>\t");

            value = input.next();

            bool = database.checkInput(value, 1);
        }

        database.passengers.addCharge(index, Integer.parseInt(value));
        System.out.println("\n-->> Currently, your credit is -->>\t|" + database.passengers.passengersInfo[index].getCredit() + "|");

    }

    /**
     * Show the tickets booked by the passenger >>
     * @param database the class that includes information.
     * @param indexUser the cell number of passengers' array.
     */
    public void bookedTickets(Database database, int indexUser) {
        String userName;
        userName = database.passengers.passengersInfo[indexUser].getUserName();

        System.out.print("\t________________________________________________________________________________________________________________________________________________________________\n\t");
        System.out.printf("|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|" , "FlightId", "origin" , "Destination" , "Date" , "Time" , "Prise" , "Seats", "ticketId" );

        int count = 0;

        for (int i = 0; i < database.tickets.ticketsInfo.length; i++) {
            if (database.tickets.ticketsInfo[i] == null) {
                break;
            } else {
                if (Objects.equals(userName, database.tickets.ticketsInfo[i].getPassenger().getUserName())) {

                    System.out.print("\n\t________________________________________________________________________________________________________________________________________________________________\n\t");
                    System.out.printf("|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s",
                            database.tickets.ticketsInfo[i].getFlight().getFlightId(),
                            database.tickets.ticketsInfo[i].getFlight().getOrigin(),
                            database.tickets.ticketsInfo[i].getFlight().getDestination(),
                            database.tickets.ticketsInfo[i].getFlight().getDate(),
                            database.tickets.ticketsInfo[i].getFlight().getTime(),
                            database.tickets.ticketsInfo[i].getFlight().getPrice(),
                            database.tickets.ticketsInfo[i].getFlight().getSeats(),
                            database.tickets.ticketsInfo[i].getTicketId());

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
