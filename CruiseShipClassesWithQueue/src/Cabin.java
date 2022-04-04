import java.io.Serializable;
import java.util.Arrays;

public class Cabin implements Serializable {
    private String cabinName;
    private int cabinNum;
    //private Passenger[] passengers;
    Passenger[] passengers = new Passenger[3];

    //Constructor
    public Cabin() {
    }

    public Cabin(int cabinNum, String cabinName) {
        this.cabinNum = cabinNum;
        this.cabinName = cabinName;
    }

    //Constructor
    public Cabin(int cabinNum, String cabinName, Passenger[] passengers) {
        this.cabinNum = cabinNum;
        this.cabinName = cabinName;
        this.passengers = passengers;
    }

    //Setters
    public void setCabinName(String cabinName) {
        this.cabinName = cabinName;
    }

    public void setCabinNum(int cabinNum) {
        this.cabinNum = cabinNum;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }


    //Getters
    public String getCabinName() {
        return cabinName;
    }

    public int getCabinNum() {
        return cabinNum;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }


    //To Print the Cabin Array for testing purposes
    @Override
    public String toString() {
        return "Cabin{" +
                "cabinName='" + cabinName + '\'' +
                ", cabinNum=" + cabinNum +
                ", passengers=" + Arrays.toString(passengers) +
                '}';
    }

}
