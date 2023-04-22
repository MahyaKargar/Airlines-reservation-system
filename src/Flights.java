public class Flights {
    Flight[] flightsInfo = new Flight[100];
    int countFlights = 1;

    /**
     *fill the array with flights based on default flights >>
     */
    public void defaultFlights() {
        flightsInfo[0] = new Flight("WX-12", "Yazd", "Tehran", "1402/02/12", "12:30", "700000", "51");
        flightsInfo[1] = new Flight("WZ-15", "Yazd", "Tehran", "1402/02/12", "08:30", "900000", "220");
        flightsInfo[2] = new Flight("BG-22", "Shiraz", "Tabriz", "1402/02/13", "22:30", "1100000", "86");
        flightsInfo[3] = new Flight("WZ-17", "Mashhad", "Shiraz", "1402/02/13", "20:30", "900000", "70");
        flightsInfo[4] = new Flight("BG-34", "Tehran", "Esfahan", "1402/04/31", "00:00", "1250000", "108");
        flightsInfo[5] = new Flight("FH-90", "Gilan", "Kordestan", "1402/05/10", "20:40", "870000", "55");
        flightsInfo[6] = new Flight("WX-20", "Yazd", "Kish", "1402/04/08", "05:05", "1800000", "190");
        flightsInfo[7] = new Flight("DX-46", "Tabriz", "Arak", "1402/07/15", "15:40", "970000" ,"100");
        flightsInfo[8] = new Flight("KJ-55", "Kerman", "Bandaabbas", "1402/10/30", "18:55", "1380000", "210");
        flightsInfo[9] = new Flight("FH-88", "Sanandaj", "Mazandaran", "1402/12/17", "22:10", "1500000", "258");
        flightsInfo[10] = new Flight("KL-65", "Shiraz", "Khoozestan", "1402/11/15", "01:50", "1680000", "168");
        flightsInfo[11] = new Flight("BM-11", "Tehran", "Esfahan", "1403/01/05", "23:00", "1300000", "202");
        flightsInfo[12] = new Flight("FH-10", "Yazd", "Kish", "1402/04/08", "21:15", "2200000","250");
        flightsInfo[13] = new Flight("WK-61", "Kerman", "Bandarabbas", "1402/10/30", "03:25", "1530000", "98");
        flightsInfo[14] = new Flight("WZ-33", "Shiraz", "Tabriz", "1402/02/13", "14:15", "1175000", "114");
        flightsInfo[15] = new Flight("BD-55", "Hormozgan", "Golestan", "1403/03/13", "00:35", "1900000", "140");
        flightsInfo[16] = new Flight("FH-16", "Gilan", "Kordestan", "1402/05/10", "11:11", "800000", "110");
        flightsInfo[17] = new Flight("BH-38", "Yazd", "Mashhad", "1404/04/04", "04:04", "1400000", "140");
        flightsInfo[18] = new Flight("HN-12", "Tehran", "Esfahan", "1402/04/31", "17:45", "1250000", "100");
        flightsInfo[19] = new Flight("FH-01", "Tabriz", "Arak", "1402/07/15", "03:00", "970000", "89");
    }

    /**
     * putting the desired flight information in the flight's array >>
     * @param flightId flight Id of the desired flight to be added.
     * @param origin the origin of the desired flight to be added.
     * @param destination the destination of the desired flight to be added.
     * @param date the date of the desired flight to be added.
     * @param time the time of the desired flight to be added.
     * @param price the price of the desired flight to be added.
     * @param seats the seats of the desired flight to be added.
     */

    public void addFlights(String flightId, String origin, String destination, String date, String time, String price, String seats) {
        flightsInfo[19 + countFlights] = new Flight(flightId, origin, destination, date, time, price, seats);
        countFlights++;
    }

    /**
     * update the desired flight and putting new flight information in the flights' array  >>
     * @param index the cell number of flights' array.
     * @param number the desired fields.
     * @param data one of the flight information.
     */
    public void updateFlights(int index, String number, String data) {
        switch (number) {
            case "1" -> flightsInfo[index].setFlightId(data);

            case "2" -> flightsInfo[index].setOrigin(data);

            case "3" -> flightsInfo[index].setDestination(data);

            case "4" -> flightsInfo[index].setDate(data);

            case "5" -> flightsInfo[index].setTime(data);

            case "6" -> flightsInfo[index].setPrice(data);

            case "7" -> flightsInfo[index].setSeats(data);

            default -> {
            }
        }

    }

    /**
     * remove the desired flight >>
     * @param index the cell number of flights' array to remove it..
     */

    public void removeFlights(int index) {
        for (int i = index; i < 19 + countFlights; i++)
            flightsInfo[i] = flightsInfo[i + 1];

        countFlights--;
    }
}
