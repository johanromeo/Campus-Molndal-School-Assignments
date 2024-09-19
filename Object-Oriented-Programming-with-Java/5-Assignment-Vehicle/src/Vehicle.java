import java.lang.reflect.Type;

public abstract class Vehicle {
    private String brand;
    private String model;
    private float fuelConsumption;
    private int speed;
    private int year;

    public Vehicle(String brand, String model, float fuelConsumption, int speed, int year) {
        this.brand = brand;
        this.model = model;
        this.fuelConsumption = fuelConsumption;
        this.speed = speed;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getSpeed() {
        return speed;
    }

    public int getYear() {
        return year;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public int getMaxSpeed() {
        return speed;
    }

    public String toString() {
        if (this instanceof Car)
            return "Car \t\t" + this.brand + " " + this.model + "\t\t" + this.fuelConsumption + " l/100 km";
        if (this instanceof Motorcycle)
            return "Motorcycle \t\t" + this.brand + " " + this.model + "\t\t" + this.fuelConsumption + " l/100 km";
        if (this instanceof Truck)
            return "Truck \t\t" + this.brand + " " + this.model + "\t\t" + this.fuelConsumption + " l/100 km";


        return super.toString();
    }
}
