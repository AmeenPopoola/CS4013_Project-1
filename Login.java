import java.io.*;
import java.io.FileReader;
import java.util.*;

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
            java.io.File File = new java.io.File("src/main/resources/UsernameAndPasswords.csv");
            Scanner input = new Scanner(File);
            Scanner count = new Scanner(File);

            String line;
            String c;
            int z = 0;

            while(count.hasNext()){
                c = count.nextLine();
                z++;
            }
            count.close();
            /**
             * Creating new array that the data from the file gets added into.
             */
            String [] [] userAndPass = new String[z+1][3];
            int i = 0;
            while(input.hasNext() &&  i <z ){
                line = input.nextLine();
                userAndPass[i] = line.split(",");
                i++;
                //System.out.println(line + i);
            }
            input.close();
            /**
             * Checks if both the username and password are equal, and if they are
             * checks what kind of role the user is allowed to have in the system.
             */
            boolean isAuth = false;
            boolean adminOrNot = false;
           for (int j =1;j< userAndPass.length-1;j++) {
                String checkUserEqual = user.toLowerCase(Locale.ROOT);
                String authCheckUser = userAndPass[j][0].toString().toLowerCase(Locale.ROOT);
                String authCheckPass = userAndPass[j][1].toString();
                String authCheckRole = userAndPass[j][2].toString().toLowerCase(Locale.ROOT);
                if (authCheckUser.equals(checkUserEqual)){
                   if (authCheckPass.equals(pass)){
                       isAuth = true;
                       if (authCheckRole.equals("admin")){
                           adminOrNot = true;
                           break;
                       }
                   }
                }


            }
            if (!isAuth){
                System.out.println("You have inputted an incorrect username or password");

            }
            return adminOrNot;
        }

        catch (FileNotFoundException e){
            System.out.println("This file is missing or the file path is incorrect");
        }
        return false;
    }







}

