public class CharQuoteModel {
    private String character;
    private String dialog;

    public CharQuoteModel(String character, String dialog) {
        this.character = character;
        this.dialog = dialog;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    @Override
    public String toString() {
        return "Character: " + character + "\nQuote: " + dialog;
    }
}
