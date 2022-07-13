package api.requests.models.admin.tablets;

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
        "profile",
        "name",
        "imei"
})
public class Attributes {
    @JsonProperty("imei")
    private String imei;
    @JsonProperty("name")
    private String name;
    @JsonProperty("profile")
    private Object profile;
}
