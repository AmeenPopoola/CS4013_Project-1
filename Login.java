import java.io.*;
import java.util.*;

public class Login {
    private String username;
    private String password;
    private Boolean isAdmin = false;

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Return the role of the user if logged in.
    public Boolean getIsAdmin () {
        return isAdmin;
    }

    // Return the username of the user that logged in.
    public String getUsername () {
        return username;
    }

    public boolean getAccess(/*String user, String pass*/){
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
            String [] [] userAndPass = new String[z][3];
            z = 0;
            while(input.hasNext()){
                line = input.nextLine();
                userAndPass[z] = line.split(",");
                z++;
            }
            input.close();

            /**
             * Checks if both the username and password are equal, and if they are
             * checks what kind of role the user is allowed to have in the system.
             */


            boolean isAuth = false;
            for (int j =1;j< userAndPass.length-1;j++) {
                String checkUserEqual = this.username.toLowerCase(Locale.ROOT);
                String authCheckUser = userAndPass[j][0].toString().toLowerCase(Locale.ROOT);
                String authCheckPassword = userAndPass[j][1].toString();
                String authCheckRole = userAndPass[j][2].toString().toLowerCase(Locale.ROOT);
                if ((authCheckUser.equals(checkUserEqual)) && (authCheckPassword.equals(this.password))){
                    isAuth = true;
                    this.isAdmin = authCheckRole.equals("admin");
                    break;
                }
            }

            if (!isAuth) {
                System.out.println("You have inputted an incorrect username or password");
            }

            return isAuth;
        }
        catch (FileNotFoundException e){
            System.out.println("This file is missing or the file path is incorrect");
        }
        return false;
    }
}
