import java.util.ArrayList;
public class Hotels {
    private int rooms_available;
    private int cost;
    private int length_of_stay;
    private int Hotel_rating;
    
    public Hotels() {
    }
    public Hotels(int rooms_available,int cost, int length_of_stay,int Hotel_rating) {
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
        public void setLength_of_stay(int length_of_stay) {
        this.length_of_stay = length_of_stay;
    }
        public void setRooms_available(int rooms_available) {
        this.rooms_available = rooms_available;
    }
            public void setHotel_rating(int Hotel_rating) {
        this.Hotel_rating = Hotel_rating;
    }
    
    public int getCost() {
        return(cost);
    }
        public int getRooms_available() {
        return(rooms_available);
    }
        public int getHotel_rating() {
        return(Hotel_rating);
    }
        public int getlength_of_stay() {
        return(length_of_stay);
    }
    //L4 owns a chain of hotels, at present they own a Five star a four star and a three star 
    ArrayList<String> Hotels = new ArrayList<String>();
    public void addStarHotels() {
    Hotels.add("Five Star");
    Hotels.add("Four Star");
    Hotels.add("Three Star");
}
    
    public void addHotels(String Hotel) {
        //according to the specificationL4 could possibly purchase more hotels so this feature must be added
        Hotels.add(Hotel);
    }
}
