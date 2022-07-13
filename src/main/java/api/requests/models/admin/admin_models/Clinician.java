package api.requests.models.admin.admin_models;

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
        "hrsid",
        "id",
        "type",
        "env",
        "email"
})
public class Clinician {
    @JsonProperty("hrsid")
    private String hrsid;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("env")
    private String env;
    @JsonProperty("email")
    private String email;
}
