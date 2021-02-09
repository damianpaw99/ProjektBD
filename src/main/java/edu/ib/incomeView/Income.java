package edu.ib.incomeView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class describing daily income
 */
public class Income {
    /**
     * Value of income in certain day
     */
    private DoubleProperty income;
    /**
     * Date as String
     */
    private StringProperty date;

    /**
     * Default constructor
     */
    public Income(){
        income=new SimpleDoubleProperty();
        date=new SimpleStringProperty();
    }

    /**
     * Income value getter
     * @return Value of income
     */
    public double getIncome() {
        return income.get();
    }

    /**
     * Income property getter
     * @return Income property
     */
    public DoubleProperty incomeProperty() {
        return income;
    }

    /**
     * Income value setter
     * @param income
     */
    public void setIncome(double income) {
        this.income.set(income);
    }

    /**
     * Date String value getter
     * @return Date value
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Date String property
     * @return Date property
     */
    public StringProperty dateProperty() {
        return date;
    }

    /**
     * Date value setter
     * @param date Date value
     */
    public void setDate(String date) {
        this.date.set(date);
    }
}
