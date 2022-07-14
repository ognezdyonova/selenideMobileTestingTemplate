package core.browserstack.endpoints.requests;

import core.api_requester.core.rest.Requester;
import core.api_requester.schemes.AuthScheme;
import core.browserstack.endpoints.BSEndpoints;
import core.browserstack.models.project_detail.ProjectDetail;
import core.config.Prop;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

/**
 * @author Olga Gnezdyonova
 */
public class Project {
    /**
     * Get project info by ID
     *
     * @return Get
     */
    public Get get(Long id) {
        return new Get(id);
    }

    /**
     * Get project detail
     *
     * @return Get
     */
    public static class Get extends Requester<ProjectDetail> {
        private Long projectId = null;

        public Get(Long id) {
            super(Method.GET, ProjectDetail.class);
            this.projectId = id;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Prop.getField("bs.api.base.url"))
                    .setBasePath(Prop.getField("bs.api.base.path")
                            .concat(BSEndpoints.PROJECTS.get())
                            .concat("/")
                            .concat(String.valueOf(this.projectId)).concat(".json"))
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization",
                            AuthScheme.basicScheme(
                                    Prop.getField("bs.username"),
                                    Prop.getField("bs.accessKey")).generateAuthToken())
                    .build();
        }
    }
}
