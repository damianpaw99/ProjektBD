package edu.ib.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import edu.ib.parcel.Parcel;
import edu.ib.parcel.ParcelHistory;
import edu.ib.parcel.ParcelHistoryDAO;
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

public class CustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAddParcel;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField etxtSearch;

    @FXML
    private TableView tbParcel;

    @FXML
    private TableColumn<ParcelHistory, Integer> tbRowID;

    @FXML
    private TableColumn<ParcelHistory, String> tbRowDate;

    @FXML
    private TableColumn<ParcelHistory, String> tbRowStatus;

    @FXML
    private ChoiceBox<String> spSearch;

    private DBUtil dbUtil;
    private ParcelHistoryDAO parcelHistoryDAO;
    private ObservableList<String> choiceBoxList = FXCollections.emptyObservableList();


    @FXML
    void addParcel(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/new_parcel.fxml"));
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            e.getStackTrace();
        }
    }

    @FXML
    void back(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            doLogout();
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            e.getStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        Logger logger=new Logger(dbUtil,etxtLogin.getText(),Logger.CUSTOMER);
        try {
            logger.logIn(Logger.hash(etxtPassword.getText()));
            loginSuccess();
            parcelHistoryDAO=new ParcelHistoryDAO(dbUtil,logger);

        } catch (WrongLoginPasswordException e){
            txtMessage.setText("Niepoprawny login lub hasło!");
            e.getStackTrace();
        } catch(NoSuchAlgorithmException | SQLException | ClassNotFoundException e){
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

    @FXML
    void logout(ActionEvent event) {
        doLogout();
    }

    @FXML
    void search(ActionEvent event) {
        if(parcelHistoryDAO!=null){
            int itemToSearch = switch (spSearch.getValue()) {
                case "ID Paczki" -> ParcelHistoryDAO.PARCEL_ID;
                case "Data" -> ParcelHistoryDAO.DATE;
                case "Status" -> ParcelHistoryDAO.STATUS;
                default -> throw new IllegalStateException("Unexpected value: " + spSearch.getValue());
            };
            tbParcel.getItems().clear();
            try {
                if(etxtSearch.getText().equals("")) {
                    tbParcel.setItems(parcelHistoryDAO.showAllParcels());
                } else {
                    tbParcel.setItems(parcelHistoryDAO.searchParcel(etxtSearch.getText(), itemToSearch));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (WrongLoginPasswordException e) {
                e.printStackTrace();
            }
        }
    }

    private void loginSuccess(){
        btnLogout.setDisable(false);
        tbParcel.setVisible(true);
        etxtSearch.setDisable(false);
        etxtLogin.setDisable(true);
        etxtPassword.setDisable(true);
        txtMessage.setText("");
    }

    private void doLogout(){
        parcelHistoryDAO=null;
        btnLogout.setDisable(true);
        tbParcel.setVisible(false);
        etxtSearch.setText("");
        etxtSearch.setDisable(false);
        etxtPassword.setDisable(false);
        etxtLogin.setDisable(false);
        etxtPassword.setText("");
        etxtLogin.setText("");
    }


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

        dbUtil=new DBUtil("customer","pass123");
        choiceBoxList.add("ID Paczki");
        choiceBoxList.add("Data");
        choiceBoxList.add("Status");
        spSearch.setItems(choiceBoxList);
    }
}
