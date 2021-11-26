import java.io.IOException;

/**
 A system to manage appointments.
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
