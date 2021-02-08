package edu.ib.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import edu.ib.parcelCourier.ParcelCourier;
import edu.ib.parcelCourier.ParcelCourierDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * class that handles employee login screen (employee.fxml)
 */
public class EmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * editable text field to enter login
     */
    @FXML
    private TextField etxtLogin;

    /**
     * editable text field to enter password
     */
    @FXML
    private PasswordField etxtPassword;

    /**
     * text field with information about success or failure of acion
     */
    @FXML
    private Text txtMessage;

    /**
     * login button
     */
    @FXML
    private Button btnLogin;

    /**
     * button to return to home screen
     */
    @FXML
    private Button btnBack;

    /**
     * logout button
     */
    @FXML
    private Button btnLogout;

    /**
     * editable text field to enter parcel ID
     */
    @FXML
    private TextField etxtParcelId;

    /**
     * button to pick up parcel from outbox
     */
    @FXML
    private Button btnGetSendParcel;

    /**
     * button to leave parcel in inbox
     */
    @FXML
    private Button btnLeaveParcel;

    /**
     * button to pick up missed parcel from inbox
     */
    @FXML
    private Button btnMissedParcel;

    /**
     * table with handled parcels history
     */
    @FXML
    private TableView tbParcel;

    /**
     * column with parcel ID
     */
    @FXML
    private TableColumn<ParcelCourier, Integer> tbRowID;

    /**
     * column with status
     */
    @FXML
    private TableColumn<ParcelCourier, String> tbRowStatus;

    /**
     * column with address of parcels machine with outbox
     */
    @FXML
    private TableColumn<ParcelCourier, String> tbRowOutboxAddress;

    /**
     * column with oddress of parcels machine with inbox
     */
    @FXML
    private TableColumn<ParcelCourier, String> tbRowInboxAddress;

    /**
     * column with date of status update
     */
    @FXML
    private TableColumn<ParcelCourier, String> tbRowDate;

    /**
     * editable text field to search parcel
     */
    @FXML
    private TextField etxtSearch;

    /**
     * object used to connect to database
     */
    private DBUtil dbUtil;

    /**
     * object used to get data from database from view courier_parcels
     */
    private ParcelCourierDAO parcelCourierDAO;

    /**
     * method of returning to home screen
     *
     * @param event information about event
     */
    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            doLogout();
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method of picking up parcel from outbox
     *
     * @param event information about event
     */
    @FXML
    void getSendParcel(ActionEvent event) {
        try {
            Integer.parseInt(etxtParcelId.getText());
            String statement = "CALL pickup_by_courier(" + etxtParcelId.getText() + ")";
            dbUtil.dbExecuteUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method of leaving parcel to inbox
     *
     * @param event information about event
     */
    @FXML
    void leaveParcel(ActionEvent event) {
        try {
            Integer.parseInt(etxtParcelId.getText());
            String statement = "CALL delivery_by_courier(" + etxtParcelId.getText() + ")";
            dbUtil.dbExecuteUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * login method
     *
     * @param event information about event
     */
    @FXML
    void login(ActionEvent event) {
        Logger logger = new Logger(dbUtil, etxtLogin.getText(), Logger.EMPLOYEE);
        try {
            logger.logIn(Logger.hash(etxtPassword.getText())); //Logger.hash
            loginSuccess();
            parcelCourierDAO = new ParcelCourierDAO(dbUtil, logger);

        } catch (WrongLoginPasswordException e) {
            txtMessage.setText("Niepoprawny login lub hasło!");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            txtMessage.setText("Nastąpił błąd podczas weryfikacji");
            e.printStackTrace();
        }
        tbParcel.getItems().clear();
        try {
            tbParcel.setItems(parcelCourierDAO.showAllParcels());
        } catch (ClassNotFoundException | SQLException | WrongLoginPasswordException e) {
            e.printStackTrace();
        }
    }

    /**
     * method of unlocking buttons, text fields and table after logging in to account
     */
    private void loginSuccess() {
        btnLogout.setDisable(false);
        tbParcel.setVisible(true);
        etxtSearch.setDisable(false);
        etxtLogin.setDisable(true);
        etxtPassword.setDisable(true);
        btnLogin.setDisable(true);
        txtMessage.setText("");
        etxtParcelId.setDisable(false);
    }

    /**
     * logout method
     *
     * @param event information about event
     */
    @FXML
    void logout(ActionEvent event) {
        doLogout();
    }

    /**
     * method of blocking buttons, text fields, table and access to database after logging out of account
     */
    private void doLogout() {
        parcelCourierDAO = null;
        btnLogout.setDisable(true);
        tbParcel.setVisible(false);
        etxtSearch.setText("");
        etxtSearch.setDisable(false);
        etxtPassword.setDisable(false);
        etxtLogin.setDisable(false);
        btnLogin.setDisable(false);
        etxtPassword.setText("");
        etxtLogin.setText("");
        tbParcel.getItems().clear();
        etxtParcelId.setText("");
        etxtParcelId.setDisable(true);
    }

    /**
     * method of unlocking parcel handling buttons
     *
     * @param event information about event
     */
    @FXML
    void toggleButtons(ActionEvent event) {
        try {
            Integer.parseInt(etxtParcelId.getText());
            btnGetSendParcel.setDisable(false);
            btnLeaveParcel.setDisable(false);
            btnMissedParcel.setDisable(false);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * method updating table with history of handled parcels
     *
     * @param event information about event
     */
    @FXML
    void update(ActionEvent event) {
        if (parcelCourierDAO != null) {

            tbParcel.getItems().clear();
            try {
                if (etxtSearch.getText().equals("")) {
                    tbParcel.setItems(parcelCourierDAO.showAllParcels());
                } else {
                    tbParcel.setItems(parcelCourierDAO.searchParcel(etxtSearch.getText()));
                }
            } catch (ClassNotFoundException | SQLException | WrongLoginPasswordException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method called when loading screen
     */
    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'employee.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'employee.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'employee.fxml'.";
        assert etxtParcelId != null : "fx:id=\"etxtParcelId\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnGetSendParcel != null : "fx:id=\"btnGetSendParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnLeaveParcel != null : "fx:id=\"btnLeaveParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnMissedParcel != null : "fx:id=\"btnMissedParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbParcel != null : "fx:id=\"tbParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbRowID != null : "fx:id=\"tbRowID\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbRowStatus != null : "fx:id=\"tbRowStatus\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbRowOutboxAddress != null : "fx:id=\"tbRowOutboxAddress\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbRowInboxAddress != null : "fx:id=\"tbRowInboxAddress\" was not injected: check your FXML file 'employee.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'employee.fxml'.";

        dbUtil = new DBUtil("employee", "emppass321");
    }
}
