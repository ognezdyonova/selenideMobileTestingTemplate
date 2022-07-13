package api.requests.models.environment.presets;

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
        "relationships",
        "attributes",
        "id",
        "resourceType"
})
public class DataPresets {
    @JsonProperty("relationships")
    private Relationships relationships;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("id")
    private String id;
    @JsonProperty("resourceType")
    private String resourceType;
}

