package config;

import utils.ApplicationProperties;

public class AWSConfig extends Config {

    private static String host = ApplicationProperties.getProperty("HOST");
    private static int port = Integer.parseInt(ApplicationProperties.getProperty("PORT"));
    private static String index = ApplicationProperties.getProperty("INDEX");
    private static String type = ApplicationProperties.getProperty("TYPE");
    private static String scheme = ApplicationProperties.getProperty("SCHEME");

    public AWSConfig() {
        super(host, port, index, type, scheme);
    }

}
