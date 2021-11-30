import java.io.IOException;

/**
 A system to manage reservations.
 Creates a new menu object that is then ran.
 */
public class HotelSystem
{   
    public static void main(String[] args)
            throws IOException
    {
        HotelMenu menu = new HotelMenu();
        menu.run();
    }
}
