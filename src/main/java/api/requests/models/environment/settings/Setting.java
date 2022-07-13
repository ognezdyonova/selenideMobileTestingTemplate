package api.requests.models.environment.settings;

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
        "id",
        "flag",
        "value"
})
public class Setting<T> {
    @JsonProperty("id")
    private String id;
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("value")
    private T value;
}