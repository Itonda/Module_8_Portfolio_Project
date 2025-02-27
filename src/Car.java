public class Car {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;
    // Default Constructor
    public Car() {
        this.make = "make";
        this.model = "model";
        this.color = "color";
        this.year = 0;
        this.mileage = 0;
    }
    // Parameterized Constructor
    public Car(String make, String model, String color, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
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
    // Object String
    @Override
    public String toString() {
        return "Car {" +
                "Make: " + make + '\'' +
                ", Model: " + model + '\'' +
                ", Color: " + color + '\'' +
                ", Year: " + year +
                ", Mileage: " + mileage +
                '}';
    }
}