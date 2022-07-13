package api.requests.models.environment.envr_data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.Data;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "name",
        "shortname"
})
public class Metric {
    @JsonProperty("data")
    private Data data;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortname")
    private String shortname;
}
