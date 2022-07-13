package core.browserstack.models.build_detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "app_url",
        "app_name",
        "app_version",
        "app_custom_id",
        "uploaded_at"
})

public class AppDetails {

    @JsonProperty("app_url")
    private String appUrl;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("app_version")
    private String appVersion;
    @JsonProperty("app_custom_id")
    private String appCustomId;
    @JsonProperty("uploaded_at")
    private String uploadedAt;

    public AppDetails() {
    }

    public AppDetails(String appUrl, String appName, String appVersion, String appCustomId, String uploadedAt) {
        this.appUrl = appUrl;
        this.appName = appName;
        this.appVersion = appVersion;
        this.appCustomId = appCustomId;
        this.uploadedAt = uploadedAt;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppCustomId() {
        return appCustomId;
    }

    public void setAppCustomId(String appCustomId) {
        this.appCustomId = appCustomId;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public String toString() {
        return "AppDetails{" +
                "appUrl='" + appUrl + '\'' +
                ", appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appCustomId='" + appCustomId + '\'' +
                ", uploadedAt='" + uploadedAt + '\'' +
                '}';
    }
}
