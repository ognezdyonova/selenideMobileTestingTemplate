package api.requests.models.admin.edit_survey;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SurveysTypes {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String best;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String worst;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Value> values = null;
}
