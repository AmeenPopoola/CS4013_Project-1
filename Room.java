import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Arrays;

public class Room {

    private String roomType;
    private int minOccupancy;
    private int maxOccupancy;
    private double[] rates;
    private ArrayList<Reservation> booked;

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

    /**
     * Checks if room is available
     */

    public boolean checkAvailablity(Reservation res , Hotels hot , GregorianCalendar dateFrom , GregorianCalendar dateTo){
        GregorianCalendar from = null ;
        GregorianCalendar to = null;
        int pos = 0;

        for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
            if(roomType.equals(hot.getArray(hot.getChoice()).get(i).getRoomType())){
                if (hot.getArray(hot.getChoice()).get(i).getBooked().size() > 0) {
                    for (int j = 0; j < hot.getArray(hot.getChoice()).get(i).getBooked.size(); j++) {
                        from = hot.getArray(hot.getChoice()).get(i).getBooked(j).getDateFrom();
                        to = hot.getArray(hot.getChoice()).get(i).getBooked(j).getDateTo();

                        if (dateFrom.before(from) && dateTo.before(to) || dateFrom.after(from) && dateTo.after(to)) {
                            pos = i;
                            hot.getArray(hot.getChoice()).get(pos).getBooked().add(res);
                            return true;
                        } else {
                            break;
                        }
                    }
                } else {
                    hot.getArray(hot.getChoice()).get(i).getBooked().add(res);
                    return true;
                }
            }
        }
        return false;
    }
                            
                    
                    }
                    
                
