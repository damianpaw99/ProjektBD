package edu.ib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Logger {

    private int typeLogged;
    private DBUtil dbUtil;
    public static final int EMPLOYEE=1;
    public static final int CUSTOMER=0;
    private final String login;
    private boolean loggedIn=false;

    public Logger(DBUtil dbUtil,String login,int typeLogged){
        if(typeLogged==EMPLOYEE || typeLogged==CUSTOMER) {
            this.typeLogged = typeLogged;
            this.login=login;
        } else {
            throw new IllegalArgumentException("Invalid logging person type number");
        }
        this.dbUtil=dbUtil;
    }

    public void logIn(String hashedPassword) throws WrongLoginPasswordException, SQLException, ClassNotFoundException {
        ResultSet result;
        String statement = "SELECT check_password(\""+login+"\",\""+hashedPassword+"\","+typeLogged+")";
        result = dbUtil.dbExecuteQuery(statement);
        result.next();
        if(result.getInt(1)==0) {
            throw new WrongLoginPasswordException("Wrong login or password");
        } else {
            loggedIn=true;
        }
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
    public String getLogin() throws WrongLoginPasswordException {
        if(loggedIn) {
            return login;
        } else throw new WrongLoginPasswordException("Person is not logged in!");
    }
}
