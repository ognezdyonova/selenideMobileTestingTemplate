package core.browserstack.models.project_detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "duration",
        "status",
        "tags",
        "group_id",
        "user_id",
        "automation_project_id",
        "created_at",
        "updated_at",
        "hashed_id",
        "delta",
        "test_data",
        "sub_group_id",
        "framework"
})

public class Build {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("duration")
    private Long duration;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tags")
    private Object tags;
    @JsonProperty("group_id")
    private Long groupId;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("automation_project_id")
    private Long automationProjectId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("hashed_id")
    private String hashedId;
    @JsonProperty("delta")
    private Boolean delta;
    @JsonProperty("test_data")
    private Object testData;
    @JsonProperty("sub_group_id")
    private Long subGroupId;
    @JsonProperty("framework")
    private String framework;

    public Build() {
    }

    public Build(Long id, String name, Long duration, String status, Object tags, Long groupId, Long userId, Long automationProjectId, String createdAt, String updatedAt, String hashedId, Boolean delta, Object testData, Long subGroupId, String framework) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.status = status;
        this.tags = tags;
        this.groupId = groupId;
        this.userId = userId;
        this.automationProjectId = automationProjectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hashedId = hashedId;
        this.delta = delta;
        this.testData = testData;
        this.subGroupId = subGroupId;
        this.framework = framework;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAutomationProjectId() {
        return automationProjectId;
    }

    public void setAutomationProjectId(Long automationProjectId) {
        this.automationProjectId = automationProjectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
    }

    public Boolean getDelta() {
        return delta;
    }

    public void setDelta(Boolean delta) {
        this.delta = delta;
    }

    public Object getTestData() {
        return testData;
    }

    public void setTestData(Object testData) {
        this.testData = testData;
    }

    public Long getSubGroupId() {
        return subGroupId;
    }

    public void setSubGroupId(Long subGroupId) {
        this.subGroupId = subGroupId;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    @Override
    public String toString() {
        return "Build{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                ", tags=" + tags +
                ", groupId=" + groupId +
                ", userId=" + userId +
                ", automationProjectId=" + automationProjectId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", hashedId='" + hashedId + '\'' +
                ", delta=" + delta +
                ", testData=" + testData +
                ", subGroupId=" + subGroupId +
                ", framework='" + framework + '\'' +
                '}';
    }
}
