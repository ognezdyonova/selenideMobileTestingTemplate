//package core.browserstack;
//
//import com.codeborne.selenide.WebDriverRunner;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import core.ConsoleLogger;
//import core.browserstack.models.build_detail.BuildDetail;
//import core.browserstack.models.project_detail.Build;
//import core.browserstack.models.project_detail.ProjectDetail;
//import core.browserstack.models.projects.Project;
//import core.config.Prop;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//public class BSApiOld {
//    private static RequestSpecification reqSpec;
//    private static ResponseSpecification resSpec;
//    private static RestUtilities restUtilities;
//    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyyMMdd][yyyy-MM-dd][yyyy-DDD]['T'[HHmmss][HHmm][HH:mm:ss][HH:mm][.SSSSSSSSS][.SSSSSS][.SSS][.SS][.S]][OOOO][O][z][XXXXX][XXXX]['['VV']']");
//
////    public static void main(String[] args) throws IOException {
////        BSApi bsApi = new BSApi();
////        BuildDetail test = bsApi.searchSession("PCM-iPhone 12 Pro Max", "LoginTests", "Samsung Galaxy S9 Plus");
////        System.out.println(new Gson().toJson(test));
////    }
//
//    private BSApi configureRequests() {
//        restUtilities = new RestUtilities();
//        restUtilities.setBaseUri(Prop.getField("bs.api.base.url"));
//        restUtilities.setContentType(ContentType.JSON);
//        reqSpec = restUtilities.getRequestSpecification();
//        resSpec = restUtilities.getResponseSpecification();
//        reqSpec.basePath(Prop.getField("bs.api.base.path"));
//        reqSpec.header("Authorization", AuthScheme.basicScheme(
//                Prop.getField("bs.username"),
//                Prop.getField("bs.accessKey")).generateAuthToken());
//        return this;
//    }
//
//    private List<Project> getProjects() {
//        restUtilities.setContentType(ContentType.JSON);
//        restUtilities.setEndPoint("/projects.json");
//        Response response = restUtilities.getResponse(reqSpec, "get");
//        List<Project> projects = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            projects = objectMapper.readValue(response.getBody().asString(), new TypeReference<List<Project>>() {
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return projects;
//    }
//
//    public ProjectDetail getProjectDetail(Long pId) {
//        restUtilities.setContentType(ContentType.JSON);
//        restUtilities.setEndPoint("/projects/" + pId + ".json");
//        Response response = restUtilities.getResponse(reqSpec, "get");
//        ProjectDetail project = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            project = objectMapper.readValue(response.getBody().asString(), ProjectDetail.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return project;
//    }
//
//    private List<BuildDetail> getBuildDetail(String bId) {
//        restUtilities.setContentType(ContentType.JSON);
//        restUtilities.setEndPoint("/builds/" + bId + "/sessions.json");
//        Response response = restUtilities.getResponse(reqSpec, "get");
//        List<BuildDetail> buildDetails = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            buildDetails = objectMapper.readValue(response.getBody().asString(), new TypeReference<List<BuildDetail>>() {
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return buildDetails;
//    }
//
//    public void setNetworkProfile(String profile) {
//        ConsoleLogger.log.info("Set up network profile for BS session "
//                .concat(WebDriverRunner.driver().getSessionId().toString())
//                .concat(" as ").concat(profile));
//
//        RestUtilities restUtilities = new RestUtilities();
//        restUtilities.setBaseUri(Prop.getField("bs.api.cloud.base.url"));
//        restUtilities.setContentType(ContentType.JSON);
//        RequestSpecification reqSpec = restUtilities.getRequestSpecification();
//        ResponseSpecification resSpec = restUtilities.getResponseSpecification();
//        reqSpec.basePath(Prop.getField("bs.api.base.path"));
//        reqSpec.header("Authorization", AuthScheme.basicScheme(
//                Prop.getField("bs.username"),
//                Prop.getField("bs.accessKey")).generateAuthToken());
//        restUtilities.setContentType(ContentType.JSON);
//        Map<String, String> body = new HashMap<>();
//        body.put("networkProfile", profile);
//
//        reqSpec.body(body);
//        restUtilities.setEndPoint("/sessions/" + WebDriverRunner.driver().getSessionId() + "/update_network.json");
//        Response response = restUtilities.getResponse(reqSpec, "put");
//    }
//
//    private Map<String, String> deviceInfo() {
//        String threadName = Thread.currentThread().getName();
//        Map<String, String> device = new HashMap<>();
//        device.put("device", threadName.substring(0, threadName.lastIndexOf("_V:")));
//        device.put("os_version", threadName.substring(threadName.lastIndexOf("_V:")).replace("_V:", ""));
//        return device;
//    }
//
//    public Project getProject(String projectName) {
//        List<Project> projects = configureRequests().getProjects();
//        projects.sort((s1, s2) -> LocalDateTime.parse(s1.getUpdatedAt(), formatter).
//                compareTo(LocalDateTime.parse(s2.getUpdatedAt(), formatter)));
//        return projects.stream().filter(p -> p.getName().contains(projectName))
//                .findFirst()
//                .orElseThrow(RuntimeException::new);
//    }
//
//    public BuildDetail searchSession(ProjectDetail projectDetail, String testClassName, String deviceName) {
//        BuildDetail searchedBuild = null;
//        for (Build build : projectDetail.getProject().getBuilds()) {
//            try {
//                List<BuildDetail> builds = configureRequests().getBuildDetail(build.getHashedId());
//                builds.sort((s1, s2) -> LocalDateTime.parse(s2.getAutomationSession().getCreatedAt(), formatter).
//                        compareTo(LocalDateTime.parse(s1.getAutomationSession().getCreatedAt(), formatter)));
//
//                searchedBuild = builds.stream()
//                        .filter(buildDetail -> buildDetail.getAutomationSession().getName().contains(testClassName) &&
//                                buildDetail.getAutomationSession().getDevice().equals(deviceName))
//                        .findFirst().get();
//            } catch (NoSuchElementException ignored) {
//            } finally {
//                break;
//            }
//        }
//        return searchedBuild;
//    }
//
//    public BuildDetail searchSession(ProjectDetail projectDetail, String deviceName) {
//        BuildDetail searchedBuild = null;
//        for (Build build : projectDetail.getProject().getBuilds()) {
//            try {
//                List<BuildDetail> builds = configureRequests().getBuildDetail(build.getHashedId());
//                builds.sort((s1, s2) -> LocalDateTime.parse(s2.getAutomationSession().getCreatedAt(), formatter).
//                        compareTo(LocalDateTime.parse(s1.getAutomationSession().getCreatedAt(), formatter)));
//
//                searchedBuild = builds.stream()
//                        .filter(buildDetail -> buildDetail.getAutomationSession().getStatus().equals("running") &&
//                                buildDetail.getAutomationSession().getDevice().equals(deviceName))
//                        .findFirst().get();
//            } catch (NoSuchElementException ignored) {
//            } finally {
//                break;
//            }
//        }
//        return searchedBuild;
//    }
//
//
//    public BuildDetail searchSession(String projectName, String testClassName, String deviceName) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyyMMdd][yyyy-MM-dd][yyyy-DDD]['T'[HHmmss][HHmm][HH:mm:ss][HH:mm][.SSSSSSSSS][.SSSSSS][.SSS][.SS][.S]][OOOO][O][z][XXXXX][XXXX]['['VV']']");
//
//        List<Project> projects = configureRequests().getProjects();
//        projects.sort((s1, s2) -> LocalDateTime.parse(s1.getUpdatedAt(), formatter).
//                compareTo(LocalDateTime.parse(s2.getUpdatedAt(), formatter)));
//
//        Project project = projects.stream().filter(p -> p.getName().contains(projectName))
//                .findFirst()
//                .orElseThrow(RuntimeException::new);
//
//        ProjectDetail projectDetail = configureRequests().getProjectDetail(project.getId());
//        BuildDetail searchedBuild = null;
//        for (Build build : projectDetail.getProject().getBuilds()) {
//            try {
//
//
//                List<BuildDetail> builds = configureRequests().getBuildDetail(build.getHashedId());
//                builds.sort((s1, s2) -> LocalDateTime.parse(s2.getAutomationSession().getCreatedAt(), formatter).
//                        compareTo(LocalDateTime.parse(s1.getAutomationSession().getCreatedAt(), formatter)));
//
//
//                searchedBuild = builds.stream()
//                        .filter(buildDetail -> buildDetail.getAutomationSession().getName().contains(testClassName) &&
//                                buildDetail.getAutomationSession().getDevice().equals(deviceName))
//                        .findFirst().get();
//            } catch (NoSuchElementException e) {
//                continue;
//            } finally {
//                break;
//            }
//        }
//        return searchedBuild;
//    }
//}
