package api.requests.models.admin.surveys;

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
        "type",
        "schedule",
        "x",
        "startday",
        "exp"
})
public class Schedule {
    @JsonProperty("type")
    private String type;
    @JsonProperty("schedule")
    private String schedule;
    @JsonProperty("x")
    private String x;
    @JsonProperty("startday")
    private String startday;
    @JsonProperty("exp")
    private String exp;
}
