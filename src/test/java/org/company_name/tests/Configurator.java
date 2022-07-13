package org.company_name.tests;


import api.Ios.Ios;
import api.android.Android;
import com.beust.jcommander.internal.Lists;
import core.Chart;
import core.CustomListener;
import core.Logger;
import core.config.Prop;
import core.managers.DriverManager;
import core.slack.SlackSender;
import core.testrail.TestRailApi;
import org.testng.*;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Configurator {

    static void sendDataToTR(Boolean turnOn, Logger logger, String suiteName) throws IOException {
        if (turnOn) {
            logger.testRailApi = new TestRailApi(
                    Prop.getField("tr.project.name"),
                    suiteName
            );

            logger.testRailApi.addNewTestCases(logger.getTestContexts().get(0).getSuite());
            logger.testRailApi.createRun("Test WB run name", "Test package id", true);

            List<ITestResult> cases = new ArrayList<>();
            cases.addAll(logger.getPassedTests());
            cases.addAll(logger.getFailedTests());
            cases.addAll(logger.getSkippedTests());

            logger.testRailApi.runCases(cases);
            logger.testRailApi.updateResults(logger.getFailedTests(), "Test WB run name");
        }

    }

    public static void sendDataToSlack(Boolean turnOn, Logger logger, String suiteName) throws IOException {
        if (turnOn) {
            SlackSender slackSender = new SlackSender(logger);
            slackSender.send(suiteName);
        }
    }

    static List<XmlSuite> getSuits(InputStream stream) throws IOException {
        List<XmlSuite> suiteList = (List<XmlSuite>) (new Parser(stream).parse());
        suiteList.forEach((key) -> {
            key.setVerbose(1);
//            key.setParallel(XmlSuite.ParallelMode.TESTS);
            key.setThreadCount(1);
        });
        return suiteList;
    }

    static List<String> getSuits(String patch) {
        List<String> suites = Lists.newArrayList();
        suites.add(patch);
        return suites;
    }

    public static InputStream androidXML() {
        return Configurator.class.getClassLoader().getResourceAsStream("conf/wb/android.xml");
    }

    public static InputStream iosXML() {
        return Configurator.class.getClassLoader().getResourceAsStream("conf/wb/ios.xml");
    }

    public static String androidXMLPath() {
        return Objects.requireNonNull(Runner.class.getClassLoader().getResource("conf/wb/android.xml")).getPath();
    }

    public static String iosXMLPath() {
        return Objects.requireNonNull(Runner.class.getClassLoader().getResource("conf/wb/ios.xml")).getPath();
    }

    static void getGenerationLineChart(Boolean status) {
        if (status) {
            new Chart(CustomListener.getTimeStamp()).lineChart();
        }
    }

    static boolean runTestsForSuite(List<String> suite, TestNG testNG) {
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

            stopDrivers();
        } catch (Throwable e) {
            /* something goes wrong... */
            e.printStackTrace();
            hasFailures = true;
        }
        return hasFailures;
    }

    static void stopDrivers() {
        if (Android.driver() != null)
            DriverManager.killAndroidDriver();
        else if (Ios.driver() != null)
            DriverManager.killIosDriver();
    }

}
