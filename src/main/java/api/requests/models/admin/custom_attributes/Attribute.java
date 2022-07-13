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
        "id",
        "name",
        "format",
        "type",
        "setup",
        "required",
        "class",
        "emrTracked",
        "shouldCalculateDateDifference",
        "choices"
})
public class Attribute {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("format")
    private String format;
    @JsonProperty("type")
    private String type;
    @JsonProperty("setup")
    private String setup;
    @JsonProperty("required")
    private String required;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("emrTracked")
    private Boolean emrTracked;
    @JsonProperty("shouldCalculateDateDifference")
    private Boolean shouldCalculateDateDifference;
    @JsonProperty("choices")
    private List<Choice> choices = null;
}

