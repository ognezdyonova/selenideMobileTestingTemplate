package api.requests.constants;


import org.preject_name.core.rest.configs.Configurations;

public class AuthData {
    public static final String TYPE = "credentials";
    public static final String SOURCE = "ClinicianConnect 2";
    public static final String CONFIGURATION_FILE = "./config.encode";
    public static final String FILE_CONFIG_PROP = "./config.properties";
    public static String MODERATOR_USER_NAME = Configurations.getConfigParam("user_name");
    public static String MODERATOR_PASSWORD = Configurations.getConfigParam("password");
}