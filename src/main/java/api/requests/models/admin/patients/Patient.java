package api.requests.models.admin.patients;

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
        "hrsid",
        "pid",
        "emrid",
        "ssn",
        "fname",
        "lname",
        "status"
})
public class Patient {
    @JsonProperty("hrsid")
    private String hrsid;
    @JsonProperty("pid")
    private String pid;
    @JsonProperty("emrid")
    private String emrid;
    @JsonProperty("ssn")
    private String ssn;
    @JsonProperty("fname")
    private String fname;
    @JsonProperty("lname")
    private String lname;
    @JsonProperty("status")
    private String status;
}
