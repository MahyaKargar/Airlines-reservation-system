public class Flights {
    Flight [] flightsInfo = new Flight[100];;
    int count = 1;
    public void defaultFlights(){
        flightsInfo[0] = new Flight("WX-12","Yazd", "Tehran", "1402-2-12", "12:30", "700000", "51");
        flightsInfo[1] = new Flight("WZ-15", "Mashhad", "Shiraz", "1402-2-13", "08:30", "900000", "220");
        flightsInfo[2] = new Flight("BG-22", "Shiraz", "Tabriz", "1402-2-13", "22:30", "1100000", "36");
        flightsInfo[3] = new Flight("WZ-17", "Mashhad", "Shiraz", "1402-2-13", "20:30", "900000", "70");



    }

    public void addFlights(String flightId, String origin, String destination, String date, String time, String price, String seats) {
        flightsInfo[3 + count] = new Flight(flightId, origin, destination, date, time, price, seats);
        count++;
    }

    public void updateFlights(int index, String number, String data){
        switch (number) {
            case "1" ->
                    flightsInfo[index].setFlightId(data);

            case "2" ->
                    flightsInfo[index].setOrigin(data);

            case "3" ->
                    flightsInfo[index].setDestination(data);

            case "4" ->
                    flightsInfo[index].setDate(data);

            case "5" ->
                    flightsInfo[index].setTime(data);

            case "6" ->
                    flightsInfo[index].setPrice(data);

            case "7" ->
                    flightsInfo[index].setSeats(data);

            default -> {
            }
        }

    }

    public void removeFlights(int index) {
        for (int i = index; i < 4 + count; i++)
            flightsInfo[i] = flightsInfo[i + 1];

        count--;
    }
}
