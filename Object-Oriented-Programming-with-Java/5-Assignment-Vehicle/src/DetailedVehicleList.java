import java.util.ArrayList;
import java.util.Collections;

public class DetailedVehicleList {
    private int travelDistance = 200;
    private float fuelPrice = 19.42f;
    ArrayList<Vehicle> vehicleList;
    ArrayList<Float> pricesForTravel;

    public DetailedVehicleList() {
        this.vehicleList = new ArrayList<>();
    }

    public void addVehicleToList(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }
//    Ville anropa metoderna nedan i printVehicleList() men de uppdaterade inte korrekta pris samt bränsle tyvärr. . .

//    public float getFuelPrice() {
//        float priceToTravel = 0.0f;
//        for (Vehicle vehicle : vehicleList) {
//            priceToTravel = fuelPrice * vehicle.getFuelConsumption() * travelDistance / 100f;
//        }
//        return priceToTravel;
//    }
//    public float fuelNeeded() {
//        float fuelNeeded = 0.0f;
//        for (Vehicle vehicle : vehicleList) {
//            fuelNeeded = vehicle.getFuelConsumption() * travelDistance / 100f;
//        }
//        return fuelNeeded;
//    }

    public void printVehicleList() {
        pricesForTravel = new ArrayList<>();

        System.out.println("Distance to travel: " + travelDistance + "\nFuel price: " + fuelPrice + "\n");

        for (Vehicle vehicle : vehicleList) {
            float costToTravel = fuelPrice * vehicle.getFuelConsumption() * travelDistance / 100f;
            float fuelNeeded = vehicle.getFuelConsumption() * travelDistance / 100f;
            pricesForTravel.add(costToTravel);

            if (vehicle instanceof Car) {
                System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ")");
                System.out.println("Max speed: " + vehicle.getMaxSpeed() + "\nFuel consumption " + vehicle.getFuelConsumption() + " l/100 km");
                System.out.println("Door count: " + ((Car) vehicle).getDoors());
            }
            if (vehicle instanceof Motorcycle) {
                System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ")");
                System.out.println("Max speed: " + vehicle.getMaxSpeed() + "\nFuel consumption " + vehicle.getFuelConsumption() + " l/100 km");
                System.out.println("Has windshield: " + ((Motorcycle) vehicle).isWindShield());
            }
            if (vehicle instanceof Truck) {
                System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ")");
                System.out.println("Max speed: " + vehicle.getMaxSpeed() + "\nFuel consumption " + vehicle.getFuelConsumption() + " l/100 km");
                System.out.println("Door count: " + ((Truck) vehicle).getDoors());
                System.out.println("Max capacity: " + ((Truck) vehicle).getCapacity() + " tons");
            }
            // ChatGPT föreslog nedan utskrift istället för min idé med metodanropen som kördes i stuprännan
            // Hur hade man kunnat ha metodanropen istället Marcus??
            System.out.println("Travel distance: " + this.travelDistance + " km");
            System.out.println("Fuel needed: " + fuelNeeded + " l");
            System.out.println("Price: " + costToTravel + " kr");
            System.out.println("\n");

        }
        // Lånat nedan av ChatGPT - Behöver öva på loopar och tilldela värden till en variabel
        float lowestCost = Float.MAX_VALUE;
        for (float cost : pricesForTravel) {
            if (cost < lowestCost) {
                lowestCost = cost;
            }
        }

        for (Vehicle vehicle : vehicleList) {
            float costToTravel = fuelPrice * vehicle.getFuelConsumption() * travelDistance / 100f;
            if (costToTravel == lowestCost) {
                System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ")" +
                        " är billigast, den kostar bara " + lowestCost + "kronor per sträcka");
            }
        }
    }
}

