import org.bson.Document;

public class EmployeeModel extends PersonModel{
    private String employeeNumber;
    public EmployeeModel() {
        super();
    }
    @Override
    public Document toBSONDocument(PersonModel person) {
        Document doc = new Document("name", person.getName())
                .append("year", person.getYear())
                .append("address", person.getAddress())
                .append("employeeNumber", this.getEmployeeNumber());
        return doc;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }
    @Override
    public int getYear() {
        return super.getYear();
    }
    @Override
    public void setYear(int year) {
        super.setYear(year);
    }
    @Override
    public String getAddress() {
        return super.getAddress();
    }
    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
