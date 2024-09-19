// Class only purpose is to connect to a database with the help of the interface DBUtils
public class ConnectToDB implements DBUtils { // Class not used in this program. Left here for future use

    // The wanted injection
    private final DBUtils injection;
    // Dependency Injection through constructor. Pass an object who implements from DBUtils
    public ConnectToDB(DBUtils injection) {
        this.injection = injection;
    }

    /**
     * Connecting to whatever database that gets passed through the constructor
     */
    @Override
    public void connect() {
        injection.connect();
    }
    /**
     * Disconnecting from whatever database that gets passed through the constructor
     */
    @Override
    public void disconnect() {
        injection.disconnect();
    }
    /**
     * Creating a database with a name dictated from the injection
     */
    @Override
    public void createDatabase() {
        injection.createDatabase();
    }
}
