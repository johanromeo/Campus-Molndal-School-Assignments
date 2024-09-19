public class Application {
    private MongoDBFacade mongoDBFacade;
    private int choice;
    public Application() {
        mongoDBFacade = new MongoDBFacade();
    }
    public void startProgram() {
        boolean isDone = false;
        while (!isDone) {
            Menu.showMenu();
            choice = Menu.getUserChoise();
            switch (choice) {
                case 1 -> createLogic();
                case 2 -> readLogic();
                case 3 -> updateLogic();
                case 4 -> deleteLogic();
                case 5 -> isDone = true;
            }
        }
    }
    public void createLogic() {
        boolean isDone = false;
        while (!isDone) {
            Menu.showCreateMenu();
            choice = Menu.getUserChoise();

            switch (choice) {
                case 1 -> mongoDBFacade.addCustomerToDatabase();
                case 2 -> mongoDBFacade.addEmployeeToDatabase();
                case 3 ->  isDone = true;
            }
        }
    }
    public void readLogic() {
        boolean isDone = false;
        while (!isDone) {
            Menu.showReadMenu();
            choice = Menu.getUserChoise();

            switch (choice) {
                case 1 -> mongoDBFacade.readAllCustomers();
                case 2 -> mongoDBFacade.readAllEmployees();
                case 3 -> mongoDBFacade.readAll();
                case 4 -> isDone = true;
            }
        }
    }
    public void updateLogic() {
        boolean isDone = false;
        while (!isDone) {
            Menu.showUpdateMenu();
            choice = Menu.getUserChoise();

            switch (choice) {
                case 1 -> mongoDBFacade.updateCustomer();
                case 2 -> mongoDBFacade.updateEmployee();
                case 3 -> isDone = true;
            }
        }
    }
    public void deleteLogic() {
        boolean isDone = false;
        while (!isDone) {
            Menu.showDeleteMenu();
            choice = Menu.getUserChoise();

            switch (choice) {
                case 1 -> mongoDBFacade.deleteCustomer();
                case 2 -> mongoDBFacade.deleteEmployee();
                case 3 -> isDone = true;
            }
        }
    }
}
