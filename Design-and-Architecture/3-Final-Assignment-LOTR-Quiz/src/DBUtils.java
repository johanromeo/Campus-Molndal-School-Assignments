public interface DBUtils { // Interface giving methods for connecting, disconnecting and creating a database
                          // for any database that implements this interface
    void connect();
    void disconnect();
    void createDatabase();
}
