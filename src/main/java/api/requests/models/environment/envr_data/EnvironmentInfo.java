package api.requests.models.environment.envr_data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "settings",
        "environmentSettings",
        "metrics",
        "conditions",
        "PATIENTINFO_CUSTOMATTRIBUTES"
})
public class EnvironmentInfo {
    @JsonProperty("settings")
    private Settings settings;
    @JsonProperty("environmentSettings")
    private List<EnvironmentSetting> environmentSettings = null;
    @JsonProperty("metrics")
    private List<Metric> metrics = null;
    @JsonProperty("conditions")
    private List<Condition> conditions = null;
    @JsonProperty("PATIENTINFO_CUSTOMATTRIBUTES")
    private List<PatientCustomAttributes> patientCustomAttributes = null;
}

