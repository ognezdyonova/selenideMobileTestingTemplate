package core.browserstack.models.build_detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "automation_session"
})
public class BuildDetail {

    @JsonProperty("automation_session")
    private AutomationSession automationSession;

    public BuildDetail() {
    }

    public BuildDetail(AutomationSession automationSession) {
        this.automationSession = automationSession;
    }

    public AutomationSession getAutomationSession() {
        return automationSession;
    }

    public void setAutomationSession(AutomationSession automationSession) {
        this.automationSession = automationSession;
    }

    @Override
    public String toString() {
        return "BuildDetail{" +
                "automationSession=" + automationSession +
                '}';
    }
}
