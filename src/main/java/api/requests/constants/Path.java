package api.requests.constants;

import core.ConsoleLogger;
import core.config.Prop;

public class Path {
    public static String BASE_URI = Prop.getField("api.base.url");
    public static final String API_V2 = "/apiv2";
    public static final String API_V1 = "/v1";
    public static final String API_ADMIN_V2 = "/v2";


    public static void setBaseUrl(String[] url) {
        try {
            Path.BASE_URI = "https://gateway.".concat(url[0]).concat(".com");
        } catch (ArrayIndexOutOfBoundsException e) {
            ConsoleLogger.log.info("Using default url ");
        }

    }

    public static void setBaseUri(String baseUri) {
        System.out.println("newURL" + baseUri);
        BASE_URI = baseUri;
        System.out.println("newURL" + BASE_URI);
    }
}
