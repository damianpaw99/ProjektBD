package edu.ib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Logger {

    private int typeLogged;
    private DBUtil dbUtil;
    public final int EMPLOYEE=0;
    public final int CUSTOMER=1;


    public Logger(DBUtil dbUtil,int typeLogged){
        if(typeLogged==EMPLOYEE || typeLogged==CUSTOMER) {
            this.typeLogged = typeLogged;
        } else {
            throw new IllegalArgumentException("Invalid logging person type number");
        }
        this.dbUtil=dbUtil;
    }

    public boolean logIn(String password){
        try {
            String pass = hash(password);



        } catch (Exception e){
            e.getStackTrace();
            return false;
        }
        return false;
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
