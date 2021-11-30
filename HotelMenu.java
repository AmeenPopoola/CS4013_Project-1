import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 A menu for the Hotel system.
 */
public class HotelMenu
{
    private Scanner in;
    private ReservationCalendar calendar = new ReservationCalendar();

    /**
     Constructs an HotelMenu object.
     */
    public HotelMenu()
    {
        in = new Scanner(System.in);
    }

    /**
     * Used to run the system
     * Displays multiple options for the user to choose from
     * decides what authority the user can have
     */
    public void run() {
        boolean more = true;
        boolean isAuth = false;
        boolean isAdmin = false;
        String currentLoggedInUser = "";

        while (more){
            if (isAuth) {
                if (isAdmin) {
                    //System.out.println("R)emove rooms A)dd rooms C)ancel a booking S)ign out");
                    System.out.println("C)ancel a booking S)ign out");
                }else{
                    System.out.println("C)ancel a booking N)ew booking S)ign out");
                }
            }
            else {
                System.out.println("L)ogin Q)uit");
            }

            String command = in.nextLine().toUpperCase();
            if (command.equals("L") && !isAuth) {
                System.out.println("Please enter your username:");
                String user = in.nextLine();
                System.out.println("Please enter your password");
                String pass = in.nextLine();
                Login login = new Login(user, pass);
                isAuth = login.getAccess();
                if (isAuth) {
                    currentLoggedInUser = login.getUsername();
                    isAdmin = login.getIsAdmin();
                }
            }else if(command.equals("Q")){
                more = false;
            }else if (command.equals("S")){
                isAuth = false;
                currentLoggedInUser = "";
            }else if (isAuth && command.equals("C")){
                //Cancel booking - this is common to both admin and a user
                System.out.println("Select reservation to cancel.");
                ArrayList<Reservation> reservations = calendar.getReservations(currentLoggedInUser, isAdmin);
                Reservation res = (Reservation) getChoice(reservations.toArray(new Object[reservations.size()]));
                if (res != null) {
                    calendar.cancelBooking(res);
                }else {
                    System.out.println("No reservation available to cancel.");
                }
//              Add and Remove rooms
//            }else if (isAdmin && isAuth){
//                adminOptions(currentLoggedInUser, command);
            }else if (!isAdmin && isAuth){
                userOptions(currentLoggedInUser, command);
            }else{
                System.out.println("Invalid option " + command + ".  Please pick a valid choice");
            }
        }
    }



    /**
     *   Admin options to add and remove rooms.
     *   Would use the same code to upload a newer hotels csv file to the system
     *   Not currently in use due to change in specification
     * @param currentLoggedInUser
     * @param command
     */
    private void adminOptions(String currentLoggedInUser, String command) {
        if (command.equals("R") || command.equals("A")){
            //Remove or Add room(s).
            String[] updateRooms = new String[2];
            updateRooms[0] = "Yes";
            updateRooms[1] = "No";

            System.out.println("Do you wish to update you hotel and rooms with your latest CSV file?");
            String update = (String) getChoice(updateRooms);
            if (update == "Yes") {
                calendar.updateRooms();
                System.out.println("Your hotel details have been updated.");
            }
        }else {
            System.out.println("Invalid option " + command + ". Only Remove room, Add room and Cancel and sign out are valid");
        }
    }



    /**
     *  User options to make a booking
     * @param currentLoggedInUser
     * @param command
     */
    private void userOptions(String currentLoggedInUser, String command) {

        if (command.equals("N")){
            System.out.println("To check availability, please enter the following information.");
            System.out.println("Please enter your check in date in the form DD/MM/YYYY:");
            String checkIn = in.nextLine();
            if (this.isDateValid(checkIn)) {

                System.out.println("Please enter your check out date in the form DD/MM/YYYY");
                String checkOut = in.nextLine();
                if (this.isDateValid(checkOut)) {
                    ReservationDate checkInDate = parseDates(checkIn);
                    ReservationDate checkOutDate = parseDates(checkOut);

                    System.out.println("How many rooms do you want to book?");
                    String numberOfRooms = in.nextLine();

                    System.out.println("How many guests per room?");
                    String numberOfGuestPerRoom = in.nextLine();

                    System.out.println("Thank you. Checking availability......");


                    // Get the availability of rooms based on the entered dates, rooms and guests.
                    // This checks if the hotel has availability, the number of rooms and guest are valid.
                    ArrayList<String> availability = calendar.checkAvailability (checkInDate, checkOutDate,
                            Integer.valueOf(numberOfRooms), Integer.valueOf(numberOfGuestPerRoom));

                    if (availability != null && availability.size() > 0) {

                        // There is availability to show to the user. Get their choice.
                        String res = (String) getChoice(availability.toArray(new Object[availability.size()]));

                        if (res != null) {
                            // A room type selection was made. That hotel name, room type and cost must be extracted.
                            // Sample room info presented:
                            //"5-star, Deluxe Double, 100 Total cost in Euros for stay, AP - Advance purchase discount"
                            StringTokenizer app = new StringTokenizer(res, ",");
                            String hotel = app.nextToken().trim();
                            String roomType = app.nextToken().trim();
                            String costPerNight[] = app.nextToken().trim().split(" ");
                            Double cost = Double.parseDouble(costPerNight[0]);
                            String rateInfo[] = app.nextToken().trim().split(" ");
                            String reservationType = rateInfo[0];

                            String bookingReference = calendar.makeBooking(currentLoggedInUser,
                            checkInDate, checkOutDate, Integer.valueOf(numberOfRooms),
                            Integer.valueOf(numberOfGuestPerRoom), hotel, roomType, cost, reservationType);

                            if (bookingReference.trim().length() > 0) {
                                System.out.println("Your reservation was successful. Reservation number: " + bookingReference);
                            }else {
                                System.out.println("Your reservation was not successful. Please try again");
                            }
                        }
                    }else {
                        System.out.println("Your availability - please check your dates, hotel and room options.");
                    }
                }else {
                    System.out.println("Check out date is invalid.");
                }
            }else {
                System.out.println("Check in date is invalid.");
            }
        }else {
            System.out.println("Invalid option " + command + ".  Only New Booking, Cancel and sign out are valid options");
        }
    }



    /**
     * Common function to split a date into day month year and return a reservation object.
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
     * Validate if an entered date is valid.
     * @param dateToCheck
     * @return true or false depending on if the date is valid
     */
    private Boolean isDateValid(String dateToCheck) {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            simpleDateFormat.parse(dateToCheck);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }



    /**
     *    Show choices to the user. Used to show
     *      - available rooms
     *      - reservations to cancel
     *      - if to update their hotel and room details (admin - add/remove rooms)
     *
     *
     * @param choices
     * @return choices[n]
     */
    private Object getChoice(Object[] choices)
    {
        if (choices.length == 0) return null;
        while (true)
        {
            char c = 'A';
            for (Object choice : choices)
            {
                    System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
           if (input.equals("")) {
                return null;
            }
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }
}
