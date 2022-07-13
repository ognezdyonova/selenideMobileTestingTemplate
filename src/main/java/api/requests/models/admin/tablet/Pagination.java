package api.requests.models.admin.tablet;

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
        "total",
        "size",
        "totalPages",
        "page"
})
public class Pagination {
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("totalPages")
    private Integer totalPages;
    @JsonProperty("page")
    private Integer page;
}

