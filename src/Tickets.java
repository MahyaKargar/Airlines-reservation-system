public class Tickets {
    Ticket [] ticketsInfo = new Ticket[1000];
    int newTicketId = 10234511 ;

    int countTickets = 0;
    public void selectTicketId(int index, int indexUser, Database database){
        if (countTickets == 0)
            ticketsInfo[countTickets++] = new Ticket(database.passengers.passengersInfo[indexUser], database.flights.flightsInfo[index], newTicketId);
        else
            ticketsInfo[countTickets++] = new Ticket(database.passengers.passengersInfo[indexUser], database.flights.flightsInfo[index], database.tickets.ticketsInfo[countTickets - 2].getTicketId() + 1000000);

    }
    public int searchTicketId(int ticketId){
        int i;
        for (i = 0; i < countTickets; i++) {
            if (ticketId == ticketsInfo[i].getTicketId())
                break;
        }
        return i;
    }
    public void removeTicket(int index){
        for (int i = index; i < countTickets; i++) {
            ticketsInfo[i] = ticketsInfo[i + 1];

        }
        countTickets--;
    }


}
