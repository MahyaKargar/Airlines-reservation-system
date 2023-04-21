import java.util.Objects;
public class Passengers {
    Passenger[] passengersInfo = new Passenger[1000];

    public void changePassword(int index, String newPassword) {
        passengersInfo[index].setPassword(newPassword);
    }

    public void searchFlights(String[] fields, Database database) {

        boolean bool = false;

        System.out.print("\t________________________________________________________________________________");
        System.out.print("\n\t|  FlightId  |  origin  |  Destination  |  Date  |  Time  |  Price  |  seats  |");

        for (int i = 0; i < database.flights.countFlights + 3; i++) {
            if ((Objects.equals(fields[0], database.flights.flightsInfo[i].getOrigin())) && (Objects.equals(fields[1], database.flights.flightsInfo[i].getDestination())) && (Objects.equals(fields[2], database.flights.flightsInfo[i].getDate()))) {
                System.out.print("\n\t________________________________________________________________________________");
                System.out.print("\n\t|\t" +
                        database.flights.flightsInfo[i].getFlightId() + "\t|\t" +
                        database.flights.flightsInfo[i].getOrigin() + "\t|\t" +
                        database.flights.flightsInfo[i].getDestination() + "\t|\t" +
                        database.flights.flightsInfo[i].getDate() + "\t|\t" +
                        database.flights.flightsInfo[i].getTime() + "\t|\t" +
                        database.flights.flightsInfo[i].getPrice() + "\t|\t" +
                        database.flights.flightsInfo[i].getSeats() + "\t|");
                bool = true;

            }
        }

        if (!bool) {
            System.out.println("\n====================================================================================================");
            System.out.print("\n<< There isn't any flight with these features. >>");
        }
        System.out.println();
    }

    public void addCharge(int index, int charge) {
        passengersInfo[index].setCredit(passengersInfo[index].getCredit() + charge);
    }

}
