import com.mongodb.MongoCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Scanner;
public class MongoDBFacade {
    private CustomerModel customer;
    private EmployeeModel employee;
    private MongoConnector mongo;
    private MongoDatabase database;
public MongoDBFacade() {
    this.mongo = new MongoConnector();
    mongo.connectToLocalMongoDB();
    if (mongo.client != null) {
        try {
            this.database = mongo.client.getDatabase("myDatabase");
            database.createCollection("Employees");
            database.createCollection("Customers");
        } catch (MongoCommandException e) {
            System.out.println("Collections already exist.");
        }
    }
}
public CustomerModel createCustomer() {
        Scanner input = new Scanner(System.in);
        var customer = new CustomerModel();
        System.out.println("Enter customer number: ");
        customer.setCustomerNumber(input.nextLine());

        System.out.println("Enter customer name: ");
        customer.setName(input.nextLine());

        System.out.println("Enter customer age: ");
        customer.setYear(input.nextInt());
        input.nextLine();

        System.out.println("Enter customer address: ");
        customer.setAddress(input.nextLine());

        return customer;
    }
    public EmployeeModel createEmployee() {
        Scanner input = new Scanner(System.in);
        var employee = new EmployeeModel();
        System.out.println("Enter employee number: ");
        employee.setEmployeeNumber(input.nextLine());

        System.out.println("Enter employee name: ");
        employee.setName(input.nextLine());

        System.out.println("Enter employee age: ");
        employee.setYear(input.nextInt());
        input.nextLine();

        System.out.println("Enter employee address: ");
        employee.setAddress(input.nextLine());

        return employee;
    }

    public void addCustomerToDatabase() {
        customer = createCustomer();
        MongoCollection<Document> collection = database.getCollection("Customers");
        collection.insertOne(customer.toBSONDocument(customer));
    }
    public void addEmployeeToDatabase() {
        employee = createEmployee();
        MongoCollection<Document> collection = database.getCollection("Employees");
        collection.insertOne(employee.toBSONDocument(employee));
    }
    public boolean readAllCustomers() {
        MongoCollection<Document> customersCollection = database.getCollection("Customers");
        FindIterable<Document> customers = customersCollection.find();

        if (!customers.iterator().hasNext()) {
            System.out.println("You have no customers in your database.");
            return false;
        }
        for (Document customer : customers) {
            System.out.println(customer.toJson());
        }
        return true;
    }
    public boolean readAllEmployees() {
        MongoCollection<Document> employeeCollection = database.getCollection("Employees");
        FindIterable<Document> employees = employeeCollection.find();

        if (!employees.iterator().hasNext()) {
            System.out.println("You have no employees in your database.");
            return false;
        }
        for (Document employee : employees) {
            System.out.println(employee.toJson());
        }
        return true;
    }
    public boolean readAll() {
        MongoCollection<Document> customersCollection = database.getCollection("Customers");
        MongoCollection<Document> employeeCollection = database.getCollection("Employees");

        FindIterable<Document> customers = customersCollection.find();
        FindIterable<Document> employees = employeeCollection.find();

        if (!customers.iterator().hasNext() && !employees.iterator().hasNext()) {
            System.out.println("You have no customers or employees in your database.");
            return false;
        }
        for (Document customer : customers) {
            System.out.println(customer.toJson());
        }
        for (Document employee : employees) {
            System.out.println(employee.toJson());
        }
        return true;
    }
    public void updateCustomer() {
        if (!readAllCustomers()) {
//            System.out.println("There are no customers in the collection.");
            return;
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter customer name to update: ");
            String customerName = input.nextLine();

            System.out.println("Enter new customer number: ");
            String newCustomerNumber = input.nextLine();

            System.out.println("Enter new customer name: ");
            String newCustomerName = input.nextLine();

            System.out.println("Enter new customer age: ");
            int newCustomerAge = input.nextInt();
            input.nextLine();

            System.out.println("Enter new customer address: ");
            String newCustomerAddress = input.nextLine();

            MongoCollection<Document> customersCollection = database.getCollection("Customers");
            Bson filter = Filters.eq("name", customerName);
            Bson update = new Document("$set", new Document("customerNumber", newCustomerNumber)
                    .append("name", newCustomerName)
                    .append("year", newCustomerAge)
                    .append("address", newCustomerAddress));
            customersCollection.updateOne(filter, update);
        }
    }

    public void updateEmployee() { // hjälp från chatgpt
        if (!readAllEmployees()) {
//            System.out.println("There are no employees in the collection.");
            return;
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter employee name to update: ");
            String employeeName = input.nextLine();;

            System.out.println("Enter new employee number: ");
            String newEmployeeNumber = input.nextLine();

            System.out.println("Enter new employee name: ");
            String newEmployeeName = input.nextLine();

            System.out.println("Enter new employee age: ");
            int newEmployeeAge = input.nextInt();
            input.nextLine();

            System.out.println("Enter new employee address: ");
            String newEmployeeAddress = input.nextLine();

            MongoCollection<Document> employeeCollection = database.getCollection("Employees");
            Bson filter = Filters.eq("name", employeeName);
            Bson update = new Document("$set", new Document("employeeNumber", newEmployeeNumber)
                    .append("name", newEmployeeName)
                    .append("year", newEmployeeAge)
                    .append("address", newEmployeeAddress));
            employeeCollection.updateOne(filter, update);
        }
    }
    public void deleteCustomer() {
        if (!readAllCustomers()) {
//            System.out.println("There are no customers in the collection.");
            return;
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter customer name to delete: ");
            String customerName = input.nextLine();
            Bson query = Filters.eq("name", customerName);
            DeleteResult result = database.getCollection("Customers").deleteOne(query);
        }
    }
    public void deleteEmployee() {
        if (!readAllEmployees()) {
//            System.out.println("There are no employees in the collection.");
            return;
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter employee name to delete: ");
            String employeeName = input.nextLine();
            Bson query = Filters.eq("name", employeeName);
            DeleteResult result = database.getCollection("Employees").deleteOne(query);
        }
    }
}
