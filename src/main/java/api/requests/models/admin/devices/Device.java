package api.requests.models.admin.devices;

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
        "devid",
        "secondary",
        "regid",
        "db",
        "platform"
})
public class Device {
    @JsonProperty("devid")
    private String devid;
    @JsonProperty("secondary")
    private String secondary;
    @JsonProperty("regid")
    private String regid;
    @JsonProperty("db")
    private String db;
    @JsonProperty("platform")
    private String platform;
}

