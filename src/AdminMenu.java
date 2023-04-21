import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminMenu {
    Scanner input = new Scanner(System.in);
    public AdminMenu() {
    }

    public String printAdminMenu(){
        System.out.println("\t\t Admin Menu Options\n<1> Add\n<2> Update\n<3> Remove\n<4> Flight schedules\n<0> sing out");
        String command;
        command = input.next();
        return command;
    }
    public void adminMenu(Database database){
//        flights();
        database.flights.defaultFlights();
        String command = printAdminMenu();
        while (!Objects.equals(command, "0")) {
            switch (command) {
                case "1" -> {
                    System.out.println("Add");
                    addInfo(database);
                }
                case "2" -> {
                    System.out.println("Update");
                    flightSchedule(database);
                    updateInfo(database);
                }
                case "3" -> {
                    System.out.println("Remove");
                    removeInfo(database);
                    flightSchedule(database);
                }
                case "4" -> {
                    System.out.println("Flight schedules");
                    flightSchedule(database);
                }
                default -> {
                }
            }
           command = printAdminMenu();
        }
    }
    public void addInfo(Database database) {

        System.out.print("How many flights do you one to add? \t");
        String countAdd = input.next();
        boolean bool;
        countAdd = checkData(countAdd, 1);

        for (int i = 0; i < Integer.parseInt(countAdd); i++) {

            System.out.print("\nPlease enter flightId :\t");
            String flightId = input.next();
            bool = checkFlightId(flightId, database);
            while (!bool){
                System.out.print("\nPlease enter flightId :\t");
                flightId = input.next();
                bool = checkFlightId(flightId, database);
            }

            System.out.print("\nPlease enter origin :\t");
            String origin = input.next();

            origin = checkData(origin,2);


            System.out.print("\nPlease enter destination :\t");
            String destination = input.next();
            bool = checkInput(destination, 2);
            while (!bool || origin.equals(destination) ){
                System.out.print("\nPlease enter destination :\t");
                destination = input.next();
                bool = checkInput(destination, 2);
            }

            System.out.print("\nPlease enter date:\t");
            String date = input.next();
            bool = checkDate(date);
            while (!bool){
                System.out.print("\nPlease enter date:\t");
                date = input.next();
                bool = checkDate(date);
            }

            System.out.print("\nPlease enter time :\t");
            String time = input.next();
            bool = checkTime(time);
            while (!bool){
                System.out.print("\nPlease enter time :\t");
                time = input.next();
                bool = checkTime(time);
            }

            System.out.print("\nPlease enter price :\t");
            String price = input.next();

            price = checkData(price, 1);

            System.out.print("\nPlease enter seats :\t");
            String seats = input.next();
            seats = checkData(seats, 1);
            database.flights.addFlights(flightId, origin, destination, date, time, price, seats);
        }
    }
    public void updateInfo(Database database){

        System.out.print("How many flights do you one to update? \t");
        String countUpdate = input.next();
        boolean bool ;

        countUpdate = checkData(countUpdate,1);

        for (int k = 0; k < Integer.parseInt(countUpdate); k++) {

            System.out.print("Please enter FlightId :\t");
            String flightId = input.next();
            bool = checkFlightId(flightId, database);
            while (bool) {
                System.out.println("The flightId is not found ");
                System.out.print("Please enter FlightId :\t");
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
                System.out.println("You can't update this flight because It has already reserve");
                continue;
            }

            for (int i = 0; i < database.flights.count + 3; i++) {

                if (Objects.equals(database.flights.flightsInfo[i].getFlightId(), flightId)) {

                    System.out.print("\n Which of the following fields do you want to change? \t");
                    System.out.println("\n 1-FlightId\t2-Origin\t3-Destination\t4-Date\t5-Time\t6-Price\t7-Seats");
                    String number = input.next();

                    number = checkData(number,1);
                    String data;

                    switch (number){
                        case "1" -> {
                            System.out.print("\n Currently, the flightId is " + database.flights.flightsInfo[i].getFlightId());
                            System.out.print("\nPlease enter new flightId: \t");
                            data = input.next();
                            bool = checkFlightId(data, database);
                            while (!bool){
                                System.out.print("\nPlease enter new flightId: \t");
                                data = input.next();
                                bool = checkFlightId(data, database);
                            }
                            database.flights.updateFlights(i,number,data);
                            flightSchedule(database);
                        }
                        case "2" -> {

                            System.out.print("\n Currently, the origin is " + database.flights.flightsInfo[i].getOrigin());
                            System.out.print("\nPlease enter new origin: \t");
                            data = input.next();
                            bool = checkInput(data, Integer.parseInt(number));
                            while (Objects.equals(database.flights.flightsInfo[i].getDestination(), data) || !bool){
                                System.out.print("\nPlease enter new origin: \t");
                                data = input.next();
                                bool = checkInput(data, Integer.parseInt(number));
                            }
                            database.flights.updateFlights(i,number,data);
                            flightSchedule(database);
                        }
                        case "3" -> {
                            System.out.print("\n Currently, the destination is " + database.flights.flightsInfo[i].getDestination());
                            System.out.print("\nPlease enter new destination: \t");
                            data = input.next();
                            bool = checkInput(data, Integer.parseInt(number));

                            while (Objects.equals(database.flights.flightsInfo[i].getOrigin(), data) || !bool){
                                System.out.print("\nPlease enter new destination: \t");
                                data = input.next();
                                bool = checkInput(data, Integer.parseInt(number));
                            }
//                            checkData(data, Integer.parseInt(number));
                            database.flights.updateFlights(i,number,data);
                            flightSchedule(database);
                        }
                        case "4" -> {
                            System.out.print("\n Currently, the date is " +database.flights.flightsInfo[i].getDate());
                            System.out.print("\nPlease enter new date: \t");
                            data = input.next();
                            bool = checkDate(data);
                            while (!bool){
                                System.out.print("\nPlease enter new date: \t");
                                data = input.next();
                                bool = checkDate(data);
                            }
                            database.flights.updateFlights(i, number, data);
                            flightSchedule(database);
                        }
                        case "5" -> {
                            System.out.print("\n Currently, the time is " + database.flights.flightsInfo[i].getTime());
                            System.out.print("\nPlease enter new time: \t");
                            data = input.next();
                            bool = checkTime(data);
                            while (!bool){
                                System.out.print("\nPlease enter new time: \t");
                                data = input.next();
                                bool = checkTime(data);
                            }

                            database.flights.updateFlights(i,number,data);
                            flightSchedule(database);
                        }
                        case "6" -> {
                            System.out.print("\n Currently, the price is " + database.flights.flightsInfo[i].getPrice());
                            System.out.print("\nPlease enter new price: \t");
                            info(i, number, database);
                        }
                        case "7" -> {
                            System.out.print("\n Currently, the seats is " +database.flights.flightsInfo[i].getSeats());
                            System.out.print("\nPlease enter new seats: \t");
                            info(i, number, database);
                        }
                        default -> {
                            k-- ;
                        }
                    }
                    break;

                }
            }
        }
    }


    public void removeInfo(Database database){
        System.out.print("How many flights do you one to remove? \t");
        String countRemove = input.next();
        countRemove = checkData(countRemove, 1);
        for (int i = 0; i < Integer.parseInt(countRemove); i++) {
            System.out.print("Please enter FlightId :\t");
            String flightId = input.next();
            int count = 0;
            for (int j = 0; j < database.tickets.ticketsInfo.length; j++) {
                if (database.tickets.ticketsInfo[j] == null)
                        break;

                else if (Objects.equals(flightId, database.tickets.ticketsInfo[j].getFlight().getFlightId())) {
                        database.tickets.ticketsInfo[j].getPassenger().setCredit(Integer.parseInt(database.tickets.ticketsInfo[j].getFlight().getPrice()) + database.tickets.ticketsInfo[j].getPassenger().getCredit());
                        database.tickets.removeTicket(j);
                    }

            }

            for (int j = 0; j < 3 + database.flights.count; j++) {
                if (Objects.equals(database.flights.flightsInfo[j].getFlightId(), flightId)) {
                    database.flights.removeFlights(j);
                    break;
                }
            }
        }
    }
    public boolean checkInput(String input, int command){
        boolean bool;
        char [] newInput = input.toCharArray();

        if (command == 3 || command == 2){

            if (newInput[0] >= 'a' && newInput[0] <= 'z')
                return false;
            for (int i = 1; i < newInput.length; i++) {
                if (newInput[i] >= 'a' && newInput[i] <= 'z')
                    bool = true;
                else
                    return false;
            }
        }
        else {
            for (int i = 0; i < newInput.length; i++) {
                if (newInput[i] >= '0' && newInput[i] <= '9')
                    bool = true;
                else
                    return false;

            }
        }
        return true;
    }
    public boolean checkFlightId(String flightId, Database database){
        for (int i = 0; i < database.flights.count + 3; i++) {
            if (database.flights.flightsInfo[i] != null) {
                if (Objects.equals(flightId, database.flights.flightsInfo[i].getFlightId()))
                    return false;
            }

        }

        return true;
    }
    public boolean isTimeValid(String time){
        try {
            String [] newTime = time.split(":");
            return   Integer.parseInt(newTime[0]) < 24 && Integer.parseInt(newTime[1]) < 60 && Integer.parseInt(newTime[0]) >= 0 && Integer.parseInt(newTime[1]) >= 0;
        } catch (Exception a){
            return false;
        }

    }
    public String checkData(String data, int command){
        boolean bool;
        bool = checkInput(data, command);
        while (!bool){
            System.out.println("Please try again >>");
            data = input.next();
            bool = checkInput(data, command);
        }
        return data;
    }
    public void info(int index, String command, Database database){
        String data = input.next();
        data = checkData(data, Integer.parseInt(command));
        database.flights.updateFlights(index, command, data);
        flightSchedule(database);
    }
    public void flightSchedule(Database database) {
        System.out.print("\t________________________________________________________________________________");
        System.out.print("\n\t|  FlightId  |  origin  |  Destination  |  Date  |  Time  |  Price  |  seats  |");
        for (int i = 0; i < 3 + database.flights.count; i++) {
            System.out.print("\n\t________________________________________________________________________________");
            System.out.print("\n\t|\t" +
                    database.flights.flightsInfo[i].getFlightId() + "\t|\t" +
                    database.flights.flightsInfo[i].getOrigin() + "\t|\t" +
                    database.flights.flightsInfo[i].getDestination() + "\t|\t" +
                    database.flights.flightsInfo[i].getDate() + "\t|\t" +
                    database.flights.flightsInfo[i].getTime() + "\t|\t" +
                    database.flights.flightsInfo[i].getPrice() + "\t|\t" +
                    database.flights.flightsInfo[i].getSeats() + "\t|");

        }
        System.out.println();
    }
    public boolean checkDate(String date) {
        Matcher matcher = Pattern.compile("\\d{4}/\\d{2}/\\d{2}").matcher(date);

        if (matcher.find()) {
            String[] digits = date.split("/");
            int[] convertedDigits = new int[digits.length];

            for (int i = 0; i < digits.length; i++)
                convertedDigits[i] = Integer.parseInt(digits[i]);

            boolean yearCheck = convertedDigits[0] > 1401 && convertedDigits[0] < 1410;
            boolean monthCheck = convertedDigits[1] > 0 && convertedDigits[1] < 13;
            boolean dayCheck = convertedDigits[2] > 0 && convertedDigits[2] < 31;

            if ((convertedDigits[1] > 0 && convertedDigits[1] < 7) )
                dayCheck = convertedDigits[2] > 0 && convertedDigits[2] < 32;

            if (monthCheck && dayCheck && yearCheck)
                return true;
            else
                return false;
        }
        return false;

    }
    public boolean checkTime(String time){
        Matcher matcher = Pattern.compile("\\d{2}:\\d{2}").matcher(time);
        if (matcher.find()){
            String[] digits = time.split(":");
            int[] convertedDigits = new int[digits.length];

            for (int i = 0; i < digits.length; i++)
                convertedDigits[i] = Integer.parseInt(digits[i]);

            boolean hourCheck = convertedDigits[0] >= 0 && convertedDigits[0] < 24;
            boolean minCheck = convertedDigits[1] >= 0 && convertedDigits[1] < 60;

            if (hourCheck && minCheck)
                return true;
            else
                return false;
        }
        return false;
    }
}




