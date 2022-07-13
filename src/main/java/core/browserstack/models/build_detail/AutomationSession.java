package core.browserstack.models.build_detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "duration",
        "os",
        "os_version",
        "browser_version",
        "browser",
        "device",
        "status",
        "hashed_id",
        "reason",
        "build_name",
        "project_name",
        "logs",
        "browserstack_status",
        "created_at",
        "browser_url",
        "public_url",
        "appium_logs_url",
        "video_url",
        "device_logs_url",
        "app_details"
})
public class AutomationSession {

    @JsonProperty("name")
    private String name;
    @JsonProperty("duration")
    private Long duration;
    @JsonProperty("os")
    private String os;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("browser_version")
    private String browserVersion;
    @JsonProperty("browser")
    private Object browser;
    @JsonProperty("device")
    private String device;
    @JsonProperty("status")
    private String status;
    @JsonProperty("hashed_id")
    private String hashedId;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("build_name")
    private String buildName;
    @JsonProperty("project_name")
    private String projectName;
    @JsonProperty("logs")
    private String logs;
    @JsonProperty("browserstack_status")
    private String browserstackStatus;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("browser_url")
    private String browserUrl;
    @JsonProperty("public_url")
    private String publicUrl;
    @JsonProperty("appium_logs_url")
    private String appiumLogsUrl;
    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("device_logs_url")
    private String deviceLogsUrl;
    @JsonProperty("build_hashed_id")
    private String build_hashed_id;
    @JsonProperty("app_details")
    private AppDetails appDetails;

    public AutomationSession() {
    }

    public AutomationSession(String name, Long duration, String os, String osVersion, String browserVersion, Object browser, String device, String status, String hashedId, String reason, String buildName, String projectName, String logs, String browserstackStatus, String createdAt, String browserUrl, String publicUrl, String appiumLogsUrl, String videoUrl, String deviceLogsUrl, String build_hashed_id, AppDetails appDetails) {
        this.name = name;
        this.duration = duration;
        this.os = os;
        this.osVersion = osVersion;
        this.browserVersion = browserVersion;
        this.browser = browser;
        this.device = device;
        this.status = status;
        this.hashedId = hashedId;
        this.reason = reason;
        this.buildName = buildName;
        this.projectName = projectName;
        this.logs = logs;
        this.browserstackStatus = browserstackStatus;
        this.createdAt = createdAt;
        this.browserUrl = browserUrl;
        this.publicUrl = publicUrl;
        this.appiumLogsUrl = appiumLogsUrl;
        this.videoUrl = videoUrl;
        this.deviceLogsUrl = deviceLogsUrl;
        this.build_hashed_id = build_hashed_id;
        this.appDetails = appDetails;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public Object getBrowser() {
        return browser;
    }

    public void setBrowser(Object browser) {
        this.browser = browser;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHashedId() {
        return hashedId;
    }

    public void setHashedId(String hashedId) {
        this.hashedId = hashedId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getBrowserstackStatus() {
        return browserstackStatus;
    }

    public void setBrowserstackStatus(String browserstackStatus) {
        this.browserstackStatus = browserstackStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public String getAppiumLogsUrl() {
        return appiumLogsUrl;
    }

    public void setAppiumLogsUrl(String appiumLogsUrl) {
        this.appiumLogsUrl = appiumLogsUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDeviceLogsUrl() {
        return deviceLogsUrl;
    }

    public void setDeviceLogsUrl(String deviceLogsUrl) {
        this.deviceLogsUrl = deviceLogsUrl;
    }

    public String getBuild_hashed_id() {
        return build_hashed_id;
    }

    public void setBuild_hashed_id(String build_hashed_id) {
        this.build_hashed_id = build_hashed_id;
    }

    public AppDetails getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }
}
