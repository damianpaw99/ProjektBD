package edu.ib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Logger {

    private int typeLogged;
    private DBUtil dbUtil;
    public static final int EMPLOYEE=0;
    public static final int CUSTOMER=1;


    public Logger(DBUtil dbUtil,int typeLogged){
        if(typeLogged==EMPLOYEE || typeLogged==CUSTOMER) {
            this.typeLogged = typeLogged;
        } else {
            throw new IllegalArgumentException("Invalid logging person type number");
        }
        this.dbUtil=dbUtil;
    }

    public boolean logIn(String login,String hashedPassword) throws WrongLoginPasswordException, SQLException, ClassNotFoundException {
        boolean out=false;
        ResultSet result;
        String statement = "SELECT check_password("+login+","+hashedPassword+","+typeLogged+")";
        result = dbUtil.dbExecuteQuery(statement);
        if(result.getInt(1)==0) {
            throw new WrongLoginPasswordException("Wrong login or password");
        } else {
            out = true;
        }
        return out;
    }

    public static String hash(String pass) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("SHA-1");
        md.update(pass.getBytes());
        byte [] bytes=md.digest();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            sb.append(Integer.toString((bytes[i]&0xFF)+0x100,16)).substring(1);
        }
        return sb.toString();
    }
}
