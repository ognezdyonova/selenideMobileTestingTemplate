package core.slack;

import com.slack.api.model.Conversation;
import core.Logger;
import core.config.Prop;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SlackSender {
    private static Logger logger;
    private static SlackConnector slackApi;
    private static String chanelName;

    public SlackSender(Logger log) {
        logger = log;
        chanelName = Prop.getField("slack.channel.name");
    }


    public void send(String suiteName) throws IOException {
        connection();

        List<ITestContext> context = logger.getTestContexts();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date start = context.get(0).getStartDate();
        Date end = context.get(context.size() - 1).getEndDate();

        List<ITestNGMethod> skipped = getAllSkipTests();
        List<ITestNGMethod> passed = getAllPassTests();
        List<ITestNGMethod> failed = getAllFailTests();

        List<ITestNGMethod> cases = new ArrayList<>();
        cases.addAll(skipped);
        cases.addAll(failed);
        cases.addAll(passed);


        String firstDividingLine = "|===============================================|\n";
        String secondDividingLine = "\n |===============================================|";

        String suite = suiteName;
        String failedTestsReport = logger.newReport("Failed ", getAllFailTests());
        String skippedTestsReport = logger.newReport("Skipped ", getAllSkipTests());

        String overData = "[ _All tests:_ " + cases.size()
                + "  _Passed_ = " + passed.size()
                + "  _Failed_ = " + failed.size()
                + "  _Skipped_ = " + skipped.size() + " ] \n\n";
        String startTime = "\n*Start:* ".concat(dateFormat.format(start));
        String endTime = "\n*End:* ".concat(dateFormat.format(end));
        String timeStamp = "\n*TimeStamp:* "
                .concat(String.valueOf((float) (end.getTime() - start.getTime()) / 1000)) + "(s)\n\n";
        String urlSiute = "\n*Test case suit: *" + logger.testRailApi.dataSuit.getUrl();
        String urlReport = "\n*Detail TestRail report: *" + logger.testRailApi.dataRun.getUrl();

        slackApi.sendMessage(Prop.getField("slack.channel.name"),
                firstDividingLine +
                        suite +
                        secondDividingLine +
                        failedTestsReport +
                        skippedTestsReport +
                        startTime +
                        endTime +
                        timeStamp +
                        overData +
                        urlSiute +
                        urlReport +
                        secondDividingLine);
    }

    private List<ITestNGMethod> getAllSkipTests() {
        List<ITestNGMethod> newLis = new ArrayList<>();
        logger.getTestContexts().forEach(iTestContext -> {
            newLis.addAll(iTestContext.getSkippedTests().getAllMethods());
        });
        return newLis;
    }

    private List<ITestNGMethod> getAllPassTests() {
        List<ITestNGMethod> newLis = new ArrayList<>();
        logger.getTestContexts().forEach(iTestContext -> {
            newLis.addAll(iTestContext.getPassedTests().getAllMethods());
        });
        return newLis;
    }

    private List<ITestNGMethod> getAllFailTests() {
        List<ITestNGMethod> newLis = new ArrayList<>();
        logger.getTestContexts().forEach(iTestContext -> {
            newLis.addAll(iTestContext.getFailedTests().getAllMethods());
        });
        return newLis;
    }

    private void connection() throws IOException {
        slackApi = new SlackConnector();
    }

    private static Conversation getChannel() {
        return slackApi.fetchConversations().filterConversation(chanelName);
    }

}
