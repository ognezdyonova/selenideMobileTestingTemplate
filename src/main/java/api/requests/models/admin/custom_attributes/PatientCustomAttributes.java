package api.requests.models.admin.custom_attributes;

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
        "status",
        "auth",
        "data"
})
public class PatientCustomAttributes {
    @JsonProperty("status")
    private String status;
    @JsonProperty("auth")
    private Auth auth;
    @JsonProperty("data")
    private List<Attribute> data = null;
}

