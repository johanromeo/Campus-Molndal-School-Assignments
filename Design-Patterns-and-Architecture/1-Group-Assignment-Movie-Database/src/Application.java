import java.util.Scanner;

public class Application {
    MovieHandler moviehandler = new MovieHandler();
    DBHandler dbhandler = new DBHandler(); // J.T
    Menu menu = new Menu();
    OMDB omdb;
    KeyReader reader;

    public Application() {
        dbhandler = new DBHandler(); // J.T
        menu = new Menu();
        moviehandler = new MovieHandler();
        reader = new KeyReader("OMDB");
        omdb = new OMDB(reader.getAPIKey());

    }

    public void startProgram() {
        boolean exit = false;
        while (!exit) {
            menu.showMainMenu();
            int userChoice = menu.userChoice();
            switch (userChoice) {
                case 1:
                    menu.showAll();
                    int choice = menu.userChoice();
                    if (choice == 1) {
                        moviehandler.showAllMovies();
                    } else if (choice == 2) {
                        moviehandler.showAllActors();
                    } else if (choice == 3) {
                        moviehandler.showAllDirectors();
                    } else if (choice == 4) {
                        moviehandler.showAllGenres();
                    } else if (choice == 5) {
                        moviehandler.showAllYears();
                    } else {
                        System.out.println("Wrong input");
                    }
                    break;
                case 2:
                    menu.searchBy();
                    int searchChoice = menu.userChoice();
                    if (searchChoice == 1) {
                        moviehandler.searchByTitle(enterTitle());
                    } else if (searchChoice == 2) {
                        moviehandler.searchByActors(enterActor());
                    } else if (searchChoice == 3) {
                        moviehandler.searchByDirector(enterDirector());
                    } else if (searchChoice == 4) {
                        moviehandler.searchByGenre(enterGenre());
                    } else if (searchChoice == 5) {
                        moviehandler.searchByYear(enterYear());
                    } else {
                        System.out.println("Wrong input");
                    }
                    break;
                case 3:
                    menu.showInformation();
                    int informationChoice = menu.userChoice();
                    if (informationChoice == 1) {
                        moviehandler.showMovieInfo(enterTitle());
                    } else if (informationChoice == 2) {
                        moviehandler.showActorInfo(enterActor());
                    } else if (informationChoice == 3) {
                        moviehandler.showDirectorInfo(enterDirector());
                    } else if (informationChoice == 4) {
                        moviehandler.showGenreInfo(enterGenre());
                    } else if (informationChoice == 5) {
                        moviehandler.showYearInfo(enterYear());
                    } else {
                        System.out.println("Wrong input");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
    // Metod för att söka på titel - J.T
    public String enterTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a title: ");
        return scanner.nextLine();
    }
    // Metod för att söka på skådespelare - J.T
    public String enterActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an actor: ");
        return scanner.nextLine();
    }
    // Metod för att söka på regissör - J.T
    public String enterDirector() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a director: ");
        return scanner.nextLine();
    }
    // Metod för att söka på genre - J.T
    public String enterGenre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a genre: ");
        return scanner.nextLine();
    }

    public String enterYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a year: ");
        return scanner.nextLine();
    }


}

