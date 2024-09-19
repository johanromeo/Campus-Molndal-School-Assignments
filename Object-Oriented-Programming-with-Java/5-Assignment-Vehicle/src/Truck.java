public class Truck extends Vehicle{
    private int doors;
    private int capacity;

    public Truck(String brand, String model, float fuelConsumption, int speed, int year, int doors, int capacity) {
        super(brand, model, fuelConsumption, speed, year);
        this.doors = doors;
        this.capacity = capacity;
    }

    public int getDoors() {
        return doors;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public float getFuelConsumption() {
        return super.getFuelConsumption();
    }

    @Override
    public int getMaxSpeed() {
        return super.getMaxSpeed();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
