import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CruiseShipArrays {

    static boolean isTrue = true; // used to exit the main menu loop

    public static void main(String[] args) {
        String[] cabins = new String[12]; // String array to store passenger names and "empty" tags for empty cabins

        initialize(cabins); // filling cabins array with "empty".

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
    */
    public static void mainMenu(String[] cabinsRef) {
        System.out.println("==========================================");
        System.out.println("                Main Menu                 ");
        System.out.println("==========================================");
        System.out.println("A: Add Passenger to Cabin");
        System.out.println("V: View All Cabins");
        System.out.println("E: Display Empty Cabins");
        System.out.println("D: Delete Passenger from Cabin");
        System.out.println("F: Find Cabin from Passenger Name");
        System.out.println("S: Store program data into a file");
        System.out.println("L: Load program data into a file");
        System.out.println("O: View Passengers Ordered Alphabetically");
        System.out.println("Q: Quit");

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
                storeProgramData(cabinsRef);
                break;
            case "l":
            case "L":
                loadProgramData(cabinsRef);
                break;
            case "o":
            case "O":
                passengersOrdered(cabinsRef);
                break;
            case "q":
            case "Q":
                isTrue = false;
                System.out.println("Quitting the program...");
                break;
            default:
                System.out.println("Wrong input! Try again.");
                System.out.println("Returning to main menu...");
                mainMenu(cabinsRef); // call back to mainMenu()
                break;
        }
    }

    // Prints whether cabins are occupied or empty.
    public static void viewAllCabins(String[] cabinsRef) {

        System.out.println("------------------------------------------");
        System.out.println("--------------View All Cabins-------------");

        for (int x = 0; x < cabinsRef.length; x++) {
            if (cabinsRef[x].equals("empty")) {
                System.out.println("Cabin " + (x + 1) + " is empty.");
            } else {
                System.out.println("Cabin " + (x + 1) + " is occupied by " + cabinsRef[x] + ".");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Returning to main menu...");
    }

    // Takes cabin number and assign a passenger to it.
    public static void addPassengerToCabin(String[] cabinsRef) {
        System.out.println("------------------------------------------");
        System.out.println("--------Add a Passenger to a Cabin--------");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cabin number (1-12) or '13' to quit:");
        try {
            int cabinNum = input.nextInt();
            if (cabinNum > 0 && cabinNum < 13) {
                System.out.print("Enter name for cabin " + cabinNum + " :");
                String cabinName = input.next();
                cabinsRef[(cabinNum - 1)] = cabinName; // takes the empty cabin number and assigns a name to it.
                System.out.println("------------------------------------------");
                System.out.println(cabinName + " was successfully added to cabin " +cabinNum+ ".");
            } else if (cabinNum == 13) { // opens main menu if 13 is entered
                System.out.println("Returning to main menu...");
                return;
            } else { // re-runs addPassengerToCabin() to try again
                addPassengerToCabin(cabinsRef);
            }
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------------");
            System.out.println("Wrong input! Please enter an integer.");
            addPassengerToCabin(cabinsRef);
        }
    }

     // Displays empty cabins
    public static void displayEmptyCabins(String[] cabinsRef) {
        System.out.println("------------------------------------------");
        System.out.println("-----------Display Empty Cabins-----------");

        System.out.println("Empty cabins: ");
        for (int x = 0; x < cabinsRef.length; x++) {
            if (cabinsRef[x].equals("empty")) { // checks whether the cabin string is "empty"
                System.out.println("\tCabin " + (x + 1));
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("");
    }

    // Takes cabin number and removes passenger form it
    public static void deletePassengerFromCabin(String[] cabinsRef) {
        System.out.println("------------------------------------------");
        System.out.println("-----Delete a passenger from a cabin------");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter cabin number (1-12) or '13' to quit:");
        try {
            int cabinNum = input.nextInt();
            if (cabinNum < 13 && cabinNum > 0) {
                cabinsRef[(cabinNum - 1)] = "empty"; // sets cabin name back to "empty"
                System.out.println("Cabin " + cabinNum + " was cleared.");
            } else if (cabinNum == 13) {
                System.out.println("Returning to main menu...");
                mainMenu(cabinsRef);
            } else {
                deletePassengerFromCabin(cabinsRef);
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input! Please enter an integer.");
            deletePassengerFromCabin(cabinsRef);
        }
        System.out.println("------------------------------------------");
    }

    // Takes in passenger name and displays the cabin occupied
    public static void findCabinFromName(String[] cabinsRef) {
        boolean cabinFound = false; // condition check to find whether there is a passenger by the given name
        System.out.println("------------------------------------------");
        System.out.println("------Find Cabin from Passenger Name------");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter passenger's name:");
        String passengerName = input.next();
        for (int x = 0; x < cabinsRef.length; x++) {
            if (cabinsRef[x].equals(passengerName)) {
                System.out.println("The cabin occupied by " + passengerName + " is cabin " + (x + 1) + ".");
                cabinFound = true;
            }
        }
        if (!cabinFound) {
            System.out.println("No passenger by that name.");
        }
        System.out.println("------------------------------------------");
    }

    // Saves array data into a text file
    public static void storeProgramData(String[] cabinsRef) {
        System.out.println("------------------------------------------");
        System.out.println("-------------Save Program Data------------");

        try{
            FileWriter writeOut = new FileWriter("CruiseShipArrayData.txt");

            for (String s : cabinsRef) {
                writeOut.write(s + "\r\n"); // "\r\n" to indicate the end of line
            }
            writeOut.close();
            System.out.println("Cabin data was successfully saved.");
            System.out.println("------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error Saving Data. Try again! (IOException)");
            //e.printStackTrace();
        }
    }

    // Loads program data back from the saved text file.
    public static void loadProgramData(String[] cabinsRef) {
        System.out.println("------------------------------------------");
        System.out.println("------------Load Program Data-------------");

        try {
            Scanner readIn = new Scanner(new File("CruiseShipArrayData.txt"));
            while(readIn.hasNext()) {
                for( int x = 0; x < cabinsRef.length; x++)
                {
                    cabinsRef[x] = readIn.next();
                }
            }
            readIn.close();
            System.out.println("The data has been successfully loaded from the text file.");
            System.out.println("------------------------------------------");

        } catch (FileNotFoundException e){
            System.out.println("Error loading file! Check for missing file and try again.");
            //e.printStackTrace();
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
    public static void passengersOrdered(String[] cabinsRef) {

        String[] tempCabins = new String[12];

        System.arraycopy(cabinsRef, 0, tempCabins, 0, tempCabins.length);
        // uses temporary array to sort alphabetically otherwise original array would be lost

        for (int i = 0; i < tempCabins.length; i++) {
            for (int j = i+1; j <tempCabins.length; j++) {
                int compare = tempCabins[i].compareTo(tempCabins[j]);
                /*
                  * 0 if the string is equal to the other string.
                  * < 0 if the string is lexicographically less than the other.
                  * > 0 if the string is lexicographically greater than the other.
                */
                if(compare > 0) {      //swaps passengers in temporary array, if they are not in order
                    String temp = tempCabins[i];
                    tempCabins[i] = tempCabins[j];
                    tempCabins[j] = temp;
                }
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("----Passengers Ordered Alphabetically-----");
        for (String tempCabin : tempCabins) { // prints only the passenger names from temporary array (without "empty")
            if (!(tempCabin.equals("empty"))) {
                System.out.println(tempCabin);
            }
        }
        System.out.println("------------------------------------------");
    }

    // Fill the array with "empty" to start program with empty cabins
    public static void initialize(String[] cabinsRef) {
        for(int i = 0; i < cabinsRef.length; i++ ){
            cabinsRef[i] = "empty";
        }
    }
}

