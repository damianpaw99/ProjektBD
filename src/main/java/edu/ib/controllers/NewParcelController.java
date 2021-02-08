package edu.ib.controllers;

import /**/java.net.URL;
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

/**
 * class that handles new parcel creation screen (new_parcel.fxml)
 */
public class NewParcelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * editable text field to enter customer login
     */
    @FXML
    private TextField etxtLogin;

    /**
     * editable text field to enter customer password
     */
    @FXML
    private PasswordField etxtPassword;

    /**
     * editable text field to enter firstname of addressee
     */
    @FXML
    private TextField etxtName;

    /**
     * editable text field to enter surname of addressee
     */
    @FXML
    private TextField etxtSurname;

    /**
     * editable text field to enter phone number of addressee
     */
    @FXML
    private TextField etxtPhone;

    /**
     * editable text field to enter email of addressee
     */
    @FXML
    private TextField etxtEmail;

    /**
     * editable text field to enter ID of parcels machine (with outbox)
     */
    @FXML
    private TextField etxtOutbox;

    /**
     * editable text field to enter ID of parcels machine (with inbox)
     */
    @FXML
    private TextField etxtInbox;

    /**
     * editable text field to enter size od parcel
     */
    @FXML
    private TextField etxtSize;

    /**
     * text field with information about success or failure of acion
     */
    @FXML
    private Text txtMessage;

    /**
     * button to return to home screen
     */
    @FXML
    private Button btnBack;

    /**
     * table with avaliable parcels machines
     */
    @FXML
    private TableView tbParcelLocker;

    /**
     * column with machine ID
     */
    @FXML
    private TableColumn<ParcelLocker, Integer> tbRowID;

    /**
     * column with machine address
     */
    @FXML
    private TableColumn<ParcelLocker, String> tbRowAddress;

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
    private ParcelLockerDAO parcelLockerDAO;

    /**
     * method of adding new parcel
     *
     * @param event information about event
     */
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

    /**
     * method of returning to previous screen (customer login screen)
     *
     * @param event information about event
     */
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

    /**
     * method of seatching parcels machine
     *
     * @param event information about event
     */
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

    /**
     * method of displaying the shipping cost
     *
     * @param event information about event
     */
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

    /**
     * method called when loading screen
     */
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
