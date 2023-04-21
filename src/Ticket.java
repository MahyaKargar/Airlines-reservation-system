public class Ticket {
    private final Passenger passenger;
    private final Flight flight;
    private final int ticketId;

    public Passenger getPassenger() {
        return passenger;
    }
    public Flight getFlight() {
        return flight;
    }
    public int getTicketId() {
        return ticketId;
    }
    public Ticket(Passenger passenger, Flight flight, int ticketId) {
        this.passenger = passenger;
        this.flight = flight;
        this.ticketId = ticketId;
    }

}
