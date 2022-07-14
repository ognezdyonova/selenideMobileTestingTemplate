package core.api_requester.core.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.ConsoleLogger;
import core.Logger;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookies;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.io.output.WriterOutputStream;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public abstract class Requester<T> {
    private final Method method;
    private T t;
    private Class<? extends T> responseClass;
    private final TypeRef<? extends T> responseType;
    private static final ObjectMapper JSON = new ObjectMapper()
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    private RequestSpecification REQUEST_SPEC;
    private ResponseSpecification RESPONSE_SPEC;
    private ResponseSpecBuilder RESPONSE_BUILDER;
    private String authToken = null;
    private Integer waitRequestSec = null;
    private static Set childrenMethod = null;
    private Object customBodyData = null;
    private Cookies customCookiesData = null;
    private Boolean customMapperClass = false;
    private Map<String, Object> customFormParams;

    private Requester(@NonNull Method method, Class<? extends T> responseClass, TypeRef<? extends T> responseType) {
        this.method = method;
        this.responseClass = responseClass;
        this.responseType = responseType;
        childrenMethod = new HashSet();
        childrenMethod.add(this);
    }

    public Requester(@NonNull Method method, @NonNull Class<? extends T> responseClass) {
        this(method, responseClass, null);
    }

    public Requester(@NonNull Method method, @NonNull TypeRef<? extends T> responseTypes) {
        this(method, null, responseTypes);
    }

    @SneakyThrows
    public T execute() {
        if (REQUEST_SPEC == null) REQUEST_SPEC = requestSpecification();

        if (Logger.isDebug()) REQUEST_SPEC.log().all();

        StringWriter requestWriter = new StringWriter();
        PrintStream requestCupture = new PrintStream(new WriterOutputStream(requestWriter, Charset.defaultCharset()), true);
        StringWriter responseWriter = new StringWriter();
        PrintStream responseCupture = new PrintStream(new WriterOutputStream(responseWriter, Charset.defaultCharset()), true);

        REQUEST_SPEC.filters(new RequestLoggingFilter(requestCupture), new ResponseLoggingFilter(responseCupture));

        if (this.customBodyData != null)
            REQUEST_SPEC.body(this.customBodyData).relaxedHTTPSValidation();
        else if (body() != null)
            REQUEST_SPEC.body(body()).relaxedHTTPSValidation();
        else if (this.customFormParams != null)
            REQUEST_SPEC.formParams(this.customFormParams).relaxedHTTPSValidation();
        else if (formParams() != null)
            REQUEST_SPEC.formParams(formParams()).relaxedHTTPSValidation();
        else if (multiPart() != null)
            REQUEST_SPEC.multiPart("file", multiPart()).relaxedHTTPSValidation();

        if (this.authToken != null)
            REQUEST_SPEC.header("Authorization", "Bearer ".concat(this.authToken));

        if (this.customCookiesData != null)
            REQUEST_SPEC.cookies(this.customCookiesData);

        Response response = given().spec(REQUEST_SPEC).request(method);

        if (Logger.isDebug()) response.then().log().all();
        String caseName = new Exception().getStackTrace()[1].getMethodName();
        Object childMethod = childrenMethod.iterator().next();
        if (isTestsCase(new Exception().getStackTrace()[1].getClassName(), caseName)) {
            String nameSubMethodNew = "";
            for (java.lang.reflect.Method declaredMethod : childMethod.getClass().getEnclosingClass().getMethods()) {
                if (declaredMethod.getGenericReturnType().getTypeName().equals(childMethod.getClass().getName())) {
                    nameSubMethodNew = Class.forName(
                                    new Exception().getStackTrace()[1].getClassName()).getSimpleName().concat(".")
                            .concat(new Exception().getStackTrace()[1].getMethodName().concat(".")
                                    .concat(declaredMethod.getName()));
                    Logger.setTimeStamp(nameSubMethodNew, response.getTimeIn(TimeUnit.MILLISECONDS));
                    setFilteredData(requestWriter, responseWriter, nameSubMethodNew);
                    break;
                }
            }
        }

        if (RESPONSE_SPEC == null) RESPONSE_SPEC = responseSpecification();

        response.then().spec(RESPONSE_SPEC);
        requestCupture.close();
        responseCupture.close();

        if (this.waitRequestSec != null) {
            checkArgument(this.waitRequestSec > 0, "Timeout must be greater than zero");
            ConsoleLogger.log.info("Using timeout for request as:" + this.waitRequestSec + " seconds");
            Thread.sleep(this.waitRequestSec * 1000);
        }

        if (responseClass != null) {
            if (responseClass == Void.class) {
                return null;
            } else if (responseClass == Response.class) {
                return (T) response;
            }
            return response.as(responseClass);
        } else {
            return response.as(responseType);
        }
    }

    @SneakyThrows
    public Object execute(Class<?> responseClass) {
        if (REQUEST_SPEC == null) REQUEST_SPEC = requestSpecification();

        if (Logger.isDebug()) REQUEST_SPEC.log().all();

        StringWriter requestWriter = new StringWriter();
        PrintStream requestCupture = new PrintStream(new WriterOutputStream(requestWriter, Charset.defaultCharset()), true);
        StringWriter responseWriter = new StringWriter();
        PrintStream responseCupture = new PrintStream(new WriterOutputStream(responseWriter, Charset.defaultCharset()), true);

        REQUEST_SPEC.filters(new RequestLoggingFilter(requestCupture), new ResponseLoggingFilter(responseCupture));

        if (this.customBodyData != null)
            REQUEST_SPEC.body(this.customBodyData).relaxedHTTPSValidation();
        else if (body() != null)
            REQUEST_SPEC.body(body()).relaxedHTTPSValidation();
        else if (this.customFormParams != null)
            REQUEST_SPEC.formParams(this.customFormParams).relaxedHTTPSValidation();
        else if (formParams() != null)
            REQUEST_SPEC.formParams(formParams()).relaxedHTTPSValidation();
        else if (multiPart() != null)
            REQUEST_SPEC.multiPart("file", multiPart()).relaxedHTTPSValidation();

        if (this.authToken != null)
            REQUEST_SPEC.header("Authorization", "Bearer ".concat(this.authToken));

        if (this.customCookiesData != null)
            REQUEST_SPEC.cookies(this.customCookiesData);

        Response response = given().spec(REQUEST_SPEC).request(method);

        if (Logger.isDebug()) response.then().log().all();
        String caseName = new Exception().getStackTrace()[1].getMethodName();
        Object childMethod = childrenMethod.iterator().next();
        if (isTestsCase(new Exception().getStackTrace()[1].getClassName(), caseName)) {
            String nameSubMethodNew = "";
            for (java.lang.reflect.Method declaredMethod : childMethod.getClass().getEnclosingClass().getMethods()) {
                if (declaredMethod.getGenericReturnType().getTypeName().equals(childMethod.getClass().getName())) {
                    nameSubMethodNew = new Exception().getStackTrace()[1].getClassName().concat(".")
                            .concat(new Exception().getStackTrace()[1].getMethodName().concat(".")
                                    .concat(declaredMethod.getName()));
                    Logger.setTimeStamp(nameSubMethodNew, response.getTimeIn(TimeUnit.MILLISECONDS));
                    setFilteredData(requestWriter, responseWriter, nameSubMethodNew);
                    break;
                }
            }
        }

        if (RESPONSE_SPEC == null) RESPONSE_SPEC = responseSpecification();

        response.then().spec(RESPONSE_SPEC);

        requestCupture.close();
        responseCupture.close();

        if (this.waitRequestSec != null) {
            checkArgument(this.waitRequestSec > 0, "Timeout must be greater than zero");
            ConsoleLogger.log.info("Using timeout for request as:" + this.waitRequestSec + " seconds");
            Thread.sleep(this.waitRequestSec * 1000);
        }

        if (customMapperClass) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.getBody().asString(), responseClass);
        } else if (responseClass != null) {
            if (responseClass == Void.class) {
                return null;
            } else if (responseClass == Response.class) {
                return (T) response;
            }
            return response.as(responseClass);
        } else {
            throw new RuntimeException("Class for mapping should not be null");
        }
    }

    public Object body() {
        return null;
    }

    public Requester<T> customBody(Object customBodyData) {
        this.customBodyData = customBodyData;
        return this;
    }

    public Requester<T> formParams(Map<String, Object> customFormParams) {
        this.customFormParams = customFormParams;
        return this;
    }

    public Map<String, ?> formParams() {
        return null;
    }


    public Requester<T> useMapper() {
        this.customMapperClass = true;
        return this;
    }

    public File multiPart() {
        return null;
    }

    public RequestSpecification requestSpecification() {
        return null;
    }

    public ResponseSpecification responseSpecification() {
        RESPONSE_BUILDER = new ResponseSpecBuilder();
        RESPONSE_BUILDER.expectStatusCode(Matchers.anyOf(
                Matchers.in(Arrays.asList(200, 201, 202, 203, 204, 205, 206))
        ));
        RESPONSE_BUILDER.registerParser("text/html", Parser.JSON);
        RESPONSE_BUILDER.expectResponseTime(lessThan(300L), TimeUnit.SECONDS);
        return RESPONSE_BUILDER.build();
    }

    /**
     * Set up new token for request
     *
     * @param token user token
     * @return Requester<T>
     */
    public Requester<T> authToken(String token) {
        this.authToken = token;
        return this;
    }

    public Requester<T> authToken(Cookies cookies) {
        this.customCookiesData = cookies;
        return this;
    }

    /**
     * @param sec - the number of seconds to wait after executing the request
     */
    public Requester<T> waitAfterRequest(Integer sec) {
        this.waitRequestSec = sec;
        return this;
    }

    public Requester<T> configuration(RequestSpecification REQUEST_SPECIFICATION, ResponseSpecification RESPONSE_SPECIFICATION) {
        REQUEST_SPEC = REQUEST_SPECIFICATION;
        RESPONSE_SPEC = RESPONSE_SPECIFICATION;
        return this;
    }

    private void setFilteredData(StringWriter requestWriter, StringWriter responseWriter, String name) {
        Map<String, StringWriter> dataMap = new HashMap<>();
        dataMap.put("requests", requestWriter);
        dataMap.put("responses", responseWriter);
        Logger.setRecSpecMap(name, dataMap);
    }

    private Boolean isTestsCase(String className, String methodName) {
        boolean state = false;
        try {
            Class c = Class.forName(className);
            Object instance = c.newInstance();
            for (java.lang.reflect.Method declaredMethod : instance.getClass().getDeclaredMethods()) {

                if (declaredMethod.getAnnotation(Test.class) != null) {
                    if (declaredMethod.getName().equals(methodName)) {
                        state = true;
                        break;
                    }
                }
            }
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }

        return state;
    }
}
