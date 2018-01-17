package formatter;

import org.json.simple.JSONObject;

public class FablesFormatter implements StoryDataFormatter {

    @Override
    public JSONObject format(JSONObject jsonObject) {
        addDateAddedProperty(jsonObject);
        jsonObject.put("subgenre", "FABLES");
        String moral = jsonObject.get("moral").toString().toLowerCase();
        moral = " The moral of the story is " + moral;
        String story = jsonObject.get("story").toString();
        jsonObject.put("story", story.concat(moral));
        return jsonObject;
    }

}
