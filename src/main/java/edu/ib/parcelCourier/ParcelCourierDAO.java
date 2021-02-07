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
            String out = switch (state) {
                case "ready_for_pickup_by_courier" -> "Nadana";
                case "pickedup_by_courier" -> "Podjęta przez kuriera";
                case "ready_for_pickup_by_addressee" -> "Gotowa do odbioru";
                case "received" -> "Odebrana";
                case "missed" -> "Nieodebrana";
                default -> "";
            };
            parcelCourier.setState(out);
            parcelCourierObservableList.add(parcelCourier);
        }
        return parcelCourierObservableList;
    }

    public ObservableList<ParcelCourier> showAllParcels() throws ClassNotFoundException, SQLException, WrongLoginPasswordException {
        String statement="SELECT ParcelID, Status, MachineOutboxAddress, MachineInboxAddress from parcels_history WHERE SenderLogin=\""+logger.getLogin()+"\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelCourierList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

//    public ObservableList<ParcelCourier> searchParcel(String value, int itemSearch) throws ClassNotFoundException, SQLException, NumberFormatException, WrongLoginPasswordException {
//        String type;
//        switch (itemSearch) {
//            case PARCEL_ID ->{
//                type = "ParcelID";
//                Integer.parseInt(value);
//            }
//            case STATUS -> {
//                type = "Status";
//                value = "\"" + value + "\"";
//            }
//
//            default -> throw new IllegalArgumentException();
//        }
//
//        String statement="SELECT ParcelID, Status, MachineOutboxAddress, MachineInboxAddress WHERE SenderLogin=\""+logger.getLogin()+"\" AND "+type+"="+value;
//
//        try {
//            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);
//
//            return getParcelCourierList(resultSet);
//
//
//        } catch (SQLException e) {
//            throw e;
//        }
//    }
}
