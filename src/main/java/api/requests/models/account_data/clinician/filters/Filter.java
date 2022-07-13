package api.requests.models.account_data.clinician.filters;

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
        "visible",
        "sort",
        "search"
})
public class Filter<T> {
    @JsonProperty("visible")
    private Boolean visible;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("search")
    private T search = null;
}
