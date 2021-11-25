public class ReservationDate{

    private int year;
    private int month;
    private int day;

    public ReservationDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
        }
    public int getDay(){
        return this.day;
        }

    public void setDay(int day){
        this.day = day;
    }

    public int getMonth(){
        return this.month;
    }
    
    public void setMonth(int month){
        this.month = month;
    }

    public int getYear(){
        return this.year;
    }

    public void setYear(int year){
        this.year = year;



    }
    
}
