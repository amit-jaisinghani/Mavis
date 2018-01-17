package config;

public class Config {

    private String host;
    private int port;
    private String index;
    private String type;
    private String scheme;

    Config(String host, int port, String index, String type, String scheme) {
        this.host = host;
        this.port = port;
        this.index = index;
        this.type = type;
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public String getScheme() {
        return scheme;
    }

}
