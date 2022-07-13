package api.requests.models.account_data.clinician.filters;

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
        "columns",
        "scrollFocus",
        "selection",
        "grouping",
        "treeView",
        "pagination"
})
public class FilterState {
    @JsonProperty("columns")
    private List<Column> columns;
    @JsonProperty("scrollFocus")
    private List<Object> scrollFocus;
    @JsonProperty("selection")
    private List<Object> selection;
    @JsonProperty("grouping")
    private List<Object> grouping;
    @JsonProperty("treeView")
    private List<Object> treeView;
    @JsonProperty("pagination")
    private List<Object> pagination;
}
