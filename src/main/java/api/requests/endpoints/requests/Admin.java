package api.requests.endpoints.requests;

import api.requests.constants.EndPoints;
import api.requests.constants.Path;
import api.requests.models.admin.surveys_types.Type;
import api.requests.models.admin.surveys_types.Value;
import core.api_requester.core.rest.Requester;
import core.api_requester.schemes.AuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.NonNull;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Olga Gnezdyonova
 */
public class Admin {

    public GetEnvironmentSettings getEnvironmentSettings() {
        return new GetEnvironmentSettings();
    }

    /**
     * @param flagName flag name
     * @return AddEnvironmentSetting
     */
    public AddEnvironmentSetting addEnvironmentSetting(@NonNull String flagName) {
        return new AddEnvironmentSetting(flagName);
    }

    /**
     * @return AddEnvironmentSetting
     */
    public AddEnvironmentSetting addEnvironmentSetting() {
        return new AddEnvironmentSetting();
    }

    /**
     * @param patientId patient id for deactivation
     * @return ChangePatientStatus
     */
    public ChangePatientStatus changePatientStatus(String patientId) {
        return new ChangePatientStatus(patientId);
    }

    /**
     * @param globalSurveyQuestionType type of survey question
     * @param typePosition             array position [0, 1, 2, ..., n]
     * @return EditGlobalSurveyQuestionType
     */
    public EditGlobalSurveyQuestionType editGlobalSurveyQuestionType(Type globalSurveyQuestionType, Integer typePosition) {
        return new EditGlobalSurveyQuestionType(globalSurveyQuestionType, typePosition);
    }

    /**
     * @param globalSurveyQuestionType type of survey question
     * @param typePosition             array position [0, 1, 2, ..., n]
     * @param keyword                  some string
     * @return EditGlobalSurveyQuestionType
     */
    public EditGlobalSurveyQuestionType editGlobalSurveyQuestionType(Type globalSurveyQuestionType, Integer typePosition, String keyword) {
        return new EditGlobalSurveyQuestionType(globalSurveyQuestionType, typePosition, keyword);
    }

    /**
     * @return GetGlobalSurveyQuestionTypes
     */
    public GetGlobalSurveyQuestionTypes getGlobalSurveyQuestionTypes() {
        return new GetGlobalSurveyQuestionTypes();
    }

    /**
     * @param clinician model for new clinician
     * @return AddEnvironmentClinician
     */
    public AddEnvironmentClinician addEnvironmentClinician(Map<String, Object> clinician) {
        return new AddEnvironmentClinician(clinician);
    }

    /**
     * @return GetSurveyList
     */
    public GetSurveyList getSurveyList() {
        return new GetSurveyList();
    }

    /**
     * @param device object for register new device
     * @return RegisterDevice
     */
    public RegisterDevice registerDevice(Map<String, Object> device) {
        return new RegisterDevice(device);
    }

    /**
     * @param surveyType type of survey question
     * @return AddGlobalSurveyQuestionType
     */
    public AddGlobalSurveyQuestionType addGlobalSurveyQuestionType(@NonNull String surveyType) {
        return new AddGlobalSurveyQuestionType(surveyType);
    }

    public static class GetEnvironmentSettings extends Requester<Response> {
        public GetEnvironmentSettings() {
            super(Method.POST, Response.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2.concat(EndPoints.DATA))
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            return data;
        }
    }

    public static class RemoveEnvironmentSetting extends Requester<Void> {
        private final String flagId;

        public RemoveEnvironmentSetting(@NonNull String flagId) {
            super(Method.POST, Void.class);
            this.flagId = flagId;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2.concat(EndPoints.DATA))
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            return data;
        }
    }

    public static class AddEnvironmentSetting extends Requester<Void> {
        private String flag = null;

        public AddEnvironmentSetting(@NonNull String flag) {
            super(Method.POST, Void.class);
            this.flag = flag;
        }

        public AddEnvironmentSetting() {
            super(Method.POST, Void.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2.concat(EndPoints.DATA))
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            return data;
        }
    }

    public static class ChangePatientStatus extends Requester<Void> {
        private String patientId = null;

        public ChangePatientStatus(@NonNull String patientId) {
            super(Method.POST, Void.class);
            this.patientId = patientId;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2.concat(EndPoints.DATA))
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            return data;
        }
    }

    public static class AddGlobalSurveyQuestionType extends Requester<Response> {
        private final String surveyType;

        public AddGlobalSurveyQuestionType(@NonNull String surveyType) {
            super(Method.POST, Response.class);
            this.surveyType = surveyType;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2
                            .concat(EndPoints.DATA)
                    )
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            data.put("best", "2");
            data.put("worst", "2");
            data.put("type", this.surveyType);

            ArrayList<Value> values = new ArrayList<>();
            Value value = new Value();
            value.setLang("eng");

            ArrayList<String> list = new ArrayList<>();
            list.add("first answer");
            list.add("second answer");
            value.setText(list);
            values.add(value);

            data.put("values", values.toString());

            Map<String, Object> formData = new HashMap<>();
            formData.put("request", "addglobalsurveyquestiontype");
            formData.put("data", new JSONObject(data).toString());
            System.out.println("FORM:" + formData);
            return formData;
        }
    }

    public static class EditGlobalSurveyQuestionType extends Requester<Response> {
        private Integer point = null;
        private String keyword = null;
        private Type surveyType = null;

        public EditGlobalSurveyQuestionType(@NonNull Type surveyType, @NonNull Integer point) {
            super(Method.POST, Response.class);
            this.surveyType = surveyType;
            this.point = point;
        }

        public EditGlobalSurveyQuestionType(@NonNull Type surveyType, @NonNull Integer point, @NonNull String keyword) {
            super(Method.POST, Response.class);
            this.surveyType = surveyType;
            this.keyword = keyword;
            this.point = point;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2
                            .concat(EndPoints.DATA)
                    )
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            return data;
        }
    }

    public static class GetGlobalSurveyQuestionTypes extends Requester<Response> {

        public GetGlobalSurveyQuestionTypes() {
            super(Method.POST, Response.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2
                            .concat(EndPoints.DATA)
                    )
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            data.put("request", "getglobalsurveyquestiontypes");
            return data;
        }
    }

    public static class GetSurveyList extends Requester<Response> {

        public GetSurveyList() {
            super(Method.POST, Response.class);
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2
                            .concat(EndPoints.DATA)
                    )
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();
            return data;
        }
    }

    public static class AddEnvironmentClinician extends Requester<Response> {
        private Map<String, Object> clinician;

        public AddEnvironmentClinician(@NonNull Map<String, Object> clinician) {
            super(Method.POST, Response.class);
            this.clinician = clinician;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2
                            .concat(EndPoints.DATA)
                    )
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();

            return Stream.concat(data.entrySet().stream(), this.clinician.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }

    public static class RegisterDevice extends Requester<Response> {
        private Map<String, Object> device;

        public RegisterDevice(@NonNull Map<String, Object> device) {
            super(Method.POST, Response.class);
            this.device = device;
        }

        @Override
        public RequestSpecification requestSpecification() {
            return new RequestSpecBuilder()
                    .setBaseUri(Path.BASE_URI)
                    .setBasePath(Path.API_ADMIN_V2
                            .concat(EndPoints.DATA)
                    )
                    .setContentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                    .addHeader("authorization", AuthScheme.basicScheme("admin", "LaCroix").generateAuthToken())
                    .build();
        }

        @Override
        public ResponseSpecification responseSpecification() {
            return new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.HTML)
                    .registerParser("text/html", Parser.JSON)
                    .build();
        }

        @Override
        public Map<String, Object> formParams() {
            Map<String, Object> data = new HashMap<>();

            return Stream.concat(data.entrySet().stream(), this.device.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }
}
