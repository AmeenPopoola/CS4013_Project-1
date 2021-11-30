public class ReservationDate{

    private int year;
    private int month;
    private int day;

    /**
     * constructor that alters the private data fields in the class with the inputted data fields.
     * @param day
     * @param month
     * @param year
     */
    public ReservationDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * meethod that gets the day
     * @return the day
     */
    public int getDay(){
        return this.day;
    }

    /**
     * method that sets the day
     * @param day
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * method that gets the month  
     * @return the month
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * method that sets the month
     * @param month
     */
    public void setMonth(int month){
        this.month = month;
    }

    /**
     * method that gets the year
     * @return  the year
     */
    public int getYear(){
        return this.year;
    }

    /**
     * method that sets the year
     * @param year
     */
    public void setYear(int year){
        this.year = year;



    }

    /**
     * method that formats the output of the datafields in the class
     * @return thehe date in the format dd/mm/yyyy
     */
    public String format(){
        return this.day + "/" + this.month + "/" + this.year;
    }
}
