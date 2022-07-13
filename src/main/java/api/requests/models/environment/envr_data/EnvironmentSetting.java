package api.requests.models.environment.envr_data;

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
        "flag",
        "value",
        "url"
})
public class EnvironmentSetting<T> {
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("value")
    private T value;
    @JsonProperty("url")
    private String url;
}

