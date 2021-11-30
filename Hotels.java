import java.util.ArrayList;
public class Hotels {
    private String name;
    private Integer rooms_available;
    private Room roomType;
    /**
     * Empty constructor
     */
    public Hotels() {
    }
    /**
     * Constructor that changes private data fields in the class with the corresponding inputted data fields
     */
    public Hotels(String hotel, Integer rooms_available, Room roomType) {
        this.name = hotel;
        this.rooms_available = rooms_available;
        this.roomType = roomType;
    }
    /**
     * Method that sets what rooms are available to book
     * 
     */
    public void setRooms_available(Integer rooms_available) {
        this.rooms_available = rooms_available;
    }
    /**
     * Method that gets what rooms are available too book
     * @return rooms that are available
     */
    public Integer getRooms_available() {
        return (rooms_available);
    }

    /**
     * method that gets the type of room
     * @return the type of room
     */
    public Room getRoomType() {
        return roomType;
    }

    /**
     * 
     * method that gets the name of a hotel
     * @return name of the hotel
     */
    public String getName() {
        return name;
    }
     
     /** method to format the output of data about the hotels
     * @return the name of the hotel and the type of room, the rate of the room and the purchase type
     */
    public String format(Double rate, Boolean isAdvancePurchase) {
        return name + ", " + roomType.format(rate, isAdvancePurchase);
    }

    @Override
    /**
     * method to format the output of data about the hotels
     * @return the name of the hotel and the type of room
     */
    public String toString() {
        return name + ", " + roomType;
    }

//    public ArrayList<Room> getArray(String choice){
//        ArrayList<Room> room = null;
////        room = getThreeStar();
////        if(choice.equals("3")){
////            room = getThreeStar();
////        }else if(choice.equals("4")){
////            room = getFourStar();
////        }else if(choice.equals("5")){
////            room = getFiveStar();
////        }
//        return room;
//    }
//    public String getChoice() {
//        return choice;
//    }
//
//    public void setChoice(String choice) {
//        this.choice = choice;
//    }
//    /*
//    Method to check occupancy of a room
//     */
//    public int getMaxOccupants(String roomType){
//        int max = 0;
//        for(int i = 0; i < getArray(this.choice).size(); i++){
//            if((roomType.equals(getArray(this.choice).get(i).getRoomType())) == true){
//                max = getArray(this.choice).get(i).getMaxOccupancy();
//                return max;
//            }
//        }
//        return max;
//    }
}
