public class Ticket {
    private Passenger passenger;
    private Flight flight;
    private int ticketId;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }


    public Ticket(Passenger passenger, Flight flight, int ticketId) {
        this.passenger = passenger;
        this.flight = flight;
        this.ticketId = ticketId;
    }


}
