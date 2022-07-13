package api.requests.models.environment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.preject_name.models.admin.admin_models.Settings;
import org.preject_name.models.environment.data.Condition;
import org.preject_name.models.environment.data.EnvironmentSetting;
import org.preject_name.models.environment.data.Metric;
import org.preject_name.models.environment.data.PatientInfoCustomAttributes;

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
public class Environment {
    @JsonProperty("settings")
    private Settings settings;
    @JsonProperty("environmentSettings")
    private List<EnvironmentSetting<Object>> environmentSettings = null;
    @JsonProperty("metrics")
    private List<Metric> metrics = null;
    @JsonProperty("conditions")
    private List<Condition> conditions = null;
    @JsonProperty("PATIENTINFO_CUSTOMATTRIBUTES")
    private List<PatientInfoCustomAttributes> patientInfoCustomAttributes = null;
}

