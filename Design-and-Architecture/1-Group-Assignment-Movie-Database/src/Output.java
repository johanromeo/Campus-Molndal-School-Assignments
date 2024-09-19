import java.sql.*;

public class Output {

    DBHandler dbHandler; // J.T - Skapa en instans av DBHandler

    OMDB omdb; // J.T - Skapa en instans av OMDB

    KeyReader reader; // J.T - Skapa en instans av KeyReader

    MovieHandler movieHandler; // J.T - Skapa en instans av MovieHandler

    public Output() {
    }

    // J.T - Skriv ut ett anpassat felmeddelande med angivet typ, meddelande och fel
    public void printCustomErrorMessage(String type, String message, String error) {
        System.out.println(type + " " + message + " " + error);
    }

    // J.T - Skriv ut ett meddelande om att angiven person eller typ av person inte hittades
    public void notFound(String typeRole, String name, String message) {
        System.out.println(typeRole + " " + name + " " + message);
    }

    // J.T - Skriv ut ett meddelande om att söka efter en person eller typ av person på OMDB
    public void searchingOMDB(String typeRole, String typeName) {
        System.out.println("Searching for " + typeRole + " " + typeName + " " + "on OMDB...");
    }


// J.T - METODER FÖR ATT SKRIVA UT ALLT I MENYN

    // J.T - Skriv ut titel och genre för en film
    public void printMovies(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Genre: " + resultSet.getString("genre"));
        System.out.println("-------------------------------------------------\n");
    }

    // J.T - Skriv ut titel och skådespelare för en film
    public void printActors(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Actors: " + resultSet.getString("actors"));
        System.out.println("-------------------------------------------------\n");
    }

    // J.T - Skriv ut titel och regissör för en film
    public void printDirectors(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Directors: " + resultSet.getString("director"));
        System.out.println("-------------------------------------------------\n");
    }

    // J.T - Skriv ut titel och genre för en film
    public void printGenres(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Genre: " + resultSet.getString("genre"));
        System.out.println("-------------------------------------------------\n");
    }

    // J.T - Skriv ut titel och år för en film
    public void printYears(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Year: " + resultSet.getString("year"));
        System.out.println("-------------------------------------------------\n");
    }

    // J.T - Skriv ut NÄSTAN allt för en film utan handling
    public void printAllNoPlot(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Year: " + resultSet.getString("year"));
        System.out.println("Genre: " + resultSet.getString("genre"));
        System.out.println("Director: " + resultSet.getString("director"));
        System.out.println("Actors: " + resultSet.getString("actors"));
        System.out.println("-------------------------------------------------\n");
    }
    // J.T - Skriv ut ALLT för en film
    public void printALL(ResultSet resultSet) throws SQLException {
        System.out.println("\n");
        System.out.println("Title: " + resultSet.getString("title"));
        System.out.println("Year: " + resultSet.getString("year"));
        System.out.println("Genre: " + resultSet.getString("genre"));
        System.out.println("Director: " + resultSet.getString("director"));
        System.out.println("Actors: " + resultSet.getString("actors"));
        System.out.println("Plot: " + resultSet.getString("plot"));
        System.out.println("-------------------------------------------------\n");
    }
}











