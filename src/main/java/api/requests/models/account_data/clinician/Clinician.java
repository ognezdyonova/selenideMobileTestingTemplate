package api.requests.models.account_data.clinician;

import api.requests.models.account_data.clinician.filters.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        "first_name",
        "last_name",
        "middle_name",
        "email",
        "telephone",
        "risk_email",
        "risk_text",
        "time_zone",
        "session_timeout",
        "clinician_insecure_reports",
        "filter_first_name",
        "filter_last_name",
        "filter_patient_id",
        "filter_tablet",
        "filter_enrolled",
        "filter_status",
        "filter_risk",
        "filter_reviewed",
        "filter_subgroup",
        "filter_clinician",
        "filter_conditions",
        "filter_medications",
        "filter_activity",
        "filter_blood_pressure",
        "filter_pulse_ox",
        "filter_survey",
        "filter_temperature",
        "filter_weight",
        "filter_glucose",
        "filter_imaging",
        "filter_state"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clinician {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middle_name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("risk_email")
    private Boolean riskEmail;
    @JsonProperty("risk_text")
    private Boolean riskText;
    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("session_timeout")
    private String sessionTimeout;
    @JsonProperty("clinician_insecure_reports")
    private Boolean clinicianInsecureReports;
    @JsonProperty("filter_first_name")
    private FilterFirstName filterFirstName;
    @JsonProperty("filter_last_name")
    private FilterLastName filterLastName;
    @JsonProperty("filter_patient_id")
    private FilterPatientId filterPatientId;
    @JsonProperty("filter_tablet")
    private FilterTablet filterTablet;
    @JsonProperty("filter_enrolled")
    private FilterEnrolled filterEnrolled;
    @JsonProperty("filter_status")
    private FilterStatus filterStatus;
    @JsonProperty("filter_risk")
    private FilterRisk filterRisk;
    @JsonProperty("filter_reviewed")
    private FilterReviewed filterReviewed;
    @JsonProperty("filter_subgroup")
    private FilterSubgroup filterSubgroup;
    @JsonProperty("filter_clinician")
    private FilterClinician filterClinician;
    @JsonProperty("filter_conditions")
    private FilterConditions filterConditions;
    @JsonProperty("filter_medications")
    private FilterMedications filterMedications;
    @JsonProperty("filter_activity")
    private FilterActivity filterActivity;
    @JsonProperty("filter_blood_pressure")
    private FilterBloodPressure filterBloodPressure;
    @JsonProperty("filter_pulse_ox")
    private FilterPulseOx filterPulseOx;
    @JsonProperty("filter_survey")
    private FilterSurvey filterSurvey;
    @JsonProperty("filter_temperature")
    private FilterTemperature filterTemperature;
    @JsonProperty("filter_weight")
    private FilterWeight filterWeight;
    @JsonProperty("filter_glucose")
    private FilterGlucose filterGlucose;
    @JsonProperty("filter_imaging")
    private FilterImaging filterImaging;
    @JsonProperty("filter_state")
    private FilterState filterState;
}