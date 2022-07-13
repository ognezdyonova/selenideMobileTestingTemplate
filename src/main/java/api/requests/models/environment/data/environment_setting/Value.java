package api.requests.models.environment.data.environment_setting;

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
        "riskdata",
        "phone",
        "appid"
})
public class Value {
    @JsonProperty("riskdata")
    private List<RiskData> riskdata = null;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("appid")
    private String appid;
}

