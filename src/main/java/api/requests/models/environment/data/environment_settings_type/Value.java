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
        "name",
        "shortname",
        "modules"
})
public class Value {
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortname")
    private String shortname;
    @JsonProperty("modules")
    private List<String> modules = null;
}

