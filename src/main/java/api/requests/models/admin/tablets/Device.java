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
        "relationships",
        "attributes",
        "id",
        "resourceType"
})
public class Device {
    @JsonProperty("relationships")
    private Relationships relationships;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("resourceType")
    private String resourceType;
}
