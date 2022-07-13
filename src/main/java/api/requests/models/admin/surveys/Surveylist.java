package api.requests.models.admin.surveys;

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
        "status",
        "category",
        "question",
        "answertype",
        "schedule",
        "medalert",
        "highalert",
        "subgroups",
        "translations",
        "exp",
        "x"
})
public class Surveylist {
    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("category")
    private String category;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answertype")
    private String answertype;
    @JsonProperty("schedule")
    private Schedule schedule;
    @JsonProperty("medalert")
    private String medalert;
    @JsonProperty("highalert")
    private String highalert;
    @JsonProperty("x")
    private String x;
    @JsonProperty("exp")
    private String exp;
    @JsonProperty("subgroups")
    private List<String> subgroups = null;
    @JsonProperty("translations")
    private List<Translation> translations = null;
}

