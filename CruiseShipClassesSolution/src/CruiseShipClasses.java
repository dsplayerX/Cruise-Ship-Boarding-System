import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CruiseShipClasses {

    static boolean isTrue = true;

    public static void main(String[] args) {
        Cabin[] cabins = new Cabin[12];

        while (isTrue) {
            mainMenu(cabins);
        }
    }

    public static void mainMenu(Cabin[] cabinsRef) {
        System.out.println("==========================================");
        System.out.println("                Main Menu                ");
        System.out.println("==========================================");
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

    public static void addPassengerToCabin(Cabin[] cabins) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cabin number: ");
        int cabinNum = scanner.nextInt();

        if (cabins[cabinNum - 1] == null) {
            cabins[cabinNum - 1] = new Cabin(cabinNum, "Cabin " + cabinNum);
        }

        if (((cabins[cabinNum - 1].passengers[0] != null) && (cabins[cabinNum - 1].passengers[1] != null)) && (cabins[cabinNum - 1].passengers[2] != null)) {
            System.out.println("Cabin " + cabinNum + " is full. Please select another cabin and try again.");
            return;
        }

        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter surname: ");
        String surName = scanner.next();
        System.out.print("Enter expenses: $");
        double expenses = scanner.nextDouble();

        for (int i = 0; i < 3; i++) {
            if (cabins[cabinNum - 1].passengers[i] == null) {
                //System.out.println(cabins[cabinNum - 1].passengers[0]);
                cabins[cabinNum - 1].passengers[i] = new Passenger(firstName, surName, expenses);
                System.out.println("Added " + firstName + " to cabin " + cabinNum + " slot " + (i + 1) + ".");
                break;
            } else {
                System.out.println("Cabin " + cabinNum + " slot " + (i + 1) + " is full. Trying next one...");
            }
        }

        //System.out.println(Arrays.toString(cabins[cabinNum-1].passengers));
        //System.out.println(Arrays.toString(cabins));
    }

    public static void viewAllCabins(Cabin[] cabins) {
        //System.out.println(Arrays.toString(cabins));

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
                System.out.println("Total Expenses of all passengers: $" + totalExpenses);
                break;
            default:
                System.out.println("Wrong Input. Try again!");
                getExpenses(cabins);
                break;
        }
    }

    public static void displayEmptyCabins(Cabin[] cabins){
        System.out.println("Empty Cabins: ");
        for (int i = 0; i < cabins.length; i++) {
            if (cabins[i] == null) {
                System.out.println("\tCabin " + (i + 1));
            }
        }
        System.out.println();
    }

    public static void deletePassengerFromCabin(Cabin[] cabins){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cabin number: ");
        int cabinNum = input.nextInt();
        System.out.print("Enter slot number: ");
        int slotNum = input.nextInt();

        if (cabins[cabinNum - 1] != null) {
            if (cabins[cabinNum - 1].passengers[slotNum - 1] != null) {
                System.out.println("Removed " + cabins[cabinNum -1].passengers[slotNum-1].getFirstName() + " from " + cabins[cabinNum-1].getCabinName() + ".");
                cabins[cabinNum -1].passengers[slotNum-1] = null;

                if (Arrays.toString(cabins[cabinNum - 1].passengers).equals("[null, null, null]")){
                    cabins[cabinNum-1] = null;
                }
            } else {
                System.out.println("Cabin " + cabinNum + " slot " + slotNum + " is already empty.");
            }
        } else {
            System.out.println("Cabin " + cabinNum + " is already empty.");
        }
    }

    public static void findCabinFromName(Cabin[] cabins){
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
    }

    public static void saveDataToFile(Cabin[] cabins) {
        try (FileOutputStream fos = new FileOutputStream("CruiseShipClassesData.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (int i=0; i<cabins.length;i++){
                oos.writeObject(cabins[i]);
            }

            System.out.println("---------------------");
            System.out.println("Saved data to a file.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadDataFromFile(Cabin[] cabins) {
        try {
            FileInputStream fileIn = new FileInputStream("CruiseShipClassesData.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            for (int i=0; i<cabins.length;i++){
                cabins[i] = (Cabin) objectIn.readObject();
            }

            System.out.println("-------------------------------");
            System.out.println("Loaded data back from the file.");
            objectIn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void passengersOrdered(Cabin[] cabins){
        ArrayList<String> tempPassengers = new ArrayList<>();
        for (int i = 0; i < cabins.length; i++) {
            if (cabins[i] != null) {
                for (int j = 0; j < cabins[i].passengers.length; j++) {
                    if (cabins[i].passengers[j] != null) {
                        tempPassengers.add((cabins[i].passengers[j].getFirstName() + " " + cabins[i].passengers[j].getSurName()));
                    }
                }
            }
        }

        for (int i = 0; i < tempPassengers.size(); i++) {
            for (int j = i+1; j <tempPassengers.size(); j++) {
                int compare = tempPassengers.get(i).compareTo(tempPassengers.get(j));
                if(compare > 0) {      //swaps elements if not in order
                    String temp = tempPassengers.get(i);
                    tempPassengers.set(i, tempPassengers.get(j));
                    tempPassengers.set(j, temp);
                }
            }
        }

        for (String tempPassenger : tempPassengers) {
            System.out.println(tempPassenger);
        }
    }
}