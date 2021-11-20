/**Public Class for Reservation */
public class Reservation {
    private String description;
    private ReservationDate day;
    private ReservationTime from;
    private ReservationTime to;

    /**Reservation Constructor method */
    public Reservation(String description, ReservationDate day, ReservationTime from, ReservationTime to){
        this.description = description;
        this.day = day;
        this.from = from;
        this.to = to;

    }
    /** get the current Description */
    public String getDescription() {
        return this.description;
    }
    /** set a new description */
    public void setDescription(String description) {
        this.description = description;
    }
    /** get the current day */
    public ReservationDate getDay() {
        return this.day;
    }
    /** set a new day */
    public void setDay(ReservationDate day) {
        this.day = day;
    }
    /** get the start time */
    public ReservationTime getFrom() {
        return this.from;
    }
    /** Set a new start time */
    public void setFrom(ReservationTime from) {
        this.from = from;
    }
    /** Get the end time */
    public ReservationTime getTo() {
        return this.to;
    }
    /** Set a new end time */
    public void setTo(ReservationTime to) {
        this.to = to;
    }

    
}
