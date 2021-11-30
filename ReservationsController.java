/**
 * Sample Skeleton for 'Reservations.fxml' Controller Class
 */

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;

public class ReservationsController {
    private ReservationCalendar calendar = new ReservationCalendar();
    private boolean isAuth = false;
    private boolean isAdmin = false;
    private String currentLoggedInUser = "";
    ReservationDate checkInDate;
    ReservationDate checkOutDate;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAddRooms"
    private Button btnAddRooms; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheckAvailability"
    private Button btnCheckAvailability; // Value injected by FXMLLoader

    @FXML // fx:id="btnGetReservations"
    private Button btnGetReservations; // Value injected by FXMLLoader

    @FXML // fx:id="btnLogin"
    private Button btnLogin; // Value injected by FXMLLoader

    @FXML // fx:id="btnQut"
    private Button btnQut; // Value injected by FXMLLoader

    @FXML // fx:id="btnRemoveRooms"
    private Button btnRemoveRooms; // Value injected by FXMLLoader

    @FXML // fx:id="btnReserve"
    private Button btnReserve; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignOut"
    private Button btnSignOut; // Value injected by FXMLLoader

    @FXML // fx:id="dteCheckIn"
    private DatePicker dteCheckIn; // Value injected by FXMLLoader

    @FXML // fx:id="dteCheckOut"
    private DatePicker dteCheckOut; // Value injected by FXMLLoader

    @FXML // fx:id="grpLogin"
    private Group grpLogin; // Value injected by FXMLLoader

    @FXML // fx:id="lblCancellationStatus"
    private Label lblCancellationStatus; // Value injected by FXMLLoader

    @FXML // fx:id="lblLoginMessage"
    private Label lblLoginMessage; // Value injected by FXMLLoader

    @FXML // fx:id="lblPassword"
    private Label lblPassword; // Value injected by FXMLLoader

    @FXML // fx:id="lblReservationStatus"
    private Label lblReservationStatus; // Value injected by FXMLLoader

    @FXML // fx:id="lblUsername"
    private Label lblUsername; // Value injected by FXMLLoader

    @FXML // fx:id="lblWelcome"
    private Label lblWelcome; // Value injected by FXMLLoader

    @FXML // fx:id="lstCancelReservation"
    private ListView<String> lstCancelReservation; // Value injected by FXMLLoader

    @FXML // fx:id="lstReserve"
    private ListView<String> lstReserve; // Value injected by FXMLLoader

    @FXML // fx:id="tabAddRooms"
    private Tab tabAddRooms; // Value injected by FXMLLoader

    @FXML // fx:id="tabCancelReservation"
    private Tab tabCancelReservation; // Value injected by FXMLLoader

    @FXML // fx:id="tabNewReservation"
    private Tab tabNewReservation; // Value injected by FXMLLoader

    @FXML // fx:id="tabOptions"
    private TabPane tabOptions; // Value injected by FXMLLoader

    @FXML // fx:id="tabRemoveRooms"
    private Tab tabRemoveRooms; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumberOfGuests"
    private TextField txtNumberOfGuests; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumberOfRooms"
    private TextField txtNumberOfRooms; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassword"
    private PasswordField txtPassword; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsername"
    private TextField txtUsername; // Value injected by FXMLLoader

    @FXML
    /**
     * method that logs the user into the system with javaFx
     */
    void LoginClick(ActionEvent event) {

        Login login = new Login(txtUsername.getText(), txtPassword.getText());
        isAuth = login.getAccess();
        if (isAuth) {
            // if user and password are valid, hide the group which has the login controls.
            // Show the tabs based on user or admin.
            // show the sign out controls
            currentLoggedInUser = login.getUsername();
            isAdmin = login.getIsAdmin();

            lblLoginMessage.setVisible(false);
            grpLogin.setVisible(false);
            txtUsername.setText("");
            txtPassword.setText("");

            btnSignOut.setVisible(true);
            tabOptions.setVisible(true);
            lblWelcome.setText("Welcome " + txtUsername.getText());
            lblWelcome.setVisible(true);

            tabOptions.getTabs().remove(tabNewReservation);
            tabOptions.getTabs().remove(tabCancelReservation);
            tabOptions.getTabs().remove(tabAddRooms);
            tabOptions.getTabs().remove(tabRemoveRooms);

            if (isAdmin) {
                tabOptions.getTabs().add(tabCancelReservation);
                //tabOptions.getTabs().add(tabAddRooms);
                //tabOptions.getTabs().add(tabRemoveRooms);
            } else {
                tabOptions.getTabs().add(tabNewReservation);
                tabOptions.getTabs().add(tabCancelReservation);
            }
        } else {
            lblLoginMessage.setVisible(true);
        }
    }

    @FXML
    /**
     * method that signs the users out of the system with javaFx
     */
    void SignOutClick(ActionEvent event) {

        // switch controls displayed to allow a user log in again

        isAuth = false;
        isAdmin = false;
        currentLoggedInUser = "";
        lblWelcome.setVisible(false);
        btnSignOut.setVisible(false);

        grpLogin.setVisible(true);
        tabOptions.setVisible(false);
    }

    @FXML
    /**
     * method that closes the software
     */
    void QuitClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    /**
     * method that updates the available rooms
     */
    void AddRoomClick(ActionEvent event) {
        calendar.updateRooms();
    }
    @FXML
    /**
     * method the remmoves the available rooms
     */
    void RemoveRoomClick(ActionEvent event) {
        calendar.updateRooms();
    }

    @FXML
    /**
     * method that checks the availability of a room
     */
    void CheckAvailabilityClick(ActionEvent event) {
        lblReservationStatus.setText("");
        lstReserve.getItems().clear();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String inDate = dateFormatter.format(dteCheckIn.getValue());
        String outDate = dateFormatter.format(dteCheckOut.getValue());

        if (this.isDateValid(inDate) && this.isDateValid(outDate))
        {
            checkInDate = parseDates(inDate);
            checkOutDate = parseDates(outDate);

            // Get the availability of rooms based on the entered dates, rooms and guests.
            // This checks if the hotel has availability, the number of rooms and guest are valid.
            ArrayList<String> availability = calendar.checkAvailability (checkInDate, checkOutDate,
                    Integer.valueOf(txtNumberOfRooms.getText()), Integer.valueOf(txtNumberOfGuests.getText()));

            if (availability != null && availability.size() > 0) {
                for (int i = 0; i < availability.size(); i++) {
                    lstReserve.getItems().add(availability.get(i));
                }
            }
        }
    }

    @FXML
    /**
     * method that will make a reservation
     */
    void ReserveClick(ActionEvent event) {

        // A room type selection was made.
        // That hotel name, room type, cost and rate type must be extracted.
        if (lstReserve.getSelectionModel().getSelectedItems().size() > 0) {
            String res = lstReserve.getSelectionModel().getSelectedItems().toString();
            // Sample room info presented:
            //"5-star, Deluxe Double, 100 Total cost in Euros for stay, AP - Advance purchase discount"
            StringTokenizer app = new StringTokenizer(res, ",");
            String hotel = app.nextToken().trim();
            String roomType = app.nextToken().trim();
            String costPerNight[] = app.nextToken().trim().split(" ");
            Double cost = Double.parseDouble(costPerNight[0]);
            String rateInfo[] = app.nextToken().trim().split(" ");
            String reservationType = rateInfo[0];

            // Make the booking
            String bookingReference = calendar.makeBooking(currentLoggedInUser,
                    checkInDate, checkOutDate, Integer.valueOf(txtNumberOfRooms.getText()),
                    Integer.valueOf(txtNumberOfGuests.getText()), hotel, roomType, cost, reservationType);

            if (bookingReference.trim().length() > 0) {
                lblReservationStatus.setText ("Your reservation was successful. Reservation number: " + bookingReference);
                lstReserve.getItems().clear();
            } else {
                lblReservationStatus.setText("Your reservation was not successful. Please try again");
            }
        }
    }

    @FXML
    /**
     * method that will return all reservations for a certain user
     */
    void GetReservationsClick(ActionEvent event) {
        lblCancellationStatus.setText("");
        lstCancelReservation.getItems().clear();

        // Get all reservations by user. If admin, then all are displayed.
        ArrayList<Reservation> reservations = calendar.getReservations(currentLoggedInUser, isAdmin);

        if (reservations != null && reservations.size() > 0) {
            for (int i = 0; i < reservations.size(); i++) {
                lstCancelReservation.getItems().add(reservations.get(i).toString());
            }
        }
    }

    @FXML
    /**
     * method to cancel a reservation for a user
     */
    void CancelReservationClick(ActionEvent event) {

        // A reservation was selected to cancel.
        if (lstCancelReservation.getSelectionModel().getSelectedItems().size() > 0) {

            String selectCancel = lstCancelReservation.getSelectionModel().getSelectedItems().toString();
            StringTokenizer app = new StringTokenizer(selectCancel, ",");
            String booking[] = app.nextToken().trim().split(" ");
            Integer resID = Integer.parseInt(booking[1]);

            // The details on screen has a reservation id. Look it up to get the actual reservation.
            Reservation res = calendar.getReservation(resID);
            if (res != null) {
                calendar.cancelBooking(res);

                //Update the reservation list by reloading the list of available reservations.
                GetReservationsClick(event);
                lblCancellationStatus.setText("Reservation " + resID + " has been cancelled.");
            }
        }
    }

    /**
     * method that is a common function to split a date into day month year and return a reservation object.
     * @param dateToParse
     * @return
     */
    private ReservationDate parseDates(String dateToParse) {
        StringTokenizer app = new StringTokenizer(dateToParse, "/");
        int day = Integer.parseInt(app.nextToken());
        int month = Integer.parseInt(app.nextToken());
        int year = Integer.parseInt(app.nextToken());
        return new ReservationDate(day, month, year);
    }

    /**
     * method that validates if an entered date is valid.
     * @param dateToCheck
     * @return
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAddRooms != null : "fx:id=\"btnAddRooms\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnCheckAvailability != null : "fx:id=\"btnCheckAvailability\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnGetReservations != null : "fx:id=\"btnGetReservations\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnQut != null : "fx:id=\"btnQut\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnRemoveRooms != null : "fx:id=\"btnRemoveRooms\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnReserve != null : "fx:id=\"btnReserve\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert btnSignOut != null : "fx:id=\"btnSignOut\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert dteCheckIn != null : "fx:id=\"dteCheckIn\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert dteCheckOut != null : "fx:id=\"dteCheckOut\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert grpLogin != null : "fx:id=\"grpLogin\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lblCancellationStatus != null : "fx:id=\"lblCancellationStatus\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lblLoginMessage != null : "fx:id=\"lblLoginMessage\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lblPassword != null : "fx:id=\"lblPassword\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lblReservationStatus != null : "fx:id=\"lblReservationStatus\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lblUsername != null : "fx:id=\"lblUsername\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lblWelcome != null : "fx:id=\"lblWelcome\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lstCancelReservation != null : "fx:id=\"lstCancelReservation\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert lstReserve != null : "fx:id=\"lstReserve\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert tabAddRooms != null : "fx:id=\"tabAddRooms\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert tabCancelReservation != null : "fx:id=\"tabCancelReservation\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert tabNewReservation != null : "fx:id=\"tabNewReservation\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert tabOptions != null : "fx:id=\"tabOptions\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert tabRemoveRooms != null : "fx:id=\"tabRemoveRooms\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert txtNumberOfGuests != null : "fx:id=\"txtNumberOfGuests\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert txtNumberOfRooms != null : "fx:id=\"txtNumberOfRooms\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'Reservations.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'Reservations.fxml'.";

        lblWelcome.setText("");
        lblLoginMessage.setVisible(false);
        btnSignOut.setVisible(false);
        tabOptions.setVisible(false);
        grpLogin.setVisible(true);

        lstReserve.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lstCancelReservation.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lblCancellationStatus.setText("");
    }
}
