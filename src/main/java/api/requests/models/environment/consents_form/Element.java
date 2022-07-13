package api.requests.models.environment.consents_form;

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
        "position",
        "top",
        "width",
        "height",
        "src",
        "class",
        "text",
        "left",
        "header",
        "len"
})
public class Element {
    @JsonProperty("type")
    private String type;
    @JsonProperty("position")
    private String position;
    @JsonProperty("header")
    private String header;
    @JsonProperty("top")
    private Integer top;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("left")
    private Integer left;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("src")
    private String src;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("text")
    private String text;
    @JsonProperty("len")
    private Integer len;
}

