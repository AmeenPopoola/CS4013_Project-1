import java.util.ArrayList;
public class Hotels {
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