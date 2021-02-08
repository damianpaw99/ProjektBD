package edu.ib.parcelHistory;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelHistoryDAO {

    private DBUtil dbUtil;
    private final Logger logger;

    public static final int PARCEL_ID=0;
    public static final int STATUS=1;
    public static final int DATE=2;

    public ParcelHistoryDAO(DBUtil dbUtil, Logger logger){
        this.dbUtil=dbUtil;
        this.logger=logger;
    }

    private ObservableList<ParcelHistory> getParcelHistoryList(ResultSet rs) throws SQLException {
        ObservableList<ParcelHistory> parcelHistoryObservableList= FXCollections.observableArrayList();

        while(rs.next()){
            ParcelHistory parcelHistory=new ParcelHistory();
            parcelHistory.setParcelId(rs.getInt("ParcelID"));
            parcelHistory.setDate(rs.getDate("Date").toString()+" "+rs.getTime("Date").toString());
            String state=rs.getString("Status");
            parcelHistory.setState(state);
            parcelHistoryObservableList.add(parcelHistory);
        }
        return parcelHistoryObservableList;
    }

    public ObservableList<ParcelHistory> showAllParcels() throws ClassNotFoundException, SQLException, WrongLoginPasswordException {
        String statement="SELECT ParcelID, Date, Status FROM parcels_history WHERE SenderLogin=\""+logger.getLogin()+"\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelHistoryList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList<ParcelHistory> searchParcel(String value, int itemSearch) throws ClassNotFoundException, SQLException, NumberFormatException, WrongLoginPasswordException {
        String type;
        switch (itemSearch) {
            case PARCEL_ID ->{
                type = "ParcelID";
                Integer.parseInt(value);
            }
            case STATUS -> {
                type = "Status";
                value = "\"%" + value + "%\"";
            }
            case DATE -> {
                type = "Date";
                value = "\"%" + value + "%\"";
            }
            default -> throw new IllegalArgumentException();
        }

        String statement="SELECT ParcelID, Date, Status FROM parcels_history WHERE SenderLogin=\""+logger.getLogin()+"\" AND "+type+" LIKE "+value;
        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelHistoryList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }
}
