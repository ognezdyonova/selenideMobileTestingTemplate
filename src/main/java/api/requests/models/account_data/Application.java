package api.requests.models.account_data;

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
        "apiKey",
        "authDomain",
        "databaseURL",
        "projectId",
        "storageBucket",
        "messagingSenderId",
        "messaging_server_key"
})
public class Application {
    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("authDomain")
    private String authDomain;
    @JsonProperty("databaseURL")
    private String databaseURL;
    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("storageBucket")
    private String storageBucket;
    @JsonProperty("messagingSenderId")
    private String messagingSenderId;
    @JsonProperty("messaging_server_key")
    private String messaging_server_key;
}

