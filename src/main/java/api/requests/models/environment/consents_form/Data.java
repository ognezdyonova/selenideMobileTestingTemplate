package api.requests.models.environment.consents_form;

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
        "createdAt",
        "content",
        "attributes"
})
public class Data {
    @JsonProperty("id")
    private String id;
    @JsonProperty("resourceType")
    private String resourceType;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("content")
    private Content content;
    @JsonProperty("attributes")
    private Attributes attributes;
}

