package formatter;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface StoryDataFormatter {

    JSONObject format(JSONObject jsonObject);

    default void addDateAddedProperty(JSONObject jsonObject){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        jsonObject.put("date_added", simpleDateFormat.format(date));
    }

}
