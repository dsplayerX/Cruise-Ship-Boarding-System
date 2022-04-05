import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CruiseShipClassesWithQueue {

    static boolean isTrue = true; // checks condition for mainMenu() while loop exit

    // parameters for the waiting list (Circular Queue)
    static Passenger[] waitingListQueue = new Passenger[5];
    static int front = -1;
    static int rear = -1;

    public static void main(String[] args) {
        Cabin[] cabins = new Cabin[12]; // Cabins object array to store cabin + passenger data

        /*
         * loops back to mainMenu() after each method
         *    until user inputs "q" or "Q" to exit the program.
         */
        while (isTrue) {
            mainMenu(cabins);
        }
    }

    /*
     * Main menu with a switch case that call different methods.
     * Defaults back to main menu.
     * "Q"/"q" exits the program
     *
     * additional options are there for the easy manipulation of the program.
     *   > examples: filling all cabins to test waiting queue
     *               clear all cabins without restarting the program
     */
    public static void mainMenu(Cabin[] cabinsRef) {
        System.out.println("==========================================");
        System.out.println("                Main Menu                 ");
        System.out.println("==========================================");
        System.out.println("-----------------|core|-------------------");
        System.out.println(" A: Add Passenger to Cabin");
        System.out.println(" V: View All Cabins");
        System.out.println(" T: Get Expenses");
        System.out.println(" E: Display Empty Cabins");
        System.out.println(" D: Delete Passenger from Cabin");
        System.out.println(" F: Find Cabin from Passenger Name");
        System.out.println(" S: Store program data into a file");
        System.out.println(" L: Load program data into a file");
        System.out.println(" O: View Passengers Ordered Alphabetically");
        System.out.println(" Q: Quit");
        System.out.println("--------------|additional|----------------");
        System.out.println(" M: Fill all cabins");
        System.out.println(" N: Empty all cabins");
        System.out.println("------------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("Select option: ");
        String menuOption = input.next();
        switch (menuOption) {
            case "a":
            case "A":
                addPassengerToCabin(cabinsRef);
                break;
            case "v":
            case "V":
                viewAllCabins(cabinsRef);
                break;
            case "t":
            case "T":
                getExpenses(cabinsRef);
                break;
            case "e":
            case "E":
                displayEmptyCabins(cabinsRef);
                break;
            case "d":
            case "D":
                deletePassengerFromCabin(cabinsRef);
                break;
            case "f":
            case "F":
                findCabinFromName(cabinsRef);
                break;
            case "s":
            case "S":
                saveDataToFile(cabinsRef);
                break;
            case "l":
            case "L":
                loadDataFromFile(cabinsRef);
                break;
            case "o":
            case "O":
                passengersOrdered(cabinsRef);
                break;
            case "m":
            case "M":
                fillAllCabins(cabinsRef);
                break;
            case "n":
            case "N":
                emptyAllCabins(cabinsRef);
                break;
            case "q":
            case "Q":
                isTrue = false; // breaks the while loop mainMenu() is in and exits the program.
                break;
            default:
                System.out.println("Wrong input. Please try again!");
                mainMenu(cabinsRef); // call back to mainMenu()
                break;
        }
    }

    // Takes cabin number and assign a passenger to it.
    public static void addPassengerToCabin(Cabin[] cabins) {
        boolean isFull = true; // to check whether all the cabins are full.
        Scanner scanner = new Scanner(System.in);

        // to find if there are empty cabins and to update isFull variable.
        for (Cabin cabin : cabins) {
            if (cabin != null) {
                for (int j = 0; j < cabin.passengers.length; j++) {
                    if (cabin.passengers[j] == null) {
                        isFull = false;
                        break;
                    }
                }
            } else {
                isFull = false;
                break;
            }
        }
        System.out.println("------------------------------------------");
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter surname: ");
        String surName = scanner.next();
        double expenses;

        try {
            System.out.print("Enter expenses: $");
            expenses = scanner.nextDouble();
        } catch (InputMismatchException e){
            System.out.println("Wrong input! You have to enter a double value.");
            System.out.println("Returning to main menu...");
            return;
        }

        if (isFull) { // adds passenger to queue if all cabins are full
            System.out.println("Cabins are full. Adding to waiting queue...");
            if (front == -1 && rear == -1){
                front = rear = 0;
                waitingListQueue[rear] = new Passenger(firstName, surName, expenses);
            } else if (((rear+1)%5) == front) {
                System.out.println("Cannot add. Queue is full.");
            } else {
                rear = (rear+1)%5;
                waitingListQueue[rear] = new Passenger(firstName, surName, expenses);
            }
        } else { // if there are empty cabins, adds to them / lets the user know there are other empty cabins
            System.out.print("Enter cabin number (or \"13\" to go back.): ");
            int cabinNum;
            try {
                cabinNum = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Wrong input! Try again.");
                System.out.println("Returning to main menu...");
                return;
            }
            if (cabinNum < 13) {
                if (cabins[cabinNum - 1] == null) {
                    cabins[cabinNum - 1] = new Cabin(cabinNum, "Cabin " + cabinNum);
                }
                for (int i = 0; i < 3; i++) {
                    if (cabins[cabinNum - 1].passengers[i] == null) {
                        cabins[cabinNum - 1].passengers[i] = new Passenger(firstName, surName, expenses);
                        System.out.println("Added " + firstName + " to cabin " + cabinNum + " slot " + (i + 1) + ".");
                        return;

                    } else {
                        System.out.println("Cabin " + cabinNum + " slot " + (i + 1) + " is full. Trying next one...");
                    }
                }
                System.out.println("Cabin " + cabinNum + " is full. Select another cabin and try again.");
                displayEmptyCabins(cabins); // runs method to display empty cabins user can add passengers to
            } else if (cabinNum == 13) {
                System.out.println("Going back to main menu...");
            } else {
                System.out.println("Wrong input! Try again.");
                System.out.println("Returning to main menu...");
            }
        }
    }

    //Prints whether cabins are occupied or empty.
    public static void viewAllCabins(Cabin[] cabins) {

        System.out.println("------------------------------------------");
        System.out.println("--------------View All Cabins-------------");

        for (int i = 0; i < cabins.length; i++) {
            if (cabins[i] == null) {
                System.out.println("Cabin " + (i+1) + " is empty.");
            } else {
                System.out.println("Cabin " + (i+1) + " is occupied by:");
                for (int j = 0; j < cabins[i].passengers.length; j++) {
                    if (cabins[i].passengers[j] != null) {
                        System.out.println("\t Slot " + (j+1) + ": " +cabins[i].passengers[j].getFirstName() + " " + cabins[i].passengers[j].getSurName());
                    } else {
                        System.out.println("\t Slot " + (j+1) + ": ~empty~");
                    }
                }
            }
        }

        System.out.println("------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    public static void getExpenses(Cabin[] cabins){
        System.out.println("---------------------------------------------");
        System.out.println("P: Print Expenses per passenger");
        System.out.println("T: Print the Total Expenses of all passengers");
        System.out.println("---------------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("Select option: ");
        String subOption = input.next();
        switch (subOption){
            case "p":
            case "P":
                System.out.println("----------------------");
                System.out.println("Expenses per passenger");
                System.out.println("----------------------");
                for (int i = 0; i < cabins.length; i++) {
                    if (cabins[i] != null) {
                        System.out.println(cabins[i].getCabinName() + ": ");
                        for (int j = 0; j < cabins[i].passengers.length; j++) {
                            if (cabins[i].passengers[j] != null) {
                                System.out.println("\t" + cabins[i].passengers[j].getFirstName() + " " + cabins[i].passengers[j].getSurName() + ": $" + cabins[i].passengers[j].getExpenses());
                            }
                        }
                    }
                }
                System.out.println("----------------------");
                System.out.println("Returning to main menu...");
                break;
            case "t":
            case "T":
                double totalExpenses = 0;
                for (int i = 0; i < cabins.length; i++) {
                    if (cabins[i] != null) {
                        for (int j = 0; j < cabins[i].passengers.length; j++) {
                            if (cabins[i].passengers[j] != null) {
                                double tempExpenses = cabins[i].passengers[j].getExpenses();
                                totalExpenses += tempExpenses;
                            }
                        }
                    }
                }
                System.out.println("------------------------------------------");
                System.out.println(" Total Expenses of all passengers: $" + totalExpenses);
                System.out.println("------------------------------------------");
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Wrong Input. Try again!");
                getExpenses(cabins);
                break;
        }
    }

    public static void displayEmptyCabins(Cabin[] cabins){
        boolean isThere = false; // checks whether there are empty cabins or not.
        System.out.println("------------------------------------------");
        System.out.println("--------------Empty Cabins----------------");
        for (int i = 0; i < cabins.length; i++) {
            if (cabins[i] == null) {
                System.out.println("\tCabin " + (i + 1));
                isThere = true;
            } else {
                for (int j = 0; j < cabins[i].passengers.length; j++) {
                    if (cabins[i].passengers[j] == null) {
                        System.out.println("\tCabin " + (i + 1) + " slot " + (j+1));
                        isThere = true;
                    }
                }
            }
        }
        if (!isThere) { // if there aren't any empty cabins lets the user know
            System.out.println("There are no empty cabins.");
        }
        System.out.println("------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    public static void deletePassengerFromCabin(Cabin[] cabins){
        System.out.println("------------------------------------------");

        int cabinNum;
        int slotNum;

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter cabin number: ");
            cabinNum = input.nextInt();
            System.out.print("Enter slot number: ");
            slotNum = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Wrong inputs! Try again.");
            System.out.println("Returning to main menu...");
            return;
        }
        System.out.println("------------------------------------------");

        if (cabinNum > 0 && cabinNum < 13) {
            if (slotNum > 0 && slotNum < 4){
                if (cabins[cabinNum - 1] != null) {
                    if (cabins[cabinNum - 1].passengers[slotNum - 1] != null) {
                        System.out.println("Removed " + cabins[cabinNum -1].passengers[slotNum-1].getFirstName() + " from " + cabins[cabinNum-1].getCabinName() + ".");
                        cabins[cabinNum -1].passengers[slotNum-1] = null;

                        //deQueue
                        if (front == -1 && rear == -1) {
                            System.out.println("The queue is empty. No passenger was added.");
                        } else {
                            cabins[cabinNum - 1].passengers[slotNum - 1] = waitingListQueue[front];
                            System.out.println("Added passenger " + cabins[cabinNum - 1].passengers[slotNum - 1].getFirstName() + " to cabin from waiting list.");
                            if (front == rear) {
                                front = rear = -1;
                            } else {
                                front = (front + 1) % 5;
                            }
                        }

                        if (Arrays.toString(cabins[cabinNum - 1].passengers).equals("[null, null, null]")){
                            cabins[cabinNum-1] = null;
                        }
                    }
                }
            } else {
                System.out.println("Enter slot 1, 2, or 3.");
                System.out.println("Returning to main menu...");
                return;
            }
        } else {
            System.out.println("Enter a cabin number between 1-12.");
            System.out.println("Returning to main menu...");
            return;
        }
        System.out.println("------------------------------------------");
    }


    public static void findCabinFromName(Cabin[] cabins){
        System.out.println("------------------------------------------");
        boolean isThere = false;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter passenger's first name: ");
        String inputFirstName = input.next();
        System.out.print("Enter passenger's last name: ");
        String inputSurName = input.next();
        for (int i = 0; i < cabins.length; i++) {
            if (cabins[i] != null) {
                for (int j = 0; j < cabins[i].passengers.length; j++) {
                    if (cabins[i].passengers[j] != null) {
                        if ((cabins[i].passengers[j].getFirstName().equals(inputFirstName)) && (cabins[i].passengers[j].getSurName().equals(inputSurName))){
                            System.out.println(cabins[i].passengers[j].getFirstName() + " " + cabins[i].passengers[j].getSurName() + " is in " + cabins[i].getCabinName() + ".");
                            isThere = true;
                        }
                    }
                }
            }
        }
        if (!isThere){
            System.out.println("No passenger by that name.");
        }
        System.out.println("------------------------------------------");

    }

    public static void saveDataToFile(Cabin[] cabins) {
        try (FileOutputStream fos = new FileOutputStream("CruiseShipWithQueueData.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (int i=0; i<cabins.length;i++){
                oos.writeObject(cabins[i]);
            }
            System.out.println("------------------------------------------");
            System.out.println("Cruise Ship Cabin data was successfully saved.");
            System.out.println("------------------------------------------");

        } catch (IOException ex) {
            System.out.println("Error Saving Data. Try again! (IOException)");
            //ex.printStackTrace();
        }
    }

    public static void loadDataFromFile(Cabin[] cabins) {
        try {
            FileInputStream fileIn = new FileInputStream("CruiseShipWithQueueData.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            for (int i = 0; i < cabins.length; i++) {
                cabins[i] = (Cabin) objectIn.readObject();
            }
            objectIn.close();
            System.out.println("------------------------------------------");
            System.out.println("The data has been successfully loaded from the data file.");
            System.out.println("------------------------------------------");

        } catch (Exception ex) {
            System.out.println("Error loading file! Check for missing file and try again.");
            //ex.printStackTrace();
        }
    }

    /*
     * Stores the passed array in a temp array,
     * Compares 2 adjacent strings,
     * And swaps them if not in order.
     *
     * Reference - comparing 2 strings lexicographically
     *    > @link https://stackoverflow.com/questions/6203411/comparing-strings-by-their-alphabetical-order
     */
    public static void passengersOrdered(Cabin[] cabins){

        ArrayList<String> tempPassengers = new ArrayList<>(); // array to store passenger names, temporarily.

        for (Cabin cabin : cabins) { // stores passenger's first and surnames in the temporary array.
            if (cabin != null) {
                for (int j = 0; j < cabin.passengers.length; j++) {
                    if (cabin.passengers[j] != null) {
                        tempPassengers.add((cabin.passengers[j].getFirstName() + " " + cabin.passengers[j].getSurName()));
                    }
                }
            }
        }

        for (int i = 0; i < tempPassengers.size(); i++) {
            for (int j = i+1; j <tempPassengers.size(); j++) {
                int compare = tempPassengers.get(i).compareTo(tempPassengers.get(j));
                /*
                 * 0 if the string is equal to the other string.
                 * < 0 if the string is lexicographically less than the other.
                 * > 0 if the string is lexicographically greater than the other.
                 */
                if(compare > 0) {      //swaps passengers in temporary array, if they are not in order
                    String temp = tempPassengers.get(i);
                    tempPassengers.set(i, tempPassengers.get(j));
                    tempPassengers.set(j, temp);
                }
            }
        }

        System.out.println("------------------------------------------");
        System.out.println("----Passengers Ordered Alphabetically-----");

        for (String tempPassenger : tempPassengers) { // prints passengers from sorted tempPassengers array.
            System.out.println("\t" + tempPassenger);
        }
        System.out.println("------------------------------------------");

    }

    //method to fill all the cabins for easier demonstration of waiting queue.
    public static void fillAllCabins(Cabin[] cabins){
        for (int i = 0; i < cabins.length; i++) {
            cabins[i] = new Cabin(i, "Cabin " + i);
            for (int j = 0; j < cabins[i].passengers.length; j++) {
                cabins[i].passengers[j] = new Passenger("John", "Doe", 420);
            }
        }
    }

    //method to clear all cabins.
    public static void emptyAllCabins(Cabin[] cabins) {
        Arrays.fill(cabins, null); // sets cabins' value back to "null".
    }
}

