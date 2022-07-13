package api.requests.models.environment.data.environment_settings_type;

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
        "alerttype",
        "type",
        "events",
        "phone",
        "value"
})
public class RiskData {
    @JsonProperty("alerttype")
    private String alerttype;
    @JsonProperty("type")
    private String type;
    @JsonProperty("events")
    private List<String> events = null;
    @JsonProperty("value")
    private String value;
    @JsonProperty("phone")
    private String phone;
}

