package api.requests.models.account_data.clinician.filters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.preject_name.models.account_data.clinician.deserializer.TermJsonDeserializer;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "term",
        "options"
})
public class FilterInfo {
    @JsonDeserialize(using = TermJsonDeserializer.class)
    @JsonProperty("term")
    private List<Object> term = null;
    @JsonProperty("options")
    private List<Option> options = null;
}
