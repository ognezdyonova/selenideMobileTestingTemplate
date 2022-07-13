package api.requests.models.environment.settings_model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "attributes",
        "id",
        "resourceType"
})
public class EnvironmentSetting {
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("resourceType")
    private String resourceType;
}

