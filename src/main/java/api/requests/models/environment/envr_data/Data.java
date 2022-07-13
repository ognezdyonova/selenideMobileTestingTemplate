package api.requests.models.environment.envr_data;

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
        "duration",
        "diastolic",
        "systolic",
        "heartrate",
        "glucose",
        "dose",
        "med",
        "spo2",
        "answer",
        "question",
        "temperature",
        "weight",
        "images",
        "files",
        "count"
})
public class Data {
    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("diastolic")
    private Diastolic diastolic;
    @JsonProperty("systolic")
    private Systolic systolic;
    @JsonProperty("heartrate")
    private HeartRate heartrate;
    @JsonProperty("glucose")
    private Glucose glucose;
    @JsonProperty("dose")
    private Dose dose;
    @JsonProperty("med")
    private Med med;
    @JsonProperty("spo2")
    private Spo2 spo2;
    @JsonProperty("answer")
    private Answer answer;
    @JsonProperty("question")
    private Question question;
    @JsonProperty("temperature")
    private Temperature temperature;
    @JsonProperty("weight")
    private Weight weight;
    @JsonProperty("images")
    private Images images;
    @JsonProperty("files")
    private Files files;
    @JsonProperty("count")
    private Count count;
}

