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

public class EmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private Text txtMessage;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField etxtParcelId;

    @FXML
    private Button btnGetSendParcel;

    @FXML
    private Button btnLeaveParcel;

    @FXML
    private Button btnMissedParcel;

    @FXML
    private TableView tbParcel;

    @FXML
    private TableColumn<ParcelCourier, Integer> tbRowID;

    @FXML
    private TableColumn<ParcelCourier, String> tbRowStatus;

    @FXML
    private TableColumn<ParcelCourier, String> tbRowOutboxAddress;

    @FXML
    private TableColumn<ParcelCourier, String> tbRowInboxAddress;

    @FXML
    private TableColumn<ParcelCourier, String> tbRowDate;

    @FXML
    private TextField etxtSearch;

    private DBUtil dbUtil;
    private ParcelCourierDAO parcelCourierDAO;


    @FXML
    void back(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            doLogout();
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void getSendParcel(ActionEvent event) {
        try{
            Integer.parseInt(etxtParcelId.getText());
            String statement = "CALL pickup_by_courier(" + etxtParcelId.getText() + ")";
            dbUtil.dbExecuteUpdate(statement);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void leaveParcel(ActionEvent event) {
        try{
            Integer.parseInt(etxtParcelId.getText());
            String statement = "CALL delivery_by_courier(" + etxtParcelId.getText() + ")";
            dbUtil.dbExecuteUpdate(statement);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        Logger logger=new Logger(dbUtil,etxtLogin.getText(),Logger.EMPLOYEE);
        try {
            logger.logIn(etxtPassword.getText()); //Logger.hash
            loginSuccess();
            parcelCourierDAO=new ParcelCourierDAO(dbUtil,logger);

        } catch (WrongLoginPasswordException e){
            txtMessage.setText("Niepoprawny login lub hasło!");
            e.printStackTrace();
        } catch(/*NoSuchAlgorithmException |*/ SQLException | ClassNotFoundException e){
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

    private void loginSuccess(){
        btnLogout.setDisable(false);
        tbParcel.setVisible(true);
        etxtSearch.setDisable(false);
        etxtLogin.setDisable(true);
        etxtPassword.setDisable(true);
        btnLogin.setDisable(true);
        txtMessage.setText("");
        etxtParcelId.setDisable(false);
    }

    @FXML
    void logout(ActionEvent event) {
        doLogout();
    }

    private void doLogout(){
        parcelCourierDAO=null;
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

    @FXML
    void toggleButtons(ActionEvent event) {
        try{
            Integer.parseInt(etxtParcelId.getText());
            btnGetSendParcel.setDisable(false);
            btnLeaveParcel.setDisable(false);
            btnMissedParcel.setDisable(false);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    void update(ActionEvent event) {
        if(parcelCourierDAO!=null){

            tbParcel.getItems().clear();
            try {
                if(etxtSearch.getText().equals("")) {
                    tbParcel.setItems(parcelCourierDAO.showAllParcels());
                } else {
                    //tbParcel.setItems(parcelCourierDAO.searchParcel(etxtSearch.getText()));
                }
            } catch (ClassNotFoundException | SQLException | WrongLoginPasswordException e) {
                e.printStackTrace();
            }
        }
    }

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

        dbUtil=new DBUtil("employee","emppass321");
    }
}
