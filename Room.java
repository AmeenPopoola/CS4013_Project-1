public class Room {

    private String roomType;
    private int minOccupancy;
    private int maxOccupancy;
    private double[] rates;

    public Room(String roomType , int minOccupancy , int maxOccupancy , double[] roomPrice){
        this.roomType = roomType;
        this.minOccupancy= minOccupancy;
        this.maxOccupancy= maxOccupancy;
        this.rates = roomPrice;
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
