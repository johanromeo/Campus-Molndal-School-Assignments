public class Word {
    private final String word;
    private String guess;
    public Word(String word) {
        this.word = word;
        this.guess = "-".repeat(word.length());
    }
    public void displayLetter(char letter) {
        char[] newGuess = guess.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                newGuess[i] = letter;
            }
        }
        guess = new String(newGuess);
    }
    public boolean matchGuess(char letter) {
        return word.contains(Character.toString(letter));
    }
    public String getWord() {
        return word;
    }
    public String getGuess() {
        return guess;
    }
    public boolean isGuessEqualsWord() {
        return guess.equals(word);
    }
}
