package edu.ib.parcelLocker;

import edu.ib.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelLockerDAO {

    private DBUtil dbUtil;

    public ParcelLockerDAO(DBUtil dbUtil){
        this.dbUtil=dbUtil;
    }

    private ObservableList<ParcelLocker> getParcelLockerList(ResultSet rs) throws SQLException {
        ObservableList<ParcelLocker> parcelLockerObservableList= FXCollections.observableArrayList();

        while(rs.next()){
            ParcelLocker parcelLocker=new ParcelLocker();
            parcelLocker.setAddress(rs.getString("Adres"));
            parcelLocker.setId(rs.getInt("IDpaczkomatu"));
            parcelLockerObservableList.add(parcelLocker);
        }
        return parcelLockerObservableList;
    }

    public ObservableList<ParcelLocker> showAllParcelLocker() throws ClassNotFoundException, SQLException {
        String statement="SELECT IDpaczkomatu, Adres FROM machines_senders_view";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelLockerList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList<ParcelLocker> searchParcelLocker(String data) throws ClassNotFoundException, SQLException {
        String statement="SELECT IDpaczkomatu, Adres FROM machines_senders_view WHERE Adres LIKE \"%"+data+"%\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelLockerList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }



}
