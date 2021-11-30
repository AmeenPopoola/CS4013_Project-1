import javax.swing.text.StyledEditorKit;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class ReservationCalendar {
    private ArrayList<Reservation> reservations;
    private ArrayList<Hotels> hotels;

    // The current booking number used by the system
    private int bookingNumber = 0;

    /**
     * Empty Constructor to load hotels and existing reservations
     */
    public ReservationCalendar()
    {
        reservations = new ArrayList<Reservation>();
        hotels = new ArrayList<Hotels>();

        AddReservations();
        AddHotels();
    }

    /**
     * method to add reservations from the csv file
     */
    private void AddReservations() {
        FileReader fr = new FileReader();
        String [] [] reservationCSV =null;
        try {
            reservationCSV = fr.readRes();

            for (int j =1;j< reservationCSV.length;j++) {
                Reservation r = new Reservation(
                        Integer.valueOf(reservationCSV[j][0].toString()),
                        reservationCSV[j][1].toString(),
                        reservationCSV[j][6].toString(),
                        parseDates(reservationCSV[j][3].toString()),
                        parseDates(reservationCSV[j][4].toString()),
                        Integer.valueOf(reservationCSV[j][5].toString()),
                        Integer.valueOf(reservationCSV[j][7].toString()),
                        Double.valueOf(reservationCSV[j][8].toString()),
                        reservationCSV[j][2].toString());

                addBooking(r, true);

                if (Integer.valueOf(reservationCSV[j][0].toString())  > bookingNumber) {
                    bookingNumber = Integer.valueOf(reservationCSV[j][0].toString());
                }
            }

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add hotels from the csv file
     */
    private void AddHotels() {
        FileReader fr = new FileReader();
        String [] [] hotelCSV =null;
        try {
            hotelCSV = fr.readHotels();

            String hotelName = "";

            //Sunday first to allow for rate look up using calendar
            for (int j =2;j< hotelCSV.length;j++) {
                double[] rates = new double[7];
                rates[0] =Double.valueOf(hotelCSV[j][11].toString());
                rates[1] =Double.valueOf(hotelCSV[j][5].toString());
                rates[2] =Double.valueOf(hotelCSV[j][6].toString());
                rates[3] =Double.valueOf(hotelCSV[j][7].toString());
                rates[4] =Double.valueOf(hotelCSV[j][8].toString());
                rates[5] =Double.valueOf(hotelCSV[j][9].toString());
                rates[6] =Double.valueOf(hotelCSV[j][10].toString());

                Room room = new Room(
                        hotelCSV[j][1].toString(),
                        Integer.valueOf(hotelCSV[j][3].toString()),
                        Integer.valueOf(hotelCSV[j][4].toString()),
                        rates
                );

                //If the hotel name is blank, use the previously recorded name.
                if (hotelCSV[j][0].toString() == "") {
                    hotelCSV[j][0] = hotelName;
                }

                Hotels h = new Hotels(
                        hotelCSV[j][0].toString(),
                        Integer.valueOf(hotelCSV[j][2].toString()),
                        room);

                hotels.add(h);
                hotelName = hotelCSV[j][0].toString();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method that adds a booking to the reservations arraylist
     * @param r
     * @param initalLoad
     */
    public void addBooking(Reservation r, Boolean initalLoad){
        reservations.add(r);
        if(!initalLoad) {
            this.UpdateReservationFile();
        }
    }

    /**
     * method that cancels a booking from the reservations arraylist
     * @param r
     */
    public void cancelBooking(Reservation r){
        reservations.remove(r);
        this.UpdateReservationFile();
    }

    /**
     * method to update the hotel details with a latest CSV file
     */
    public void updateRooms(){
    }

    /**
     * method that processes the selected room type the user selected that they want to reserve.
     * @param name
     * @param checkIn
     * @param checkOut
     * @param numberOfRooms
     * @param numberOfPeople
     * @param hotel
     * @param roomType
     * @param cost
     * @param reservationType
     * @return the current reservation number
     */
    public String makeBooking(String name, ReservationDate checkIn,
                                 ReservationDate checkOut,
                                 int numberOfRooms, int numberOfPeople,
                                 String hotel, String roomType, double cost,
                                 String reservationType){

        String currentReservation = "";
        try
        {
            // Increment the booking number for the new reservation number
            bookingNumber++;

            Reservation res = new Reservation(bookingNumber, name, roomType, checkIn,
            checkOut, numberOfRooms, numberOfPeople, cost, reservationType);

            this.addBooking(res,false);

            currentReservation = String.valueOf(bookingNumber);
        }
        catch(Exception e) {
            // Failed to add the reservation.
            // We need to decrement the reference number.
            bookingNumber--;
        }

        // We return and show the user their booking reference number.
        return currentReservation;
    }

    /**
     * method that gets a list of available rooms combinations based on the enquiry details.
     * @param checkIn
     * @param checkOut
     * @param numberOfRooms
     * @param numberOfPeople
     * @return list of available rooms 
     */
    public ArrayList<String> checkAvailability(ReservationDate checkIn,
                                                  ReservationDate checkOut,
                                                  int numberOfRooms, int numberOfPeople){

        ArrayList<String> res = new ArrayList<String>();

        for (int i = 0; i < hotels.size(); i++)
        {
            Hotels h = hotels.get(i);
            if ((numberOfPeople <= h.getRoomType().getMaxOccupancy()) &&
                    (numberOfPeople >= h.getRoomType().getMinOccupancy())) {

                // Next check if the number of rooms is within limits
                if (h.getRooms_available() >= numberOfRooms) {

                    // Get the number of available rooms by checking the room type and existing reservations.
                    if (this.CheckNumberOfRoomsAvailable(h, checkIn, checkOut) >= numberOfRooms) {
                        // Rooms are available. AP is if the checkin is 1 week from now or later.
                        Boolean isAdvancePurchase = false;
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date dateCheckIn = df.parse(checkIn.format());

                            Date nowDate=new java.util.Date();
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(nowDate);
                            cal.add(Calendar.WEEK_OF_YEAR, 1); // If check is 7 or more days time, then AP.
                            nowDate = cal.getTime();
                            if (dateCheckIn.after(nowDate)) {
                                isAdvancePurchase = true;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // Calculate the rates for each day based on the daily rate
                        double rate = (GetRates(h, checkIn, checkOut) * numberOfRooms);

                        // Apply the 5% discount is AP
                        if (isAdvancePurchase) {
                            rate = rate * 0.95;
                        }

                        // Add the hotel info that will be displayed to the user
                        res.add(hotels.get(i).format(rate, isAdvancePurchase));
                    }
                }
            }
        }
        return res;
    }

    /**
     * method that gets a list of reservation that can be cancelled in the system based on the user and their permission.
     * @param username
     * @param isAdmin
     * @return list of reservations
     */
    public ArrayList<Reservation> getReservations(String username, Boolean isAdmin){
        ArrayList<Reservation> res = new ArrayList<Reservation>();

        // If it matches the username add to an ArrayList.
        // Or if admin, always add it.
        for (int i = 0; i < reservations.size(); i++)
        {
            if (isAdmin || (reservations.get(i).getName().equals(username)))
            {
                res.add(reservations.get(i));
            }
        }
        return res;
    }

    /**
     * method that gets a reservation based on a reservation ID.
     * @param reservationID
     * @return a single reservation 
     */
    public Reservation getReservation(Integer reservationID){
        // If it matches the username add to an ArrayList.
        // Or if admin, always add it.
        for (int i = 0; i < reservations.size(); i++)
        {
            if (reservations.get(i).getResNumber() == reservationID)
            {
                return reservations.get(i);
            }
        }
        return null;
    }

    /**
     * method that is a common function to split a date into day month year and return a reservation object.
     * @param dateToParse
     * @return a new ReservationDate object
     */
    private ReservationDate parseDates(String dateToParse) {
        StringTokenizer app = new StringTokenizer(dateToParse, "/");
        int day = Integer.parseInt(app.nextToken());
        int month = Integer.parseInt(app.nextToken());
        int year = Integer.parseInt(app.nextToken());
        return new ReservationDate(day, month, year);
    }

    /**
     * method that gets rates by getting the day of the week and looking up the rates array for the value.
     * @param h
     * @param checkIn
     * @param checkOut
     * @return rates of the total stay 
     */
    private Double GetRates(Hotels h, ReservationDate checkIn, ReservationDate checkOut) {
        Double rate = 0.0;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateCheckIn = df.parse(checkIn.format());
            Date dateCheckOut = df.parse(checkOut.format());

            // getting milliseconds for both dates
            long timeDiffOfDates  = dateCheckOut.getTime() - dateCheckIn.getTime();

            // converting diff into days
            int numberOfDays = (int) (timeDiffOfDates / (1000 * 3600 * 24));

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateCheckIn);
            for (int i = 0; i < numberOfDays; i++) {
                int day = cal.get(Calendar.DAY_OF_WEEK);
                rate = rate + h.getRoomType().getRates()[day - 1]; //rate array is zero based
                cal.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rate;
    }

    /**
     * method that figuress out if there are rooms in the hotel.
     * the method then checks the room type and if there is overlapping reservations, how many rooms that reservation had.
     * @param h
     * @param checkIn
     * @param checkOut
     * @return number of available rooms
     */
    private Integer CheckNumberOfRoomsAvailable(Hotels h, ReservationDate checkIn, ReservationDate checkOut) {

        Integer maxRoomsAvailable = h.getRooms_available();

        for (int i = 0; i < reservations.size(); i++)
        {
            if (reservations.get(i).getRoomType().equals(h.getRoomType().getRoomType().toString()))
            {
                // A reservation for the same room type. Check if overlapping.
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date dateCheckIn = df.parse(checkIn.format());
                    Date resCheckIn = df.parse(reservations.get(i).getCheckin().format());

                    Date dateCheckOut = df.parse(checkOut.format());
                    Date resCheckOut = df.parse(reservations.get(i).getCheckout().format());

                    //The date after method doesnt contain equals - removing 1 day to allow checks.
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(resCheckIn);
                    cal.add(Calendar.DATE, -1);
                    resCheckIn = cal.getTime();

                    // check if dates ranges overlap
                    //(StartA <= EndB) and (EndA >= StartB),
                    if (dateCheckIn.before(resCheckOut) && dateCheckOut.after(resCheckIn)) {
                        // Our count of available rooms is reduced.
                        maxRoomsAvailable = maxRoomsAvailable - reservations.get(i).getNumberOfRooms();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return maxRoomsAvailable;
    }

    /**
     * method that writes out all reservations.
     */
    private void UpdateReservationFile() {
        String header = "Res.No,Res.Name,Res.Type,Check-In,Check-Out,No.Rooms,Room Type,Occupants Total Cost";

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("Reservations.csv"));
            writer.write(header + System.getProperty("line.separator"));
            for (int i = 0; i < reservations.size(); i++)
            {
                Reservation r = reservations.get(i);
                writer.write(r.getResNumber() + "," +
                        r.getName() + "," +
                        r.getReservationType() +"," +
                        r.getCheckin().format() +"," +
                        r.getCheckout().format() +"," +
                        r.getNumberOfRooms() +"," +
                        r.getRoomType() +"," +
                        r.getNumberOfPeople() +"," +
                        r.getTotalCost() + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// import java.util.ArrayList;
// import java.util.GregorianCalendar;

//  public class ReservationCalendar {
//     private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    
//     /** Fill the array list with reservations from reservations.csv */
//     public ReservationCalendar(String[][] resCal, Hotels hotel){
//         ArrayList<Room> ro;
//         for(int i = 1; i < resCal.length; i++){
//             int num = Integer.parseInt(resCal[i][0]);
//             int count = 0;

//             for(int x = 0; x < reservations.size(); x++){
//                 if(num == reservations.get(x).getResNumber()){
//                     count++;
//                 }
                
//             }
//             if(count == 0){
//                 if(resCal[i][2].equals("Classic Single") || resCal[i][2].equals("Classic Twin") || resCal[i][2].equals("Classic Double")){
//                     ro = hotel.getArray("3");
//                 }else if(resCal[i][2].equals("Executive Single") || resCal[i][2].equals("Executive Twin") || resCal[i][2].equals("ExecutiveDouble")){
//                     ro = hotel.getArray("4");
//                 }else{
//                     ro = hotel.getArray("5");
//                 }
//                 int resNumber = Integer.parseInt(resCal[i][0]);
//                 String name = resCal[i][1];
//                 String roomType = resCal[i][2];
//                 String dateFrom = resCal[i][3];
//                 boolean resType = Boolean.parseBoolean(resCal[i][4]);
//                 int nights = Integer.parseInt(resCal[i][5]);
//                 int numberOfRooms = Integer.parseInt(resCal[i][6]);
//                 int numberOfPeople = Integer.parseInt(resCal[i][7]);
//                 double totalCost = Double.parseDouble(resCal[i][8]);

//                 Reservation reservation = new Reservation(resNumber, name, roomType, dateFrom, resType, nights, numberOfRooms, numberOfPeople, totalCost);
//                 int loop = 1;
//                 for(int j = 0; j < ro.size(); j++){
//                     if((roomType.equals(ro.get(j).getRoomType())) == true){
//                         if(loop <= numberOfRooms){
//                             ro.get(j).getBooked().add(reservation);
//                             loop++;
//                         }else{
//                             reservations.add(reservation);
//                             break;
//                         }
//                     }
//                 }

//             }
//         }
//     }

//     /**Retrieve the reates for the chosen roomType */
//     public double[] getRates(Reservation res, Hotels hot){
//         for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
//             if((res.getRoomType().equals(hot.getArray(hot.getChoice()).get(i).getRoomType())) == true){
//                 return hot.getArray(hot.getChoice()).get(i).getRates();
//             }
//         }
//         return null;
//     }
//     /**Cancel a reservation and remove it from the reservation list
//      * and removes it from the rooms
//      */

//      public boolean cancel(int resNumber, Hotels hot, HotelSystem hsys){
//         GregorianCalendar greg = new GregorianCalendar();
// 		boolean flip = false;
// 		Reservation cancelled = null;
// 		for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
// 			for(int j = 0; j < hot.getArray(hot.getChoice()).get(i).getBooked().size(); j++){
// 				if(resNumber == hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getNumber()){
// 					if(hot.getArray(hot.getChoice()).get(i).getBooked().get(j).isResType()){
// 						if((hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getDateFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
// 							cancelled = hot.getArray(hot.getChoice()).get(i).getBooked().get(j);
// 							hot.getArray(hot.getChoice()).get(i).getBooked().remove(j);
// 							flip = true;

// 						}else{
// 							System.out.println("Too close to checkin date to cancel!");
// 							return false;
// 						}
// 					}else{
// 						System.out.println("Not allowed cancel AP reservations!");
// 						return false;
// 					}
// 				}
// 			}
// 		}
		
// 		if(flip == false){
// 			return false;
// 		}
// 		hsys.getCheckData().getCancellations().add(cancelled);
// 		hsys.getRead().writeCancellations(hot, hsys.getCheckData());
// 		for(int i = 0; i < reservations.size(); i++){
// 			if(reservations.get(i).getResNumber() == resNumber){
// 				if((reservations.get(i).getFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
// 					reservations.remove(i);
// 				}
// 			}
// 		}
// 		return true;
// 	}
    
// 	public ArrayList<Reservation> getReservations() {
// 		return reservations;
// 	}
// }
