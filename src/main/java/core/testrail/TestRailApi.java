package core.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.ConsoleLogger;
import core.browserstack.BSApi;
import core.browserstack.models.build_detail.BuildDetail;
import core.browserstack.models.project_detail.ProjectDetail;
import core.config.Prop;
import core.constants.DevicesBS;
import core.testrail.testrail.APIClient;
import core.testrail.testrail.APIException;
import me.tongfei.progressbar.ProgressBar;
import org.testng.ISuite;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestRailApi {
    final static String HOST = Prop.getField("tr.host");
    final static String USER_NAME = Prop.getField("tr.username");
    final static String PASSWORD = Prop.getField("tr.password");
    public Suite dataSuit;
    public Project dataProject;
    public Run dataRun;
    private TestRail testRail;
    private static List<CaseField> customCaseFields;
    private static List<String> testMethods = new ArrayList<>();
    private static Run run;
    private static List<ResultField> customResultFields;
    private static Integer projectId;
    private static Integer suiteId;
    private List<Case> caseList;
    private static String pId;

    public TestRailApi(String appName, String suiteName) {
        connectionToApi(appName);
        List<Project> prj = testRail.projects().list().execute();
        Project project = prj.stream().filter(p -> p.getName().equals(appName)).collect(Collectors.toList()).get(0);
        dataProject = project;
        projectId = project.getId();
        ConsoleLogger.log.info("Set data to project by id: " + projectId + "(" + project.getName() + ")\n");
        Suite suite = testRail.suites().list(projectId).execute().stream().filter(s -> s.getName().equals(suiteName)).collect(Collectors.toList()).get(0);
        suiteId = suite.getId();
        dataSuit = suite;
    }

    public void connectionToApi(String appName) {
        TestRail.Builder testRailBulder = TestRail.builder(HOST, USER_NAME, PASSWORD);
        testRail = testRailBulder.applicationName(appName).build();
        customCaseFields = testRail.caseFields().list().execute();
        customResultFields = testRail.resultFields().list().execute();
    }

    private void setAllTestMethods(ISuite suite) {
        suite.getAllMethods().forEach(iTestNGMethod -> {
            String pack = iTestNGMethod.getRealClass().getCanonicalName().substring(iTestNGMethod.getRealClass().getCanonicalName().lastIndexOf(".") + 1);
            testMethods.add(pack + "." + iTestNGMethod.getMethodName());
        });
    }


    private List<String> getCaseList() {
        List<String> list = new ArrayList<>();
        parseCases(projectId, suiteId).forEach(item -> {
            list.add(item.getTitle());
        });
        return list;
    }

    private List<String> getDifferentCases() {
        List<String> result = new ArrayList<>();
        List<String> trCases = getCaseList();
        testMethods.forEach(localCases -> {
            if (!trCases.contains(localCases)) {
                result.add(localCases);
            }
        });

        return result;
    }


    private static List<Case> parseCases(Integer pId, Integer sId) {
        Set<Case> cases = new HashSet<>();
        int step = 0;
        int current = 0;

        APIClient client = new APIClient(HOST);
        client.setUser(USER_NAME);
        client.setPassword(PASSWORD);

        List<Case> c = null;
        do {
            ObjectMapper mapper = new ObjectMapper();
            try {
                c = mapper.readValue(client.sendGet("get_cases/" + pId + "&suite_id=" + sId + "&offset=" + step).toString(), new TypeReference<List<Case>>() {
                });
                cases.addAll(c);
                current = c.size();
                step += 250;
            } catch (IOException | APIException e) {
                e.printStackTrace();
            }

        } while (250 == current);

        return new ArrayList<>(cases);
    }

    public synchronized void addNewTestCases(ISuite suite) {
        createSection(suite);
        setAllTestMethods(suite);
        updateNameCases();
        List<Section> listAlreadySections = testRail.sections().list(projectId, suiteId).execute();

        ConsoleLogger.log.info("Add new test cases to sections:");
        List<String> diffCases = getDifferentCases();
        try (ProgressBar progressBar = new ProgressBar(" \r ",
                listAlreadySections.size())) {
            for (Section section : listAlreadySections) {
                progressBar.step();
                if (diffCases.size() > 0) {
                    for (String item : diffCases) {
                        String patternStr = "\\.";
                        Matcher matcher = Pattern.compile(patternStr).matcher(item);
                        if (matcher.find()) {
                            if (item.substring(0, matcher.start()).equals(section.getName())) {
                                testRail.cases().add(section.getId(),
                                        new Case().setSuiteId(suiteId).setTitle(item), customCaseFields).execute();
                            }
                        }
                    }
                }
            }
        }
        caseList = testRail.cases().list(projectId, suiteId, customCaseFields).execute();
    }

    public void createSection(ISuite suite) {
        List<Section> listSection = testRail.sections().list(projectId, suiteId).execute();

        for (String className : getClassesName(suite.getAllMethods())) {
            if (!listSection.toString().contains(className)) {
                testRail.sections().add(projectId, new Section().setSuiteId(suiteId)
                        .setName(className)).execute();
            }
        }
    }

    public Set<String> getClassesName(List<ITestNGMethod> methods) {
        Set<String> setData = new HashSet<>();
        for (ITestNGMethod o : methods) {
            setData.add(o.getRealClass().getSimpleName());
        }
        return setData;
    }


    public void createRun(String name, String packageId, Boolean multipleRunners) {
        pId = packageId;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat formForFilter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        if (!multipleRunners) {
            List<Run> runFiltered = testRail.runs().
                    list(projectId).execute().
                    stream().filter(
                            r -> r.getName().contains(packageId) &&
                                    r.getName().contains(formForFilter.format(date)))
                    .collect(Collectors.toList());
            if (runFiltered.size() > 0) {
                testRail.runs().delete(runFiltered.get(0).getId()).execute();
            }
        }

        run = testRail.runs().add(projectId, new Run().setSuiteId(suiteId)
                        .setDescription("Test app: ".concat(packageId))
                        .setName(name.concat(formatter.format(date))))
                .execute();
        dataRun = run;
    }

    public void updateRunDescription(String newData) {
        ConsoleLogger.log.info("Updating current run");
        SimpleDateFormat formForFilter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        Run run = testRail.runs().
                list(projectId).execute().
                stream().filter(
                        r -> r.getName().contains(pId) &&
                                r.getName().contains(formForFilter.format(date)))
                .findFirst().get();

        String current = run.getDescription();
        run.setDescription(current + "\n" + newData);
        testRail.runs().update(run).execute();
        ConsoleLogger.log.info("Run " + run.getId() + " has been updated.");
    }

    public void runCases(List<ITestResult> result, Integer status, String statusLine, Map<String, Map<String, StringWriter>> dataDebag) {
        ArrayList<Result> results = new ArrayList<>();
        ConsoleLogger.log.info("Send to TestRail[" + statusLine + "]:");
        try (ProgressBar pb = new ProgressBar("\r", result.size())) {
            for (Case caseT : caseList) {
                for (ITestResult item : result) {
                    String pack = item.getTestClass()
                            .getRealClass().getCanonicalName()
                            .substring(item.getTestClass().getRealClass().getCanonicalName().lastIndexOf(".") + 1);

                    String testName = pack + "." + item.getName();
                    if (testName.equals(caseT.getTitle())) {
                        Throwable exception = item.getThrowable();
                        if (exception != null) {
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            exception.printStackTrace(pw);
                            String exceptionMessage = sw.toString();

                            String commentLine;
                            try {
                                commentLine = "requests:\n".toUpperCase() +
                                        dataDebag.get(item.getName())
                                                .get("requests") +
                                        "\n\nresponses\n".toUpperCase() +
                                        dataDebag.get(item.getName()).get("responses") +
                                        "\n\nexception Message\n" +
                                        exceptionMessage;
                            } catch (NullPointerException e) {
                                commentLine = "\n\nexception Message\n" + exceptionMessage;
                            }

                            Result resul = new Result();
                            resul.setCaseId(caseT.getId());
                            resul.setStatusId(status);
                            resul.setComment(commentLine);
                            results.add(resul);
                            pb.step();

                        } else {
                            String commentLine;
                            try {
                                commentLine = "requests:\n".toUpperCase() +
                                        dataDebag.get(item.getName())
                                                .get("requests") +
                                        "\n\nresponses\n".toUpperCase() +
                                        dataDebag.get(item.getName()).get("responses");
                            } catch (NullPointerException e) {
                                commentLine = "Data not found!";
                            }

                            Result resul = new Result();
                            resul.setCaseId(caseT.getId());
                            resul.setStatusId(status);
                            resul.setComment(commentLine);
                            results.add(resul);
                            pb.step();
                        }
                    }

                }
            }
        }
        if (!result.isEmpty()) {
            testRail.results().addForCases(run.getId(), results, customResultFields).execute();
        }
    }

    public void runCases(List<ITestResult> result, Integer status, String statusLine) {
        ArrayList<Result> results = new ArrayList<>();
        System.out.println("\nSend to TestRail[" + statusLine + "]:");
        try (ProgressBar pb = new ProgressBar("\r", result.size())) {
            for (Case caseT : caseList) {
                for (ITestResult item : result) {
                    String testName = item.getTestClass().getRealClass().getCanonicalName() + "." + item.getName();
                    if (testName.equals(caseT.getTitle())) {

                        Throwable exception = item.getThrowable();
                        if (exception != null) {
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            exception.printStackTrace(pw);

                            Result resul = new Result();
                            resul.setCaseId(caseT.getId());
                            resul.setStatusId(status);
                            resul.setComment(sw.toString());
                            results.add(resul);
                            pb.step();

                        } else {

                            Result resul = new Result();
                            resul.setCaseId(caseT.getId());
                            resul.setStatusId(status);
                            resul.setComment("");
                            results.add(resul);
                            pb.step();
                        }
                    }

                }
            }
        }
        if (!result.isEmpty()) {
            testRail.results().addForCases(run.getId(), results, customResultFields).execute();
        }
    }

    public void runCases(List<ITestResult> result) {
        ArrayList<Result> results = new ArrayList<>();
        ConsoleLogger.log.info("Send data to TestRail:");
        try (ProgressBar pb = new ProgressBar("\r", result.size())) {
            for (Case caseT : caseList) {
                for (ITestResult item : result) {
                    String pack = item.getTestClass()
                            .getRealClass().getCanonicalName()
                            .substring(item.getTestClass().getRealClass()
                                    .getCanonicalName().lastIndexOf(".") + 1);

                    String testName = pack + "." + item.getName();
                    if (testName.equals(caseT.getTitle())) {
                        Throwable exception = item.getThrowable();
                        Result resul = new Result();
                        resul.setCaseId(caseT.getId());

                        if (item.getStatus() == 1) {
                            resul.setStatusId(CaseStatus.PASSED);
                        } else if (item.getStatus() == 2) {
                            resul.setStatusId(CaseStatus.FAILED);
                        } else if (item.getStatus() == 3) {
                            resul.setStatusId(CaseStatus.BLOCKED);
                        }

                        if (exception != null) {
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            exception.printStackTrace(pw);
                            String exceptionMessage = sw.toString();

                            String commentLine = "";

                            final String[] messages = {""};
                            messages[0] += "\n__Exception Message__\n" +
                                    exceptionMessage;

                            commentLine = messages[0];

                            resul.setComment(commentLine);
                        } else {
                            String commentLine = "";
                            final String[] messages = {""};
                            commentLine = messages[0];
                            resul.setComment(commentLine);
                        }
                        results.add(resul);
                        pb.step();
                    }
                }
            }
        }
        if (!result.isEmpty()) {
            testRail.results().addForCases(run.getId(), results, customResultFields).execute();
        }

    }

    private Map<String, String> deviceInfo() {
        String threadName = Thread.currentThread().getName();
        Map<String, String> device = new HashMap<>();
        device.put("device", threadName.substring(0, threadName.lastIndexOf("_V:")));
        device.put("os_version", threadName.substring(threadName.lastIndexOf("_V:")).replace("_V:", ""));
        return device;
    }

    public synchronized void updateResults(List<ITestResult> result, String runName) {
        List<Case> cases = parseCases(projectId, suiteId);
        if (Boolean.parseBoolean(Prop.getField("bs.available"))) {
            SimpleDateFormat formForFilter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            Run run = testRail.runs().list(projectId).execute().
                    stream().filter(
                            r -> r.getName().contains(runName) &&
                                    r.getName().contains(formForFilter.format(date)))
                    .findFirst().get();

            ConsoleLogger.log.info("Attach BS session links to the failed tests.");
            BSApi bsApi = new BSApi();
            Long bSprojectId = bsApi.getProject(DevicesBS.getAppName().concat(deviceInfo().get("device"))).getId();
            ProjectDetail bSprojectDetail = bsApi.getProjectDetail(bSprojectId);

            List<Result> results = new ArrayList<>();
            result.forEach(localResult -> {
                String pack = localResult.getTestClass()
                        .getRealClass().getCanonicalName()
                        .substring(localResult.getTestClass().getRealClass()
                                .getCanonicalName().lastIndexOf(".") + 1);

                String testName = pack + "." + localResult.getName();
                BuildDetail buildDetail = bsApi.searchSession(bSprojectDetail, pack, deviceInfo().get("device"));
                Integer caseId = cases.stream().filter(c -> c.getTitle().equals(testName)).findFirst().get().getId();
                Result caseResult = getRunResultsForCase(run.getId(), caseId, 5).stream().findFirst().get();
                caseResult.setCaseId(caseId);
                String sessionLink = "[Public link to session in Browserstack](" + buildDetail.getAutomationSession().getPublicUrl() + ")\n";
                String appiumLog = "[Appium log from Browserstack](" + buildDetail.getAutomationSession().getAppiumLogsUrl() + ")\n";
                String deviceLogs = "[Device logs from Browserstack](" + buildDetail.getAutomationSession().getDeviceLogsUrl() + ")\n";
                String videoUrl = "[Video from Browserstack](" + buildDetail.getAutomationSession().getVideoUrl() + ")\n";

                caseResult.setComment(sessionLink.concat(appiumLog).concat(deviceLogs).concat(videoUrl));
                results.add(caseResult);
            });

            if (!results.isEmpty()) {
                testRail.results().addForCases(run.getId(), results, customResultFields).execute();
            }
        }
    }


    private static List<Result> getRunResultsForRun(Integer rId, Integer status) {
        APIClient client = new APIClient(HOST);
        client.setUser(USER_NAME);
        client.setPassword(PASSWORD);

        List<Result> r = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            r = mapper.readValue(client
                            .sendGet("get_results_for_run/" + rId + "&status_id=" + status).toString(),
                    new TypeReference<List<Result>>() {
                    });
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
        return r;
    }

    private static void attachScreenShotToResult(Integer resultId, String path) {
        APIClient client = new APIClient(HOST);
        client.setUser(USER_NAME);
        client.setPassword(PASSWORD);
        try {
            client.sendPost("add_attachment_to_result/" + resultId, path);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
    }

    private static List<Result> getRunResultsForCase(Integer rId, Integer caseId, Integer status) {
        APIClient client = new APIClient(HOST);
        client.setUser(USER_NAME);
        client.setPassword(PASSWORD);

        List<Result> r = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            r = mapper.readValue(client
                            .sendGet("get_results_for_case/" + rId + "/" + caseId + "&status_id=" + status).toString(),
                    new TypeReference<List<Result>>() {
                    });
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
        return r;
    }

    public void closeRun() {
        testRail.runs().close(run.getId()).execute();
    }

    public void updateNameCases() {
        ConsoleLogger.log.info("Updating cases");
        List<Case> trCases = testRail.cases().list(projectId, suiteId, customCaseFields).execute();
        List<Section> trSections = testRail.sections().list(projectId, suiteId).execute();
        try (ProgressBar progressBar = new ProgressBar(" \r ", trSections.size())) {
            trSections.forEach(section -> {
                progressBar.step();
                trCases.forEach(case_item -> {
                    String nameOfCase = case_item.getTitle().substring(case_item.getTitle().lastIndexOf(".") + 1);
                    if (!case_item.getTitle().equals(section.getName() + "." + nameOfCase)) {
                        if (case_item.getSectionId() == section.getId()) {
                            case_item.setTitle(section.getName() + "." + nameOfCase);
                            testRail.cases().update(case_item, customCaseFields).execute();
                        }
                    }
                });
            });
        }
    }

    public Set<String> searchDuplicatesOfCases(List<String> list) {
        return list.stream().filter(i -> Collections.frequency(list, i) > 1)
                .collect(Collectors.toSet());
    }
}

