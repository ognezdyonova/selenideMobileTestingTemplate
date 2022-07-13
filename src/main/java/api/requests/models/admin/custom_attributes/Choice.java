package api.requests.models.admin.custom_attributes;

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
        "patient_attribute_id",
        "choice"
})
public class Choice {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("patient_attribute_id")
    private Long patientAttributeId;
    @JsonProperty("choice")
    private String choice;
}

