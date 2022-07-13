package api.requests.models.admin.answered_call_notify;

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
        "resourceType",
        "attributes",
        "relationships"
})
public class Data {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("resourceType")
    private String resourceType;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("relationships")
    private Relationships relationships;
}

