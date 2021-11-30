import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Arrays;

public class Room {

    private String roomType;
    private int minOccupancy;
    private int maxOccupancy;
    private double[] rates;
    private ArrayList<Room> function;
    private ArrayList<Reservation> booked;

    /***
     * constructor for creating a new room
     * @param roomType
     * @param minOccupancy
     * @param maxOccupancy
     * @param rates
     */
    public Room(String roomType, int minOccupancy, int maxOccupancy, double[] rates) {
        this.roomType = roomType;
        this.minOccupancy = minOccupancy;
        this.maxOccupancy = maxOccupancy;
        this.rates = rates;
    }

    /**
     * method to add rooms
     * @param add
     */
    public void addRoom(Room add) {
        function.add(add);
    }

    /**
     * method to remove rooms
     * @param add
     */
    public void removeRoom(Room add) {
        function.remove(add);
    }

    /**
     * method for setting the max occupancy
     * @param maxOccupancy
     */
    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    /**
     * method to set the minimum occupancy
     * @param minOccupancy
     */
    public void setMinOccupancy(int minOccupancy) {
        this.minOccupancy = minOccupancy;
    }

    /**
     * method to set the type of room
     * @param roomType
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * method to set the rates for a room
     * @param rates
     */
    public void setRates(double[] rates) {
        this.rates = rates;
    }

    /**
     * method to get the room type
     * @return returns the type of room
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * method to get the minimum occupancy
     * @return minimum occupancy
     */
    
    public int getMinOccupancy() {
        return minOccupancy;
    }

    /**
     * method for getting max occupancy
     * @return max occupancy
     */
    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    /**
     * method to get the rates for a room
     * @return the room rates
     */
    public double[] getRates() {
        return rates;
    }

    /**
     * method to format the data rate, and isAdvancePurchase
     * @param rate
     * @param isAdvancePurchase
     * @return roomtype rate and rate type
     */
    public String format(Double rate, Boolean isAdvancePurchase) {
        String rateType = "S - Standard rate";
        if (isAdvancePurchase) {
            rateType = "AP - Advance purchase discount";
        }
        return roomType + ", " + rate + " Total cost in Euros for stay, " + rateType;
    }

    @Override
    /**
     * @return the type of room
     */
    public String toString() {
        return roomType;
    }

 
