import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class QuoteModel {

    private final String dialog;
    private final String character;

    public QuoteModel(String dialog, String character) {
        this.dialog = dialog;
        this.character = character;
    }


    // Static method for converting JSON to CharacterModel with help from TheOneAPI class
    public static List<QuoteModel> fromJson(String json) {
        var obj = new JSONObject(json);
        JSONArray quotesArray = obj.getJSONArray("docs");

        List<QuoteModel> quoteModels = new ArrayList<>();
        for (int i = 0; i < quotesArray.length(); i++) {
            JSONObject quoteObject = quotesArray.getJSONObject(i);
            QuoteModel quoteModel = new QuoteModel(
                    quoteObject.getString("dialog"),
                    quoteObject.getString("character")
            );
            quoteModels.add(quoteModel);
        }

        return quoteModels;
    }
    @Override
    public String toString() {
        return "Quote: " + dialog +
                "\nCharacterID: " + character;
    }

    public String getDialog() {
        return dialog;
    }

    public String getCharacter() {
        return character;
    }
}
