public class Room {

    private String roomType;
    private int minOccupancy;
    private int maxOccupancy;
    private double[] rates;
    private ArrayList<Reservation> booked = new ArrayList<Reservation>();

    public Room(String roomType , int minOccupancy , int maxOccupancy , double[] rates){
        this.roomType = roomType;
        this.minOccupancy= minOccupancy;
        this.maxOccupancy= maxOccupancy;
        this.rates = rates;
    }
    public ArrayList<Reservation> getBooked(){
        return booked;
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
