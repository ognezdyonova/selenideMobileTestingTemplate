package core.browserstack.endpoints.requests;

import api.requests.constants.EndPoints;
import core.api_requester.core.rest.Requester;
import core.api_requester.schemes.AuthScheme;
import core.browserstack.endpoints.BSEndpoints;
import core.browserstack.models.projects.Project;
import core.config.Prop;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import java.util.List;

/**
 * @author Olga Gnezdyonova
 */
public class Projects {
    /**
     * Get list of projects from BS
     *
     * @return Get
     */
    public Get get() {
        return new Get();
    }

    /**
     * Get projects
     * @return Get
     */
    public static class Get extends Requester<List<Project>> {
        public Get() {
            super(Method.GET, new TypeRef<List<Project>>() {
            });
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Prop.getField("bs.api.base.url"))
                    .setBasePath(Prop.getField("bs.api.base.path")
                            .concat(BSEndpoints.PROJECTS.get()).concat(".json"))
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization",
                            AuthScheme.basicScheme(
                                    Prop.getField("bs.username"),
                                    Prop.getField("bs.accessKey")).generateAuthToken())
                    .build();
        }
    }
}
