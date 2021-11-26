import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
public class Login {
    public static void main(String[] args) {
        getAccess("ciarabrod", "ciarabrod");
    }
    private String username;
    private String password;
    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }
    public static boolean getAccess(String user, String pass){
        try {
            /**Reading the file and adding the password and username to an array
             * along with the access that the person is given (admin or customer)
            **/
            readUserPasswordFile();

            Scanner input = new Scanner(System.in);
            System.out.println("Enter your username");
            String UserInput = input.next();
            System.out.println("Enter your password");
            String PassInput = input.next();

           for (int i =1;i< userAndPass.length-1;i++) {
                String checkAccess = UserInput + "," + PassInput;
                String authCheck = userAndPass[i].toString().toLowerCase(Locale.ROOT);
               // String authCheckUser = authCheck.split(",");
                if (authCheck == checkAccess.toLowerCase(Locale.ROOT)){
                    System.out.println("Not a user");
                }
                else{
                    System.out.println("This is a user and can log in");
                }
            }
            Filereader.close();
            return true;
        }

        catch (FileNotFoundException e){
            System.out.println("This file is missing or the file path is incorrect");
        }
        return false;
    }







}
