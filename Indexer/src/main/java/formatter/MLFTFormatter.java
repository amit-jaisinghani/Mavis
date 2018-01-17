package formatter;

import org.json.simple.JSONObject;

public class MLFTFormatter implements StoryDataFormatter {

    @Override
    public JSONObject format(JSONObject jsonObject) {
        addDateAddedProperty(jsonObject);
        jsonObject.put("subgenre", "Multilingual Folk Tale");
        return jsonObject;
    }

}
