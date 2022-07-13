package api.requests.models.account_data.clinician.filters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.preject_name.models.account_data.clinician.deserializer.ColumnFilterJsonDeserializer;
import org.preject_name.models.account_data.clinician.deserializer.SortJsonDeserializer;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "visible",
        "width",
        "sort",
        "filters"
})
public class Column {
    @JsonProperty("name")
    private String name;
    @JsonProperty("visible")
    private Boolean visible;
    @JsonProperty("width")
    private String width;
    @JsonProperty("sort")
    @JsonDeserialize(using = SortJsonDeserializer.class)
    private Sort sort = null;
    @JsonDeserialize(using = ColumnFilterJsonDeserializer.class)
    @JsonProperty("filters")
    private List<FilterInfo> filters = null;
}
