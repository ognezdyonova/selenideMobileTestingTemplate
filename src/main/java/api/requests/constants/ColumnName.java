package api.requests.constants;

public enum ColumnName {
    FIRST_NAME("firstName"),
    STATUS("status"),
    RISK("risk"),
    DAYS_ENROLLED("daysEnrolled"),
    REVIEWED("reviewed"),
    SUBGROUP("subgroup"),
    CLINICIAN("clinician");

    private String name;

    ColumnName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
