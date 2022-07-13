package core.api_requester.schemes;

import core.ConsoleLogger;
import io.restassured.authentication.*;

/**
 * Is this a helper class
 * All possible (popular) authorization templates
 * that we can use are described here.
 *
 * @author Olga Gnezdyonova
 * @since 1.8
 */
public class AuthScheme {
    private static final PreemptiveBasicAuthScheme basicAuthScheme = new PreemptiveBasicAuthScheme();
    private static final OAuthScheme oAuthScheme = new OAuthScheme();
    private static final OAuth2Scheme oAuth2Scheme = new OAuth2Scheme();
    private static final FormAuthScheme formAuthScheme = new FormAuthScheme();
    private static final OAuthSignature signature = OAuthSignature.HEADER;

    /**
     * Basic Authorization
     *
     * @param userName - String username
     * @param password - String password
     * @return PreemptiveBasicAuthScheme
     */
    public static PreemptiveBasicAuthScheme basicScheme(String userName, String password) {
        basicAuthScheme.setUserName(userName);
        basicAuthScheme.setPassword(password);
        basicAuthScheme.generateAuthToken();
        return basicAuthScheme;
    }

    /**
     * 0Auth Scheme Authorization
     *
     * @param apikey         - String api key
     * @param secretApikey   - String secret api key
     * @param tokenKey       - String token key
     * @param secretTokenKey String secret token key
     * @return OAuthScheme
     */
    public static OAuthScheme oAuthScheme(String apikey,
                                          String secretApikey,
                                          String tokenKey,
                                          String secretTokenKey) {
        oAuthScheme.setConsumerKey(apikey);
        oAuthScheme.setConsumerSecret(secretApikey);
        oAuthScheme.setAccessToken(tokenKey);
        oAuthScheme.setSecretToken(secretTokenKey);
        oAuthScheme.setSignature(signature);
        return oAuthScheme;
    }

    /**
     * 0Auth2Scheme Authorization
     *
     * @param token - String token key
     * @return OAuth2Scheme
     */
    public static OAuth2Scheme oAuth2Scheme(String token) {
        oAuth2Scheme.setAccessToken(token);
        oAuth2Scheme.setSignature(signature);
        return oAuth2Scheme;
    }

    /**
     * Please watch official documentation
     * {@link}https://github.com/rest-assured/rest-assured/wiki/usage#form-authentication
     *
     * @param userName    - String username
     * @param password    - String password
     * @param action      - String
     * @param userNameTag - String
     * @param passwordTag - String
     * @return FormAuthScheme
     */
    public static FormAuthScheme formAuthScheme(String userName, String password, String action, String userNameTag, String passwordTag) {
        FormAuthConfig formAuthConfig = new FormAuthConfig(action, userNameTag, passwordTag);
        formAuthScheme.setUserName(userName);
        formAuthScheme.setPassword(password);
        try {
            if (formAuthConfig.hasFormAction() && formAuthConfig.hasUserInputTagName() && formAuthConfig.hasPasswordInputTagName()) {
                formAuthScheme.setConfig(formAuthConfig);
            }
        } catch (Exception e) {
            ConsoleLogger.log.debug("Please check form!");
            ConsoleLogger.log.debug("Status of parameters:");
            ConsoleLogger.log.debug("Action: " + formAuthConfig.hasFormAction());
            ConsoleLogger.log.debug("User input: " + formAuthConfig.hasUserInputTagName());
            ConsoleLogger.log.debug("Pass input: " + formAuthConfig.hasPasswordInputTagName());
        }
        return formAuthScheme;
    }

    /**
     * Please watch official documentation
     * {@link}https://github.com/rest-assured/rest-assured/wiki/usage#form-authentication
     *
     * @param userName - String username
     * @param password - String password
     * @return FormAuthScheme
     */
    public static FormAuthScheme formAuthScheme(String userName, String password) {
        formAuthScheme.setUserName(userName);
        formAuthScheme.setPassword(password);
        return formAuthScheme;
    }

}
