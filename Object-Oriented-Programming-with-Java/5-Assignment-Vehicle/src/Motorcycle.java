public class Motorcycle extends Vehicle{
    private boolean windShield;

    public Motorcycle(String brand, String model, float fuelConsumption, int speed, int year, boolean windShield) {
        super(brand, model, fuelConsumption, speed, year);
        this.windShield = windShield;
    }

    public boolean isWindShield() {
        return windShield;
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
