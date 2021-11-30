import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Arrays;

public class Room {

    private String roomType;
    private int minOccupancy;
    private int maxOccupancy;
    private double[] rates;
    private ArrayList<Reservation> booked;
    private ArrayList<Room> function;

    public Room(String roomType , int minOccupancy , int maxOccupancy , double[] rates , ArrayList<Reservation> booked ){
        this.roomType = roomType;
        this.minOccupancy= minOccupancy;
        this.maxOccupancy= maxOccupancy;
        this.rates = rates;
        this.booked = new ArrayList<>();
    }

    public ArrayList<Reservation> getBooked(){
        return booked;
    }
    
    // function to add rooms 
    public void addRoom(Room add) {
        function.add(add);
    }
    
    //fuction to remove rooms
        public void removeRoom(Room add) {
        function.remove(add);
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public void setMinOccupancy(int minOccupancy) {
        this.minOccupancy = minOccupancy;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRates(double[] rates) {
        this.rates = rates;
    }

    public String getRoomType(){
        return roomType;
    }

    public int getMinOccupancy() {
        return minOccupancy;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public double[] getRates() {
        return rates;
    }
}
                
