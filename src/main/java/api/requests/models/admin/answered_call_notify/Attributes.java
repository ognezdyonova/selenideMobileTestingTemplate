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
        "message",
        "acknowledged",
        "createdAt",
        "updatedAt",
        "acknowledgedAt",
        "deletedAt"
})
public class Attributes {
    @JsonProperty("message")
    private String message;
    @JsonProperty("acknowledged")
    private Boolean acknowledged;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("acknowledgedAt")
    private Object acknowledgedAt;
    @JsonProperty("deletedAt")
    private Object deletedAt;
}

