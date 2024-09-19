import org.bson.Document;

public class CustomerModel extends PersonModel {
    private String customerNumber;
    public CustomerModel() {
        super();
    }
    @Override
    public Document toBSONDocument(PersonModel person) {
        Document doc = new Document("name", person.getName())
                .append("year", person.getYear())
                .append("address", person.getAddress())
                .append("customerNumber", this.getCustomerNumber());
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
    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
