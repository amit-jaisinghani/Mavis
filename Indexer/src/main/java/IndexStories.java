import config.AWSConfig;
import config.Config;
import config.LocalConfig;
import formatter.FablesFormatter;
import formatter.MLFTFormatter;
import formatter.StoryDataFormatter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import parser.JsonArrayParser;
import parser.JsonObjectParser;
import parser.Parser;
import utils.ApplicationProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class IndexStories {

    public static void main(String[] args) throws Exception {

        ApplicationProperties.loadProperties(
                IndexStories.class.getResourceAsStream("aws.properties"), true);
        indexData();
    }

    private static void indexData() throws IOException {
        //update config
        Config config = new AWSConfig();
        RestClient client = RestClient.builder(new HttpHost(config.getHost(), config.getPort()
                , config.getScheme())).build();

        //update id
        int id = 1;

        //Update formatter
        StoryDataFormatter storyDataFormatter = new FablesFormatter();
        ArrayList<JSONObject> list = getStories();
        for(JSONObject item : list){
            if(item.get("story").toString().length() > 8000)
                continue;
            storyDataFormatter.format(item);
            HttpEntity entity = new NStringEntity(item.toJSONString(), ContentType.APPLICATION_JSON);
            Response response = client.performRequest("PUT", "/" + config.getIndex() + "/"
                    + config.getType() + "/" + id++ , Collections.emptyMap(), entity);
            System.out.println(response);
        }
        client.close();
    }

    private static ArrayList<JSONObject> getStories() {
        ArrayList<JSONObject> list = new ArrayList<>();
        try {
            //update Parser
            Parser parser = new JsonArrayParser();

            //update file
            list =  parser.parse("stories.txt");
//            list =  parser.parse("mftd-stories.txt");
//            list =  parser.parse("/home/amit/IdeaProjects/Mavis/mftd-stories.txt");
//            list =  parser.parse("/home/amit/Documents/Marvis/stories.txt");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
