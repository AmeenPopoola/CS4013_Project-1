import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 A menu for the Hotel system.
 */
public class HotelMenu
{
    private Scanner in;

    /**
     Constructs an HotelMenu object.
     */
    public HotelMenu()
    {
        in = new Scanner(System.in);
    }

    /**
     Runs the system.
     */
    public void run() {
        boolean more = true;
        ReservationCalendar calendar = new ReservationCalendar();
        boolean isAuth = false;
        boolean isAdmin = false;

        while (more){
            if (isAuth) {
                if (isAdmin) {
                    System.out.println("R)emove rooms A)dd rooms C)ancel a booking");
                }else{
                    System.out.println("C)ancel a booking N)ew booking S)ign out");
                }
            }
            else
            {
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
                    isAdmin = login.getIsAdmin();
                }
            }else if(command.equals("Q")){
                more = false;
            }else if (isAdmin && isAuth){
                // Only R, A, C are valid
                System.out.println("Only R, A, C are valid");
            }else if (!isAdmin && isAuth){
                isAuth = userOptions(isAuth, command);
            }else{
                System.out.println("Invalid option " + command + ".  Please pick a valid choice");
            }


//            String command = in.nextLine().toUpperCase();
//                if (command.equals("L")){
//                    System.out.println("Please enter your username:");
//                    String user = in.nextLine();
//                    System.out.println("Please enter your password");
//                    String pass = in.nextLine();
//                    Login a = new Login(user, pass);
//                    if (a)
//
//
//                }
//                System.out.println("A)dd  C)ancel  S)how  Q)uit");
//
//
//                if (command.equals("A"))
//                {
//                    System.out.println("Appointment (Description Date From To)");
//                    String line = in.nextLine();
//                    Reservation a = new Reservation(line, 22,22,22);
//                    calendar.addBooking(a);
//                }
//                else if (command.equals("C"))
//                {
//                    System.out.println("Enter Appointment Date");
//                    String line = in.nextLine();
//                    ReservationDate day = new ReservationDate(line);
//                    Reservation a = getChoice(calendar.getReservationForDay(day));
//                    if (a != null)
//                        calendar.cancelBooking(a);
//                }
//                else if (command.equals("S"))
//                {
//                    System.out.println("Date");
//                    String line = in.nextLine();
//                    ReservationDate day = new ReservationDate(line);
//                    for (Reservation appt : calendar.getReservationForDay(day))
//                        System.out.println(appt.format());
//                }
//                else if (command.equals("Q"))
//                {
//                    more = false;
//                }
    }
}

    private boolean userOptions(boolean isAuth, String command) {
        if (command.equals("N")){
           /** new booking
            *name-- using logged in user
            *checkin date -- look at accommadation token
            * check out date -- ^^^^
            * number of rooms{
            * room type --
            * occupants}
            *
            */
            System.out.println("Please enter your checkin date in the form DD/MM/YYYY:");
            String checkIn = in.nextLine();
            StringTokenizer app = new StringTokenizer(checkIn, "/");
            int day = Integer.parseInt(app.nextToken());
            int month = Integer.parseInt(app.nextToken());
            int year = Integer.parseInt(app.nextToken());
            System.out.println(month);


        }else if (command.equals("C")){
            //Cancel booking
        }else if (command.equals("S")){
            isAuth = false;
        }else {
            // Only C N S are valid
            System.out.println("Invalid option " + command + ".  Please pick a valid choice");
        }
        return isAuth;
    }

    private Reservation getChoice(ArrayList<Reservation> choices)
{
    if (choices.size() == 0) return null;
    while (true)
    {
        char c = 'A';
        for (Reservation choice : choices)
        {
            System.out.println(c + ") " + choice.format());
            c++;
        }
        String input = in.nextLine();
        int n = input.toUpperCase().charAt(0) - 'A';
        if (0 <= n && n < choices.size())
            return choices.get(n);
    }
}
}
