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

    /**
     * memthod that creates a new reservation with the provided info
     * @param resNumber
     * @param name
     * @param roomType
     * @param checkin
     * @param checkout
     * @param numberOfRooms
     * @param numberOfPeople
     * @param totalCost
     * @param reservationType
     */
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

    /**
     * method that gets the reservation number of a reservation
     * @return the reservation number
     */
    public int getResNumber() {
        return this.resNumber;
    }

    /**
     * method that sets the reservation number of a reservation
     * @param resNumber
     */
    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }

    /**
     * method that gets what type of reservation it is
     * @return  the type of reservation
     */
    public String getReservationType() {
        return this.reservationType;
    }

    /**
     * method that sets what the reservation type is
     * @param reservationType
     */
    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    /**
     * method that gets the name of the user who made the reservation
     * @return name of customer
     */
    public String getName() {
        return this.name;
    }

    /***
     * method that sets the name of the customer who created the reservation
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method that creates the ReservationDate checkin
     * @return the checkkin date in the required form for ReservationDate
     */
    public ReservationDate getCheckin() {
        return this.checkin;
    }

    /**
     * method that sets the checkin date
     * @param checkin
     */
    public void setCheckin(ReservationDate checkin) {
        this.checkin = checkin;
    }

    /**
     * method that gets the date the reservation ends
     * @return the checkout date
     */
    public ReservationDate getCheckout() {
        return this.checkout;
    }

    /**
     * method that sets what the checkout date is
     * @param checkout
     */
    public void setCheckout(ReservationDate checkout) {
        this.checkout = checkout;
    }

    /**
     * method that gets the number of rooms available in the hotel
     * @return the number of rooms available
     */
    public int getNumberOfRooms() {
        return this.numberOfRooms;
    }

    /**
     * method that sets the number of rooms that are available to book
     * @param numberOfRooms
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * method that gets the type of the room
     * @return the room type
     */
    public String getRoomType() {
        return this.roomType;
    }

    /**
     * method that gets the number of people who will be staying  
     * @return number of people staying in a room
     */
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    /**
     * method that sets the number of people who are staying
     * @param numberOfPeople
     */
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * method that gets the total cost of the entire booking
     * @return total cost of the booking
     */
    public double getTotalCost() {
        return this.totalCost;
    }

    /**
     * method that sets the total cost for the entire stay
     * @param totalCost
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * method that formats the output of the required data
     * @return string containing reservation number, checkin date,and  checkout date
     */
    @Override
    public String toString() {
        return "ReservationID: " + resNumber + ", Check in: " + checkin.format() +  ", Check out: " + checkout.format();
    }
}    
