package api.requests.models.admin.admin_models;

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
        "login",
        "daysactive",
        "fname",
        "mname",
        "lname",
        "email",
        "phone",
        "env",
        "rights",
        "twilio",
        "settings",
        "videotriage"
})
public class ClinicianInfo {
    @JsonProperty("login")
    private String login;
    @JsonProperty("daysactive")
    private String daysactive;
    @JsonProperty("fname")
    private String fname;
    @JsonProperty("mname")
    private String mname;
    @JsonProperty("lname")
    private String lname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("env")
    private String env;
    @JsonProperty("rights")
    private String rights;
    @JsonProperty("twilio")
    private String twilio;
    @JsonProperty("status")
    private String status;
    @JsonProperty("settings")
    private List<Settings> settings = null;
    @JsonProperty("videotriage")
    private String videotriage;
}

