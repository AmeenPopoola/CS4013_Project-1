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

            /**
             * Checks if both the username and password are equal, and if they are
             * checks what kind of role the user is allowed to have in the system.
             * Does this using the readUserPasswordFile method from the fileReader class
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
