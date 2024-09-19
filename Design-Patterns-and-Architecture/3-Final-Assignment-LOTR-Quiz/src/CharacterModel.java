import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class CharacterModel {

    private final String _id;
    private final String name;

    public CharacterModel(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    // Static method for converting JSON to CharacterModel with help from TheOneAPI class
    public static List<CharacterModel> fromJson(String json) {
        var obj = new JSONObject(json);
        JSONArray charactersArray = obj.getJSONArray("docs");

        List<CharacterModel> characterModels = new ArrayList<>();
        for (int i = 0; i < charactersArray.length(); i++) {
            JSONObject characterObject = charactersArray.getJSONObject(i);
            CharacterModel characterModel = new CharacterModel(
                    characterObject.getString("_id"),
                    characterObject.getString("name"));
            characterModels.add(characterModel);
        }

        return characterModels;
    }
    @Override
    public String toString() {
        return "CharacterID: " + _id +
                "\nName: " + name;

    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
}
