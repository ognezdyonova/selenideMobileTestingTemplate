package org.company_name.tests.waybetter.android;

import com.beust.jcommander.internal.Lists;
import core.ConsoleLogger;
import core.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.List;
import java.util.Objects;

public class WBAndroidLocalDeviceRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG(true);
        Logger logger = new Logger();
        testNG.addListener((ITestNGListener) logger);
        testNG.setDefaultTestName("WB Android tests");
        runTestsForSuite(getSuits(androidXMLPath("WB")), testNG);
    }

    public static boolean runTestsForSuite(List<String> suite, TestNG testNG) {

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

    public static List<String> getSuits(String patch) {
        List<String> suites = Lists.newArrayList();
        suites.add(patch);
        return suites;
    }

    public static String androidXMLPath(String suite) {
        ConsoleLogger.log.info("Getting test configuration for " + suite);
        String path;
        switch (suite) {
            case "WB":
                path = Objects.requireNonNull(WBAndroidLocalDeviceRunner.class.getClassLoader().getResource("conf/wb/android.xml")).getPath();
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
                path = Objects.requireNonNull(WBAndroidLocalDeviceRunner.class.getClassLoader().getResource("conf/wb/ios.xml")).getPath();
                break;
            default:
                throw new RuntimeException("The suite name " + suite + " does not match any of the available ones. Please check your connections and try again");
        }
        return path;
    }

}
