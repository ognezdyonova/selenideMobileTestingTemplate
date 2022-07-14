package core.browserstack.endpoints;

import core.browserstack.endpoints.requests.Build;
import core.browserstack.endpoints.requests.Project;
import core.browserstack.endpoints.requests.Projects;
import core.browserstack.endpoints.requests.Sessions;


public class BSApiRequester {
    public BSApiRequester() {
    }

    /**
     * New instants for endpoint
     *
     * @return Projects
     */
    public Projects getProjects() {
        return new Projects();
    }

    /**
     * New instants for endpoint
     *
     * @return Projects
     */
    public Project getProjectDetail() {
        return new Project();
    }

    /**
     * New instants for endpoint
     *
     * @return Projects
     */
    public Build getBuildDetail() {
        return new Build();
    }

    /**
     * New instants for endpoint
     *
     * @return Sessions
     */
    public Sessions setNetworkProfile() {
        return new Sessions();
    }
}
