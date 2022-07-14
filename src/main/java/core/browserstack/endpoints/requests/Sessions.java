package core.browserstack.endpoints.requests;

import core.api_requester.core.rest.Requester;
import core.api_requester.schemes.AuthScheme;
import core.browserstack.endpoints.BSEndpoints;
import core.config.Prop;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.remote.SessionId;

import java.util.HashMap;
import java.util.Map;

public class Sessions {

    /**
     * Set network profile
     *
     * @param networkProfile network profile typr
     * @param sessionId      current session id
     * @return Put
     */
    public Put put(String networkProfile, SessionId sessionId) {
        return new Put(networkProfile, sessionId);
    }

    /**
     * Set network settings
     *
     * @param sessionId current session id
     * @return Put
     */
    public Put put(SessionId sessionId) {
        return new Put(sessionId);
    }


    /**
     * Put config to session
     *
     * @return Get
     */
    public static class Put extends Requester<Void> {
        private String networkProfile = null;
        private SessionId sessionId = null;

        public Put(String networkProfile, SessionId sessionId) {
            super(Method.PUT, Void.class);
            this.networkProfile = networkProfile;
            this.sessionId = sessionId;
        }

        public Put(SessionId sessionId) {
            super(Method.PUT, Void.class);
            this.sessionId = sessionId;
        }

        @Override
        public Object body() {
            Map<String, String> body = new HashMap<>();
            body.put("networkProfile", this.networkProfile);
            return body;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Prop.getField("bs.api.base.url"))
                    .setBasePath(Prop.getField("bs.api.base.path")
                            .concat(BSEndpoints.SESSIONS.get())
                            .concat("/")
                            .concat(this.sessionId.toString())
                            .concat(BSEndpoints.UPDATE_NETWORK.get()).concat(".json"))
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization",
                            AuthScheme.basicScheme(
                                    Prop.getField("bs.username"),
                                    Prop.getField("bs.accessKey")).generateAuthToken())
                    .build();
        }
    }
}
