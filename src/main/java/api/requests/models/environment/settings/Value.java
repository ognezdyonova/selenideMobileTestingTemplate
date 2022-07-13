package api.requests.models.environment.settings;

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
        "appid",
        "phone",
        "riskdata",
        "content",
        "name",
        "modules",
        "value",
        "pcVoiceEnv",
        "pcVoiceAllPatients",
        "title",
        "subgroups",
        "activity"
})
public class Value<T> {
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("riskdata")
    private List<RiskData> riskdata = null;
    @JsonProperty("content")
    private List<Content> content = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("modules")
    private List<String> modules = null;
    @JsonProperty("value")
    private String value;
    @JsonProperty("pcVoiceEnv")
    private String pcVoiceEnv;
    @JsonProperty("pcVoiceAllPatients")
    private String pcVoiceAllPatients;
    @JsonProperty("activity")
    private Object activity;
    @JsonProperty("title")
    private Object title;
    @JsonProperty("subgroups")
    private Object subgroups;
}

