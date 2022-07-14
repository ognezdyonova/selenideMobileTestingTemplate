package core.browserstack.endpoints;

public enum BSEndpoints {
    PROJECTS("/projects"),
    BUILDS("/builds"),
    SESSIONS("/sessions"),
    UPDATE_NETWORK("/update_network");
    private final String name;

    BSEndpoints(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
