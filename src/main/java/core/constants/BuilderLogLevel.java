package core.constants;

public enum BuilderLogLevel {
    DEBUG("debug"),
    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private String level;

    BuilderLogLevel(String i) {
        this.level = i;

    }

    public String get() {
        return level;
    }
}
