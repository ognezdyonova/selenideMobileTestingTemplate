package api.requests.endpoints.requests;

import api.requests.constants.EndPoints;
import api.requests.constants.Path;
import api.requests.models.environment.envr_data.EnvironmentInfo;
import core.api_requester.core.rest.Requester;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

/**
 * @author Olga Gnezdyonova
 */
public class Environment {
    /**
     * Get Environment Info by V2 endpoint
     *
     * @return Get
     */
    public GetV2 getV2() {
        return new GetV2();
    }

    public static class GetV2 extends Requester<EnvironmentInfo> {
        public GetV2() {
            super(Method.GET, EnvironmentInfo.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_V2
                            .concat(EndPoints.ENVIROMENT)
                            .concat(EndPoints.V2))
                    .setContentType(ContentType.JSON)
                    .build();
        }
    }
}
