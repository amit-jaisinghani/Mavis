package config;

public class LocalConfig extends Config {

    public LocalConfig(){
        super("localhost", 9200, "stories", "story", "http");
    }

}
