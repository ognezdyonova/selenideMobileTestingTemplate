package api.requests.endpoints.requests;

import api.requests.constants.Path;
import api.requests.models.account_data.Account;
import core.api_requester.core.rest.Requester;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

/**
 * @author Olga Gnezdyonova
 */
public class ApiV2 {
    /**
     * Get clinician info
     *
     * @return Get
     */
    public Get get() {
        return new Get();
    }

    public static class Get extends Requester<Account> {
        public Get() {
            super(Method.GET, Account.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_V2)
                    .setContentType(ContentType.JSON)
                    .build();
        }
    }
}
