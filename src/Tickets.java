public class Tickets {
    Ticket[] ticketsInfo = new Ticket[1000];
    int ticketId = 10234511;
    int countTickets = 0;

    /**
     * putting the reserved flight information and passenger information in the array of tickets and assigning the ticketId to the reservation ticket >>
     * @param index the cell number of flights' array.
     * @param indexUser the cell number of passengers' array.
     * @param database the class that includes information.
     */

    public void selectTicketId(int index, int indexUser, Database database) {
        if (countTickets == 0)
            ticketsInfo[countTickets++] = new Ticket(database.passengers.passengersInfo[indexUser], database.flights.flightsInfo[index], ticketId);
        else
            ticketsInfo[countTickets++] = new Ticket(database.passengers.passengersInfo[indexUser], database.flights.flightsInfo[index], database.tickets.ticketsInfo[countTickets - 2].getTicketId() + 1000000);

    }

    /**
     * search the ticket by its ticketId >>
     * @param ticketId the ticketId
     * @return the cell number of the array of tickets.
     */

    public int searchTicketId(int ticketId) {
        int index = -1;
        for (int i = 0; i < countTickets; i++) {
            if (ticketId == ticketsInfo[i].getTicketId()) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     *remove the ticket desired by the passenger >>
     * @param index the cell number of the array of tickets to remove it.
     */

    public void removeTicket(int index) {
        for (int i = index; i < countTickets; i++) {
            ticketsInfo[i] = ticketsInfo[i + 1];
        }
        countTickets--;
    }

}
