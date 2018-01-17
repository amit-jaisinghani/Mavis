package parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface Parser {

    public ArrayList<JSONObject> parse(String filePath) throws IOException, ParseException;

}
