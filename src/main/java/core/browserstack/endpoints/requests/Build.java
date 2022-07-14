package core.browserstack.endpoints.requests;

import core.api_requester.core.rest.Requester;
import core.api_requester.schemes.AuthScheme;
import core.browserstack.endpoints.BSEndpoints;
import core.browserstack.models.build_detail.BuildDetail;
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
public class Build {
    /**
     * Get build info by ID
     *
     * @return Get
     */
    public Get get(String id) {
        return new Get(id);
    }

    /**
     * Get build detail
     *
     * @return Get
     */
    public static class Get extends Requester<List<BuildDetail>> {
        private String buildId = null;

        public Get(String id) {
            super(Method.GET, new TypeRef<List<BuildDetail>>() {
            });
            this.buildId = id;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Prop.getField("bs.api.base.url"))
                    .setBasePath(Prop.getField("bs.api.base.path")
                            .concat(BSEndpoints.BUILDS.get())
                            .concat("/")
                            .concat(this.buildId)
                            .concat(BSEndpoints.SESSIONS.get()).concat(".json"))
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization",
                            AuthScheme.basicScheme(
                                    Prop.getField("bs.username"),
                                    Prop.getField("bs.accessKey")).generateAuthToken())
                    .build();
        }
    }
}
