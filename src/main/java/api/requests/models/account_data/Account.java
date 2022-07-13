package api.requests.models.account_data;

import api.requests.models.account_data.clinician.deserializer.SubgroupsDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
        "usertype",
        "user",
        "token",
        "env",
        "roles",
        "username",
        "firstname",
        "lastname",
        "exp",
        "time_zone",
        "firebase",
        "subgroup"
})
public class Account {
    @JsonProperty("usertype")
    private String usertype;
    @JsonProperty("user")
    private String user;
    @JsonProperty("token")
    private String token;
    @JsonProperty("env")
    private String env;
    @JsonProperty("roles")
    private List<String> roles = null;
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("exp")
    private Integer exp;
    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("firebase")
    private Firebase firebase;
    @JsonDeserialize(using = SubgroupsDeserializer.class)
    @JsonProperty("subgroup")
    private List<String> subgroup;
}
