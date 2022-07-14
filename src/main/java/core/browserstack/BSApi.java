package core.browserstack;

import com.codeborne.selenide.WebDriverRunner;
import com.google.gson.Gson;
import core.ConsoleLogger;
import core.browserstack.endpoints.BSApiRequester;
import core.browserstack.models.build_detail.BuildDetail;
import core.browserstack.models.project_detail.Build;
import core.browserstack.models.project_detail.ProjectDetail;
import core.browserstack.models.projects.Project;
import core.config.Prop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class BSApi {
    private BSApiRequester api;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyyMMdd][yyyy-MM-dd][yyyy-DDD]['T'[HHmmss][HHmm][HH:mm:ss][HH:mm][.SSSSSSSSS][.SSSSSS][.SSS][.SS][.S]][OOOO][O][z][XXXXX][XXXX]['['VV']']");

    public BSApi() {
        this.api = new BSApiRequester();
    }

    public static void main(String[] args)  {
        BSApi bsApi = new BSApi();
        BuildDetail test = bsApi.searchSession("PCM-iPhone 12 Pro Max", "LoginTests", "Samsung Galaxy S9 Plus");
        System.out.println(new Gson().toJson(test));
    }

    private List<Project> getProjects() {
        return api.getProjects()
                .get()
                .execute();
    }

    public ProjectDetail getProjectDetail(Long pId) {
        return api.getProjectDetail()
                .get(pId)
                .execute();
    }

    private List<BuildDetail> getBuildDetail(String bId) {
        return api.getBuildDetail()
                .get(bId)
                .execute();
    }

    public void setNetworkProfile(String profile) {
        api.setNetworkProfile()
                .put(profile, WebDriverRunner.driver().getSessionId())
                .execute();
    }

    private Map<String, String> deviceInfo() {
        String threadName = Thread.currentThread().getName();
        Map<String, String> device = new HashMap<>();
        device.put("device", threadName.substring(0, threadName.lastIndexOf("_V:")));
        device.put("os_version", threadName.substring(threadName.lastIndexOf("_V:")).replace("_V:", ""));
        return device;
    }

    public Project getProject(String projectName) {
        List<Project> projects = this.getProjects();
        projects.sort((s1, s2) -> LocalDateTime.parse(s1.getUpdatedAt(), formatter).
                compareTo(LocalDateTime.parse(s2.getUpdatedAt(), formatter)));
        return projects.stream().filter(p -> p.getName().contains(projectName))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public BuildDetail searchSession(ProjectDetail projectDetail, String testClassName, String deviceName) {
        BuildDetail searchedBuild = null;
        for (Build build : projectDetail.getProject().getBuilds()) {
            try {
                List<BuildDetail> builds = this.getBuildDetail(build.getHashedId());
                builds.sort((s1, s2) -> LocalDateTime.parse(s2.getAutomationSession().getCreatedAt(), formatter).
                        compareTo(LocalDateTime.parse(s1.getAutomationSession().getCreatedAt(), formatter)));

                searchedBuild = builds.stream()
                        .filter(buildDetail -> buildDetail.getAutomationSession().getName().contains(testClassName) &&
                                buildDetail.getAutomationSession().getDevice().equals(deviceName))
                        .findFirst()
                        .orElseThrow(NoSuchElementException::new);
            } catch (NoSuchElementException e) {
                ConsoleLogger.log.error(e.getMessage());
            } finally {
                break;
            }
        }
        return searchedBuild;
    }


    public BuildDetail searchSession(ProjectDetail projectDetail, String deviceName) {
        BuildDetail searchedBuild = null;
        for (Build build : projectDetail.getProject().getBuilds()) {
            try {
                List<BuildDetail> builds = this.getBuildDetail(build.getHashedId());
                builds.sort((s1, s2) -> LocalDateTime.parse(s2.getAutomationSession().getCreatedAt(), formatter).
                        compareTo(LocalDateTime.parse(s1.getAutomationSession().getCreatedAt(), formatter)));

                searchedBuild = builds.stream()
                        .filter(buildDetail -> buildDetail.getAutomationSession().getStatus().equals("running") &&
                                buildDetail.getAutomationSession().getDevice().equals(deviceName))
                        .findFirst()
                        .orElseThrow(NoSuchElementException::new);
            } catch (NoSuchElementException e) {
                ConsoleLogger.log.error(e.getMessage());
            } finally {
                break;
            }
        }
        return searchedBuild;
    }


    public BuildDetail searchSession(String projectName, String testClassName, String deviceName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyyMMdd][yyyy-MM-dd][yyyy-DDD]['T'[HHmmss][HHmm][HH:mm:ss][HH:mm][.SSSSSSSSS][.SSSSSS][.SSS][.SS][.S]][OOOO][O][z][XXXXX][XXXX]['['VV']']");

        List<Project> projects = this.getProjects();
        projects.sort((s1, s2) -> LocalDateTime.parse(s1.getUpdatedAt(), formatter).
                compareTo(LocalDateTime.parse(s2.getUpdatedAt(), formatter)));

        Project project = projects.stream().filter(p -> p.getName().contains(projectName))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        ProjectDetail projectDetail = this.getProjectDetail(project.getId());
        BuildDetail searchedBuild = null;
        for (Build build : projectDetail.getProject().getBuilds()) {
            try {
                List<BuildDetail> builds = this.getBuildDetail(build.getHashedId());
                builds.sort((s1, s2) -> LocalDateTime.parse(s2.getAutomationSession().getCreatedAt(), formatter).
                        compareTo(LocalDateTime.parse(s1.getAutomationSession().getCreatedAt(), formatter)));


                searchedBuild = builds.stream()
                        .filter(buildDetail -> buildDetail.getAutomationSession().getName().contains(testClassName) &&
                                buildDetail.getAutomationSession().getDevice().equals(deviceName))
                        .findFirst()
                        .orElseThrow(NoSuchElementException::new);
            } catch (NoSuchElementException e) {
                ConsoleLogger.log.error(e.getMessage());
                continue;
            } finally {
                break;
            }
        }
        return searchedBuild;
    }

}
