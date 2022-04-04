import java.io.Serializable;

public class Passenger implements Serializable {
    private String firstName;
    private String surName;
    private double expenses;

    //Constructor
    public Passenger() {

    }

    //Constructor
    public Passenger(String firstName, String surName, double expenses) {
        this.firstName = firstName;
        this.surName = surName;
        this.expenses = expenses;

    }

    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public double getExpenses() {
        return expenses;
    }


    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
