/**Public Class for Reservation */
public class Reservation {
    private int resNumber;
    private String name;
    private String reservationType; //S or AP
    private ReservationDate checkin;
    private ReservationDate checkout;
    private int numberOfRooms;
    private String roomType;
    private int numberOfPeople;
    private double totalCost;

    /**Reservation Constructor method */
    public Reservation(int resNumber, String name, String roomType, ReservationDate checkin,
                       ReservationDate checkout,
                       int numberOfRooms, int numberOfPeople, double totalCost,
                       String reservationType) {

        this.resNumber = resNumber;
        this.name = name;
        this.roomType = roomType;
        this.checkin = checkin;
        this.checkout = checkout;
        this.numberOfRooms = numberOfRooms;
        this.numberOfPeople = numberOfPeople;
        this.totalCost = totalCost;
        this.reservationType = reservationType;
    }

    public int getResNumber() {
        return this.resNumber;
    }

    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }


    public String getReservationType() {
        return this.reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReservationDate getCheckin() {
        return this.checkin;
    }

    public void setCheckin(ReservationDate checkin) {
        this.checkin = checkin;
    }

    public ReservationDate getCheckout() {
        return this.checkout;
    }

    public void setCheckout(ReservationDate checkout) {
        this.checkout = checkout;
    }

    public int getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "ReservationID: " + resNumber + ", Check in: " + checkin.format() +  ", Check out: " + checkout.format();
    }
}    
