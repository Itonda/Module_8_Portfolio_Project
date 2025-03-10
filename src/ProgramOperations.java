import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
/**
 * Class that handles operations related to car inventory.
 **/
public class ProgramOperations {
    private List<Car> carsList = new ArrayList<>();
    // Returns the number of cars in the inventory
    public int getCarCount() {
        return carsList.size();
    }
    // Adds a car to the inventory
    public void addCar(Car car) {
        carsList.add(car);
    }
    // Retrieves a car from the inventory by index
    public Car getCar(int index) {
        if (index < 0 || index >= carsList.size()) {
            return null;
        }
        return carsList.get(index);
    }
    // Displays the car inventory
    public void displayCarInventory() {
        System.out.println(getCarInventoryAsString());
    }
    // Returns formatted strings of car inventory
    public String getCarInventoryAsString() {
        if (carsList.isEmpty()) {
            return "Car inventory is empty.";
        }
        StringBuilder output = new StringBuilder("Cars in inventory:\n");

        for (int i = 0; i < carsList.size(); i++) {
            output.append("Car ").append(i + 1).append(": ").append(carsList.get(i).toString()).append("\n");
        }
        return output.toString();
    }
    // Exports the car inventory to a text file to a predefined location
    public void exportInventoryToTextFile(String filename) {
        String directory = "C:\\Temp\\";
        String filePath = directory + filename;
        String inventoryString = getCarInventoryAsString();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(inventoryString);
            System.out.println("Inventory successfully exported to " + filePath + "\n");
        } catch (IOException e) {
            System.out.println("Error exporting inventory: " + e.getMessage() + "\n");
        }
    }
    // Update methods for car attributes
    public void updateCarMake(int index, String make) {
        carsList.get(index).setMake(make);
    }

    public void updateCarModel(int index, String model) {
        carsList.get(index).setModel(model);
    }

    public void updateCarColor(int index, String color) {
        carsList.get(index).setColor(color);
    }

    public void updateCarVin(int index, String vin) {
        carsList.get(index).setVin(vin);
    }

    public void updateCarYear(int index, int year) {
        carsList.get(index).setYear(year);
    }

    public void updateCarMileage(int index, int mileage) {
        carsList.get(index).setMileage(mileage);
    }

    public void updateCarPrice(int index, Double price) {
        carsList.get(index).setPrice(price);
    }
    // Deletes a car from the inventory
    public void deleteCar(int index) {
        carsList.remove(index);
    }
}