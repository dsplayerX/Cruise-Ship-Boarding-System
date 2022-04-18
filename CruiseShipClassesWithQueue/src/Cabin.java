import java.io.Serializable;
import java.util.Arrays;

public class Cabin implements Serializable {
    private String cabinName;
    private int cabinNum;
    Passenger[] passengers = new Passenger[3]; // Passenger array to hold 3 passengers in each cabin

    //Constructor
    public Cabin() {
    }

    //Constructor
    //    * A two argument constructor was used because passengers were always added after a cabin is created.
    public Cabin(int cabinNum, String cabinName) {
        this.cabinNum = cabinNum;
        this.cabinName = cabinName;
    }

    //Getters
    public String getCabinName() {
        return cabinName;
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
