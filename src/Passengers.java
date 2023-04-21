import java.util.Objects;

public class Passengers {

    Passenger[] passengersInfo = new Passenger[1000];

    public void changePassword(int index, String newPassword) {
        passengersInfo[index].setPassword(newPassword);

    }

    public void searchFlights(String[] fields, Database database) {

        boolean bool = false;
        int index = 0;
        for (int i = 0; i < database.flights.count + 3; i++) {

            if ((Objects.equals(fields[0], database.flights.flightsInfo[i].getOrigin())) && (Objects.equals(fields[1], database.flights.flightsInfo[i].getDestination())) && (Objects.equals(fields[2], database.flights.flightsInfo[i].getDate()))) {
                bool = true;
                index = i;
                break;
            }
        }


        if (bool) {
            System.out.print("\t________________________________________________________________________________");
            System.out.print("\n\t|  FlightId  |  origin  |  Destination  |  Date  |  Time  |  Price  |  seats  |");
            System.out.print("\n\t________________________________________________________________________________");
            System.out.print("\n\t|\t" +
                    database.flights.flightsInfo[index].getFlightId() + "\t|\t" +
                    database.flights.flightsInfo[index].getOrigin() + "\t|\t" +
                    database.flights.flightsInfo[index].getDestination() + "\t|\t" +
                    database.flights.flightsInfo[index].getDate() + "\t|\t" +
                    database.flights.flightsInfo[index].getTime() + "\t|\t" +
                    database.flights.flightsInfo[index].getPrice() + "\t|\t" +
                    database.flights.flightsInfo[index].getSeats() + "\t|");
        } else
            System.out.print("\n\nThere isn't any flight with these features\n");
    }

    public void addCharge(int index, int charge) {
        passengersInfo[index].setCredit(passengersInfo[index].getCredit() + charge);
    }

}
