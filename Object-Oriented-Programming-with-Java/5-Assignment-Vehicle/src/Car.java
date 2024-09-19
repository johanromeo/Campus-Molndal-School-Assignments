public class Car extends Vehicle{
    private int doors;


    public Car(String brand, String model, float fuelConsumption, int speed, int year, int doors) {
        super(brand, model, fuelConsumption, speed, year);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
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
