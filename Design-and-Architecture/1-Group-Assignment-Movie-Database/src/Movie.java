public class Movie {
    // JT starts here:
    private String title;
    private String year;
    private String genre;
    private String director;
    private String actors;
    private String plot;

    // Constructor
    public Movie(String title, String year, String genre, String director, String actors, String plot) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
    }
        // getters
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() { return actors; }
    public String getPlot() { return plot; }

    // JT ends here
}
