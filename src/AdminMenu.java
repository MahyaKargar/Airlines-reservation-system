import java.util.Objects;
import java.util.Scanner;
public class AdminMenu {
    Scanner input = new Scanner(System.in);

    /**
     * print the menu of admin >>
     * @return command that has chosen by admin to do his activity.
     */
    public String printAdminMenu(){

        System.out.println("----------------------------------<< ADMIN MENU OPTIONS >>------------------------------------------");
        System.out.println("<< 1-Add >>\n<< 2-Update >>\n<< 3-Remove >>\n<< 4-Flight schedules >>\n<< 0-Sing out >>");
        System.out.print(">>\t");

        return input.next();
    }

    /**
     * get the command from the admin menu >>
     * / add flights by entering command : 1
     * / update flights by entering command : 2
     * / remove Flights by entering command : 3
     * / Flight schedules by entering command : 4
     * @param database  the class that includes information.
     */
    public void adminMenu(Database database){

        String command = printAdminMenu();

        while (!Objects.equals(command, "0")) {
            switch (command) {
                case "1" -> {
                    System.out.println("-----------------------------------------<< Add Flights >>-------------------------------------------\n");
                    addInfo(database);
                }
                case "2" -> {
                    System.out.println("------------------------------------<< Update Flights >>----------------------------------------------\n");
                    updateInfo(database);
                }
                case "3" -> {
                    System.out.println("------------------------------------<< Remove Flights >>----------------------------------------------\n");
                    removeInfo(database);
                }
                case "4" -> {
                    System.out.println("------------------------------------<< Flight schedules >>----------------------------------------------\n");
                    flightSchedule(database);
                    System.out.println();
                }
                default ->
                    System.out.println("<< Please try again >>");
            }
           command = printAdminMenu();
        }
    }

    /**
     *to add each flight, get the flight's information from the admin and checking the format of data >>
     * @param database the class that includes information and checking the format of data.
     */
    public void addInfo(Database database) {

        System.out.print("<< How many flights do you one to add? >>\t");

        String countAdd = input.next();

        boolean bool;
        countAdd = database.checkData(countAdd, 1);

        for (int i = 0; i < Integer.parseInt(countAdd); i++) {

            System.out.print("\n<< Please enter flightId to add >>\t");

            String flightId = input.next();

            bool = checkFlightId(flightId, database);
            while (!bool){
                System.out.println("============================================================================================");
                System.out.print("This is a repetitive flightId. Please try again >>\t");

                flightId = input.next();
                bool = checkFlightId(flightId, database);
            }

            System.out.println("============================================================================================\n");
            System.out.print("<< Please enter origin to add >>\t");

            String origin = input.next();
            origin = database.checkData(origin,2);

            System.out.println("============================================================================================\n");
            System.out.print("<< Please enter destination to add >>\t");

            String destination = input.next();
            bool = database.checkInput(destination, 2);
            while (!bool || Objects.equals(origin, destination)){
                System.out.println("============================================================================================");
                System.out.print("<< Please enter origin to add >>\t");

                destination = input.next();
                bool = database.checkInput(destination, 2);
            }

            System.out.println("============================================================================================\n");
            System.out.print("<< Please enter date >>\t");

            String date = input.next();
            bool = database.checkDate(date);
            while (!bool){
                System.out.println("============================================================================================");
                System.out.print("<< Please try again >>\t");

                date = input.next();
                bool = database.checkDate(date);
            }

            System.out.println("============================================================================================");
            System.out.print("\n<< Please enter time >>\t");

            String time = input.next();
            bool = database.checkTime(time);
            while (!bool){
                System.out.println("============================================================================================");
                System.out.print("<< Please try again >>\t");

                time = input.next();
                bool = database.checkTime(time);
            }

            System.out.println("============================================================================================\n");
            System.out.print("<< Please enter price >>\t");

            String price = input.next();
            price = database.checkData(price, 1);
            while (Objects.equals(price, "0")) {

                System.out.println("============================================================================================");
                System.out.print("<< Please try again >>\t");

                price = input.next();
                price = database.checkData(price, 1);
            }

            System.out.println("============================================================================================\n");
            System.out.print("<< Please enter seats >>\t");

            String seats = input.next();
            seats = database.checkData(seats, 1);
            while (Objects.equals(seats, "0")) {

                System.out.println("============================================================================================");
                System.out.print("<< Please try again >>\t");

                seats = input.next();
                seats = database.checkData(seats, 1);
            }

            database.flights.addFlights(flightId, origin, destination, date, time, price, seats);

            System.out.println("\n<< The desired flight was registered successfully. >>\n");
        }
    }

    /**
     *to update each flight, get the flight's information from the admin and checking the format of data >>
     * @param database the class that includes information and checking the format of data.
     */
    public void updateInfo(Database database){
        boolean bool ;

        System.out.print("<< How many flights do you one to update? >>\t");
        String countUpdate = input.next();
        countUpdate = database.checkData(countUpdate,1);

        for (int k = 0; k < Integer.parseInt(countUpdate); k++) {

            System.out.print("\n\t-->> Update flight -->>\n");
            System.out.print("\n<< Please enter FlightId >>\t");
            String flightId = input.next();

            bool = checkFlightId(flightId, database);
            while (bool) {
                System.out.println("=====================================================================================================");
                System.out.println("<< The flightId is not found >>");
                System.out.print("<< Please enter again >>\t");
                flightId = input.next();
                 bool = checkFlightId(flightId, database);
            }

            int count = 0;
            for (int j = 0; j < database.tickets.ticketsInfo.length; j++) {
                if (database.tickets.ticketsInfo[j] == null)
                    break;

                else if (Objects.equals(flightId, database.tickets.ticketsInfo[j].getFlight().getFlightId()))
                    count++;

            }
            if (count != 0) {
                System.out.println("<< You can't update this flight because It has already reserve. >>");
                continue;
            }

            for (int i = 0; i < database.flights.countFlights + 19; i++) {

                if (Objects.equals(database.flights.flightsInfo[i].getFlightId(), flightId)) {

                    System.out.println("\n\t\t-->> Which of the following fields do you want to change? -->>");
                    System.out.println("____________________________________________________________________________________");
                    System.out.println("\n<< 1-FlightId >>\n<< 2-Origin >>\n<< 3-Destination >>\n<< 4-Date >>\n<< 5-Time >>\n<< 6-Price >>\n<< 7-Seats >>");
                    System.out.print(">>\t");

                    String number = input.next();
                    number = database.checkData(number,1);

                    String data;

                    switch (number){
                        case "1" -> {
                            System.out.print("\n-->> Currently,the flightId -->> |" + database.flights.flightsInfo[i].getFlightId() + "|");
                            System.out.print("\n<< Please enter new flightId >>\t");

                            data = input.next();

                            bool = checkFlightId(data, database);
                            if (!bool){
                                if (Objects.equals(flightId, data))
                                    bool = true;

                            }
                            while (!bool){
                                System.out.println("============================================================================================");
                                System.out.print("<< Please try again >>\t");

                                data = input.next();

                                bool = checkFlightId(data, database);
                                if (!bool){
                                    if (Objects.equals(flightId, data))
                                        bool = true;

                                }
                            }

                            database.flights.updateFlights(i,number,data);
                        }
                        case "2" -> {

                            System.out.print("\n-->> Currently,the origin -->> |" + database.flights.flightsInfo[i].getOrigin() + "|");
                            System.out.print("\n<< Please enter new origin >>\t");

                            data = input.next();

                            bool = database.checkInput(data, Integer.parseInt(number));
                            while (Objects.equals(database.flights.flightsInfo[i].getDestination(), data) || !bool){
                                System.out.println("============================================================================================");
                                System.out.print("<< Please try again >>\t");

                                data = input.next();

                                bool = database.checkInput(data, Integer.parseInt(number));
                            }

                            database.flights.updateFlights(i,number,data);
                        }
                        case "3" -> {
                            System.out.print("\n-->> Currently,the destination -->> |" + database.flights.flightsInfo[i].getDestination() + "|");
                            System.out.print("\n<< Please enter new destination >>\t");

                            data = input.next();

                            bool = database.checkInput(data, Integer.parseInt(number));
                            while (Objects.equals(database.flights.flightsInfo[i].getOrigin(), data) || !bool){
                                System.out.println("============================================================================================");
                                System.out.print("<< Please try again >>\t");

                                data = input.next();

                                bool = database.checkInput(data, Integer.parseInt(number));
                            }

                            database.flights.updateFlights(i,number,data);
                        }
                        case "4" -> {
                            System.out.print("\n-->> Currently,the date -->> |" +database.flights.flightsInfo[i].getDate() + "|");
                            System.out.print("\nPlease enter new date: \t");

                            data = input.next();

                            bool = database.checkDate(data);
                            while (!bool){
                                System.out.println("============================================================================================");
                                System.out.print("<< Please try again >>\t");

                                data = input.next();

                                bool = database.checkDate(data);
                            }
                            database.flights.updateFlights(i, number, data);
                        }
                        case "5" -> {
                            System.out.print("\n-->> Currently,the time -->> |" + database.flights.flightsInfo[i].getTime() + "|");
                            System.out.print("\nPlease enter new time: \t");

                            data = input.next();

                            bool = database.checkTime(data);
                            while (!bool){
                                System.out.println("============================================================================================");
                                System.out.print("<< Please try again >>\t");

                                data = input.next();

                                bool = database.checkTime(data);
                            }

                            database.flights.updateFlights(i,number,data);
                        }
                        case "6" -> {
                            System.out.print("\n-->> Currently,the price -->> |" + database.flights.flightsInfo[i].getPrice() + "|");
                            System.out.print("\n<< Please enter new price >>\t");

                            data = input.next();
                            data = database.checkData(data, Integer.parseInt(number));

                            database.flights.updateFlights(i, number, data);
                        }
                        case "7" -> {
                            System.out.print("\n-->> Currently,the seats -->> |" +database.flights.flightsInfo[i].getSeats() + "|");
                            System.out.print("\n<< Please enter new seats >>\t");

                            data = input.next();
                            data = database.checkData(data, Integer.parseInt(number));

                            database.flights.updateFlights(i, number, data);
                        }
                        default ->
                            k-- ;
                    }
                    break;

                }
            }
        }
    }

    /**
     * to remove each flight, get the flight's information from the admin  >>
     * @param database the class that includes information and checking the format of data.
     */
    public void removeInfo(Database database){

        System.out.print("<< How many flights do you one to remove? >>\t");

        String countRemove = input.next();
        countRemove = database.checkData(countRemove, 1);

        for (int i = 0; i < Integer.parseInt(countRemove); i++) {

            System.out.print("<< Please enter flightId >>\t");
            String flightId = input.next();

            boolean bool = checkFlightId(flightId, database);
            if (bool)
                System.out.println("<< No flights were found with this fightId. >>\n");


            for (int j = 0; j < database.tickets.ticketsInfo.length; j++) {
                if (database.tickets.ticketsInfo[j] == null)
                        break;

                else if (Objects.equals(flightId, database.tickets.ticketsInfo[j].getFlight().getFlightId())) {
                        database.tickets.ticketsInfo[j].getPassenger().setCredit(Integer.parseInt(database.tickets.ticketsInfo[j].getFlight().getPrice()) + database.tickets.ticketsInfo[j].getPassenger().getCredit());
                        database.tickets.removeTicket(j);
                    }

            }

            for (int j = 0; j < 19 + database.flights.countFlights; j++) {
                if (Objects.equals(database.flights.flightsInfo[j].getFlightId(), flightId)) {
                    database.flights.removeFlights(j);
                    break;
                }
            }
        }
    }

    /**
     *show flights for admin >>
     * @param database the class that includes information and checking the format of data.
     */

    public void flightSchedule(Database database) {

        System.out.print("\t____________________________________________________________________________________________________________________________________________\n\t");
        System.out.printf("|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|" , "FlightId", "origin" , "Destination" , "Date" , "Time" , "Prise" , "Seats" );
        for (int i = 0; i < 19 + database.flights.countFlights; i++) {
            System.out.print("\n\t____________________________________________________________________________________________________________________________________________\n\t");
            System.out.printf("|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|" ,
                    database.flights.flightsInfo[i].getFlightId(),
                    database.flights.flightsInfo[i].getOrigin(),
                    database.flights.flightsInfo[i].getDestination(),
                    database.flights.flightsInfo[i].getDate(),
                    database.flights.flightsInfo[i].getTime(),
                    database.flights.flightsInfo[i].getPrice(),
                    database.flights.flightsInfo[i].getSeats());

        }
        System.out.println();
    }

    /**
     * move on index of flight and check this flightId exist >>
     * @param data the flightId
     * @param database the class that includes information and checking the format of data.
     * @return true if there isn't any flights with this flightId otherwise false.
     */
    public boolean checkFlightId(String data, Database database){

        for (int i = 0; i < database.flights.countFlights + 19; i++) {
            if (database.flights.flightsInfo[i] != null) {
                if (Objects.equals(data, database.flights.flightsInfo[i].getFlightId()))
                    return false;
            }
        }
        return true;
    }
}




