import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        var car = new Car("Volvo", "S60", 7.5f, 220, 2018, 4);
        var motorcycle = new Motorcycle("BMW", "R 1200", 4.5f, 250, 2022, true);
        var truck = new Truck("Scania", "R450", 35.0f, 90, 1998, 2, 40);
        System.out.println(car);
        System.out.println(motorcycle);
        System.out.println(truck);
        System.out.println("\n");


        var vehicleList = new DetailedVehicleList();
        vehicleList.addVehicleToList(new Car("Toyota", "Camry", 7.50f, 220, 2018, 4));
        vehicleList.addVehicleToList(new Motorcycle("Harley-Davidson", "Sportster", 5.0f, 200, 2019, false));
        vehicleList.addVehicleToList(new Truck("Scania", "R450", 35.0f, 90, 2015, 2, 40));


        vehicleList.printVehicleList();







    }
}
