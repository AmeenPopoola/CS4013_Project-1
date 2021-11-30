import java.io.*;
import java.util.*;

public class Login {
    private String username;
    private String password;
    private Boolean isAdmin = false;

    /**
     * method that updates the private data fields in the class with the inputted data fields
     * @param username
     * @param password
     */
    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }


    /**
     *  determines if the user can get admin privileges or not
     * @return the role of the user if logged in.
     */
    public Boolean getIsAdmin () {
        return isAdmin;
    }



    /**
     * method that gets the username of the current user
     * @return the username of the user that logged in.
     */
    public String getUsername () {
        return username;
    }

    /**
     * method that determines if the user has entered correct information to log into the system
     * @return isAuth or false 
     */
    public boolean getAccess(/*String user, String pass*/){
        try {

            /**
             * Checks if both the username and password are equal, and if they are
             * checks what kind of role the user is allowed to have in the system.
             */

            FileReader fr = new FileReader();
            String [] [] userAndPass = fr.readUserPasswordFile();

            boolean isAuth = false;
            for (int j =1;j< userAndPass.length;j++) {
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
