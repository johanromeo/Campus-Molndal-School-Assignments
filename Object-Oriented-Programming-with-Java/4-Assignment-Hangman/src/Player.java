public class Player {
    private final String name;
    private int guesses;
    public Player(String name) {
        this.name = name;
        guesses = 10;
    }
    public void decreaseGuesses() {
        guesses--;
    }

    public String getName() {
        return name;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }
}