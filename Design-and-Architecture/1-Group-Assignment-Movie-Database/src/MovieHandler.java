import java.util.*;
import java.sql.*;
public class MovieHandler {
    Menu menu = new Menu();
    DBHandler dbHandler = new DBHandler();
    KeyReader reader = new KeyReader("OMDB");
    OMDB omdb = new OMDB(reader.getAPIKey());

    Output out = new Output();

    public MovieHandler() {

    }

    // Metod för att söka efter filmer med en specifik skådespelare. J.R and J.T
    public void searchActor() {
        Scanner input = new Scanner(System.in);
        System.out.print("Ange skådespelarens namn: ");
        String actor = input.nextLine();
        String sql = "SELECT * FROM movies WHERE actors LIKE '%" + actor + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("Titel: " + resultSet.getString("title"));
                System.out.println("-------------------------------------------------");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            System.out.println("Error: kunde inte söka efter skådespelare på grund av: " + e.getMessage()); // J.T
        }
    }

    // Metod för att lägga till en ny film i databasen. J.R and J.T
    public void addMovie(Movie movie) {
        if (movie == null) {
            return;
        }
        String sql = "SELECT * FROM movies WHERE title = ? AND director = ?";
        try {
            dbHandler.connectToDatabase("jonasDB");
            PreparedStatement preparedStatement = dbHandler.connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDirector());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Filmen finns redan i databasen");
            } else {
                sql = "INSERT INTO movies (title, genre, plot, actors, director, year) VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = dbHandler.connection.prepareStatement(sql);
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getPlot());
                preparedStatement.setString(4, movie.getActors());
                preparedStatement.setString(5, movie.getDirector());
                preparedStatement.setString(6, movie.getYear());
                preparedStatement.executeUpdate();
                System.out.println("Filmen har lagts till i databasen");
            }
            preparedStatement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            System.out.println("Error: kunde inte lägga till film på grund av: " + e.getMessage()); // J.T
        }
    }

    // Metod för att visa alla filmer i databasen. J.T
    public void showAllMovies() {
        String sql = "SELECT * FROM movies";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                out.printMovies(resultSet);
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            out.printCustomErrorMessage("filmer", "kunde inte visas på grund av: ", e.getMessage()); // J.T
        }
    }
    //Metod för att visa alla skådespelare i databasen. J.T
    public void showAllActors() {
        String sql = "SELECT * FROM movies";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
              out.printActors(resultSet);
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage("actors", "could not be shown because of: ", e.getMessage());
        }
    }
    //Metod för att visa alla regissörer i databasen. J.T
    public void showAllDirectors() {
        String sql = "SELECT * FROM movies";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
           out.printDirectors(resultSet);
            }
            statement.close();
            dbHandler.closeConnectionToDatabase(); // hej
        } catch (
                SQLException e) {
            out.printCustomErrorMessage("directors", "could not be shown because of: ", e.getMessage());
        }
    }
    //Metod för att visa alla genrer i databasen. J.T
    public void showAllGenres() {
        String sql = "SELECT * FROM movies";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                out.printGenres(resultSet);
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage("genres", "could not be shown because of: ", e.getMessage());
        }
    }
//Metod för att visa alla år i databasen. J.T
    public void showAllYears() {
        String sql = "SELECT * FROM movies";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
             out.printYears(resultSet);
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage("years", "could not be shown because of: ", e.getMessage());
        }
    }
    // För alla searchBy options i menyn // J.T

    // Metod för att söka efter en film i databasen, finns den inte --> sök, finns ja/nej --> ja lägg till / nej --> avsluta. J.T
    public void searchByTitle(String title) {

        String sql = "SELECT * FROM movies WHERE title LIKE '%" + title + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) { // check if the result set is empty
            out.notFound("Movie", title, "was not found in the database");
                addMovie(omdb.addMovieToDB(omdb.searchTitle(title)));
            } else {
                while (resultSet.next()) {
                    out.printMovies(resultSet);
                }
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
             } catch (
                SQLException e) {
            out.printCustomErrorMessage(title, "Movie was not found.. probably because that name does not exist or your entered a faulty name. Try again... : ", e.getMessage());
        }
    }
    //Metod för att söka på specfik skådis som finns i våran db. J.T
    public void searchByActors(String actor) {


        String sql = "SELECT * FROM movies WHERE actors LIKE '%" + actor + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                out.notFound("Actor", actor, "was not found in the database");
            } else {
                while (resultSet.next()) {
                    out.printActors(resultSet);
                }
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage(actor, "could not be searched because of: ", e.getMessage());
        }
    }

    //Metod för att söka på specifik regissör som finns i våran db. J.T
    public void searchByDirector(String director) {


        String sql = "SELECT * FROM movies WHERE director LIKE '%" + director + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do
                {
                    out.printDirectors(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound("Director", director, "was not found in the database");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage(director, "could not be searched because of: ", e.getMessage());
        }
    }
//Metod för att söka på specifik genre som finns i våran db. J.T
    public void searchByGenre(String genre) {


        String sql = "SELECT * FROM movies WHERE genre LIKE '%" + genre + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do
                {
                   out.printGenres(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound("Genre", genre,"was not found in the database");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage(genre, "could not be searched because of: ", e.getMessage());
        }
    }
//Metod för att söka på specifikt år som finns i våran db. J.T
    public void searchByYear(String year) {


        String sql = "SELECT * FROM movies WHERE year = '" + year + "'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do
                {
                    out.printYears(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound("Movie with year:", year, "not found in the database");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (
                SQLException e) {
            out.printCustomErrorMessage(year, "could not be searched because of: ", e.getMessage());
        }
    }

    // För alla showInformation options i menyn // J.T
    // Metod för att visa ALL information om en film i databasen. J.T

    public void showMovieInfo(String title) {
        String sql = "SELECT * FROM movies WHERE title LIKE '%" + title + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    out.printALL(resultSet);
                } while (resultSet.next());
            } else {
                out.printCustomErrorMessage(title, "was not found in the database.", "Use Search option to add it to the database!");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            out.printCustomErrorMessage(title, "could not be shown because of: ", e.getMessage());
        }
    }

// metod för att visa ALL information om en skådis i databasen. J.T
    public void showActorInfo(String actor) {

        String sql = "SELECT * FROM movies WHERE actors LIKE '%" + actor + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    out.printAllNoPlot(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound("Actor", actor, "was not found in the database");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            out.printCustomErrorMessage(actor, "could not be shown because of: ", e.getMessage());
        }
    }
// metod för att visa all information om en regissör i databasen. J.T
    public void showDirectorInfo(String director) {


        String sql = "SELECT * FROM movies WHERE director LIKE '%" + director + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    out.printAllNoPlot(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound("Director", director, "was not found in the database");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            out.printCustomErrorMessage(director, "could not be shown because of: ", e.getMessage());
        }
    }
// metod för att visa all information om en genre i databasen. J.T
    public void showGenreInfo(String genre) {


        String sql = "SELECT * FROM movies WHERE genre LIKE '%" + genre + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    out.printAllNoPlot(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound(genre, "genre","was not found in the database.");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            out.printCustomErrorMessage(genre, "could not be shown because of: ", e.getMessage());
        }
    }
// metod för att visa all information om en film baserat på ett år i databasen. J.T
    public void showYearInfo(String year) {


        String sql = "SELECT * FROM movies WHERE year LIKE '%" + year + "%'";
        try {
            dbHandler.connectToDatabase("jonasDB");
            Statement statement = dbHandler.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                do {
                    out.printAllNoPlot(resultSet);
                } while (resultSet.next());
            } else {
                out.notFound("year", year,"was not found in the database.");
            }
            statement.close();
            dbHandler.closeConnectionToDatabase();
        } catch (SQLException e) {
            out.printCustomErrorMessage(year, "could not be shown because of: ", e.getMessage());
        }
    }



}