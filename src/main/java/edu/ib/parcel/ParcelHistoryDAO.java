package edu.ib.parcel;

import edu.ib.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelHistoryDAO {

    DBUtil dbUtil;
    private final String userLogin;

    public static final int PARCEL_ID=0;
    public static final int STATUS=1;
    public static final int DATE=2;

    public ParcelHistoryDAO(DBUtil dbUtil, String userLogin){
        this.dbUtil=dbUtil;
        this.userLogin=userLogin;
    }

    private ObservableList<ParcelHistory> getParcelHistoryList(ResultSet rs) throws SQLException {
        ObservableList<ParcelHistory> parcelHistoryObservableList= FXCollections.observableArrayList();

        while(rs.next()){
            ParcelHistory parcelHistory=new ParcelHistory();
            parcelHistory.setParcelId(rs.getInt("ParcelID"));
            parcelHistory.setDate(rs.getDate("Date").toString());
            String state=rs.getString("Status");
            String out = switch (state) {
                case "ready_for_pickup_by_courier" -> "Nadana";
                case "pickedup_by_courier" -> "Podjęta przez kuriera";
                case "ready_for_pickup_by_addressee" -> "Gotowa do odbioru";
                case "received" -> "Odebrana";
                case "missed" -> "Nieodebrana";
                default -> "";
            };
            parcelHistory.setState(out);
            parcelHistoryObservableList.add(parcelHistory);
        }
        return parcelHistoryObservableList;
    }

    public ObservableList<ParcelHistory> showAllParcels() throws ClassNotFoundException, SQLException {
        String statement="SELECT ParcelID, Date, Status from parcels_history WHERE SenderLogin=\""+userLogin+"\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelHistoryList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList<ParcelHistory> searchParcel(String value, int itemSearch) throws ClassNotFoundException, SQLException, NumberFormatException {
        String type;
        switch (itemSearch) {
            case PARCEL_ID ->{
                type = "ParcelID";
                Integer.parseInt(value);
            }
            case STATUS -> {
                type = "Status";
                value = "\"" + value + "\"";
            }
            case DATE -> {
                type = "Date";
                value = "\"" + value + "\"";
            }
            default -> throw new IllegalArgumentException();
        }

        String statement="SELECT ParcelID, Date, Status from parcels_history WHERE SenderLogin=\""+userLogin+"\" AND "+type+"="+value;

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelHistoryList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }
}
