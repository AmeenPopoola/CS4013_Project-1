/**Public Class for Reservation */
import java.util.GregorianCalendar;
public class Reservation {
    private int resNumber;
    private String name;
    private String description;
    private GregorianCalendar from;
    private GregorianCalendar to;
    private String dateFrom;
    private String dateTo;
    private int numberOfRooms;
    private boolean resType;
    private String roomType;
    private int numberOfPeople;
    private int numberOfNights;
    private double totalCost;

    /**Reservation Constructor
     * to create a new reservation object from user input
     */

    public Reservation(String name, String description, String dateFrom, int nights, int numberOfRooms, boolean resType, String roomType, int numberOfPeople, double totalCost) {
        this.resNumber = (int) (Math.random() * 10000);
        this.name = name;
        this.description = description;
        

        this.dateFrom = dateFrom;
        String[] df = dateFrom.split(".");
        int day = Integer.parseInt(df[0]);
        int month = Integer.parseInt(df[1]) - 1;
        int year = Integer.parseInt(df[2]);
    
        int length = day + nights;
        this.dateTo = length + "/" + (month + 1) + "/" + year;

        this.from = new GregorianCalendar(day, month, year);
        this.to = new GregorianCalendar(day, month, year + nights);
      
        this.numberOfRooms = numberOfRooms;
        this.resType = resType;
        this.numberOfNights = nights;
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
        this.totalCost = totalCost;
    }
    /** Create a new Reservation Object
     * from the reservation.csv file
     * make sure the reservation number is the same
     */
    public Reservation(int resNumber, String name, String dateFrom, boolean resType, int nights, int numberOfRooms, String roomType, int numberOfPeople, double totalCost){
        this.resNumber = resNumber;
        this.name = name;
       
        this.dateFrom = dateFrom;
        String[] df = dateFrom.split("/");
        int day = Integer.parseInt(df[0]);
        int month = Integer.parseInt(df[1]) - 1;
        int year = Integer.parseInt(df[2]);

        int length = day + nights;
        this.dateTo = length + "/" + (month + 1) + "/" + year;
        this.from = new GregorianCalendar(day, month, year);
        this.to = new GregorianCalendar(day, month, year + nights);

        this.resType = resType;
        this.numberOfNights = nights;
        this.numberOfRooms = numberOfRooms;
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
        this.totalCost = totalCost;

    }
    /** Accessor and Mutator Methods */
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

    public GregorianCalendar getFrom() {
        return this.from;
    }

    public void setFrom(GregorianCalendar from) {
        this.from = from;
    }

    public GregorianCalendar getTo() {
        return this.to;
    }

    public void setTo(GregorianCalendar to) {
        this.to = to;
    }

    public String getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
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

    public void setRoomType(String roomType) {
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
    public int getNumberOfNights() {
        return this.numberOfNights;
    }
    public boolean isResType(){
        return resType;
    }
    

    @Override
    public String toString() {
        return "{" +
            " resNumber='" + getResNumber() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", from='" + getFrom() + "'" +
            ", to='" + getTo() + "'" +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", numberOfRooms='" + getNumberOfRooms() + "'" +
            ", resType='" + isResType() + "'" +
            ", roomType='" + getRoomType() + "'" +
            ", numberOfPeople='" + getNumberOfPeople() + "'" +
            ", numberOfNights='" + getNumberOfNights() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            "}";
    }
    
    }
    



 


    

