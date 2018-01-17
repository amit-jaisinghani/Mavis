package parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonArrayParser implements Parser{

    @Override
    public ArrayList<JSONObject> parse(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<>();
        Object obj = parser.parse(new FileReader(filePath));
        JSONArray msg = (JSONArray) obj;
        msg.forEach(item -> list.add((JSONObject) item));
        return list;
    }
}
