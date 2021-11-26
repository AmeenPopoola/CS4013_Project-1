public class Hotels {
    private String rooms_available;
    private int cost;
    private int length_of_stay;
    
    
    public Hotels() {
    }
    public Hotels(String rooms_available,int cost, int length_of_stay,int Hotel_rating) {
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
        public void setLength_of_stay(int length_of_stay) {
        this.length_of_stay = length_of_stay;
    }
        public void setRooms_available(String rooms_available) {
        this.rooms_available = rooms_available;
    }

    
    public int getCost() {
        return(cost);
    }
        public String getRooms_available() {
        return(rooms_available);
    }

        public int getlength_of_stay() {
        return(length_of_stay);
    }

}
