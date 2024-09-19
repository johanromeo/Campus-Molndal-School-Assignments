import java.sql.*;

public class DBHandler {
    Connection connection = null;
    public void connectToDatabase(String databaseName) { //J.R - "Ge mig ett namn och jag kopplar upp dig mot en databas som du namngett"
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
        } catch (SQLException e) {
            System.out.println("Error: could not connect to: " + databaseName + " because of: " + e.getMessage());
        }
    }

    public void closeConnectionToDatabase() { //J.R - Avslutar kontakt med databas. Behöver fixas. Något känns inte helt rätt.
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: could not close connection to database because of: " + e.getMessage());
        }
    }

    public void createTableForDatabase() { //J.R Skapar ett table med alla viktigheter till databasen.
        String sql = "CREATE TABLE IF NOT EXISTS movies (" +
                "movie_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(50)," +
                "year VARCHAR(50)," +
                "genre VARCHAR(50)," +
                "director VARCHAR(50)," +
                "actors VARCHAR(200)," +
                "plot VARCHAR(500)" +
                ")";
        tableStatement(sql); // Hämtar statement från tableStatement();
    }

    public void tableStatement(String sql) { //J.R - Skapade en metod för statement eftersom det blir för mycket text annars
        try {
            connectToDatabase("jonasDB"); //J.R - Använder mig av connectToDatabase för att koppla upp mig mot databasen
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            closeConnectionToDatabase();
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println("Error: could not create table because of: " + e.getMessage());
        }
    }

}