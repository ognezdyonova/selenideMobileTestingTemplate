package api.requests.endpoints;


import api.requests.endpoints.requests.Admin;
import api.requests.endpoints.requests.ApiV2;
import api.requests.endpoints.requests.Environment;

/**
 * @author Olga Gnezdyonova
 */
public class ProjectNameApi {

    public ProjectNameApi() {
    }

    /**
     * New instants for endpoint
     *
     * @return Admin
     */
    public Admin admin() {
        return new Admin();
    }

    /**
     * New instants for endpoint
     *
     * @return ApiV2
     */
    public ApiV2 apiv2() {
        return new ApiV2();
    }

    /**
     * New instants for endpoint
     *
     * @return Environment
     */
    public Environment environment() {
        return new Environment();
    }
}
