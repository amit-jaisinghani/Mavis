package parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonObjectParser implements Parser{

    @Override
    public ArrayList<JSONObject> parse(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;
        while ( (line = bufferedReader.readLine()) != null ) {
            System.out.println(line);
            list.add( (JSONObject) parser.parse(line));
        }
        return list;
    }
}
