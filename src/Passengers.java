import java.util.Objects;
public class Passengers {
    Passenger[] passengersInfo = new Passenger[1000];

    /**
     * putting a new password in the passengers' array >>
     * @param index the cell number of passengers' array.
     * @param password  the new password.
     */
    public void changePassword(int index, String password) {
        passengersInfo[index].setPassword(password);
    }

    /**
     * by checking the information of flights, show the desired flights' passenger >>
     * @param fields includes information of flights such as origin, destination and date.
     * @param database the class that includes information.
     */

    public void searchFlights(String[] fields, Database database) {

        boolean bool = false;

        System.out.print("\t____________________________________________________________________________________________________________________________________________\n\t");
        System.out.printf("|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|" , "FlightId", "origin" , "Destination" , "Date" , "Time" , "Prise" , "Seats" );

        for (int i = 0; i < database.flights.countFlights + 19; i++) {
            if ((Objects.equals(fields[0], database.flights.flightsInfo[i].getOrigin())) && (Objects.equals(fields[1], database.flights.flightsInfo[i].getDestination())) && (Objects.equals(fields[2], database.flights.flightsInfo[i].getDate()))) {
                System.out.print("\n\t____________________________________________________________________________________________________________________________________________\n\t");
                System.out.printf("|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|\t%-15s|",
                        database.flights.flightsInfo[i].getFlightId(),
                        database.flights.flightsInfo[i].getOrigin(),
                        database.flights.flightsInfo[i].getDestination(),
                        database.flights.flightsInfo[i].getDate(),
                        database.flights.flightsInfo[i].getTime(),
                        database.flights.flightsInfo[i].getPrice(),
                        database.flights.flightsInfo[i].getSeats());

                bool = true;

            }
        }

        if (!bool) {
            System.out.println("\n====================================================================================================");
            System.out.print("\n<< There isn't any flight with these features. >>");
        }
        System.out.println();
    }

    /**
     *the passenger's desired charge add to previous credit >>
     * @param index the cell number of passengers' array.
     * @param charge the charge that the passenger wants to add his credit.
     */
    public void addCharge(int index, int charge) {
        passengersInfo[index].setCredit(passengersInfo[index].getCredit() + charge);
    }

}
