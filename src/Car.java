import java.text.NumberFormat;
import java.util.Locale;

public class Car {
    private String make;
    private String model;
    private String color;
    private String vin;
    private int year;
    private int mileage;
    private Double price;
    // Default Constructor
    public Car() {
        this.make = "make";
        this.model = "model";
        this.color = "color";
        this.vin = "vin";
        this.year = 1926;
        this.mileage = 0;
        this.price = 0.0;
    }
    // Parameterized Constructor
    public Car(String make, String model, String color, String vin, int year, int mileage, Double price) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
        this.vin = vin;
        this.price = price;
    }
    // Setters
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setYear (int year) {
        this.year = year;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    // Getters
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public int getYear() {
        return year;
    }
    public int getMileage() {
        return mileage;
    }
    public String getVin() {
        return vin;
    }
    public Double getPrice() {
        return price;
    }
    // Object String
    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return  "[Make: " + make +
                " / Model: " + model +
                " / Color: " + color +
                " / Year: " + year +
                " / Mileage: " + numberFormat.format(mileage) +
                " / VIN: " + vin +
                " / Price: $" + numberFormat.format(price) + "]";
    }
}