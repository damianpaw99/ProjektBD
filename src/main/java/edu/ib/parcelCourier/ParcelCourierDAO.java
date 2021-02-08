package edu.ib.parcelCourier;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelCourierDAO {

    private DBUtil dbUtil;
    private final Logger logger;

    public ParcelCourierDAO(DBUtil dbUtil, Logger logger){
        this.dbUtil=dbUtil;
        this.logger=logger;
    }

    private ObservableList<ParcelCourier> getParcelCourierList(ResultSet rs) throws SQLException {
        ObservableList<ParcelCourier> parcelCourierObservableList= FXCollections.observableArrayList();

        while(rs.next()){
            ParcelCourier parcelCourier=new ParcelCourier();
            parcelCourier.setParcelId(rs.getInt("ParcelID"));
            String outboxAddress=rs.getString("MachineOutboxAddress");
            String inboxAddress=rs.getString("MachineInboxAddress");
            String state=rs.getString("Status");
            String date=rs.getDate("Date").toString()+" "+rs.getTime("Date").toString();
            parcelCourier.setDate(date);
            parcelCourier.setState(state);
            parcelCourier.setInboxAddress(inboxAddress);
            parcelCourier.setOutboxAddress(outboxAddress);
            parcelCourierObservableList.add(parcelCourier);
        }
        return parcelCourierObservableList;
    }

    public ObservableList<ParcelCourier> showAllParcels() throws ClassNotFoundException, SQLException, WrongLoginPasswordException {
        String statement="SELECT ParcelID, Status, MachineOutboxAddress, MachineInboxAddress, Date FROM courier_parcels WHERE CourierLogin=\""+logger.getLogin()+"\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelCourierList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList<ParcelCourier> searchParcel(String value) throws ClassNotFoundException, SQLException, NumberFormatException, WrongLoginPasswordException {
       String statement="SELECT ParcelID, Status, MachineOutboxAddress, MachineInboxAddress, Date FROM courier_parcels WHERE CourierLogin=\""+logger.getLogin()+"\"";
       statement+=" AND (ParcelID LIKE \"%"+value+"%\" OR Status LIKE \"%"+value+"%\" OR MachineOutboxAddress LIKE \"%"+value+"%\" OR MachineInboxAddress LIKE \"%"+value+"%\" OR Date LIKE \"%"+value+"%\")";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelCourierList(resultSet);


     } catch (SQLException e) {
          throw e;
        }
    }
}
