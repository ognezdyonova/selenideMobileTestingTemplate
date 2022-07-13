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
        "lastUpdated",
        "name",
        "value"
})
public class Attributes {
    @JsonProperty("lastUpdated")
    private final String lastUpdated = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
}

