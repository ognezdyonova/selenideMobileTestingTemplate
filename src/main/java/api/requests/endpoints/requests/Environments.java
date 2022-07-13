package api.requests.endpoints.requests;

import api.requests.constants.EndPoints;
import api.requests.constants.Path;
import api.requests.models.admin.envr.AdminEnvironments;
import core.api_requester.core.rest.Requester;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

/**
 * @author Olga Gnezdyonova
 */
public class Environments {
    /**
     * Get list of environments from admin panel
     *
     * @return Get
     */
    public Get get() {
        return new Get();
    }

    public static class Get extends Requester<AdminEnvironments> {

        public Get() {
            super(Method.GET, AdminEnvironments.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_V1.concat(EndPoints.ENVIROMENTS))
                    .setContentType(ContentType.JSON)
                    .build();
        }
    }
}
