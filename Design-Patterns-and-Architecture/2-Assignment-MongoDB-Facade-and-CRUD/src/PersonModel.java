import org.bson.Document;

public abstract class PersonModel {
    private String name;
    private int year;
    private String address;
    public Document toBSONDocument (PersonModel person) {
        Document doc = new Document("name", person.getName())
                .append("year", person.getYear())
                .append("address", person.getAddress());
        return doc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
