package edu.ib.customer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Customer {

    private StringProperty name;
    private StringProperty surname;
    private StringProperty login;
    private StringProperty password;
    private StringProperty email;
    private IntegerProperty phone;

    public Customer(){
        name = new SimpleStringProperty();
        surname=new SimpleStringProperty();
        login=new SimpleStringProperty();
        password=new SimpleStringProperty();
        email=new SimpleStringProperty();
        phone=new SimpleIntegerProperty();
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public StringProperty getSurname() {
        return surname;
    }

    public void setSurname(StringProperty surname) {
        this.surname = surname;
    }

    public StringProperty getLogin() {
        return login;
    }

    public void setLogin(StringProperty login) {
        this.login = login;
    }

    public StringProperty getPassword() {
        return password;
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    public StringProperty getEmail() {
        return email;
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }

    public IntegerProperty getPhone() {
        return phone;
    }

    public void setPhone(IntegerProperty phone) {
        this.phone = phone;
    }
}
