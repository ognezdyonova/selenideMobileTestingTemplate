package org.company_name.tests;

import core.Logger;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.io.IOException;

public class RunnerIos {
    public static void main(String[] args) throws IOException {
        TestNG testNG = new TestNG(true);
        Logger logger = new Logger();
        testNG.addListener((ITestNGListener) logger);
        testNG.setDefaultTestName("WB iOS mobile tests");

        boolean hasFailures = Configurator.runTestsForSuite(Configurator.getSuits(Configurator.iosXMLPath()), testNG);
        Configurator.sendDataToTR(false, logger,"test WB suite");
        Configurator.sendDataToSlack(false, logger, "Android WayBetter");
        Configurator.getGenerationLineChart(true);
        System.exit(hasFailures ? 1 : 0);
    }
}
