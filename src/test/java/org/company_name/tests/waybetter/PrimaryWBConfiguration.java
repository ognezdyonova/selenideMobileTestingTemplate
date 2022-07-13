package org.company_name.tests.waybetter;

import com.beust.jcommander.internal.Lists;
import core.Chart;
import core.ConsoleLogger;
import core.Logger;
import core.PropertyWorker;
import core.config.Prop;
import core.constants.DevicesBS;
import core.slack.SlackSender;
import core.testrail.TestRailApi;
import org.apache.log4j.Level;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.*;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PrimaryWBConfiguration implements Runnable {
    private final Map<String, String> deviceInfo;
    private final TestNG testNG;
    private final Logger logger;
    private final PropertyWorker propertyWorker;
    private Thread currentThread = null;
    private String typeTest;
    private String appPackageId;

    public PrimaryWBConfiguration(String deviceEnvironment, String type, String appName, String packageId) {
        propertyWorker = new PropertyWorker();
        deviceInfo = parseJsonDevices(deviceEnvironment);
        this.typeTest = type;
        this.appPackageId = packageId;

        DevicesBS.setAppName(appName);
        ConsoleLogger.log.info("Creating  thread " + deviceInfo.get("device"));

        testNG = new TestNG(true);
        logger = new Logger();
        testNG.addListener((ITestNGListener) logger);

        if (this.typeTest.contains("Android"))
            testNG.setDefaultTestName("WB Android tests");
        else
            testNG.setDefaultTestName("WB IOS tests");
    }

    @Override
    public void run() {
        ConsoleLogger.log.info("Running thread " + deviceInfo.get("device"));
        try {
            if (this.typeTest.contains("Android")) {
                runTestsForSuite(getSuits(androidXMLPath("WB")), testNG);
            } else {
                runTestsForSuite(getSuits(iosXMLPath("WB")), testNG);
            }

            String suiteName;
            if (this.typeTest.contains("Android"))
                suiteName = Prop.getField("tr.project.suite.wb.android");
            else
                suiteName = Prop.getField("tr.project.suite.wb.ios");

            synchronized (this) {
                sendDataToTR(false,
                        suiteName,
                        logger,
                        true,
                        this.typeTest + "WB report for " + deviceInfo.get("device") + " ",
                        this.appPackageId);
            }

            sendDataToSlack(false,
                    logger,
                    suiteName);

            getGenerationLineChart(false);
//            System.exit(hasFailures ? 1 : 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    public Map<String, String> parseJsonDevices(String envr) {
        JSONParser parser = new JSONParser();
        JSONObject config = null;
        try {
            config = (JSONObject) parser.parse(new FileReader(
                    Objects.requireNonNull(PrimaryWBConfiguration.class.getClassLoader().getResource(Prop.getField("bs.devices"))).getPath()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        Map<String, String> device = new HashMap<>();
        if (propertyWorker.getProperty("device_index") == null) {
            propertyWorker.setProperty("device_index", String.valueOf(0));
            device = ((ArrayList<Map<String, String>>) config.get(envr)).get(0);
        } else {
            device = ((ArrayList<Map<String, String>>) config.get(envr)).get(Integer.parseInt(propertyWorker.getProperty("device_index")));
            propertyWorker.setProperty("device_index", String.valueOf(Integer.parseInt(propertyWorker.getProperty("device_index")) + 1));
        }

        return device;
    }

    public void start() {
        if (currentThread == null) {
            String threadName = deviceInfo.get("device").concat("_V:").concat(deviceInfo.get("os_version"));
            currentThread = new Thread(this, threadName);
            currentThread.setPriority(10);
            currentThread.setContextClassLoader(PrimaryWBConfiguration.class.getClassLoader());
            currentThread.start();
            ConsoleLogger.log.setLevel(Level.DEBUG);
            if (currentThread.isAlive())
                ConsoleLogger.log.info("Thread ".concat(currentThread.getName()).concat(" has been started"));
            try {
                ConsoleLogger.log.info("Waiting for thread to complete.");
                currentThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendDataToTR(Boolean turnOn,
                             String suiteName,
                             Logger logger,
                             Boolean multipleRunners,
                             String runnerName,
                             String packageId) {
        if (turnOn) {
            logger.testRailApi = new TestRailApi(
                    Prop.getField("tr.project.name"),
                    suiteName
            );

            logger.testRailApi.addNewTestCases(logger.getTestContexts().get(0).getSuite());
            logger.testRailApi.createRun(runnerName, packageId, multipleRunners);

            List<ITestResult> cases = new ArrayList<>();
            cases.addAll(logger.getPassedTests());
            cases.addAll(logger.getFailedTests());
            cases.addAll(logger.getSkippedTests());

            logger.testRailApi.runCases(cases);
            logger.testRailApi.updateResults(logger.getFailedTests(), runnerName);
        }

    }

    public void sendDataToSlack(Boolean turnOn, Logger logger, String suiteName) throws IOException {
        if (turnOn) {
            SlackSender slackSender = new SlackSender(logger);
            slackSender.send(suiteName);
        }
    }

    public List<XmlSuite> getSuits(InputStream stream) throws IOException {
        List<XmlSuite> suiteList = (List<XmlSuite>) (new Parser(stream).parse());
        suiteList.forEach((key) -> {
            key.setVerbose(1);
//            key.setParallel(XmlSuite.ParallelMode.TESTS);
            key.setThreadCount(1);
        });
        return suiteList;
    }

    public List<String> getSuits(String patch) {
        List<String> suites = Lists.newArrayList();
        suites.add(patch);
        return suites;
    }

    public String androidXMLPath(String suite) {
        ConsoleLogger.log.info("Getting test configuration for " + suite);
        String path;
        switch (suite) {
            case "WB":
                path = Objects.requireNonNull(PrimaryWBConfiguration.class.getClassLoader().getResource("conf/android.xml")).getPath();
                break;
            default:
                throw new RuntimeException("The suite name " + suite + " does not match any of the available ones. Please check your connections and try again");
        }
        return path;
    }

    public String iosXMLPath(String suite) {
        ConsoleLogger.log.info("Getting test configuration for " + suite);
        String path;
        switch (suite) {
            case "WB":
                path = Objects.requireNonNull(PrimaryWBConfiguration.class.getClassLoader().getResource("conf/wb/ios.xml")).getPath();
                break;
            default:
                throw new RuntimeException("The suite name " + suite + " does not match any of the available ones. Please check your connections and try again");
        }
        return path;
    }

    public void getGenerationLineChart(Boolean status) {
        if (status) {
            new Chart(Logger.getTimeStamp()).lineChart();
        }
    }

    public boolean runTestsForSuite(List<String> suite, TestNG testNG) {

        ITestNGListener results = new TestListenerAdapter() {
            @Override
            public void onStart(ITestContext testContext) {
                super.onStart(testContext);
            }
        };
        testNG.addListener(results);
        boolean hasFailures;
        try {
            testNG.setTestSuites(suite);
            testNG.run();
            hasFailures = ((TestListenerAdapter)
                    results).getFailedTests().size() > 0 || ((TestListenerAdapter) results).getSkippedTests().size() > 0;
//            if (Runner.class.getProtectionDomain().getCodeSource().getLocation().getFile().endsWith(".jar")) {
//                new Configurations().removeAllProperty();
//            }
        } catch (Throwable e) {
            /* something goes wrong... */
            e.printStackTrace();
            hasFailures = true;
        }
        return hasFailures;
    }


}
