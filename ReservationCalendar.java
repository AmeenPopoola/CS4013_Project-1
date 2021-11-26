import java.util.ArrayList;

/**
     * Class to create an ArrayList that will contain all bookings.
     * Along with methods to add and remove reservations.
     * And a method to show all Reservations on a day
     */
 public class ReservationCalendar {
    private ArrayList<Reservation> reservations;

        /** Empty Constructor */
    public void addBooking(Reservation r){
        reservations.add(r);
        
    
}
    public void cancelBooking(Reservation r){
        reservations.remove(r);
    }
    public ArrayList<Reservation> getReservationForDay(ReservationDate day){
        if(day.equals(day)){
            
        }return reservations;
    }
}