package core;

import core.config.Prop;
import core.testrail.TestRailApi;
import org.testng.*;

import java.io.PrintStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomListener extends TestListenerAdapter {
    private static Date date = new Date();
    public TestRailApi testRailApi;
    private ITestNGMethod[] allMethods;
    public IResultMap skipTests;
    public static StringWriter requestWriter;
    public static PrintStream requestCupture;
    public static StringWriter responseWriter;
    public static PrintStream responseCupture;
    private static Map<String, Map<String, StringWriter>> recSpecMap = new HashMap<String, Map<String, StringWriter>>();
    private static Map<String, Long> timeStamp = new HashMap<String, Long>();
    private String name;
    private static boolean debug = false;
    private long startTime;
    private long endTime;
    public static String testClassName = null;

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        testClassName = null;
        CustomLogger.log.info("[" + getCurrentTime() + "] " + "Test " + result.getName() + " is start");
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        allMethods = testContext.getAllTestMethods();
        skipTests = testContext.getSkippedTests();
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        CustomLogger.log.info("[+] " + tr.getName() + ": is success" + " [" + getCurrentTime() + "]");
        endTime = System.currentTimeMillis();
        setTimeStamp(tr.getTestClass().getName() + "." + tr.getName(), endTime - startTime);
        startTime = 0;
        endTime = 0;
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        CustomLogger.log.info("[-] " + tr.getName() + ": is fail" + " [" + getCurrentTime() + "]");
        endTime = System.currentTimeMillis();
        setTimeStamp(tr.getName(), endTime - startTime);
        startTime = 0;
        endTime = 0;
        if (!Boolean.parseBoolean(Prop.getField("bs.available")))
            new ScreenshotCaptureMobile().drawDots(tr.getName());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        CustomLogger.log.info("Test '" + tr.getName() + "' SKIPPED");
        endTime = System.currentTimeMillis();
        setTimeStamp(tr.getName(), endTime - startTime);
        startTime = 0;
        endTime = 0;
    }

    @Override
    public List<ITestContext> getTestContexts() {
        return super.getTestContexts();
    }

    @Override
    public List<ITestResult> getFailedTests() {
        return super.getFailedTests();
    }

    @Override
    public List<ITestResult> getPassedTests() {
        return super.getPassedTests();
    }

    @Override
    public List<ITestResult> getSkippedTests() {
        return new ArrayList<>(skipTests.getAllResults());
    }


    @Override
    public ITestNGMethod[] getAllTestMethods() {
        return allMethods;
    }

    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    @Deprecated
    public String getSimpleReports(String blockName, List<ITestResult> list) {
        List<String> newString = new ArrayList<>();
        newString.add("*" + blockName + " tests:*\n");
        if (!list.isEmpty()) {
            list.forEach((test) -> {

                String testName = test.getMethod().getMethodName();
                Float timeStamp = (float) (test.getEndMillis() - test.getStartMillis()) / 1000;
                String line = testName + " *time:* " + timeStamp + "\n";
                newString.add(line);
            });
            return String.join(" ", newString);
        } else {
            newString.add("_Block is empty_");
            return String.join(" ", newString);
        }

    }

    public String newReport(String blockName, IResultMap list) {
        List<String> newString = new ArrayList<>();
        newString.add("*" + blockName + " tests:*\n");

        if (!list.getAllMethods().isEmpty()) {
            list.getAllMethods().forEach(iTestNGMethod -> {
                String testName = iTestNGMethod.getMethodName();
//                String instants = iTestNGMethod.getInstance().toString();
                String line = testName + "\n";
                newString.add(line);
            });
            return String.join(" ", newString);
        } else {
            newString.add("_Block is empty_");
            return String.join(" ", newString);
        }
    }

    public String newReport(String blockName, List<ITestNGMethod> list) {
        List<String> newString = new ArrayList<>();
        newString.add("\n*" + blockName + " tests:*\n");

        if (!list.isEmpty()) {
            list.forEach(iTestNGMethod -> {

                String testName = iTestNGMethod.getMethodName();
//                String instants = iTestNGMethod.getInstance().toString();
                String line = iTestNGMethod.getTestClass().getName() + "-->" + testName + "\n";
                newString.add(line);
            });
            return String.join(" ", newString);
        } else {
            newString.add("_Block is empty_");
            return String.join(" ", newString);
        }
    }

    public static Map<String, Map<String, StringWriter>> getRecSpecMap() {
        return recSpecMap;
    }

    public static void setRecSpecMap(String name, Map<String, StringWriter> data) {
        getRecSpecMap().put(name, data);
    }

    public static Map<String, Long> getTimeStamp() {
        return timeStamp;
    }

    public static void setTimeStamp(String name, Long time) {
        getTimeStamp().put(name, time);
    }

    public static void debugging(boolean debugStatus) {
        debug = debugStatus;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setCurrentClass(String simpleName) {
        testClassName = simpleName;
    }
}
