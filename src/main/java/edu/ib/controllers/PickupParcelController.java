package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PickupParcelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtID;

    @FXML
    private TextField etxtCode;

    @FXML
    private Text txtMessage;

    private DBUtil dbUtil;

    @FXML
    void back(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void pickupParcel(ActionEvent event) {
        try{
            String id=etxtID.getText();
            Integer.parseInt(id);
            String code=etxtCode.getText();
            Integer.parseInt(code);
            String statement="CALL pickup_by_recipient("+id+","+code+")";
            dbUtil.dbExecuteUpdate(statement);
            txtMessage.setText("Odebranie paczki zakończone sukcesem");
        } catch(NumberFormatException e) {
            txtMessage.setText("Niepoprawne dane!");
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            txtMessage.setText("Błąd połączenia lub niepoprawne dane!");
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert etxtID != null : "fx:id=\"etxtID\" was not injected: check your FXML file 'pickup_parcel.fxml'.";
        assert etxtCode != null : "fx:id=\"etxtCode\" was not injected: check your FXML file 'pickup_parcel.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'pickup_parcel.fxml'.";
        dbUtil=new DBUtil("basic","haslo100");
    }
}
