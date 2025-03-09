import java.util.Scanner;
import java.util.function.Function;

/**
 * Class that represents the menu for the car inventory program.
 */
public class ProgramMenu {
    private ProgramOperations operations = new ProgramOperations();
    private Scanner scanner = new Scanner(System.in);
    // Close the scanner when the program ends
    public void closeScanner() {
        scanner.close();
    }
    // Runs the main menu until the user decides to exit
    public void menuSelection() {
        while (true) {
            displayMainMenu();
            int input = getMenuInput(1, 6);
            if (input == 6) {
                System.out.println("Exiting program.");
                return;
            }
            handleMainMenuSelection(input);
        }
    }
    // Displays the main menu
    private void displayMainMenu() {
        System.out.println("Car Inventory - Main Menu");
        System.out.println("1. Add car(s) to inventory");
        System.out.println("2. Display cars list");
        System.out.println("3. Edit car data");
        System.out.println("4. Delete car");
        System.out.println("5. Export to text file");
        System.out.println("6. Exit");
    }
    // Displays menu for editing car attributes
    private void displayCarEditMenu() {
        System.out.println("Edit Car - Select Field");
        System.out.println("1. Make");
        System.out.println("2. Model");
        System.out.println("3. Color");
        System.out.println("4. VIN");
        System.out.println("5. Year");
        System.out.println("6. Mileage");
        System.out.println("7. Price");
        System.out.println("8. Return to main menu");
    }
    // Handles the user's selection for the main menu
    private void handleMainMenuSelection(int input) {
        switch (input) {
            case 1:
                addCarMenu();
                break;
            case 2:
                operations.displayCarInventory();
                break;
            case 3:
                editCarMenu();
                break;
            case 4:
                deleteCarMenu();
                break;
            case 5:
                exportToFileMenu();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                break;
        }
    }
    // Adds specified number of cars to the inventory
    private void addCarMenu() {
        System.out.println("\n-- Add Car to Inventory --");
        int numCars = getValidNumber("Number of cars to add: ", 1, 100);

        for (int i = 0; i < numCars; i++) {
            System.out.println("\nEnter car " + (operations.getCarCount() + 1) + " details:");
            Car car = getCarDetailsFromUser();
            operations.addCar(car);
        }
        System.out.println("Cars added:");

        for (int i = 0; i < numCars; i++) {
            System.out.println(operations.getCar(operations.getCarCount() - numCars + i).toString());
        }
        System.out.println();
    }
    // Collects car attributes from the user
    private Car getCarDetailsFromUser() {
        String make = getStringInput("Enter Make: ");
        String model = getStringInput("Enter Model: ");
        String color = getStringInput("Enter Color: ");
        String vin = getStringInput("Enter VIN: ");
        int year = getValidNumber("Enter Year: ", 1926, 2100);
        int mileage = getValidNumber("Enter Mileage: ", 0, Integer.MAX_VALUE);
        double price = getValidDouble("Enter Price: ", 0, Double.MAX_VALUE);
        // Creates a new car object with the collected attributes
        return new Car(make, model, color, vin, year, mileage, price);
    }
    // Displays the menu for editing cars by displaying the inventory
    private void editCarMenu() {
        operations.displayCarInventory();
        if (operations.getCarCount() == 0) {
            System.out.println("No cars to edit. Returning to main menu.\n");
            return;
        }

        int carIndex = getValidNumber("Enter car number: ", 1, operations.getCarCount()) - 1;
        displayCarEditMenu();
        int fieldSelection = getMenuInput(1, 8);

        if (fieldSelection == 8) {
            return; // Return to main menu
        }

        editCarField(carIndex, fieldSelection);
    }
    // Edits the selected car attribute
    private void editCarField(int carIndex, int field) {
        switch (field) {
            case 1:
                operations.updateCarMake(carIndex, getStringInput("Enter new Make: "));
                break;
            case 2:
                operations.updateCarModel(carIndex, getStringInput("Enter new Model: "));
                break;
            case 3:
                operations.updateCarColor(carIndex, getStringInput("Enter new Color: "));
                break;
            case 4:
                operations.updateCarVin(carIndex, getStringInput("Enter new VIN: "));
                break;
            case 5:
                operations.updateCarYear(carIndex, getValidNumber("Enter new Year: ", 1926, 2100));
                break;
            case 6:
                operations.updateCarMileage(carIndex, getValidNumber("Enter new Mileage: ", 0, Integer.MAX_VALUE));
                break;
            case 7:
                operations.updateCarPrice(carIndex, getValidDouble("Enter new Price: ", 0, Double.MAX_VALUE));
                break;
        }
        System.out.println("Car updated successfully!\n");
    }
    // Displays the menu for deleting cars, confirms deletion
    private void deleteCarMenu() {
        operations.displayCarInventory();
        if (operations.getCarCount() == 0) {
            System.out.println("No cars to delete. Returning to main menu.\n");
            return;
        }

        int carIndex = getValidNumber("Enter car number to delete: ", 1, operations.getCarCount()) - 1;
        System.out.print("Are you sure you want to delete: " + operations.getCar(carIndex).toString() + " (Y/N)? ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            String carDeleted = operations.getCar(carIndex).toString();
            operations.deleteCar(carIndex);
            System.out.println(carDeleted + " deleted successfully!\n");
        } else {
            System.out.println("Deletion cancelled.\n");
        }
    }
    // Confirms the export of the inventory to a text file
    private void exportToFileMenu() {
        System.out.print("Are you sure you want to export the inventory? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            operations.exportInventoryToTextFile("car_inventory.txt");
        } else {
            System.out.println("Export cancelled.\n");
        }
    }
    // Validates number inputs
    private <T extends Number> T getValidInput(String prompt, T min, T max, Function<String, T> parser) {
        T input = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(prompt);
                input = parser.apply(scanner.nextLine().strip());
                if (input.doubleValue() >= min.doubleValue() && input.doubleValue() <= max.doubleValue()) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }
    // Returns inputs for string attributes, end spaces stripped
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().strip();
    }
    // Returns valid inputs for menu selection, year, mileage, and price, end spaces stripped
    private int getMenuInput(int min, int max) {
        return getValidInput("Make a selection: ", min, max, Integer::parseInt);
    }
    private int getValidNumber(String prompt, int min, int max) {
        return getValidInput(prompt, min, max, Integer::parseInt);
    }
    private double getValidDouble(String prompt, double min, double max) {
        return getValidInput(prompt, min, max, Double::parseDouble);
    }
}