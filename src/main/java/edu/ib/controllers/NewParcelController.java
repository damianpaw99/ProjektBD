package edu.ib.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import edu.ib.parcelLocker.ParcelLocker;
import edu.ib.parcelLocker.ParcelLockerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewParcelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private TextField etxtName;

    @FXML
    private TextField etxtSurname;

    @FXML
    private TextField etxtPhone;

    @FXML
    private TextField etxtEmail;

    @FXML
    private TextField etxtOutbox;

    @FXML
    private TextField etxtInbox;

    @FXML
    private TextField etxtSize;

    @FXML
    private Text txtMessage;

    @FXML
    private Button btnBack;

    @FXML
    private TableView tbParcelLocker;

    @FXML
    private TableColumn<ParcelLocker, Integer> tbRowID;

    @FXML
    private TableColumn<ParcelLocker, String> tbRowAddress;

    @FXML
    private TextField etxtSearch;

    private DBUtil dbUtil;
    private ParcelLockerDAO parcelLockerDAO;

    @FXML
    void addParcel(ActionEvent event) {
        try {
            Logger logger = new Logger(dbUtil, etxtLogin.getText(), Logger.CUSTOMER);
            logger.logIn(Logger.hash(etxtPassword.getText()));
            String name=etxtName.getText();
            String surname=etxtSurname.getText();
            String phone=etxtPhone.getText();
            String size=etxtSize.getText();
            String email=etxtEmail.getText();
            String inbox=etxtInbox.getText();
            String outbox=etxtOutbox.getText();
            if(!email.contains("@")){
                txtMessage.setText("Niepoprawny e-mail!");
                throw new IllegalArgumentException("Wrong e-mail");
            }
            Integer.parseInt(inbox);
            Integer.parseInt(outbox);
            if(!size.equals("A") && !size.equals("B") && !size.equals("C")){
                txtMessage.setText("Niepoprawny rozmiar paczki!");
                throw new IllegalArgumentException("Wrong parcel size");
            }
            if(name.isEmpty() || surname.isEmpty()){
                txtMessage.setText("Pola nie mogą być puste!");
                throw new IllegalArgumentException("Fields can't be empty");
            }

            StringBuilder statement=new StringBuilder();
            statement.append("CALL add_parcel(\"");
            statement.append(etxtLogin.getText());
            statement.append("\",\"");
            statement.append(name);
            statement.append("\",\"");
            statement.append(surname);
            statement.append("\",");
            statement.append(phone);
            statement.append(",\"");
            statement.append(email);
            statement.append("\",");
            statement.append(outbox);
            statement.append(",");
            statement.append(inbox);
            statement.append(",'");
            statement.append(size);
            statement.append("')");
            dbUtil.dbExecuteUpdate(statement.toString());

            txtMessage.setText("Paczka została dodana!");
        } catch(WrongLoginPasswordException e){
            e.printStackTrace();
            txtMessage.setText("Niepoprawny login lub hasło!");
        } catch(NoSuchAlgorithmException | SQLException | ClassNotFoundException e){
            txtMessage.setText("Błąd dodawania paczki!\nSprawdź ID automatów lub spróbuj później.");
            e.printStackTrace();
        } catch (NumberFormatException e){
            txtMessage.setText("Niepoprany numer telefonu lub ID automatów");
            e.printStackTrace();
        }

    }

    @FXML
    void back(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/customer.fxml"));
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @FXML
    void search(ActionEvent event) {
        tbParcelLocker.getItems().clear();
        try {
            if (!etxtSearch.getText().isEmpty()) {
                tbParcelLocker.setItems(parcelLockerDAO.searchParcelLocker(etxtSearch.getText()));
            } else {
                tbParcelLocker.setItems(parcelLockerDAO.showAllParcelLocker());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void cost(ActionEvent event){
        if(etxtSize.getText().equals("A") || etxtSize.getText().equals("B") || etxtSize.getText().equals("C")) {
            String statement = "SELECT calculate_shipping_cost('" + etxtSize.getText() + "')";
            try {
                ResultSet r = dbUtil.dbExecuteQuery(statement);
                r.next();
                txtMessage.setText(r.getDouble(1)+"zł");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtName != null : "fx:id=\"etxtName\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtSurname != null : "fx:id=\"etxtSurname\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtPhone != null : "fx:id=\"etxtPhone\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtEmail != null : "fx:id=\"etxtEmail\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtOutbox != null : "fx:id=\"etxtOutbox\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtInbox != null : "fx:id=\"etxtInbox\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtSize != null : "fx:id=\"etxtSize\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert tbParcelLocker != null : "fx:id=\"tbParcelLocker\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert tbRowID != null : "fx:id=\"tbRowID\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert tbRowAddress != null : "fx:id=\"tbRowAddress\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'new_parcel.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'new_parcel.fxml'.";

        dbUtil=new DBUtil("customer","pass123");
        parcelLockerDAO=new ParcelLockerDAO(dbUtil);
        try {
            tbParcelLocker.setItems(parcelLockerDAO.showAllParcelLocker());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
