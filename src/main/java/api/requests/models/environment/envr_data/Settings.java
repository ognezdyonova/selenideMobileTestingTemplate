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
        "subgroups",
        "phone",
        "name"
})
public class Settings {
    @JsonProperty("subgroups")
    private List<String> subgroups = null;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("name")
    private String name;
}

