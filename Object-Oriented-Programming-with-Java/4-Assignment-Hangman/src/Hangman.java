public class Hangman {
    public static void main(String[] args) {
        var app = new Application(new WordList());
        app.initializeProgram();
        app.enterHogwarts();
    }
}