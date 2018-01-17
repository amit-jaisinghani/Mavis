import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoryCrawler {

    public static void main(String[] args) {
        saveData(getStories(getStoryURLs()));
    }

    private static void saveData(ArrayList<JSONObject> stories) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("mftd-stories.txt", false));
            for(JSONObject jsonObject : stories){
                bufferedWriter.write(jsonObject.toJSONString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<JSONObject> getStories(ArrayList<String> urls) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for(String url : urls) {
            try {
                JSONObject jsonObject = new JSONObject();
                Document document = Jsoup.connect(url).get();
                Element div = document.getElementsByTag("div").get(1);
                Element title = div.child(1);
                Element story = div.getElementById("story");
                Element author = div.child(2);
                if(author.children().size() > 0) {
                    author = author.child(0);
                }
                jsonObject.put("title", title.text());
                jsonObject.put("story", story.text());
                jsonObject.put("author", author.text());
                System.out.println(jsonObject.toJSONString());
                jsonObjects.add(jsonObject);
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }

    private static ArrayList<String> getStoryURLs(){
        ArrayList<String> urls = new ArrayList<>();
        try {
            Document document = Jsoup.connect("http://www.mftd.org/search.php?action=search&act=show&langname=English&orglangname=&author=&verse=&hq=&tq=").get();
            Element table = document.getElementsByTag("table").get(0);
            Elements rows = table.select("tr");
            for(Element row : rows) {
                Elements tds = row.select("td");
                if(tds.size() < 1){
                    continue;
                }
                Element href = tds.first().getElementsByTag("a").first();
                urls.add(href.attr("abs:href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

}
