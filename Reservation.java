/**Public Class for Reservation */
public class Reservation {
    private int resNumber;
    private String name;
    private Reservation standard;
    private Reservation advanced;
    private String description;
    private ReservationDate day;
    private ReservationTime from;
    private ReservationTime to;
    private int numberOfRooms;
    private Reservation roomType;
    private int numberOfPeople;
    private double totalCost;

    /**Reservation Constructor method */

    public Reservation(int resNumber, String name, String description, ReservationDate day, ReservationTime from, ReservationTime to, int numberOfRooms, int numberOfPeople, double totalCost) {
        this.resNumber = resNumber;
        this.name = name;
        this.description = description;
        this.day = day;
        this.from = from;
        this.to = to;
        this.numberOfRooms = numberOfRooms;
        this.numberOfPeople = numberOfPeople;
        this.totalCost = totalCost;
    }

    public int getResNumber() {
        return this.resNumber;
    }

    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReservationDate getDay() {
        return this.day;
    }

    public void setDay(ReservationDate day) {
        this.day = day;
    }

    public ReservationTime getFrom() {
        return this.from;
    }

    public void setFrom(ReservationTime from) {
        this.from = from;
    }

    public ReservationTime getTo() {
        return this.to;
    }

    public void setTo(ReservationTime to) {
        this.to = to;
    }

    public int getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Reservation getRoomType() {
        return this.roomType;
    }

    public void setRoomType(Reservation roomType) {
        this.roomType = roomType;
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
}    

    

