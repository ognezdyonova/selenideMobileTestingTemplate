package api.requests.models.environment.envr_data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.ArrayList;

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
        "required",
        "patientsetup",
        "class",
        "emr_tracked",
        "should_calculate_date_difference",
        "last_updated",
        "choices"
})
public class PatientCustomAttributes {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("format")
    private String format;
    @JsonProperty("type")
    private String type;
    @JsonProperty("required")
    private Boolean required;
    @JsonProperty("patientsetup")
    private Boolean patientsetup;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("emr_tracked")
    private Boolean emrTracked;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("choices")
    private ArrayList<Object> choices;
    @JsonProperty("should_calculate_date_difference")
    private Boolean shouldCalculateDateDifference;
}

