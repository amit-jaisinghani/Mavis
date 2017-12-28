import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class IndexStories {

    public static void main(String[] args) throws IOException {
        indexData();
    }

    private static void indexData() throws IOException {
//        String host = "vpc-mavis-ess-zehi3iypikycqy7xtqpakl6awa.us-east-2.es.amazonaws.com";
        String host = "localhost";
        String index = "stories";
        String type = "story";
        int id = 1;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

//        RestClient client = RestClient.builder(new HttpHost(host, 443, "https")).build();
        RestClient client = RestClient.builder(new HttpHost(host, 9200, "http")).build();

        ArrayList<JSONObject> list = getStories();

        for(JSONObject item : list){
            item.put("subgenre", "FABLES");
            item.put("date_added", simpleDateFormat.format(date));
            String moral = item.get("moral").toString().toLowerCase();
            moral = "The moral of story is " + moral;
            item.replace("moral", moral);
//            HttpEntity entity = new NStringEntity(item.toJSONString(), ContentType.APPLICATION_JSON);
//            Response response = client.performRequest("PUT", "/" + index + "/" + type + "/" + id++ , Collections.emptyMap(), entity);
            System.out.println(item);
        }
        client.close();
    }

    private static ArrayList<JSONObject> getStories() {
        JSONParser parser = new JSONParser();
        ArrayList<JSONObject> list = new ArrayList<>();
        try {
//            Object obj = parser.parse(new FileReader("/home/amit/Documents/Marvis/stories.txt"));
            Object obj = parser.parse(new FileReader("stories.txt"));

            // loop array
            JSONArray msg = (JSONArray) obj;
            for (Object aMsg : msg) {
                list.add((JSONObject) aMsg);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
