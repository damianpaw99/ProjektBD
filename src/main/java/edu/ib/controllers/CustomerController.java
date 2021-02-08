package edu.ib.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import edu.ib.parcelHistory.ParcelHistory;
import edu.ib.parcelHistory.ParcelHistoryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * class that handles customer login screen (customer.fxml)
 */
public class CustomerController {

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
     * logout button
     */
    @FXML
    private Button btnLogout;

    /**
     * button to return to home screen
     */
    @FXML
    private Button btnBack;

    /**
     * button to add new parcel
     */
    @FXML
    private Button btnAddParcel;

    /**
     * table with history of shipped parcels
     */
    @FXML
    private TableView tbParcel;

    /**
     * column with parcel ID
     */
    @FXML
    private TableColumn<ParcelHistory, String> tbRowID;

    /**
     * column with date of status update
     */
    @FXML
    private TableColumn<ParcelHistory, String> tbRowDate;

    /**
     * column with status
     */
    @FXML
    private TableColumn<ParcelHistory, String> tbRowStatus;

    /**
     * editable text field to search parcel
     */
    @FXML
    private TextField etxtSearch;

    /**
     * choice box with options to search parcel
     */
    @FXML
    private ChoiceBox<String> spSearch;

    /**
     * object used to connect to database
     */
    private DBUtil dbUtil;

    /**
     * object used to get data from database from view parcels_history
     */
    private ParcelHistoryDAO parcelHistoryDAO;

    /**
     * list with options to choose in choice box to search parcels
     */
    private ObservableList<String> choiceBoxList;


    /**
     * method of adding parcel for shipping
     *
     * @param event information about event
     */
    @FXML
    void addParcel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/new_parcel.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            e.getStackTrace();
        }
    }

    /**
     * login method
     *
     * @param event information about event
     */
    @FXML
    void login(ActionEvent event) {
        Logger logger = new Logger(dbUtil, etxtLogin.getText(), Logger.CUSTOMER);
        try {
            logger.logIn(Logger.hash(etxtPassword.getText())); //Logger.hash
            loginSuccess();
            parcelHistoryDAO = new ParcelHistoryDAO(dbUtil, logger);

        } catch (WrongLoginPasswordException e) {
            txtMessage.setText("Niepoprawny login lub hasło!");
            e.getStackTrace();
        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            txtMessage.setText("Nastąpił błąd podczas weryfikacji");
            e.getStackTrace();
        }
        tbParcel.getItems().clear();
        try {
            tbParcel.setItems(parcelHistoryDAO.showAllParcels());
        } catch (ClassNotFoundException | SQLException | WrongLoginPasswordException e) {
            e.printStackTrace();
        }
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
     * method of seatching shipped parcel
     *
     * @param event information about event
     */
    @FXML
    void search(ActionEvent event) {
        if (parcelHistoryDAO != null) {
            int itemToSearch = switch (spSearch.getValue()) {
                case "ID Paczki" -> ParcelHistoryDAO.PARCEL_ID;
                case "Data" -> ParcelHistoryDAO.DATE;
                case "Status" -> ParcelHistoryDAO.STATUS;
                default -> throw new IllegalStateException("Unexpected value: " + spSearch.getValue());
            };
            tbParcel.getItems().clear();
            try {
                if (etxtSearch.getText().equals("")) {
                    tbParcel.setItems(parcelHistoryDAO.showAllParcels());
                } else {
                    tbParcel.setItems(parcelHistoryDAO.searchParcel(etxtSearch.getText(), itemToSearch));
                }
            } catch (ClassNotFoundException | SQLException | WrongLoginPasswordException e) {
                e.printStackTrace();
            }
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
        btnAddParcel.setDisable(false);
        txtMessage.setText("");
    }

    /**
     * method of blocking buttons, text fields, table and access to database after logging out of account
     */
    private void doLogout() {
        parcelHistoryDAO = null;
        btnLogout.setDisable(true);
        tbParcel.setVisible(false);
        etxtSearch.setText("");
        etxtSearch.setDisable(false);
        etxtPassword.setDisable(false);
        etxtLogin.setDisable(false);
        btnLogin.setDisable(false);
        btnAddParcel.setDisable(true);
        etxtPassword.setText("");
        etxtLogin.setText("");
        tbParcel.getItems().clear();
    }

    /**
     * method called when loading screen
     */
    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'customer.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnAddParcel != null : "fx:id=\"btnAddParcel\" was not injected: check your FXML file 'customer.fxml'.";
        assert tbParcel != null : "fx:id=\"tbParcel\" was not injected: check your FXML file 'customer.fxml'.";
        assert tbRowID != null : "fx:id=\"tbRowID\" was not injected: check your FXML file 'customer.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'customer.fxml'.";
        assert tbRowStatus != null : "fx:id=\"tbRowStatus\" was not injected: check your FXML file 'customer.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'customer.fxml'.";
        assert spSearch != null : "fx:id=\"spSearch\" was not injected: check your FXML file 'customer.fxml'.";

        dbUtil = new DBUtil("customer", "pass123");
        choiceBoxList = FXCollections.observableArrayList();
        choiceBoxList.add("ID Paczki");
        choiceBoxList.add("Data");
        choiceBoxList.add("Status");
        spSearch.setItems(choiceBoxList);
    }
}
