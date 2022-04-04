import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CruiseShipArrays {

    static boolean isTrue = true;

    public static void main(String[] args) throws IOException {
        String[] cabins = new String[12];

        initialize(cabins);
        //System.out.println(Arrays.toString(cabins));

        while (isTrue) {
            mainMenu(cabins);
        }
    }

    public static void mainMenu(String[] cabinsRef) throws IOException {
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
                break;
            default:
                System.out.println("Wrong input. Please try again!");
                mainMenu(cabinsRef);
                break;
        }
    }

    public static void viewAllCabins(String[] cabinsRef) {
        for (int x = 0; x < cabinsRef.length; x++) {
            if (cabinsRef[x].equals("empty")) {
                System.out.println("Cabin " + (x + 1) + " is empty.");
            } else {
                System.out.println("Cabin " + (x + 1) + " is occupied by " + cabinsRef[x] + ".");
            }
        }
    }

    public static void addPassengerToCabin(String[] cabinsRef) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cabin number (1-12) or '13' to quit.:");
        try {
            int cabinNum = input.nextInt();
            if (cabinNum < 13 && cabinNum > 0) {
                System.out.print("Enter name for cabin " + cabinNum + " :");
                String cabinName = input.next();
                cabinsRef[(cabinNum - 1)] = cabinName;
                System.out.println(cabinName + " was successfully added to cabin " +cabinNum+ ".");
            } else if (cabinNum == 13) {
                mainMenu(cabinsRef);
            } else {
                addPassengerToCabin(cabinsRef);
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input! Please enter an integer.");
            addPassengerToCabin(cabinsRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayEmptyCabins(String[] cabinsRef) {
        System.out.println("Empty cabins: ");
        for (int x = 0; x < cabinsRef.length; x++) {
            if (cabinsRef[x].equals("empty")) {
                System.out.println("\tCabin " + (x + 1));
            }
        }
        System.out.println("");
    }

    public static void deletePassengerFromCabin(String[] cabinsRef) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cabin number (1-12):");
        try {
            int cabinNum = input.nextInt();
            if (cabinNum < 13 && cabinNum > 0) {
                cabinsRef[(cabinNum - 1)] = "empty";
            } else if (cabinNum == 13) {
                mainMenu(cabinsRef);
            } else {
                deletePassengerFromCabin(cabinsRef);
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input! Please enter an integer.");
            deletePassengerFromCabin(cabinsRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findCabinFromName(String[] cabinsRef) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name:");
        String cabinName = input.next();
        for (int x = 0; x < cabinsRef.length; x++) {
            if (cabinsRef[x].equals(cabinName)) {
                System.out.println("The cabin occupied by " + cabinName + " is cabin " + (x + 1) + ".");
            }
        }
    }

    public static void storeProgramData(String[] cabinsRef) throws IOException {
        FileWriter writeOut = new FileWriter("CruiseShipArrayData.txt");

        for( int x = 0; x < cabinsRef.length; x++) {
            writeOut.write(cabinsRef[x] + "\r\n");
        }
        writeOut.close();
    }

    public static void loadProgramData(String[] cabinsRef) throws FileNotFoundException {
        Scanner readIn = new Scanner(new File("CruiseShipArrayData.txt"));
        while(readIn.hasNext()) {
            for( int x = 0; x < cabinsRef.length; x++)
            {
                cabinsRef[x] = readIn.next();
            }
        }
        readIn.close();
        //System.out.println(Arrays.toString(cabinsRef));
    }

    public static void passengersOrdered(String[] cabinsRef) {

        String[] tempCabins = new String[12];

        System.arraycopy(cabinsRef, 0, tempCabins, 0, tempCabins.length);

        for (int i = 0; i < tempCabins.length; i++) {
            for (int j = i+1; j <tempCabins.length; j++) {
                int compare = tempCabins[i].compareTo(tempCabins[j]);
                if(compare > 0) {      //swaps elements if not in order
                    String temp = tempCabins[i];
                    tempCabins[i] = tempCabins[j];
                    tempCabins[j] = temp;
                }
            }
        }
        //System.out.println(Arrays.toString(cabinsRef));
        for (int x = 0; x < tempCabins.length; x++) {
            if (!(tempCabins[x].equals("empty"))) {
                System.out.println(tempCabins[x]);
            }
        }
    }

    public static void initialize(String[] cabinsRef) {
        Arrays.fill(cabinsRef, "empty");
    }
}

